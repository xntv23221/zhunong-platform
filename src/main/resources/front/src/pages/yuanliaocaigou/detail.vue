<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'-'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/yuanliaocaigou?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item3"><a href="javascript:void(0);">详情</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-fanhui12"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="detail-preview">
			<div class="attr">
				<div class="info">
					<div class="title-item">
						<div class="detail-title">
							{{detail.yuanliaomingcheng}}
						</div>
					</div>
					<div class="item">
						<div class="lable">原料分类</div>
						<div class="text "  >{{detail.yuanliaofenlei}}</div>
					</div>
					<div class="item">
						<div class="lable">有效成分</div>
						<div class="text "  >{{detail.youxiaochengfen}}</div>
					</div>
					<div class="item">
						<div class="lable">价格</div>
						<div class="text "  >{{detail.jiage}}</div>
					</div>
					<div class="item">
						<div class="lable">采购数量</div>
						<div class="text "  >{{detail.kucun}}</div>
					</div>
					<div class="item">
						<div class="lable">采购总价</div>
						<div class="text "  >{{detail.caigouzongjia}}</div>
					</div>
					<div class="item">
						<div class="lable">高校名称</div>
						<div class="text "  >{{detail.gaoxiaomingcheng}}</div>
					</div>
					<div class="item">
						<div class="lable">采购时间</div>
						<div class="text "  >{{detail.caigoushijian}}</div>
					</div>
					<div class="item">
						<div class="lable">采购备注</div>
						<div class="text "  >{{detail.caigoubeizhu}}</div>
					</div>
					<div class="item">
						<div class="lable">农民账号</div>
						<div class="text "  >{{detail.nongminzhanghao}}</div>
					</div>
					<div class="item">
						<div class="lable">是否支付</div>
						<div class="text">{{detail.ispay=='已支付'?'已支付':'未支付'}}</div>
					</div>
					<div class="item" v-if="centerType">
						<div class="lable">审核状态</div>
						<div class="text">{{detail.sfsh=='是'?'通过':(detail.sfsh=='否'?'不通过':'待审核')}}</div>
					</div>
					<div class="item" v-if="centerType">
						<div class="lable">审核回复</div>
						<div class="text">{{detail.shhf}}</div>
					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('yuanliaocaigou','修改')&&detail.sfsh!='是'&&detail.sfsh!='否' " @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('yuanliaocaigou','删除')" @click="delClick">删除</el-button>
						<el-button class="reserveBtn" 
							v-if="btnAuth('yuanliaocaigou','采购配送')" 
							@click="onAcross('caigoupeisong','是','是','[1]','已配送')" type="warning">
							采购配送
						</el-button>
						<el-button class="auditBtn" v-if="btnAuth('yuanliaocaigou','审核') && (detail.sfsh=='待审核'||!detail.sfsh)" @click="onSH" type="warning">审核</el-button>
						<el-button class="payBtn" v-if="(detail.ispay=='未支付'||!detail.ispay)&&btnAuth('yuanliaocaigou','支付')" type="warning" size="small" @click="payClick">支付</el-button>
						<el-button class="editBtn" @click="allchapterClick()" type="warning" v-if="btnAuth('yuanliaocaigou','章节管理')">章节管理</el-button>
					</div>
				</div>
			</div>
		
			<div class="detail-swpier2" v-if="detailBanner.length">
				<div class="swiper21">
					<div class="swiper-container mySwiper21">
						<div class="swiper-wrapper">
							<div class="swiper-slide" v-for="item in detailBanner" :key="item.id">
								<div class="swiper-item">
									<img v-if="item.substr(0,4)=='http'" :src="item" class="image">
									<img v-else :src="baseUrl + item" class="image">
								</div>
							</div>
						</div>
						<div class="swiper-button-prev">
							<span class="icon iconfont icon-jiantou39"></span>
						</div>
						<div class="swiper-button-next">
							<span class="icon iconfont icon-jiantou18"></span>
						</div>
					</div>
				</div>
				<div class="swiper22">
					<div class="swiper-container mySwiper22">
						<div class="swiper-wrapper">
							<div class="swiper-slide" v-for="item in detailBanner" :key="item.id">
								<div class="swiper-item">
									<img v-if="item.substr(0,4)=='http'" :src="item" class="image">
									<img v-else :src="baseUrl + item" class="image">
								</div>
							</div>
						</div>
						<div class="swiper-button-prev">
							<span class="icon iconfont icon-jiantou39"></span>
						</div>
						<div class="swiper-button-next">
							<span class="icon iconfont icon-jiantou18"></span>
						</div>
					</div>
				</div>
			</div>



			<el-tabs class="detail-tabs" v-model="activeName" type="border-card" v-if="tabsNum>0" >
			</el-tabs>
			<el-dialog title="审核" :visible.sync="sfshVisiable" width="50%">
				<el-form ref="shForm" :model="shForm" label-width="80px">
					<el-form-item label="审核状态">
						<el-select v-model="shForm.sfsh" placeholder="审核状态">
							<el-option label="通过" value="是"></el-option>
							<el-option label="不通过" value="否"></el-option>
							<el-option label="待审核" value="待审核"></el-option>
						</el-select>
					</el-form-item>
					<el-form-item label="内容">
						<el-input type="textarea" :rows="8" v-model="shForm.shhf"></el-input>
					</el-form-item>
				</el-form>
				<div slot="footer" class="dialog-footer">
					<el-button @click="sfshVisiable = false">取 消</el-button>
					<el-button type="primary" @click="shHandler">确 定</el-button>
				</div>
			</el-dialog>

		</div>
	</div>
