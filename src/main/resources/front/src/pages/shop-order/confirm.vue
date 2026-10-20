<template>
	<div :style='{"width":"100%","padding":"20px 10% 40px","margin":"10px auto","position":"relative","background":"none"}'>
		<div class="section-title" :style='{"padding":"0","borderColor":"#e61f4d","margin":"10px auto","color":"#000000","textAlign":"center","borderWidth":"0 0 0px","display":"none","width":"100%","lineHeight":"32px","fontSize":"30px","borderStyle":"solid","fontWeight":"600"}'>确认{{totalPrice>0?'下单':'预订'}}</div>
		<el-descriptions v-if="seat!=1 " title="选择收货地址"></el-descriptions>
		<el-table v-if="seat!=1 "
			:data="addressList"
			style="width: 100%;padding: 0 10px;">
			<el-table-column
				label="选择"
				width="55">
				<template slot-scope="scope">
					<el-radio
						v-model="radio"
						:label="scope.$index"
						@change.native="getCurrentRow(scope.$index)">
						&nbsp;</el-radio>
				</template>
			</el-table-column>
			<el-table-column
				label="收件人"
				prop="name">
			</el-table-column>
			<el-table-column
				label="联系方式"
				prop="phone">
			</el-table-column>
			<el-table-column
				label="地址"
				prop="address">
			</el-table-column>
		</el-table>
		<div v-if="seat!=1 " style="width:100%;display:flex;align-items:center;justify-content:center;margin:10px 0">
			<el-button type="success" @click="addAddress">添加收货地址</el-button>
		</div>
		<el-descriptions title="清单列表"></el-descriptions>
		<el-table
			:data="tableData"
			style="width: 100%">
			<el-table-column
				label="名称">
				<template slot-scope="scope">
					<div class="shangpin">
						<el-image
							style="width: 100px; height: 100px;flex: 1"
							:src="baseUrl + scope.row.picture"
							fit="fill"></el-image>
						<span style="margin-left: 10px;flex: 1">{{ scope.row.goodname }}</span>
					</div>
				</template>
			</el-table-column>
			<el-table-column v-if="totalPrice>0"
				label="价格">
				<template slot-scope="scope">
					<span v-if="type == 2">{{ scope.row.price }} 积分</span>
					<span v-else><span :style='{"fontSize":"12px"}'>￥</span>{{ scope.row.price }}</span>
				</template>
			</el-table-column>
			<el-table-column v-if="seat==1"
				label="座位">
				<template slot-scope="scope">
					<span>{{ scope.row.activeSeat }}</span>
				</template>
			</el-table-column>
			<el-table-column v-if="seat!=1"
				label="数量">
				<template slot-scope="scope">
					<span>{{ scope.row.buynumber }}</span>
				</template>
			</el-table-column>
			<el-table-column v-if="totalPrice>0"
				label="总价">
				<template slot-scope="scope">
					<span v-if="type == 2">{{ scope.row.price * scope.row.buynumber }} 积分</span>
					<span v-else><span :style='{"fontSize":"12px"}'>￥</span>{{ changePrice(scope.row) }}</span>
				</template>
			</el-table-column>
		</el-table>
		<div style="width: 100%;padding: 12px;box-sizing: border-box;display: flex;align-items: center;">
			<span style="width: 80px; font-size: 14px;">备注：</span><el-input type="textarea" v-model="remark" placeholder="备注" style="width: calc(100% - 80px);"></el-input>
		</div>
		<div class="pay-method" v-if="seat!=1 && tableData.length > 0 && type != 2">
			<div class="pm-title">支付方式</div>
			<div class="pm-list">
				<div class="pm-item" :class="{on: payMethod=='wxpay'}" @click="payMethod='wxpay'">
					<img src="@/assets/weixin.png"><div><b>微信支付</b> <span class="mock-badge">模拟支付</span><p>扫码支付，不产生真实扣款</p></div>
					<i class="el-icon-success" v-if="payMethod=='wxpay'"></i>
				</div>
				<div class="pm-item" :class="{on: payMethod=='balance'}" @click="payMethod='balance'">
					<i class="el-icon-wallet pm-wallet"></i><div><b>余额支付</b><p>使用账户余额支付</p></div>
					<i class="el-icon-success" v-if="payMethod=='balance'"></i>
				</div>
			</div>
		</div>
		<div class="buy" v-if="seat!=1 && tableData.length > 0">
			<template v-if="type == 2">
				<span style="color: red;margin-right: 10px;font-weight: 700;">总价：{{totalPrice}} 积分</span>
				<el-button type="warning" @click="beforePay">兑换</el-button>
			</template>
			<template v-else>
				<span style="color: red;margin-right: 10px;font-weight: 700;">总价：<span :style='{"fontSize":"12px"}'>￥</span>{{totalPrice}}</span>
				<el-button type="success" @click="beforePay">支付</el-button>
			</template>
		</div>
		<div class="buy" v-if="seat==1 && tableData.length > 0">
			<template v-if="type == 2">
				<span style="color: red;margin-right: 10px;font-weight: 700;">总价：{{totalPrice}} 积分</span>
				<el-button type="success" @click="beforePay">兑换</el-button>
			</template>
			<template v-else>
				<span style="color: red;margin-right: 10px;font-weight: 700;" v-if="totalPrice>0">总价：<span :style='{"fontSize":"12px"}'>￥</span>{{totalPrice}}</span>
				<el-button type="success" @click="beforePay">{{totalPrice>0?'支付':'预订'}}</el-button>
			</template>
		</div>
		<el-dialog title="验证支付密码" :visible.sync="payPasswordVisible" width="20%">
			<el-input type="password" v-model="payPassword" placeholder="支付密码" style="width: 100%;"></el-input>
			<span slot="footer" class="dialog-footer">
				<el-button @click="payPasswordVisible=false">返回</el-button>
				<el-button @click="payConfirm" type="success">确认支付</el-button>
			</span>
		</el-dialog>
	</div>
