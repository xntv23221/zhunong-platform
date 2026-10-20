<template>
	<div class="phone-bg">
		<div class="phone">
			<div class="ph-bar"><span>9:41</span><span>微信支付</span><span>●●●</span></div>
			<div class="ph-mock">模拟支付环境 · 不会产生真实扣款</div>
			<div v-if="info" class="ph-body">
				<template v-if="state=='pay'">
					<div class="ph-merchant"><div class="avatar">农</div><div>{{ info.merchant }}</div></div>
					<div class="ph-amount">¥{{ Number(info.amount).toFixed(2) }}</div>
					<div class="ph-desc">{{ info.description }}</div>
					<div class="ph-row"><span>付款方式</span><span>零钱（模拟）</span></div>
					<div class="ph-row"><span>订单号</span><span class="mono">{{ info.out_trade_no }}</span></div>
					<button class="ph-btn" @click="showPwd=true">立即支付</button>
				</template>
				<div v-else-if="state=='ok'" class="ph-done">
					<div class="ok-icon">✓</div><h3>支付成功</h3>
					<div class="ph-amount small">¥{{ Number(info.amount).toFixed(2) }}</div>
					<p>收款方：{{ info.merchant }}</p>
					<p class="hint">电脑端收银台将自动刷新支付结果</p>
					<button class="ph-btn ghost" @click="closeWin">完成</button>
				</div>
				<div v-else class="ph-done"><div class="ok-icon warn">!</div><h3>{{ stateMsg }}</h3></div>
			</div>
			<div v-else class="ph-body ph-done"><h3>{{ err || '加载中…' }}</h3></div>

			<div class="pwd-mask" v-if="showPwd" @click.self="showPwd=false">
				<div class="pwd-sheet">
					<div class="pwd-title"><span @click="showPwd=false">✕</span>请输入支付密码</div>
					<div class="pwd-amount">¥{{ Number(info.amount).toFixed(2) }}</div>
					<input ref="pwd" v-model="pwd" type="password" placeholder="支付密码（演示：账号登录密码）" @keyup.enter="confirm" autofocus>
					<button class="ph-btn" :disabled="loading" @click="confirm">{{ loading ? '支付中…' : '确认支付' }}</button>
					<div class="pwd-err" v-if="pwdErr">{{ pwdErr }}</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
export default {
	data() { return { info: null, err: '', state: 'pay', stateMsg: '', showPwd: false, pwd: '', pwdErr: '', loading: false } },
	mounted() {
		this.$http.get('wxpay/scan/' + this.$route.query.no, { params: { t: this.$route.query.t } }).then(res => {
			if (res.data.code === 0) {
				this.info = res.data.data
				if (this.info.status == 'SUCCESS' || this.info.status.indexOf('REFUND') >= 0) this.state = 'ok'
				else if (this.info.status != 'NOTPAY') { this.state = 'closed'; this.stateMsg = '交易已关闭' }
			} else this.err = res.data.msg
		})
	},
	methods: {
		confirm() {
			if (!this.pwd) { this.pwdErr = '请输入支付密码'; return }
			this.loading = true; this.pwdErr = ''
			this.$http.post('wxpay/confirm', { no: this.$route.query.no, t: this.$route.query.t, password: this.pwd }).then(res => {
				this.loading = false
				if (res.data.code === 0) { this.showPwd = false; this.state = 'ok' } else this.pwdErr = res.data.msg
			})
		},
		closeWin() { window.close() }
	}
}
</script>

<style lang="scss" scoped>
$wx: #07c160;
.phone-bg { min-height: 100vh; display: flex; align-items: center; justify-content: center; background: #e9f5ee; padding: 20px; box-sizing: border-box; }
.phone { position: relative; width: 375px; max-width: 100%; height: 760px; background: #ededed; border-radius: 36px; overflow: hidden; box-shadow: 0 30px 70px rgba(0,0,0,.18); border: 8px solid #111; display: flex; flex-direction: column; }
.ph-bar { display: flex; justify-content: space-between; padding: 12px 20px; background: #ededed; font-size: 13px; font-weight: 600; color: #111; }
.ph-mock { background: #fff7ed; color: #c2410c; font-size: 12px; text-align: center; padding: 6px; }
.ph-body { flex: 1; background: #fff; margin: 12px; border-radius: 14px; padding: 28px 22px; display: flex; flex-direction: column; }
.ph-merchant { display: flex; flex-direction: column; align-items: center; gap: 10px; color: #333; font-size: 15px; .avatar { width: 56px; height: 56px; border-radius: 12px; background: $wx; color: #fff; font-size: 26px; display: flex; align-items: center; justify-content: center; font-weight: 700; } }
.ph-amount { text-align: center; font-size: 40px; font-weight: 700; margin: 22px 0 6px; color: #111; &.small { font-size: 28px; margin: 6px 0; } }
.ph-desc { text-align: center; color: #888; font-size: 13px; margin-bottom: 26px; }
.ph-row { display: flex; justify-content: space-between; padding: 12px 0; border-top: 1px solid #f1f1f1; font-size: 14px; color: #666; span:last-child { color: #111; } }
.mono { font-family: Menlo, Consolas, monospace; font-size: 12px; }
.ph-btn { margin-top: auto; width: 100%; height: 48px; border: 0; border-radius: 10px; background: $wx; color: #fff; font-size: 17px; font-weight: 600; cursor: pointer; &:disabled { opacity: .6; } &.ghost { background: #f2f2f2; color: $wx; } }
.ph-done { align-items: center; text-align: center; h3 { margin: 12px 0 4px; font-size: 20px; } p { color: #666; font-size: 14px; margin: 4px 0; } .hint { color: #999; font-size: 12px; margin-top: 18px; } }
.ok-icon { margin-top: 30px; width: 72px; height: 72px; border-radius: 50%; background: $wx; color: #fff; font-size: 40px; line-height: 72px; &.warn { background: #f59e0b; } }
.pwd-mask { position: absolute; inset: 0; background: rgba(0,0,0,.45); display: flex; align-items: flex-end; }
.pwd-sheet { width: 100%; background: #fff; border-radius: 16px 16px 0 0; padding: 18px 22px 30px; box-sizing: border-box;
  .pwd-title { text-align: center; font-size: 16px; font-weight: 600; position: relative; span { position: absolute; left: 0; cursor: pointer; color: #999; } }
  .pwd-amount { text-align: center; font-size: 34px; font-weight: 700; margin: 18px 0; }
  input { width: 100%; height: 48px; border: 1px solid #ddd; border-radius: 10px; padding: 0 14px; font-size: 15px; box-sizing: border-box; margin-bottom: 18px; outline: none; &:focus { border-color: $wx; } }
  .pwd-err { color: #ef4444; text-align: center; margin-top: 10px; font-size: 13px; } }
</style>
