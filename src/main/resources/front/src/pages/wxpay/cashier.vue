<template>
	<div class="wx-cashier">
		<div class="wx-card" v-if="tx">
			<div class="wx-head">
				<div class="wx-logo"><img src="@/assets/weixin.png"><span>微信支付</span></div>
				<span class="mock-badge">⚠ 模拟支付 · 不产生真实扣款</span>
			</div>
			<div class="wx-body">
				<div class="wx-left">
					<div class="wx-amount-label">应付金额</div>
					<div class="wx-amount">¥<b>{{ Number(tx.amount).toFixed(2) }}</b></div>
					<dl>
						<dt>商品</dt><dd>{{ tx.description }}</dd>
						<dt>商户订单号</dt><dd class="mono">{{ tx.out_trade_no }}</dd>
						<dt v-if="tx.biz_ref">关联订单</dt><dd v-if="tx.biz_ref" class="mono">{{ tx.biz_ref }}</dd>
						<dt>收款方</dt><dd>助农服务平台（模拟商户）</dd>
						<dt>创建时间</dt><dd>{{ tx.create_time }}</dd>
					</dl>
				</div>
				<div class="wx-right">
					<template v-if="tx.status=='NOTPAY'">
						<div class="wx-qr-box"><div ref="qr" class="wx-qr"></div></div>
						<div class="wx-scan-tip"><i class="el-icon-full-screen"></i><div>请使用微信扫一扫<br>扫描二维码支付</div></div>
						<div class="wx-countdown">二维码 <b>{{ countdown }}</b> 后失效</div>
						<el-button size="small" round class="wx-sim" @click="openScan"><i class="el-icon-mobile-phone"></i> 没有手机？点此模拟扫码</el-button>
					</template>
					<div v-else-if="tx.status=='SUCCESS' || tx.status=='REFUND' || tx.status=='PARTIAL_REFUND'" class="wx-result ok">
						<i class="el-icon-success"></i><h3>支付成功</h3>
						<p>微信支付单号 <span class="mono">{{ tx.transaction_id }}</span></p>
						<p>支付时间 {{ tx.pay_time }}</p>
						<el-button type="primary" round @click="done">{{ tx.biz_type=='RECHARGE' ? '返回个人中心' : '查看我的订单' }}</el-button>
					</div>
					<div v-else class="wx-result fail">
						<i class="el-icon-warning"></i><h3>交易已关闭</h3><p>二维码已过期，请重新发起支付</p>
						<el-button round @click="done">返回</el-button>
					</div>
				</div>
			</div>
			<div class="wx-flow">
				<span :class="{on:true}">① 下单</span><i></i>
				<span :class="{on:true}">② 生成二维码</span><i></i>
				<span :class="{on:tx.status!='NOTPAY'}">③ 扫码确认</span><i></i>
				<span :class="{on:tx.status!='NOTPAY'&&tx.status!='CLOSED'}">④ 支付回调 · 更新订单</span>
			</div>
		</div>
		<div v-else class="wx-card" style="padding:60px;text-align:center;color:#64748b">{{ err || '加载中…' }}</div>
	</div>
</template>

