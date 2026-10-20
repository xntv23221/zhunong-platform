package com.wxpay;

import com.annotation.IgnoreAuth;
import com.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 模拟微信支付（Native 扫码支付流程仿真，不产生真实扣款）
 * 下单 -> 生成二维码 -> 手机端扫码确认(输入支付密码) -> 模拟网关签名回调 notify -> 更新业务单状态 -> 收银台轮询 -> 退款
 * 业务类型：ORDER（关联现有 orders 表，支付后状态改为“已支付”） / RECHARGE（充值，成功后加余额并写 chargerecord）
 */
@RestController
@RequestMapping("/wxpay")
public class MockWxPayController {

    @Autowired private JdbcTemplate jdbc;
    @Value("${wxpay.mock.secret:MOCK_WXPAY_SECRET_FOR_DEMO_ONLY_32B}") private String secret;
    @Value("${server.port:8014}") private int port;
    @Value("${server.servlet.context-path:}") private String ctx;
    @Value("${wxpay.mock.expire-minutes:15}") private int expireMinutes;

    private static final SecureRandom RND = new SecureRandom();

    @PostConstruct
    public void initTables() {
        jdbc.execute("CREATE TABLE IF NOT EXISTS wxpay_transaction (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "out_trade_no VARCHAR(64) NOT NULL UNIQUE COMMENT '商户订单号'," +
                "transaction_id VARCHAR(64) NULL COMMENT '模拟微信支付单号'," +
                "biz_type VARCHAR(20) NOT NULL COMMENT 'ORDER/RECHARGE'," +
                "biz_ref VARCHAR(1000) NULL COMMENT '关联orders.orderid，逗号分隔'," +
                "userid BIGINT NOT NULL, tablename VARCHAR(50) NOT NULL, username VARCHAR(100) NULL, role VARCHAR(50) NULL," +
                "description VARCHAR(255) NULL," +
                "amount DECIMAL(12,2) NOT NULL, refunded_amount DECIMAL(12,2) NOT NULL DEFAULT 0," +
                "status VARCHAR(20) NOT NULL COMMENT 'NOTPAY/SUCCESS/CLOSED/REFUND/PARTIAL_REFUND'," +
                "qr_token VARCHAR(64) NOT NULL," +
                "notify_count INT NOT NULL DEFAULT 0," +
                "create_time DATETIME NOT NULL, expire_time DATETIME NOT NULL, pay_time DATETIME NULL" +
                ") DEFAULT CHARSET=utf8mb4 COMMENT='模拟微信支付交易流水'");
        jdbc.execute("CREATE TABLE IF NOT EXISTS wxpay_refund (" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "out_refund_no VARCHAR(64) NOT NULL UNIQUE, refund_id VARCHAR(64) NOT NULL," +
                "out_trade_no VARCHAR(64) NOT NULL, orderid VARCHAR(200) NULL," +
                "userid BIGINT NOT NULL, amount DECIMAL(12,2) NOT NULL, reason VARCHAR(255) NULL," +
                "status VARCHAR(20) NOT NULL, create_time DATETIME NOT NULL," +
                "UNIQUE KEY uk_trade_order (out_trade_no, orderid)" +
                ") DEFAULT CHARSET=utf8mb4 COMMENT='模拟微信支付退款记录'");
    }

