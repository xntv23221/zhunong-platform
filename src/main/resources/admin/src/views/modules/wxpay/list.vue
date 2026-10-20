<template>
	<div class="wxp">
		<el-alert type="warning" :closable="false" show-icon title="模拟支付：本模块仿照微信支付 Native 流程（下单 → 二维码 → 扫码确认 → 签名回调 → 更新订单 → 退款），不产生真实扣款。"></el-alert>
		<div class="wxp-stats">
			<div class="st"><span>交易笔数</span><b>{{ summary.cnt || 0 }}</b></div>
			<div class="st"><span>累计实收（元）</span><b class="g">{{ Number(summary.paid || 0).toFixed(2) }}</b></div>
			<div class="st"><span>累计退款（元）</span><b class="r">{{ Number(summary.refunded || 0).toFixed(2) }}</b></div>
		</div>
		<el-tabs v-model="tab" @tab-click="reload">
			<el-tab-pane label="支付流水" name="tx">
				<div class="wxp-bar">
					<el-input v-model="keyword" size="small" clearable placeholder="商户单号 / 订单号 / 用户" style="width:260px" @keyup.enter.native="reload"></el-input>
					<el-select v-model="status" size="small" clearable placeholder="全部状态" style="width:150px">
						<el-option v-for="(v,k) in statusMap" :key="k" :label="v.t" :value="k"></el-option>
					</el-select>
					<el-button size="small" type="primary" icon="el-icon-search" @click="reload">查询</el-button>
				</div>
				<el-table :data="list" stripe size="small">
					<el-table-column prop="out_trade_no" label="商户订单号" min-width="190"></el-table-column>
					<el-table-column prop="transaction_id" label="微信支付单号(模拟)" min-width="200"></el-table-column>
					<el-table-column label="业务" width="80"><template slot-scope="s">{{ s.row.biz_type=='ORDER'?'订单':'充值' }}</template></el-table-column>
					<el-table-column prop="biz_ref" label="关联订单号" min-width="150" show-overflow-tooltip></el-table-column>
					<el-table-column prop="username" label="用户" width="90"></el-table-column>
					<el-table-column label="金额" width="100"><template slot-scope="s">¥{{ s.row.amount }}</template></el-table-column>
					<el-table-column label="已退" width="90"><template slot-scope="s">¥{{ s.row.refunded_amount }}</template></el-table-column>
					<el-table-column label="状态" width="100"><template slot-scope="s"><el-tag size="mini" :type="(statusMap[s.row.status]||{}).c">{{ (statusMap[s.row.status]||{}).t || s.row.status }}</el-tag></template></el-table-column>
					<el-table-column prop="notify_count" label="回调次数" width="80"></el-table-column>
					<el-table-column prop="create_time" label="创建时间" width="160"></el-table-column>
					<el-table-column prop="pay_time" label="支付时间" width="160"></el-table-column>
					<el-table-column label="操作" width="80" fixed="right"><template slot-scope="s"><el-button v-if="s.row.status=='NOTPAY'" type="text" @click="close(s.row)">关单</el-button></template></el-table-column>
				</el-table>
			</el-tab-pane>
			<el-tab-pane label="退款记录" name="rf">
				<el-table :data="list" stripe size="small">
					<el-table-column prop="out_refund_no" label="退款单号" min-width="180"></el-table-column>
					<el-table-column prop="refund_id" label="微信退款单号(模拟)" min-width="190"></el-table-column>
					<el-table-column prop="out_trade_no" label="原商户订单号" min-width="180"></el-table-column>
					<el-table-column prop="orderid" label="订单号" min-width="140"></el-table-column>
					<el-table-column label="金额" width="100"><template slot-scope="s">¥{{ s.row.amount }}</template></el-table-column>
					<el-table-column prop="reason" label="原因" min-width="120"></el-table-column>
					<el-table-column label="状态" width="90"><template slot-scope="s"><el-tag size="mini" type="success">{{ s.row.status=='SUCCESS'?'退款成功':s.row.status }}</el-tag></template></el-table-column>
					<el-table-column prop="create_time" label="退款时间" width="160"></el-table-column>
				</el-table>
			</el-tab-pane>
		</el-tabs>
		<el-pagination background layout="total, prev, pager, next" :total="total" :page-size="limit" :current-page.sync="page" @current-change="load"></el-pagination>
	</div>
</template>
<script>
export default {
	data() {
		return { tab: 'tx', list: [], total: 0, page: 1, limit: 10, keyword: '', status: '', summary: {},
			statusMap: { NOTPAY: {t:'待支付',c:'warning'}, SUCCESS: {t:'支付成功',c:'success'}, CLOSED: {t:'已关闭',c:'info'}, REFUND: {t:'已全额退款',c:'danger'}, PARTIAL_REFUND: {t:'部分退款',c:'danger'} } }
	},
	mounted() { this.load() },
	methods: {
		reload() { this.page = 1; this.load() },
		load() {
			const url = this.tab == 'tx' ? 'wxpay/admin/transactions' : 'wxpay/admin/refunds'
			this.$http({ url, method: 'get', params: { page: this.page, limit: this.limit, keyword: this.keyword, status: this.status } }).then(({ data }) => {
				if (data && data.code === 0) { this.list = data.data.list; this.total = data.data.total; if (data.data.summary) this.summary = data.data.summary }
				else this.$message.error(data.msg)
			})
		},
		close(row) {
			this.$http({ url: 'wxpay/admin/close/' + row.out_trade_no, method: 'post' }).then(({ data }) => { data.code === 0 ? this.$message.success(data.msg) : this.$message.error(data.msg); this.load() })
		}
	}
}
</script>
<style lang="scss" scoped>
.wxp { padding: 20px; background: #fff; border-radius: 14px; }
.wxp-stats { display: grid; grid-template-columns: repeat(3, 1fr); gap: 16px; margin: 16px 0;
  .st { padding: 18px 20px; border-radius: 14px; background: linear-gradient(135deg, #f0fdf4, #fff); border: 1px solid #dcfce7; span { color: #64748b; font-size: 13px; } b { display: block; font-size: 26px; margin-top: 6px; color: #0f172a; } .g { color: #07c160; } .r { color: #ef4444; } } }
.wxp-bar { display: flex; gap: 10px; margin-bottom: 12px; }
.el-pagination { margin-top: 16px; text-align: right; }
</style>