<script>
import QRCode from 'qrcode2'
export default {
	data() { return { tx: null, err: '', timer: null, tick: null, countdown: '--:--', qrDone: false } },
	computed: {
		scanUrl() { return this.tx ? (location.origin + location.pathname + this.tx.code_url) : '' }
	},
	mounted() { this.load(); this.timer = setInterval(this.load, 2000); this.tick = setInterval(this.calc, 1000) },
	beforeDestroy() { clearInterval(this.timer); clearInterval(this.tick) },
	methods: {
		load() {
			this.$http.get('wxpay/query/' + this.$route.query.no).then(res => {
				if (res.data && res.data.code === 0) {
					const old = this.tx && this.tx.status
					this.tx = res.data.data
					if (this.tx.status == 'NOTPAY' && !this.qrDone) this.$nextTick(this.drawQr)
					if (this.tx.status != 'NOTPAY') clearInterval(this.timer)
					if (old == 'NOTPAY' && this.tx.status == 'SUCCESS') this.$message.success('支付成功')
				} else { this.err = res.data.msg; clearInterval(this.timer) }
			})
		},
		drawQr() {
			if (!this.$refs.qr) return
			this.$refs.qr.innerHTML = ''
			new QRCode(this.$refs.qr, { text: this.scanUrl, width: 200, height: 200, colorDark: '#111827', colorLight: '#ffffff' })
			this.qrDone = true
		},
		calc() {
			if (!this.tx) return
			const ms = new Date(this.tx.expire_time.replace(/-/g, '/')) - new Date()
			if (ms <= 0) { this.countdown = '00:00'; return }
			const m = Math.floor(ms / 60000), s = Math.floor(ms % 60000 / 1000)
			this.countdown = (m < 10 ? '0' : '') + m + ':' + (s < 10 ? '0' : '') + s
		},
		openScan() { window.open(this.scanUrl, '_blank', 'width=420,height=820') },
		done() { this.$router.push(this.tx && this.tx.biz_type == 'RECHARGE' ? '/index/center' : '/index/shop-order/order') }
	}
}
</script>

<style lang="scss" scoped>
$wx: #07c160;
.wx-cashier { padding: 8px 0 20px; }
.wx-card { max-width: 920px; margin: 0 auto; background: #fff; border-radius: 20px; box-shadow: 0 20px 60px rgba(15,23,42,.08); border: 1px solid #e5ece8; overflow: hidden; }
.wx-head { display: flex; justify-content: space-between; align-items: center; padding: 18px 28px; background: linear-gradient(90deg, #07c160, #10b981); color: #fff;
  .wx-logo { display: flex; align-items: center; gap: 10px; font-size: 20px; font-weight: 700; img { width: 32px; height: 32px; background: #fff; border-radius: 8px; padding: 3px; } } }
.wx-body { display: grid; grid-template-columns: 1.1fr 1fr; }
.wx-left { padding: 32px; border-right: 1px solid #eef2ef;
  .wx-amount-label { color: #64748b; font-size: 14px; }
  .wx-amount { color: #0f172a; font-size: 22px; margin: 6px 0 22px; b { font-size: 46px; font-weight: 800; letter-spacing: 1px; } }
  dl { display: grid; grid-template-columns: 90px 1fr; gap: 12px 10px; margin: 0; font-size: 14px; dt { color: #94a3b8; } dd { margin: 0; color: #334155; word-break: break-all; } } }
.mono { font-family: Menlo, Consolas, monospace; font-size: 13px; }
.wx-right { padding: 32px; display: flex; flex-direction: column; align-items: center; justify-content: center; background: #fafcfb; }
.wx-qr-box { padding: 14px; background: #fff; border-radius: 16px; border: 1px solid #e5ece8; box-shadow: 0 8px 24px rgba(7,193,96,.12); }
.wx-qr { width: 200px; height: 200px; }
.wx-scan-tip { display: flex; align-items: center; gap: 12px; margin: 18px 0 8px; padding: 10px 18px; border-radius: 12px; background: $wx; color: #fff; font-size: 14px; line-height: 1.5; i { font-size: 30px; } }
.wx-countdown { color: #64748b; font-size: 13px; margin-bottom: 14px; b { color: #ef4444; } }
.wx-sim { border-color: $wx !important; color: $wx !important; }
.wx-result { text-align: center; i { font-size: 72px; } h3 { margin: 10px 0; font-size: 22px; } p { color: #64748b; margin: 4px 0; font-size: 13px; } .el-button { margin-top: 18px; }
  &.ok i { color: $wx; } &.fail i { color: #f59e0b; } }
.wx-flow { display: flex; align-items: center; justify-content: center; gap: 10px; padding: 16px; border-top: 1px solid #eef2ef; font-size: 13px; color: #94a3b8;
  span.on { color: $wx; font-weight: 600; } i { width: 30px; height: 1px; background: #d1d5db; } }
@media (max-width: 760px) { .wx-body { grid-template-columns: 1fr; } }
</style>