</template>

<script>
import config from '@/config/config'
export default {
	data() {
		return {
			baseUrl: config.baseUrl,
			userTableName: localStorage.getItem('UserTableName'),
			addressList: [],
			tableData: [],
			currentRow: null,
			radio: -1,
			order: {
				address: "",
				buynumber: 0,
				consignee: '',
				discountprice: 0,
				discounttotal: 0,
				goodid: 0,
				goodname: "",
				nongminzhanghao : "",
				orderid: "",
				picture: "",
				price: 0,
				status: "未支付",
				tablename: "",
				tel: "",
				total: 0,
				type: 1,
				userid: localStorage.getItem('frontUserid')
			},
			user: {},
			type: 0,
			seat: 0,
			buynumber: 0,
			remark:'',
			orderIdList: {},
			payMethod: 'wxpay',
			wxOrderIds: [],
			payList: this.$config.payList,
			payPasswordVisible: false,
			payPassword: ''
		}
	},
	created() {
		if(this.$route.query.type != undefined) {
			this.type = this.$route.query.type;
		}
		if(this.$route.query.seat != undefined) {
			this.seat = this.$route.query.seat;
		}
		if(this.$route.query.tablename != undefined) {
			this.order.tablename = this.$route.query.tablename;
		}
		this.getSession();
		this.getAddrList();
		// 获取商品购买的清单列表
		var orderGoods = localStorage.getItem('orderGoods')
		// 转换成json类型，localstorage保存的是string数据
		this.tableData = JSON.parse(orderGoods);
		this.type = this.$route.query.type;
	},
	methods: {
		changePay(e){
			for(let x in this.payList) {
				if(this.payList[x].roleName == e) {
					return this.payList[x].tableName
				}
			}
		},
		changePrice(row){
			let price = 0
			price = row.price * row.buynumber
			row.total = (price).toFixed(2)
			return (price).toFixed(2)
		},
		getSession() {
			if(!this.userTableName){
				this.$router.push('/login');
				return false
			}
			this.$http.get(`${this.userTableName}/session`, {emulateJSON: true}).then(res => {
				if (res.data.code == 0) {
					this.user = res.data.data;
				}
			});
		},
		getCurrentRow(index) {
			this.radio = index;　
		},
		getAddrList() {
			this.$http.get('address/list', {params: {userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
				if (res.data.code == 0) {
					this.addressList = res.data.data.list;
					this.addressList.forEach((item, index) => {
						if (item.isdefault == '是') {
							this.radio = index;
						}
					});
				}
			});
		},
		addAddress(){
			this.$router.push('/index/shop-address/addOrUpdate');
		},
		toWxCashier() {
			this.$http.post('wxpay/create', {bizType: 'ORDER', orderids: this.wxOrderIds}).then(res => {
				if (res.data && res.data.code === 0) {
					this.$router.push({path: '/index/wxpay/cashier', query: {no: res.data.data.out_trade_no}})
				} else {
					this.$message.error(res.data.msg || '下单失败')
					this.$router.push('/index/shop-order/order')
				}
			})
		},
		beforePay(){
			if (this.radio == -1 ) {
				this.$message({
					message: '请选择收货地址',
					type: 'error',
					duration: 1500
				});
				return;
			}
			if (this.payMethod == 'wxpay' && this.type != 2 && this.seat != 1) {
				this.wxOrderIds = []
				this.payClick()
				return
			}
			if(localStorage.getItem('hasPayPassword')&&localStorage.getItem('hasPayPassword')==1) {
				this.payPassword = ''
				this.payPasswordVisible = true
			}else {
				if(this.seat == 1) {
					if(this.type == 2) {
						this.xzJfClick()
						return false
					}
					this.xzPayClick()
				}else {
					if(this.type == 2) {
						this.jfClick()
						return false
					}
					this.payClick()
				}
			}
		},
		payConfirm() {
			let that = this
			if(this.payPassword == this.user.paypassword) {
				this.$message({
					message: '支付密码正确！',
					type: 'success',
					duration: 1500,
					onClose(){
						that.payPasswordVisible = false
						if(that.seat == 1) {
							if(that.type == 2) {
								that.xzJfClick()
								return false
							}
							that.xzPayClick()
						}else {
							if(that.type == 2) {
								that.jfClick()
								return false
							}
							that.payClick()
						}
					}
				});
			}else {
				this.$message({
					message: '支付密码错误！',
					type: 'error',
					duration: 1500
				});
			}
		},
		// 正常下单，生成订单，减少余额，添加积分，减少库存，修改状态已支付
		 payClick() {
			let that = this
			// 生成订单
			for(let index in this.tableData){
				let item = this.tableData[index]
				setTimeout(()=>{
					
				// 获取商品详情信息
				 this.$http.get(`${item.tablename}/info/${item.goodid}`).then( res => {
					// 订单编号
					this.orderIdList['orderId' + Number(index)] = String(this.createOrder())
					let data = res.data.data;
					if(item.tablename=='nongchanpinxinxi'){
						// 减少库存
						data.alllimittimes = data.alllimittimes - item.buynumber;
						if(data.alllimittimes==0){
							data.onshelves = 0
						}
					}
					// 更新库存信息
					 this.$http.post(`${item.tablename}/update`, data).then( res => {
						// 添加订单信息
						let order = {
							orderid: this.orderIdList['orderId' + Number(index)],
							tablename: item.tablename,
							userid: this.user.id,
							goodid: item.goodid,
							goodname: item.goodname,
							nongminzhanghao: item.nongminzhanghao,
							goodtype: item.goodtype,
							picture: item.picture,
							buynumber: item.buynumber,
							discountprice: item.price,
							price: item.price,
							total: Number(item.total),
							discounttotal: Number(item.total),
							type: this.type?Number(this.type):1,
							//total: this.totalPrice,
							address:  this.addressList[this.radio].address,
							tel:  this.addressList[this.radio].phone,
							consignee:  this.addressList[this.radio].name,
							remark: this.remark,
							status: '未支付',
							sfsh: '',
							role: this.userTableName,
						}
						this.$http.post('orders/add', order).then( res => {
							// 删除购物车数据(如果是购物车下单)
							if (item.id) {
								this.$http.post('cart/delete', [item.id]).then(res => {});
							}
							// 模拟微信支付：订单保持“未支付”，全部生成后统一下单跳转收银台
							if (this.payMethod == 'wxpay' && this.type != 2) {
								this.wxOrderIds.push(order.orderid)
								if (this.wxOrderIds.length == this.tableData.length) this.toWxCashier()
								return
							}
							// 减少余额，更新订单状态
							// 判断余额是否充足
							if (Number(this.user.money) < Number(item.total)) {
								this.$message({
									message: '余额不足，请先充值',
									type: 'error',
									duration: 1500,
								onClose(){
									that.$router.push('/index/center')
								}
								});
								return
							}
							// 如果该商品存在积分
							if (data.jf) {
								this.user.jf = Number((Number(this.user.jf) + Number(item.total)).toFixed(2))
							}
							this.user.money = Number((Number(this.user.money) - Number(item.total)).toFixed(2))
							// 更新用户余额
							this.$http.post(`${this.userTableName}/update`, this.user).then(res => {
								localStorage.setItem('sessionForm',JSON.stringify(this.user))
								order.status = '已支付'
								var params = {
									orderid: this.orderIdList['orderId' + Number(index)],
									page: 1,
									limit: 1,
								}
								this.$http.get('orders/list', {params: params}).then(res => {
									order.id = res.data.data.list[0].id;
									this.$http.post(`orders/update`, order).then(res => {
										
										this.$message({
											message: '购买成功',
											type: 'success',
											duration: 1500,
											onClose: () => {
												this.$router.push('/index/shop-order/order');
											}
										});
									});
								});
							});
						});
					});
				});
				},500)
			}
		},
		createOrder() {
			let order = '';
			let now = new Date();
			order += now.getFullYear();
			order += now.getMonth() + 1;
			order += now.getDate();
			order += now.getHours();
			order += now.getMinutes();
			order += now.getSeconds();
			order += now.getMilliseconds();

			return order;
		}
	},
	computed: {
		totalPrice() {
			let totalPrice = 0;
			this.tableData.forEach(item => {
				totalPrice += item.price * item.buynumber;
			});
			return totalPrice.toFixed(2);
		},
		totalJifen() {
			let totalJifen = 0;
			this.tableData.forEach(item => {
				totalJifen += parseInt(item.jf * this.buynumber);
			});

			return totalJifen;
		},
	}
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .section {
	width: 900px;
	margin: 0 auto;
  }

  .shangpin {
	display: flex;
  }
  .buy {
	text-align: right;
	padding-right: 82px;
  }
.pay-method { background: #fff; border: 1px solid #e5ece8; border-radius: 14px; padding: 16px 20px; margin: 16px 0;
  .pm-title { font-weight: 700; margin-bottom: 12px; }
  .pm-list { display: flex; gap: 14px; flex-wrap: wrap; }
  .pm-item { position: relative; display: flex; align-items: center; gap: 12px; width: 300px; padding: 14px 16px; border: 1.5px solid #e5ece8; border-radius: 12px; cursor: pointer; transition: .2s;
    img { width: 36px; height: 36px; } p { margin: 4px 0 0; font-size: 12px; color: #64748b; }
    .pm-wallet { font-size: 32px; color: #f59e0b; }
    .el-icon-success { position: absolute; right: 12px; top: 12px; color: #07c160; font-size: 18px; }
    &.on { border-color: #07c160; background: #f0fdf4; } } }
</style>