    // ---------- 工具 ----------
    private static String rand(int n) {
        String s = "abcdefghijklmnopqrstuvwxyz0123456789"; StringBuilder b = new StringBuilder();
        for (int i = 0; i < n; i++) b.append(s.charAt(RND.nextInt(s.length())));
        return b.toString();
    }
    private static Object fmt(Object o) { return o instanceof Date ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) o) : o; }
    private static List<Map<String, Object>> fmtList(List<Map<String, Object>> l) { for (Map<String, Object> m : l) for (Map.Entry<String, Object> e : m.entrySet()) e.setValue(fmt(e.getValue())); return l; }
    private static String now() { return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()); }
    private static String ts() { return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); }

    String sign(Map<String, String> p) {
        TreeMap<String, String> t = new TreeMap<>(p); t.remove("sign");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> e : t.entrySet()) if (e.getValue() != null && !e.getValue().isEmpty()) sb.append(e.getKey()).append('=').append(e.getValue()).append('&');
        sb.append("key=").append(secret);
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] h = mac.doFinal(sb.toString().getBytes(StandardCharsets.UTF_8));
            StringBuilder hex = new StringBuilder(); for (byte x : h) hex.append(String.format("%02X", x));
            return hex.toString();
        } catch (Exception e) { throw new RuntimeException(e); }
    }

    private Long uid(HttpServletRequest r) { Object o = r.getSession().getAttribute("userId"); return o == null ? null : Long.valueOf(o.toString()); }
    private String table(HttpServletRequest r) { Object o = r.getSession().getAttribute("tableName"); return o == null ? null : o.toString(); }
    private boolean isAdmin(HttpServletRequest r) { Object o = r.getSession().getAttribute("role"); return o != null && "管理员".equals(o.toString()); }
    private static final Set<String> USER_TABLES = new HashSet<>(Arrays.asList("yonghu", "nongmin", "gaoxiao"));

    private Map<String, Object> tx(String no) {
        List<Map<String, Object>> l = jdbc.queryForList("SELECT * FROM wxpay_transaction WHERE out_trade_no=?", no);
        return l.isEmpty() ? null : l.get(0);
    }
    /** 过期未支付自动关单 */
    private void closeIfExpired(Map<String, Object> t) {
        if (t != null && "NOTPAY".equals(t.get("status")) && ((Date) t.get("expire_time")).before(new Date())) {
            jdbc.update("UPDATE wxpay_transaction SET status='CLOSED' WHERE out_trade_no=? AND status='NOTPAY'", t.get("out_trade_no"));
            t.put("status", "CLOSED");
        }
    }

    // ---------- 1. 统一下单 ----------
    @PostMapping("/create")
    @Transactional
    public R create(@RequestBody Map<String, Object> body, HttpServletRequest req) {
        Long userid = uid(req); String tbl = table(req);
        if (userid == null || !USER_TABLES.contains(tbl)) return R.error("请先登录用户账号");
        String bizType = String.valueOf(body.getOrDefault("bizType", "ORDER"));
        BigDecimal amount; String ref = null; String desc;
        if ("ORDER".equals(bizType)) {
            Object ids = body.get("orderids");
            List<String> orderids = new ArrayList<>();
            if (ids instanceof List) for (Object o : (List<?>) ids) orderids.add(String.valueOf(o));
            else if (ids != null) orderids.addAll(Arrays.asList(String.valueOf(ids).split(",")));
            if (orderids.isEmpty()) return R.error("缺少订单号");
            amount = BigDecimal.ZERO; List<String> names = new ArrayList<>();
            for (String oid : orderids) {
                List<Map<String, Object>> l = jdbc.queryForList("SELECT * FROM orders WHERE orderid=?", oid);
                if (l.isEmpty()) return R.error("订单不存在：" + oid);
                Map<String, Object> o = l.get(0);
                if (!userid.equals(((Number) o.get("userid")).longValue())) return R.error("不能支付他人订单");
                if (!"未支付".equals(o.get("status"))) return R.error("订单 " + oid + " 当前状态为“" + o.get("status") + "”，无法支付");
                Object type = o.get("type");
                if (type != null && ((Number) type).intValue() == 2) return R.error("积分订单不支持微信支付");
                amount = amount.add(new BigDecimal(String.valueOf(o.get("total"))));
                names.add(String.valueOf(o.get("goodname")));
            }
            // 同一批订单若已有未过期的待支付单，直接复用（防重复下单）
            ref = String.join(",", orderids);
            List<Map<String, Object>> exist = jdbc.queryForList("SELECT * FROM wxpay_transaction WHERE biz_ref=? AND userid=? AND status='NOTPAY' AND expire_time>NOW()", ref, userid);
            if (!exist.isEmpty()) return R.ok().put("data", view(exist.get(0)));
            desc = "农产品订单：" + String.join("、", names);
            if (desc.length() > 200) desc = desc.substring(0, 200);
        } else if ("RECHARGE".equals(bizType)) {
            try { amount = new BigDecimal(String.valueOf(body.get("amount"))); } catch (Exception e) { return R.error("充值金额不正确"); }
            if (amount.compareTo(BigDecimal.ZERO) <= 0 || amount.compareTo(new BigDecimal("50000")) > 0) return R.error("充值金额需在 0~50000 元之间");
            desc = "账户余额充值";
        } else return R.error("不支持的业务类型");
        amount = amount.setScale(2, RoundingMode.HALF_UP);
        String no = "WX" + ts() + rand(6).toUpperCase();
        Calendar c = Calendar.getInstance(); c.add(Calendar.MINUTE, expireMinutes);
        jdbc.update("INSERT INTO wxpay_transaction(out_trade_no,biz_type,biz_ref,userid,tablename,username,role,description,amount,status,qr_token,create_time,expire_time) VALUES(?,?,?,?,?,?,?,?,?, 'NOTPAY',?,NOW(),?)",
                no, bizType, ref, userid, tbl, String.valueOf(req.getSession().getAttribute("username")), String.valueOf(req.getSession().getAttribute("role")), desc, amount, rand(24),
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime()));
        return R.ok().put("data", view(tx(no)));
    }

    private Map<String, Object> view(Map<String, Object> t) {
        Map<String, Object> m = new LinkedHashMap<>();
        for (String k : new String[]{"out_trade_no", "transaction_id", "biz_type", "biz_ref", "description", "amount", "refunded_amount", "status", "create_time", "expire_time", "pay_time", "username"}) m.put(k, fmt(t.get(k)));
        // code_url：真实微信为 weixin://wxpay/bizpayurl?pr=xxx，这里指向本系统的模拟“手机扫码确认页”
        m.put("code_url", "#/wxpay/scan?no=" + t.get("out_trade_no") + "&t=" + t.get("qr_token"));
        return m;
    }

    // ---------- 2. 查询（收银台轮询） ----------
    @GetMapping("/query/{no}")
    public R query(@PathVariable String no, HttpServletRequest req) {
        Map<String, Object> t = tx(no);
        if (t == null) return R.error("交易不存在");
        if (!isAdmin(req) && !Objects.equals(uid(req), ((Number) t.get("userid")).longValue())) return R.error("无权查看");
        closeIfExpired(t);
        return R.ok().put("data", view(t));
    }

    // ---------- 3. 手机扫码页：凭二维码 token 取单 ----------
    @IgnoreAuth
    @GetMapping("/scan/{no}")
    public R scan(@PathVariable String no, @RequestParam("t") String token) {
        Map<String, Object> t = tx(no);
        if (t == null || !token.equals(t.get("qr_token"))) return R.error("二维码无效");
        closeIfExpired(t);
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("out_trade_no", no); m.put("amount", t.get("amount")); m.put("description", t.get("description"));
        m.put("status", t.get("status")); m.put("merchant", "助农服务平台（模拟商户）");
        return R.ok().put("data", m);
    }

    // ---------- 4. 手机端确认支付（模拟微信客户端 + 微信支付网关） ----------
    @IgnoreAuth
    @PostMapping("/confirm")
    public R confirm(@RequestBody Map<String, String> body) {
        String no = body.get("no"), token = body.get("t"), pwd = body.get("password");
        Map<String, Object> t = tx(no);
        if (t == null || token == null || !token.equals(t.get("qr_token"))) return R.error("二维码无效");
        closeIfExpired(t);
        if ("SUCCESS".equals(t.get("status"))) return R.ok("该订单已支付");
        if (!"NOTPAY".equals(t.get("status"))) return R.error("交易已关闭，请重新下单");
        // 支付密码：模拟环境下使用该用户的登录密码
        String tbl = String.valueOf(t.get("tablename"));
        if (!USER_TABLES.contains(tbl)) return R.error("账户类型错误");
        String col = "mima";
        List<Map<String, Object>> u;
        try { u = jdbc.queryForList("SELECT " + col + " AS p FROM " + tbl + " WHERE id=?", t.get("userid")); }
        catch (Exception e) { u = jdbc.queryForList("SELECT password AS p FROM " + tbl + " WHERE id=?", t.get("userid")); }
        if (u.isEmpty() || pwd == null || !pwd.equals(String.valueOf(u.get(0).get("p")))) return R.error("支付密码错误");
        // 模拟网关：生成支付单号并对回调报文签名，异步通知商户 notify 地址
        Map<String, String> n = new TreeMap<>();
        n.put("out_trade_no", no);
        n.put("transaction_id", "4200" + ts() + rand(10).replaceAll("[a-z]", "0"));
        n.put("trade_state", "SUCCESS");
        n.put("total_fee", t.get("amount").toString());
        n.put("success_time", now());
        n.put("nonce_str", rand(16));
        n.put("sign", sign(n));
        String url = "http://127.0.0.1:" + port + ctx + "/wxpay/notify";
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> resp = new RestTemplate().postForObject(url, n, Map.class);
            if (resp == null || !"SUCCESS".equals(resp.get("code"))) return R.error("商户回调处理失败：" + (resp == null ? "" : resp.get("message")));
        } catch (Exception e) { return R.error("回调商户失败：" + e.getMessage()); }
        return R.ok("支付成功");
    }

    // ---------- 5. 支付结果回调（验签 + 幂等） ----------
    @IgnoreAuth
    @PostMapping("/notify")
    @Transactional
    public Map<String, Object> notifyPay(@RequestBody Map<String, String> n) {
        Map<String, Object> resp = new HashMap<>();
        if (n.get("sign") == null || !n.get("sign").equals(sign(n))) { resp.put("code", "FAIL"); resp.put("message", "签名错误"); return resp; }
        String no = n.get("out_trade_no");
        List<Map<String, Object>> l = jdbc.queryForList("SELECT * FROM wxpay_transaction WHERE out_trade_no=? FOR UPDATE", no);
        if (l.isEmpty()) { resp.put("code", "FAIL"); resp.put("message", "交易不存在"); return resp; }
        Map<String, Object> t = l.get(0);
        jdbc.update("UPDATE wxpay_transaction SET notify_count=notify_count+1 WHERE out_trade_no=?", no);
        if (new BigDecimal(n.get("total_fee")).compareTo((BigDecimal) t.get("amount")) != 0) { resp.put("code", "FAIL"); resp.put("message", "金额不一致"); return resp; }
        if (!"NOTPAY".equals(t.get("status"))) { // 幂等：重复通知直接返回成功
            resp.put("code", "SUCCESS"); resp.put("message", "已处理"); return resp;
        }
        int c = jdbc.update("UPDATE wxpay_transaction SET status='SUCCESS', transaction_id=?, pay_time=NOW() WHERE out_trade_no=? AND status='NOTPAY'", n.get("transaction_id"), no);
        if (c == 1) {
            if ("ORDER".equals(t.get("biz_type"))) {
                for (String oid : String.valueOf(t.get("biz_ref")).split(","))
                    jdbc.update("UPDATE orders SET status='已支付', remark=CONCAT(IFNULL(remark,''), IF(IFNULL(remark,'')='', '', ' '), '[微信支付(模拟)]') WHERE orderid=? AND status='未支付'", oid);
            } else if ("RECHARGE".equals(t.get("biz_type"))) {
                String tbl = String.valueOf(t.get("tablename"));
                jdbc.update("UPDATE " + tbl + " SET money=IFNULL(money,0)+? WHERE id=?", t.get("amount"), t.get("userid"));
                jdbc.update("INSERT INTO chargerecord(addtime,userid,username,role,amount) VALUES(NOW(),?,?,?,?)",
                        t.get("userid"), t.get("username"), t.get("role") == null ? tbl : t.get("role"), t.get("amount"));
            }
        }
        resp.put("code", "SUCCESS"); resp.put("message", "成功");
        return resp;
    }

    // ---------- 6. 退款（原路退回，按订单） ----------
    @GetMapping("/byOrder/{orderid}")
    public R byOrder(@PathVariable String orderid, HttpServletRequest req) {
        List<Map<String, Object>> l = jdbc.queryForList("SELECT * FROM wxpay_transaction WHERE biz_type='ORDER' AND status IN('SUCCESS','PARTIAL_REFUND','REFUND') AND FIND_IN_SET(?, biz_ref) ORDER BY id DESC LIMIT 1", orderid);
        if (l.isEmpty()) return R.ok().put("data", null);
        if (!isAdmin(req) && !Objects.equals(uid(req), ((Number) l.get(0).get("userid")).longValue())) return R.error("无权查看");
        return R.ok().put("data", view(l.get(0)));
    }

    @PostMapping("/refund")
    @Transactional
    public R refund(@RequestBody Map<String, String> body, HttpServletRequest req) {
        String orderid = body.get("orderid"); String reason = body.getOrDefault("reason", "用户申请退款");
        List<Map<String, Object>> ol = jdbc.queryForList("SELECT * FROM orders WHERE orderid=? FOR UPDATE", orderid);
        if (ol.isEmpty()) return R.error("订单不存在");
        Map<String, Object> o = ol.get(0);
        if (!isAdmin(req) && !Objects.equals(uid(req), ((Number) o.get("userid")).longValue())) return R.error("无权操作");
        List<Map<String, Object>> tl = jdbc.queryForList("SELECT * FROM wxpay_transaction WHERE biz_type='ORDER' AND status IN('SUCCESS','PARTIAL_REFUND','REFUND') AND FIND_IN_SET(?, biz_ref) FOR UPDATE", orderid);
        if (tl.isEmpty()) return R.error("该订单不是微信支付(模拟)订单");
        Map<String, Object> t = tl.get(0);
        if (!jdbc.queryForList("SELECT id FROM wxpay_refund WHERE out_trade_no=? AND orderid=?", t.get("out_trade_no"), orderid).isEmpty()) return R.ok("已退款");
        if (!"已支付".equals(o.get("status")) && !"已完成".equals(o.get("status"))) return R.error("订单当前状态“" + o.get("status") + "”不可退款");
        BigDecimal amt = new BigDecimal(String.valueOf(o.get("total"))).setScale(2, RoundingMode.HALF_UP);
        BigDecimal refunded = ((BigDecimal) t.get("refunded_amount")).add(amt);
        if (refunded.compareTo((BigDecimal) t.get("amount")) > 0) return R.error("退款金额超过实付金额");
        String rno = "RF" + ts() + rand(6).toUpperCase();
        jdbc.update("INSERT INTO wxpay_refund(out_refund_no,refund_id,out_trade_no,orderid,userid,amount,reason,status,create_time) VALUES(?,?,?,?,?,?,?,'SUCCESS',NOW())",
                rno, "5030" + ts() + rand(6).replaceAll("[a-z]", "0"), t.get("out_trade_no"), orderid, o.get("userid"), amt, reason);
        jdbc.update("UPDATE wxpay_transaction SET refunded_amount=?, status=? WHERE id=?", refunded,
                refunded.compareTo((BigDecimal) t.get("amount")) == 0 ? "REFUND" : "PARTIAL_REFUND", t.get("id"));
        jdbc.update("UPDATE orders SET status='已退款' WHERE orderid=?", orderid);
        // 退回库存
        if ("nongchanpinxinxi".equals(o.get("tablename")) && o.get("buynumber") != null)
            jdbc.update("UPDATE nongchanpinxinxi SET alllimittimes=alllimittimes+? WHERE id=?", o.get("buynumber"), o.get("goodid"));
        return R.ok("退款成功，¥" + amt + " 已原路退回（模拟）").put("out_refund_no", rno);
    }

    // ---------- 7. 管理端：交易流水 / 退款记录 ----------
    @GetMapping("/admin/transactions")
    public R adminTx(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit,
                     @RequestParam(required = false) String status, @RequestParam(required = false) String keyword, HttpServletRequest req) {
        if (!isAdmin(req)) return R.error("仅管理员可查看");
        StringBuilder w = new StringBuilder(" WHERE 1=1"); List<Object> a = new ArrayList<>();
        if (status != null && !status.isEmpty()) { w.append(" AND status=?"); a.add(status); }
        if (keyword != null && !keyword.isEmpty()) { w.append(" AND (out_trade_no LIKE ? OR biz_ref LIKE ? OR username LIKE ?)"); String k = "%" + keyword + "%"; a.add(k); a.add(k); a.add(k); }
        Long total = jdbc.queryForObject("SELECT COUNT(*) FROM wxpay_transaction" + w, a.toArray(), Long.class);
        List<Object> a2 = new ArrayList<>(a); a2.add((page - 1) * limit); a2.add(limit);
        List<Map<String, Object>> list = jdbc.queryForList("SELECT id,out_trade_no,transaction_id,biz_type,biz_ref,username,tablename,description,amount,refunded_amount,status,notify_count,create_time,pay_time FROM wxpay_transaction" + w + " ORDER BY id DESC LIMIT ?,?", a2.toArray());
        Map<String, Object> sum = jdbc.queryForMap("SELECT IFNULL(SUM(CASE WHEN status IN('SUCCESS','PARTIAL_REFUND','REFUND') THEN amount END),0) paid, IFNULL(SUM(refunded_amount),0) refunded, COUNT(*) cnt FROM wxpay_transaction");
        Map<String, Object> d = new HashMap<>(); d.put("list", fmtList(list)); d.put("total", total); d.put("summary", sum);
        return R.ok().put("data", d);
    }

    @GetMapping("/admin/refunds")
    public R adminRefunds(@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "10") int limit, HttpServletRequest req) {
        if (!isAdmin(req)) return R.error("仅管理员可查看");
        Long total = jdbc.queryForObject("SELECT COUNT(*) FROM wxpay_refund", Long.class);
        List<Map<String, Object>> list = jdbc.queryForList("SELECT * FROM wxpay_refund ORDER BY id DESC LIMIT ?,?", (page - 1) * limit, limit);
        Map<String, Object> d = new HashMap<>(); d.put("list", fmtList(list)); d.put("total", total);
        return R.ok().put("data", d);
    }

    /** 管理端：手动关闭待支付交易 */
    @PostMapping("/admin/close/{no}")
    public R close(@PathVariable String no, HttpServletRequest req) {
        if (!isAdmin(req)) return R.error("仅管理员可操作");
        int c = jdbc.update("UPDATE wxpay_transaction SET status='CLOSED' WHERE out_trade_no=? AND status='NOTPAY'", no);
        return c == 1 ? R.ok("已关闭") : R.error("只能关闭待支付交易");
    }
}
