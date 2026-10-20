package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import com.utils.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;
import com.entity.OrdersEntity;
import com.service.OrdersService;

import com.entity.NongchanpinxinxiEntity;
import com.entity.view.NongchanpinxinxiView;

import com.service.NongchanpinxinxiService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 农产品信息
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
@RestController
@RequestMapping("/nongchanpinxinxi")
public class NongchanpinxinxiController {
    @Autowired
    private NongchanpinxinxiService nongchanpinxinxiService;

    @Autowired
    private StoreupService storeupService;

    @Autowired
    private OrdersService ordersService;








    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,NongchanpinxinxiEntity nongchanpinxinxi,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("nongmin")) {
			nongchanpinxinxi.setNongminzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<NongchanpinxinxiEntity> ew = new EntityWrapper<NongchanpinxinxiEntity>();
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);


        //查询结果
		PageUtils page = nongchanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpinxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,NongchanpinxinxiEntity nongchanpinxinxi,
                @RequestParam(required = false) Double onelimittimesstart,
                @RequestParam(required = false) Double onelimittimesend,
                @RequestParam(required = false) Double alllimittimesstart,
                @RequestParam(required = false) Double alllimittimesend,
                @RequestParam(required = false) Double pricestart,
                @RequestParam(required = false) Double priceend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimestart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date clicktimeend,
                @RequestParam(required = false) Double discussnumstart,
                @RequestParam(required = false) Double discussnumend,
                @RequestParam(required = false) Double onshelvesstart,
                @RequestParam(required = false) Double onshelvesend,
                @RequestParam(required = false) Double storeupnumstart,
                @RequestParam(required = false) Double storeupnumend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<NongchanpinxinxiEntity> ew = new EntityWrapper<NongchanpinxinxiEntity>();
        if(onelimittimesstart!=null) ew.ge("onelimittimes", onelimittimesstart);
        if(onelimittimesend!=null) ew.le("onelimittimes", onelimittimesend);
        if(alllimittimesstart!=null) ew.ge("alllimittimes", alllimittimesstart);
        if(alllimittimesend!=null) ew.le("alllimittimes", alllimittimesend);
        if(pricestart!=null) ew.ge("price", pricestart);
        if(priceend!=null) ew.le("price", priceend);
        if(clicktimestart!=null) ew.ge("clicktime", clicktimestart);
        if(clicktimeend!=null) ew.le("clicktime", clicktimeend);
        if(discussnumstart!=null) ew.ge("discussnum", discussnumstart);
        if(discussnumend!=null) ew.le("discussnum", discussnumend);
        if(onshelvesstart!=null) ew.ge("onshelves", onshelvesstart);
        if(onshelvesend!=null) ew.le("onshelves", onshelvesend);
        if(storeupnumstart!=null) ew.ge("storeupnum", storeupnumstart);
        if(storeupnumend!=null) ew.le("storeupnum", storeupnumend);

        //查询结果
		PageUtils page = nongchanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpinxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( NongchanpinxinxiEntity nongchanpinxinxi){
       	EntityWrapper<NongchanpinxinxiEntity> ew = new EntityWrapper<NongchanpinxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( nongchanpinxinxi, "nongchanpinxinxi"));
        return R.ok().put("data", nongchanpinxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(NongchanpinxinxiEntity nongchanpinxinxi){
        EntityWrapper< NongchanpinxinxiEntity> ew = new EntityWrapper< NongchanpinxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( nongchanpinxinxi, "nongchanpinxinxi"));
		NongchanpinxinxiView nongchanpinxinxiView =  nongchanpinxinxiService.selectView(ew);
		return R.ok("查询农产品信息成功").put("data", nongchanpinxinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NongchanpinxinxiEntity nongchanpinxinxi = nongchanpinxinxiService.selectById(id);
		nongchanpinxinxi.setClicktime(new Date());
		nongchanpinxinxiService.updateById(nongchanpinxinxi);
        nongchanpinxinxi = nongchanpinxinxiService.selectView(new EntityWrapper<NongchanpinxinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(nongchanpinxinxi,deSens);
        return R.ok().put("data", nongchanpinxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        NongchanpinxinxiEntity nongchanpinxinxi = nongchanpinxinxiService.selectById(id);
		nongchanpinxinxi.setClicktime(new Date());
		nongchanpinxinxiService.updateById(nongchanpinxinxi);
        nongchanpinxinxi = nongchanpinxinxiService.selectView(new EntityWrapper<NongchanpinxinxiEntity>().eq("id", id));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(nongchanpinxinxi,deSens);
        return R.ok().put("data", nongchanpinxinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NongchanpinxinxiEntity nongchanpinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(nongchanpinxinxi);
        nongchanpinxinxiService.insert(nongchanpinxinxi);
        return R.ok().put("data",nongchanpinxinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody NongchanpinxinxiEntity nongchanpinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(nongchanpinxinxi);
        nongchanpinxinxiService.insert(nongchanpinxinxi);
        return R.ok().put("data",nongchanpinxinxi.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody NongchanpinxinxiEntity nongchanpinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(nongchanpinxinxi);
        //全部更新
        nongchanpinxinxiService.updateById(nongchanpinxinxi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        nongchanpinxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,NongchanpinxinxiEntity nongchanpinxinxi, HttpServletRequest request,String pre){
        EntityWrapper<NongchanpinxinxiEntity> ew = new EntityWrapper<NongchanpinxinxiEntity>();
        ew.eq("onshelves","1");
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = nongchanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpinxinxi), params), params));
        return R.ok().put("data", page);
    }



    /**
     * 协同算法（按用户购买推荐）
     */
    @RequestMapping("/autoSort2")
    public R autoSort2(@RequestParam Map<String, Object> params,NongchanpinxinxiEntity nongchanpinxinxi, HttpServletRequest request){
        String userId = request.getSession().getAttribute("userId").toString();
        String goodtypeColumn = "nongchanpinleixing";
        //查询用户订单数据
        List<OrdersEntity> orders = ordersService.selectList(new EntityWrapper<OrdersEntity>().eq("userid", userId).eq("tablename", "nongchanpinxinxi").orderBy("addtime", false));
        List<String> goodtypes = new ArrayList<String>();
        Integer limit = params.get("limit")==null?10:Integer.parseInt(params.get("limit").toString());
        List<NongchanpinxinxiEntity> nongchanpinxinxiList = new ArrayList<NongchanpinxinxiEntity>();
	    //去重
    	List<OrdersEntity> ordersDist = new ArrayList<OrdersEntity>();
    	for(OrdersEntity o1 : orders) {
    		boolean addFlag = true;
    		for(OrdersEntity o2 : ordersDist) {
    			if(o1.getGoodid()==o2.getGoodid() || o1.getGoodtype().equals(o2.getGoodtype())) {
    				addFlag = false;
    				break;
    			}
    		}
    		if(addFlag) ordersDist.add(o1);
    	}
        if(ordersDist!=null && ordersDist.size()>0) {
            for(OrdersEntity o : ordersDist) {
                nongchanpinxinxiList.addAll(nongchanpinxinxiService.selectList(new EntityWrapper<NongchanpinxinxiEntity>().eq(goodtypeColumn, o.getGoodtype())));
            }
        }
        EntityWrapper<NongchanpinxinxiEntity> ew = new EntityWrapper<NongchanpinxinxiEntity>();
        params.put("sort", "id");
        params.put("order", "desc");
        // 查询数据并协同过滤，等到需要内容
        PageUtils page = nongchanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpinxinxi), params), params));
        List<NongchanpinxinxiEntity> pageList = (List<NongchanpinxinxiEntity>)page.getList();
        if(nongchanpinxinxiList.size()<limit) {
                int toAddNum = (limit-nongchanpinxinxiList.size())<=pageList.size()?(limit-nongchanpinxinxiList.size()):pageList.size();
                for(NongchanpinxinxiEntity o1 : pageList) {
                    boolean addFlag = true;
                    for(NongchanpinxinxiEntity o2 : nongchanpinxinxiList) {
                        if(o1.getId().intValue()==o2.getId().intValue()) {
                            addFlag = false;
                            break;
                        }
                    }
                    if(addFlag) {
                        nongchanpinxinxiList.add(o1);
                        if(--toAddNum==0) break;
                    }
                }
        } else if(nongchanpinxinxiList.size()>limit) {
            nongchanpinxinxiList = nongchanpinxinxiList.subList(0, limit);
        }
        //将新集合放入分页组件中并返回结果
        page.setList(nongchanpinxinxiList);
        return R.ok().put("data", page);
    }






}