</template>

<script>
	import axios from 'axios'
	import Swiper from "swiper";
	export default {
		//数据集合
		data() {
			return {
				tablename: 'yuanliaocaigou',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '原料采购'
					}
				],
				title: '',
				detailBanner: [],
				userid: Number(localStorage.getItem('frontUserid')),
				id: 0,
				detail: {},
				tabsNum: 0,
				activeName: '1',
				buynumber: 1,
				sfshVisiable: false,
				shForm: {
					sfsh: '是',
					shhf: ''
				},
				centerType: false,
				storeupType: false,
			}
		},
		created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0) {
				this.centerType = true
			}
			if(this.$route.query.storeupType&&this.$route.query.storeupType!=0) {
				this.storeupType = true
			}
			
			this.init();
		},
		mounted() {
			setTimeout(()=>{
				let mySwiper22 = new Swiper(".mySwiper22", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"freeMode":true,"watchSlidesVisibility":true,"watchSlidesProgress":true,"loopedSlides":6,"slidesPerView":3,"spaceBetween":10})
				let option21 = {...{"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"loopedSlides":5,"spaceBetween":10}}
				option21.thumbs = {
					swiper: mySwiper22
				}
				new Swiper(".mySwiper21", option21)
			},100)
		},
		computed: {
			username() {
				return localStorage.getItem('username')
			}
		},
		//方法集合
		methods: {
			init() {
				this.id = this.$route.query.id
				this.baseUrl = this.$config.baseUrl;
				this.$http.get(this.tablename + '/detail/'  + this.id, {}).then(async res => {
					if (res.data.code == 0) {
						this.detail = res.data.data;
						this.title = this.detail.yuanliaomingcheng;
						if(this.detail.yuanliaozhaopian) {
							this.detailBanner = this.detail.yuanliaozhaopian.split(",w").length>1?[this.detail.yuanliaozhaopian]:this.detail.yuanliaozhaopian.split(',');
						}
						this.$forceUpdate();
						if(localStorage.getItem('frontToken')){
						}

					}
				});
			},
			async onAcross(acrossTable,crossOptAudit,crossOptPay,statusColumnName,tips,statusColumnValue,type=1){
				if(crossOptAudit=='是'&&this.detail.sfsh!='是') {
					this.$message({
						message: "请审核通过后再操作",
						type: "error",
						duration: 1500,
						onClose: () => {
						}
					});
					return
				}
				if(crossOptPay=='是'&&this.detail.ispay!='已支付') {
					this.$message.error('请支付完成再操作');
					return;
				}
				localStorage.setItem('crossTable',`yuanliaocaigou`);
				localStorage.setItem('crossObj', JSON.stringify(this.detail));
				localStorage.setItem('statusColumnName',statusColumnName);
				localStorage.setItem('statusColumnValue',statusColumnValue);
				localStorage.setItem('tips',tips);
				if(statusColumnName!=''&&!statusColumnName.startsWith("[")) {
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o==statusColumnName && obj[o]==statusColumnValue){
							this.$message({
								type: 'error',
								message: tips,
								duration: 1500
							});
							return
						}
					}
				}
				this.$router.push({path: '/index/' + acrossTable + 'Add', query: {type: 'cross'}});
			},
			onSH() {
				this.sfshVisiable = true;
			},
			shHandler(){
				this.$confirm(`确定操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(async () => {
					this.detail.sfsh = this.shForm.sfsh;
					this.detail.shhf = this.shForm.shhf;
					await this.$http.post(`${this.tablename}/update`, this.detail).then(async res => {
						if (res.data.code == 0) {
							this.sfshVisiable = false;
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
							});
						}
					});
				});
			},
			payClick(){
				if(this.detail.sfsh!='是'){
					this.$message.error('请审核通过后再支付')
					return false
				}
				localStorage.setItem('paytable','yuanliaocaigou')
				localStorage.setItem('payObject',JSON.stringify(this.detail))
				this.$router.push({path:'/index/pay'})
			},
			curChange(page) {
				this.getDiscussList(page);
			},
			prevClick(page) {
				this.getDiscussList(page);
			},
			nextClick(page) {
				this.getDiscussList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getDiscussList(1);
			},
			// 返回按钮
			backClick(){
				if(this.storeupType){
					history.back()
				}else{
					let params = {}
					if(this.centerType){
						params.centerType = 1
					}
					this.$router.push({path: '/index/yuanliaocaigou', query: params});
				}
			},
			// 下载
			download(file ){
				if(!file) {
					this.$message({
						type: 'error',
						message: '文件不存在',
						duration: 1500,
					});
					return;
				}
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.baseUrl + 'file/download?fileName=' + arr, {
					headers: {
						token: localStorage.getItem("frontToken")
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$config.name).length>1 ? location.href.split(this.$config.name)[0] :'') + this.$config.name + 'file/download?fileName=' + arr, {
						headers: {
							token: localStorage.getItem("frontToken")
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},


			// 权限判断
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			// 修改
			editClick(){
				this.$router.push(`/index/yuanliaocaigouAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此原料采购？') .then(_ => {
					this.$http.post('yuanliaocaigou/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$message({
								type: 'success',
								message: '删除成功!',
								duration: 1500,
								onClose: () => {
									history.back()
								}
							});
						}
					});
				}).catch(_ => {});
			},
			allchapterClick (){
				let params = {
					refid: this.detail.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/chapteryuanliaocaigou', query: params});
			},
			// 获取uuid
			getUUID() {
				return new Date().getTime();
			},
		},
		components: {
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.detail-preview {
		padding: 0 10%;
		margin: 10px auto;
		background: #F2F2F2;
		display: flex;
		width: 100%;
		position: relative;
		flex-wrap: wrap;
		.attr {
			padding: 0px;
			margin: 0 0 20px;
			flex: 1;
			background: url(http://codegen.caihongy.cn/20251216/4ad7a6ab37d44f85907c7a012f46bedf.png) no-repeat center/ 100% 100%;
			display: flex;
			min-height: 700px;
			position: relative;
			order: 2;
			.info {
				border-radius: 8px;
				padding: 10px;
				margin: 0;
				background: none;
				flex: 1;
				.title-item {
					border-radius: 4px;
					padding: 10px;
					margin: 0 0 0px 0;
					background: none;
					display: flex;
					border-color: #ff6f3d30;
					border-width: 0 0 0px;
					justify-content: space-between;
					align-items: center;
					border-style: solid;
					.detail-title {
						padding: 0;
						color: #333333;
						font-weight: 600;
						font-size: 24px;
						border-color: #009899;
						border-width: 0 0 0 0px;
						line-height: 1;
						border-style: solid;
					}
				}
				.item {
					padding: 5px 10px;
					margin: 0 0 0px 0;
					background: none;
					display: flex;
					border-color: #ff6f3d30;
					border-width: 0 0 0px;
					justify-content: spaceBetween;
					border-style: solid;
					.lable {
						padding: 0 10px 0 0;
						color: #333;
						white-space: nowrap;
						font-weight: 500;
						width: auto;
						font-size: 16px;
						line-height: 24px;
						text-align: right;
						height: auto;
					}
					.text {
						padding: 0 10px;
						color: #000000;
						word-break: break-all;
						flex: 1;
						font-weight: bold;
						font-size: 15px;
						line-height: 24px;
						height: auto;
					}
					.price {
						color: #f00;
					}
					.bold {
						font-weight: bold;
					}
					.link {
						cursor: pointer;
						text-decoration: underline;
					}
				}
				.btn_box {
					padding: 10px 0;
					display: flex;
					flex-wrap: wrap;
				}
				.editBtn {
					border: 1px solid #ABDE53;
					cursor: pointer;
					padding: 0 10px;
					margin: 0 0 0 10px;
					color: #ABDE53;
					font-size: 14px;
					line-height: 40px;
					border-radius: 20px;
					outline: none;
					background: none;
					width: auto;
					min-width: 80px;
					height: 40px;
				}
				.editBtn:hover {
					opacity: 0.7;
				}
				.delBtn {
					border: 1px solid #ABDE53;
					cursor: pointer;
					padding: 0 10px;
					margin: 0 0 0 10px;
					color: #ABDE53;
					font-size: 14px;
					line-height: 40px;
					border-radius: 20px;
					outline: none;
					background: none;
					width: auto;
					min-width: 80px;
					height: 40px;
				}
				.delBtn:hover {
					opacity: 0.7;
				}
				.auditBtn {
					border: 0;
					cursor: pointer;
					border-radius: 20px;
					padding: 0 10px;
					margin: 0 0 0 10px;
					outline: none;
					color: #FFFFFF;
					background: #ABDE53;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
				}
				.auditBtn:hover {
					opacity: 0.7;
				}
				.payBtn {
					border: 1px solid #ABDE53;
					cursor: pointer;
					padding: 0 10px;
					margin: 0 0 0 10px;
					color: #ABDE53;
					font-size: 14px;
					line-height: 40px;
					border-radius: 20px;
					outline: none;
					background: none;
					width: auto;
					min-width: 80px;
					height: 40px;
				}
				.payBtn:hover {
					opacity: 0.7;
				}
			}
		}
		.detail-swpier2 {
			padding: 20px;
			margin: 0 0px 0px 0;
			overflow: hidden;
			background: url(http://codegen.caihongy.cn/20251216/4ad7a6ab37d44f85907c7a012f46bedf.png) no-repeat center /100% 100%;
			width: 650px;
			height: 700px;
			order: 1;
			.swiper21 {
				width: 100%;
				height: auto;
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				.swiper-item {
					width: 100%;
					height: auto;
					img {
						object-fit: cover;
						width: 100%;
						height: 500px;
					}
				}
				.swiper-button-prev {
					margin: -12px 0 0;
					top: 50%;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
				.swiper-button-next {
					margin: -12px 0 0;
					top: 50%;
					width: 24px;
					height: 24px;
					.icon {
						color: #fff;
						width: 24px;
						font-size: 24px;
						height: 24px;
					}
				}
			}
			.swiper22 {
				margin: 10px 0 0 0;
				width: 100%;
				height: 212px;
				
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				
				.swiper-item {
					width: 100%;
					opacity: 0.4;
					height: auto;
					img {
						object-fit: cover;
						width: 100%;
						height: 150px;
					}
				}
				.swiper-slide.swiper-slide-thumb-active div {
					opacity: 1;
				}
				.swiper-button-prev {
					margin: -7px 0 0;
					top: 50%;
					width: 14px;
					height: 14px;
					.icon {
						color: #fff;
						width: 14px;
						font-size: 14px;
						height: 14px;
					}
				}
				.swiper-button-next {
					margin: -7px 0 0;
					top: 50%;
					width: 14px;
					height: 14px;
					.icon {
						color: #fff;
						width: 14px;
						font-size: 14px;
						height: 14px;
					}
				}
			}
		}
		.detail-tabs {
			border: 0px solid #DCDFE6;
			box-shadow: none;
			padding: 0;
			margin: 20px auto;
			background: none;
			width: 100%;
			order: 5;
			& /deep/ .el-tabs__header .el-tabs__nav-wrap {
				margin-bottom: 0;
			}
			/deep/ .el-tabs__header {
				padding: 8px 20px;
				background: none;
				display: flex;
				width: 100%;
				border-color: #ddd;
				border-width: 0 0 0px;
				line-height: 1.5;
				justify-content: center;
				border-style: solid;
				text-align: center;
			}
			
			/deep/ .el-tabs__header .el-tabs__item {
				border: 0;
				padding: 0 20px;
				margin: 0 10px 0 0;
				color: #FFF;
				font-weight: 500;
				display: inline-block;
				font-size: 18px;
				border-color: #ff6f3c;
				line-height: 40px;
				border-radius: 0px;
				background: url(http://codegen.caihongy.cn/20251216/b70e9adb7da84b729d1b4a49ef25eef9.png) no-repeat center /100% 100%;
				border-width: 0 0 0px;
				position: relative;
				border-style: solid;
				list-style: none;
				height: 40px;
			}
			
			/deep/ .el-tabs__header .el-tabs__item:hover {
				color: #fff;
				background: url(http://codegen.caihongy.cn/20251216/9edd1be1060643f7be9ac151744c9d60.png) no-repeat center /100% 100%;
				border-color: #fff;
			}
			
			/deep/ .el-tabs__header .el-tabs__item.is-active {
				border-radius: 0px;
				padding: 0 20px;
				margin: 0 10px 0 0;
				color: #fff;
				background: url(http://codegen.caihongy.cn/20251216/9edd1be1060643f7be9ac151744c9d60.png) no-repeat center /100% 100%;
				font-size: 18px;
				border-color: #fff;
				border-width: 0 0 0px;
				border-style: solid;
			}
			
			/deep/ .el-tabs__content {
				padding: 15px 5px;
				color: #333;
				background: none;
				width: 100%;
				font-size: 16px;
			}
		}
	}
</style>
