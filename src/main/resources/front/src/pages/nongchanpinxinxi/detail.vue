<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'-'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index" :to="'/index/nongchanpinxinxi?centerType=' + (centerType?'1':'0')"><a>{{item.name}}</a></el-breadcrumb-item>
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
							{{detail.nongchanpinmingcheng}}
						</div>
						<div class="colectBtn" @click="storeup(1)" v-show="!isStoreup">
							<i class="icon iconfont icon-shoucang10"></i>
							<span class="text">收藏({{detail.storeupnum}})</span>
						</div>
						<div class="colectBtnActive" @click="storeup(-1)" v-show="isStoreup">
							<i class="icon iconfont icon-shoucang12"></i>
							<span class="text">已收藏({{detail.storeupnum}})</span>
						</div>
					</div>
					<div class="item" v-if="detail.price">
						<div class="lable">价格</div>
						<div class="text price bold"><span style="font-size: 12px">￥</span>{{detail.price}}</div>
					</div>
					<div class="item" v-if="detail.jf">
						<div class="lable">积分</div>
						<div class="text price bold">{{detail.jf}}</div>
					</div>
					<div class="item">
						<div class="lable">单限</div>
						<div class="text">{{detail.onelimittimes}}</div>
					</div>
					<div class="item">
						<div class="lable">库存</div>
						<div class="text">{{detail.alllimittimes}}</div>
					</div>
					<div class="item">
						<div class="lable">农产品类型</div>
						<div class="text "  >{{detail.nongchanpinleixing}}</div>
					</div>
					<div class="item">
						<div class="lable">产品规格</div>
						<div class="text "  >{{detail.chanpinguige}}</div>
					</div>
					<div class="item">
						<div class="lable">营养成分</div>
						<div class="text "  >{{detail.yingyangchengfen}}</div>
					</div>
					<div class="item">
						<div class="lable">外观特征</div>
						<div class="text "  >{{detail.waiguantezheng}}</div>
					</div>
					<div class="item">
						<div class="lable">农民账号</div>
						<div class="text  link" @click="merchantClick('nongminzhanghao')" >{{detail.nongminzhanghao}}</div>
					</div>
					<div class="btn_box">
						<el-input-number v-if="detail.alllimittimes" :min=1 v-model="buynumber"></el-input-number>
						<el-button class="addCartBtn" v-if="detail.alllimittimes" type="warning" size="small" @click="addCart">添加到购物车</el-button>
						<el-button class="buyBtn" v-if="detail.alllimittimes" type="warning" size="small" @click="buyNow">立即购买</el-button>

					</div>
					<div class="btn_box">
						<el-button class="editBtn" v-if="btnAuth('nongchanpinxinxi','修改')" @click="editClick">修改</el-button>
						<el-button class="delBtn" v-if="btnAuth('nongchanpinxinxi','删除')" @click="delClick">删除</el-button>
						<el-button class="editBtn" @click="allchapterClick()" type="warning" v-if="btnAuth('nongchanpinxinxi','章节管理')">章节管理</el-button>
						<el-button class="editBtn" @click="discussClick()" type="warning" v-if="btnAuth('nongchanpinxinxi','查看评论')">查看评论</el-button>
						<el-button v-if="centerType" class="editBtn" @click="onshelvesClick" type="warning">{{detail.onshelves==1?'下架':'上架'}}</el-button>
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
				<el-tab-pane label="产品介绍" name="1">
					<div class="ql-snow ql-editor" v-html="detail.chanpinjieshao"></div>
				</el-tab-pane>
				<el-tab-pane label="评论" name="2">
					<el-form class="add commentForm" :model="form" :rules="rules" ref="form">
						<el-form-item class="item" label="评论" prop="content">
							<editor
								myQuillEditor="content"
								v-model="form.content" 
								class="editor" 
								action="file/upload">
							</editor>
						</el-form-item>
						<el-form-item class="commentBtn">
							<el-button class="submitBtn" type="primary" @click="submitForm('form')">立即提交</el-button>
							<el-button class="resetBtn" @click="resetForm('form')">重置</el-button>
						</el-form-item>
					</el-form>
				
					<div v-if="infoList.length" class="comment-list">
						<div class="comment-item" v-for="item in infoList" :key="item.id" @mouseenter="discussEnter(item.id)" :id="'discuss' + item.id"
							@mouseleave="discussLeave">
							<div class="istop" v-if="item.istop">
								<span class="icon iconfont icon-jiantou24"></span>
							</div>
							<div class="user">
								<el-image v-if="item.avatarurl" :size="50" :src="baseUrl + item.avatarurl"></el-image>
								<el-image v-if="!item.avatarurl" :size="50" :src="require('@/assets/touxiang.png')"></el-image>
								<div class="name">{{item.nickname}}</div>
							</div>
							<div class="comment-content-box">
								<div class="ql-snow ql-editor" v-html="item.content"></div>
								<div class="comment-time">{{item.addtime}}</div>
								<div class="zancai-box">
									<div v-if="!comcaiChange(item)" class="zan-item" :class="comzanChange(item)?'active':''" @click="comzanClick(item)">
										<span class="icon iconfont" :class="comzanChange(item)?'icon-zan11':'icon-zan07'"></span>
										<span class="label">{{comzanChange(item)?'已赞':'赞'}}</span>
										<span class="num">({{item.thumbsupnum}})</span>
									</div>
									<div v-if="!comzanChange(item)" class="cai-item" :class="comcaiChange(item)?'active':''" @click="comcaiClick(item)">
										<span class="icon iconfont" :class="comcaiChange(item)?'icon-cai16':'icon-cai01'"></span>
										<span class="label">{{comcaiChange(item)?'已踩':'踩'}}</span>
										<span class="num">({{item.crazilynum}})</span>
									</div>
								</div>
								<div class="comment-btn">
									<!-- <el-button :style='{"border":"0","cursor":"pointer","padding":"0 20px","margin":"0 10px","outline":"none","color":"#fff","borderRadius":"4px","background":"url(http://codegen.caihongy.cn/20251216/b70e9adb7da84b729d1b4a49ef25eef9.png) no-repeat center /100% 100%","width":"auto","lineHeight":"34px","fontSize":"14px","height":"34px"}'>回复</el-button> -->
									<el-button class="delBtn" v-if="showIndex==item.id&&userid==item.userid" @click="discussDel(item.id)">删除</el-button>
								</div>
							</div>
							<div class="comment-content-box" v-if="item.reply">
								回复：<span class="ql-snow ql-editor" v-html="item.reply"></span>
							</div>
						</div>
					</div>
				
					<el-pagination
						background
						id="pagination" class="pagination"
						:page-size="pageSize"
						prev-text="<"
						next-text=">"
						:hide-on-single-page="true"
						:layout='["total","prev","pager","next","sizes","jumper"].join()'
						:total="total"
						@current-change="curChange"
						@prev-click="prevClick"
						@next-click="nextClick"
						@size-change="sizeChange"
						></el-pagination>
				</el-tab-pane>
			</el-tabs>

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
				tablename: 'nongchanpinxinxi',
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '农产品信息'
					}
				],
				title: '',
				detailBanner: [],
				userid: Number(localStorage.getItem('frontUserid')),
				id: 0,
				detail: {},
				tabsNum: 2,
				activeName: '1',
				form: {
					content: '',
					userid: Number(localStorage.getItem('frontUserid')),
					nickname: localStorage.getItem('username'),
					avatarurl: '',
				},
				showIndex: -1,
				infoList: [],
				rules: {
					content: [
						{ required: true, message: '请输入内容', trigger: 'blur' }
					],
				},
				total: 1,
				pageSize: 10,
				totalPage: 1,
				storeupParams: {
					name: '',
					picture: '',
					refid: 0,
					tablename: 'nongchanpinxinxi',
					userid: Number(localStorage.getItem('frontUserid'))
				},
				isStoreup: false,
				storeupInfo: {},
				buynumber: 1,
				cart: {
					buynumber: 0,
					discountprice: 0,
					goodid: 0,
					goodname: '',
					picture: '',
					price: 0,
					userid: Number(localStorage.getItem('frontUserid'))
				},
				isInCart: false,
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
						this.title = this.detail.nongchanpinmingcheng;
						if(this.detail.nongchanpinzhaopian) {
							this.detailBanner = this.detail.nongchanpinzhaopian.split(",w").length>1?[this.detail.nongchanpinzhaopian]:this.detail.nongchanpinzhaopian.split(',');
						}
						this.$forceUpdate();
						this.getDiscussList(1);
						if(localStorage.getItem('frontToken')){
							this.getStoreupStatus();
							this.getCartList();
						}

					}
				});
			},
			merchantClick(name){
				if(this.detail.isanon=='是'&&this.detail.nongminzhanghao != this.username) {
					return false
				}
				this.$http.get('nongmin/query',{params: {
					nongminzhanghao: this.detail.nongminzhanghao
				}}).then(rs=>{
					let params = {
						id: rs.data.data.id
					}
					if(this.centerType){
						params.centerType = 1
					}
					this.$router.push({path: '/index/nongminDetail', query: params});
				})
			},
			storeup(type) {
				if (type == 1 && !this.isStoreup) {
					this.storeupParams.name = this.title;
					this.storeupParams.picture = this.detailBanner[0];
					this.storeupParams.inteltype = this.detail.nongchanpinleixing;
					this.storeupParams.refid = this.detail.id;
					this.storeupParams.type = String(type);
					this.$http.post('storeup/add', this.storeupParams).then(res => {
						if (res.data.code == 0) {
							this.isStoreup = true;
							this.detail.storeupnum++
							this.$http.post('nongchanpinxinxi/update', this.detail).then(res => {});
							this.$message({
								type: 'success',
								message: '收藏成功!',
								duration: 1500,
							});
						}
					});
				}
				if (type == -1 && this.isStoreup) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '1', refid: this.detail.id, tablename: 'nongchanpinxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
							let delIds = new Array();
							delIds.push(this.storeupInfo.id);
							this.$http.post('storeup/delete', delIds).then(res => {
								if (res.data.code == 0) {
									this.isStoreup = false;
									this.detail.storeupnum--
									this.$http.post('nongchanpinxinxi/update', this.detail).then(res => {});
									this.$message({
										type: 'success',
										message: '取消成功!',
										duration: 1500,
									});
								}
							});
						}
					});
				}
			},
			getStoreupStatus(){
				if(localStorage.getItem("frontToken")) {
					this.$http.get('storeup/list', {params: {page: 1, limit: 1, type: '1', refid: this.detail.id, tablename: 'nongchanpinxinxi', userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
						if (res.data.code == 0 && res.data.data.list.length > 0) {
							this.isStoreup = true;
							this.storeupInfo = res.data.data.list[0];
						}
					});
				}
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
					this.$router.push({path: '/index/nongchanpinxinxi', query: params});
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
			getDiscussList(page,type=1) {
				this.$http.get('discussnongchanpinxinxi/list', {
					params: {
						page, 
						limit: this.pageSize, 
						refid: this.detail.id,
						sort: 'istop',
						order: 'desc',
					}
				}).then(res => {
					if (res.data.code == 0) {
						for(let x in res.data.data.list) {
							res.data.data.list[x].content = res.data.data.list[x].content.replace(/img src/gi,"img style=\"width:30%;\" src");
						}
						this.infoList = res.data.data.list;
						this.total = res.data.data.total;
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(type==1) {
							if(this.$route.query.discussId&&this.$route.query.discussId!=0) {
								this.$nextTick(()=>{
									document.getElementById('discuss' + this.$route.query.discussId).scrollIntoView({
										behavior: 'smooth'
									});
								})
							}
						}
					}
				});
			},
			comzanChange(row){
				if(row.tuserids){
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comzanClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comzanChange(row)){
					row.thumbsupnum++
					if(row.tuserids){
						row.tuserids = row.tuserids + ',' + this.userid
					}else {
						row.tuserids = String(this.userid)
					}
					this.$http.post('discussnongchanpinxinxi/update',row).then(rs=>{
						this.$message.success('点赞成功')
					})
				}else {
					row.thumbsupnum--
					let arr = row.tuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.tuserids = arr.join(',')
					this.$http.post('discussnongchanpinxinxi/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			comcaiChange(row){
				if(row.cuserids){
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							return true
						}
					}
				}
				return false
			},
			comcaiClick(row){
				if(!this.userid){
					return false
				}
				if(!this.comcaiChange(row)){
					row.crazilynum++
					if(row.cuserids){
						row.cuserids = row.cuserids + ',' + this.userid
					}else {
						row.cuserids = String(this.userid)
					}
					this.$http.post('discussnongchanpinxinxi/update',row).then(rs=>{
						this.$message.success('点踩成功')
					})
				}else {
					row.crazilynum--
					let arr = row.cuserids.split(',')
					for(let x in arr){
						if(arr[x] == this.userid){
							arr.splice(x,1)
						}
					}
					row.cuserids = arr.join(',')
					this.$http.post('discussnongchanpinxinxi/update',row).then(rs=>{
						this.$message.success('取消成功')
					})
				}
			},
			discussEnter(index){
				this.showIndex = index
			},
			discussLeave(){
				this.showIndex = -1
			},
			discussDel(id){
				this.$confirm('是否删除此评论？').then(_ => {
					this.$http.post('discussnongchanpinxinxi/delete',[id]).then(res=>{
						if(res.data&&res.data.code==0){
							this.addDiscussNum(1)
							this.$message({
								type: 'success',
								message: '删除成功!',
								duration: 1500,
								onClose: () => {
									this.getDiscussList(1);
								}
							});
						}
					})
				}).catch(_ => {});
			},
			submitForm(formName) {
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http.get('orders/list', {params: {page: 1, limit: 1, status: '已完成', goodid: this.detail.id, userid: Number(localStorage.getItem('frontUserid'))}}).then(res => {
							if (res.data.code == 0 && res.data.data.list.length == 0) {
								this.$message({
									type: 'error',
									message: '请完成订单后再评论!',
									duration: 1500
								});
								return false
							} else {
								this.addComments()
							}
						});
					} else {
						return false;
					}
				});
			},
			addComments(content=null,type=1) {
				let data = JSON.parse(JSON.stringify(this.form))
				data.refid = this.detail.id;
				data.avatarurl = localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'';
				if(type==2) {
					data.content = content
				}
				this.$http.post('discussnongchanpinxinxi/add', data).then(rs2 => {
					if (rs2.data.code == 0) {
						if(type==1) {
							this.form.content = '';
						}
						this.addDiscussNum(2)
						this.getDiscussList(1,type);
						this.$message({
							type: 'success',
							message: '评论成功!',
							duration: 1500,
						});
					}
				});
			},
			resetForm(formName) {
				this.$refs[formName].resetFields();
			},
			addDiscussNum(type){
				if(type==2){
					this.detail.discussnum++
				}else if(type==1){
					if(this.detail.discussnum!=0){
						this.detail.discussnum--
					}else{
						this.detail.discussnum = 0
					}
				}
				this.$http.post('nongchanpinxinxi/update',this.detail).then(res=>{})
			},
			getCartList() {
				this.$http.get('cart/list', {params: {userid: Number(localStorage.getItem('frontUserid')), tablename: 'nongchanpinxinxi', goodid: this.detail.id}}).then(res => {
					if (res.data.code == 0) {
						if (res.data.data.list.length > 0) {
							this.isInCart = true;
						} else {
							this.isInCart = false;
						}
					}
				});
			},
			addCart() {
				// 单次购买限制
				if (this.detail.onelimittimes > 0 && this.detail.onelimittimes < this.buynumber) {
					this.$message.error(`每人单次只能购买${this.detail.onelimittimes}件`);
					return
				}
				// 库存不够
				if (this.detail.alllimittimes <= 0 ) {
					this.$message.error(`商品已售罄`);
					return
				}
				// 库存限制
				if (this.detail.alllimittimes > 0 && this.detail.alllimittimes < this.buynumber) {
					this.$message.error(`库存不足`);
					return
				}
				if (this.isInCart) {
					this.$message.error('该商品已经添加到购物车');
					return;
				}
		
				this.cart.buynumber = this.buynumber;
				this.cart.goodid = this.detail.id;
				this.cart.goodname = this.title;
				this.cart.tablename = this.tablename;
				this.cart.nongminzhanghao = this.detail.nongminzhanghao;
				this.cart.goodtype = this.detail.nongchanpinleixing;
				this.cart.picture = this.detailBanner[0];
				this.cart.price = this.detail.price;
				this.cart.discountprice = this.detail.vipprice?this.detail.vipprice:this.detail.price;
				this.$http.post('cart/save', this.cart).then(res => {
					if (res.data.code === 0) {
						this.getCartList();
						this.$message({
							message: '添加成功',
							type: 'success',
							duration: 1500,
						});
					} else {
						this.$message.error(res.data.msg);
					}
				});
			},
			//立即购买
			buyNow() {
				// 单次购买限制
				if (this.detail.onelimittimes > 0 && this.detail.onelimittimes < this.buynumber) {
					this.$message.error(`每人单次只能购买${this.detail.onelimittimes}件`);
					return
				}
				// 库存不够
				if (this.detail.alllimittimes <= 0 ) {
					this.$message.error(`商品已售罄`);
					return
				}
				// 库存限制
				if (this.detail.alllimittimes > 0 && this.detail.alllimittimes < this.buynumber) {
					this.$message.error(`库存不足`);
					return
				}
				// 保存到storage中，在确认下单页面中获取要购买的商品
				localStorage.setItem('orderGoods', JSON.stringify([{
					tablename: this.tablename,
					goodid: this.detail.id,
					goodname: this.title,
					nongminzhanghao: this.detail.nongminzhanghao,
					goodtype: this.detail.nongchanpinleixing,

					picture:this.detailBanner[0],
					buynumber: this.buynumber,
					userid: Number(localStorage.getItem('frontUserid')),
					price: this.detail.price,
					discountprice: this.detail.vipprice ? this.detail.vipprice : this.detail.price
				}]));
				// 跳转到确认下单页面
				let query = {
					type: 1,
				}
				this.$router.push({path: '/index/shop-order/orderConfirm', query: query});
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
				this.$router.push(`/index/nongchanpinxinxiAdd?type=edit&&id=${this.detail.id}`);
			},
			// 删除
			async delClick(){
				await this.$confirm('是否删除此农产品信息？') .then(_ => {
					this.$http.post('nongchanpinxinxi/delete', [this.detail.id]).then(async res => {
						if (res.data.code == 0) {
							this.$http.get('storeup/list',{params:{
								page: 1,
								limit: 100,
								refid: this.detail.id,
								tablename: 'nongchanpinxinxi',
							}}).then(async obj=>{
								if(obj.data&&obj.data.code==0){
									let arr = []
									for(let x in obj.data.data.list){
										arr.push(obj.data.data.list[x].id)
									}
									if(arr.length){
										await this.$http.post('storeup/delete',arr).then(()=>{})
									}
									this.$message({
										type: 'success',
										message: '删除成功!',
										duration: 1500,
										onClose: () => {
											history.back()
										}
									});
								}
							})
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
				this.$router.push({path: '/index/chapternongchanpinxinxi', query: params});
			},
			discussClick (){
				let params = {
					refid: this.detail.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/discussnongchanpinxinxi', query: params});
			},
			onshelvesClick(){
				this.$confirm(`是否${this.detail.onshelves==1?'下架':'上架'}？`).then(async _ => {
					this.detail.onshelves = (this.detail.onshelves==1?0:1)
					await this.$http.post('nongchanpinxinxi/update',this.detail).then(rs=>{
						this.$message.success('操作成功！')
					})
				}).catch(_ => {});
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
					.colectBtn {
						cursor: pointer;
						border: 0px solid #eee;
						border-radius: 4px;
						padding: 5px 15px;
						background: #BBB38E;
						.icon {
							color: #fff;
							font-size: 16px;
						}
						.text {
							color: #fff;
							font-size: 16px;
						}
					}
					.colectBtnActive {
						background: #BBB38E;
						border-color: #153584;
						.icon {
							color: #fff;
							font-size: 16px;
						}
						.text {
							color: #fff;
							font-size: 16px;
						}
					}
					.colectBtn:hover {
						background: #BBB38E;
						border-color: #153584;
						.icon {
							color: #fff;
						}
						.text {
							color: #fff;
						}
					}
					.colectBtnActive:hover {
						background: #BBB38E;
						border-color: #153584;
						.icon {
							color: #fff;
						}
						.text {
							color: #fff;
						}
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
				.el-input-number {
					margin: 0 5px 0 0;
					background: none;
					display: inline-block;
					width: 180px;
					line-height: 38px;
					position: relative;
					.el-input-number__decrease:hover:not(.is-disabled)~.el-input .el-input__inner:not(.is-disabled), .el-input-number__increase:hover:not(.is-disabled)~.el-input .el-input__inner:not(.is-disabled) {
					  border-color: none;
					}
					/deep/ .el-input-number__decrease {
						cursor: pointer;
						z-index: 1;
						display: flex;
						border-color: #DCDFE6;
						border-radius: 20px;
						top: 1px;
						left: 1px;
						background: #FFF;
						width: 40px;
						justify-content: center;
						border-width: 0 0px 0 0;
						align-items: center;
						position: absolute;
						border-style: solid;
						text-align: center;
						height: 38px;
					}
					
					/deep/ .el-input-number__decrease i {
						color: #666;
						font-size: 14px;
					}
				
					/deep/ .el-input-number__increase {
						cursor: pointer;
						z-index: 1;
						display: flex;
						border-color: #DCDFE6;
						right: 1px;
						border-radius: 20px;
						top: 1px;
						background: #FFF;
						width: 40px;
						justify-content: center;
						border-width: 0 0 0 0px;
						align-items: center;
						position: absolute;
						border-style: solid;
						text-align: center;
						height: 38px;
					}
					
					/deep/ .el-input-number__increase i {
						color: #666;
						font-size: 14px;
					}
					
					/deep/ .el-input .el-input__inner {
						border: 1px solid #ABDE53;
						border-radius: 20px;
						padding: 0 40px;
						outline: none;
						color: #666;
						background: #FFFFFF;
						display: inline-block;
						width: 100%;
						font-size: 14px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.addCartBtn {
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
				.addCartBtn:hover {
					opacity: 0.7;
				}
				.buyBtn {
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
				.buyBtn:hover {
					opacity: 0.7;
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
			.commentForm {
				box-shadow: none;
				padding: 0px;
				margin: 20px 0 20px;
				width: 100%;
				.item {
					display: block;
					width: 100%;
					justify-content: center;
					align-items: center;
					height: auto;
					/deep/ .el-form-item__label {
						padding: 0 10px 0 0;
						color: #666;
						white-space: nowrap;
						width: 100%;
						font-size: 16px;
						line-height: 1;
						text-align: left;
					}
					.editor {
						border: 0px solid #ddd;
						border-radius: 4px;
						box-shadow: none;
						outline: none;
						margin: 30px 0 0 0;
						color: #333;
						background: #FFF;
						width: 100%;
						clear: both;
						font-size: 14px;
						line-height: 32px;
						/deep/ .avatar-uploader {
							height: 0;
							line-height: 0;
						}
					}
					.editor /deep/.ql-toolbar {
						background: none;
					}
					.editor /deep/.ql-container {
						background: none;
						min-height: 180px;
					}
					.editor /deep/.ql-container .ql-blank::before {
						color: #999;
					}
				}
				.commentBtn {
					padding: 0;
					margin: 10px 0 0;
					width: 100%;
					text-align: center;
					height: auto;
					.submitBtn {
						border: 0;
						cursor: pointer;
						border-radius: 40px;
						padding: 0 20px;
						margin: 0 20px 0 0;
						outline: none;
						color: #fff;
						background: #ABDE53;
						width: 206px;
						font-size: 20px;
						line-height: 60px;
						height: 60px;
					}
					.submitBtn:hover {
						opacity: 0.7;
					}
					.resetBtn {
						border: 0;
						cursor: pointer;
						border-radius: 40px;
						padding: 0 20px;
						margin: 0 20px 0 0;
						outline: none;
						color: #fff;
						background: #ABDE53;
						width: 206px;
						font-size: 20px;
						line-height: 60px;
						height: 60px;
					}
					.resetBtn:hover {
						opacity: 0.7;
					}
				}
			}
			.comment-list {
				box-shadow: none;
				padding: 0px;
				margin: 30px 0 0;
				display: flex;
				width: 100%;
				justify-content: space-between;
				flex-wrap: wrap;
				.comment-item {
					padding: 8px 10px;
					box-shadow: 0px 3px 6px 1px rgba(0,0,0,0.16);
					margin: 0 0 20px;
					background: linear-gradient( 180deg, #EAFFC5 0%, #FCFFF8 23%, #FFFFFF 100%);
					width: calc(25% - 10px);
					border-color: #eee;
					border-width: 1px;
					align-items: center;
					position: relative;
					border-style: solid;
					height: auto;
					.istop {
						cursor: pointer;
						box-shadow: none;
						top: 26px;
						background: none;
						position: absolute;
						right: 16px;
						.icon {
							color: #000;
							font-size: 24px;
						}
					}
					.user {
						padding: 10px;
						background: none;
						display: flex;
						width: 100%;
						align-items: center;
						height: auto;
						.el-image {
							border-radius: 100%;
							margin: 0 10px 0 0;
							object-fit: cover;
							width: 40px;
							height: 40px;
						}
						.name {
							color: #333;
							font-weight: 600;
							font-size: 16px;
						}
					}
					.comment-time {
						padding: 0 10px;
						color: #666;
						width: 100%;
						text-align: right;
					}
					.comment-content-box {
						padding: 8px;
						margin: 10px 0px 0px;
						word-wrap: break-word;
						color: #888;
						display: flex;
						font-size: 15px;
						border-color: #153584;
						line-height: 24px;
						border-radius: 0px;
						box-shadow: none;
						flex-direction: column;
						background: none;
						border-width: 0px 0px 0px 0px;
						border-style: solid;
						.zancai-box {
							margin: 8px 0 0 0;
							display: none;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 30px;
							.zan-item {
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								display: none;
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.zan-item.active {
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								display: none;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.zan-item:hover {
								opacity: 0.8;
								.icon {
								color: #ff000030;
								}
								.label {
								color: #ff000030;
								}
								.num {
								color: #ff000030;
								}
							}
							.cai-item {
								display: flex;
								align-items: center;
								.icon {
								font-size: 14px;
								}
								.label {
								display: none;
								font-size: 14px;
								}
								.num {
								font-size: 14px;
								}
							}
							.cai-item.active {
								background: none;
								.icon {
								color: #ff0000;
								font-size: 14px;
								}
								.label {
								color: #ff0000;
								display: none;
								font-size: 14px;
								}
								.num {
								color: #ff0000;
								font-size: 14px;
								}
							}
							.cai-item:hover {
								opacity: 0.8;
								.icon {
								color: #ff000030;
								}
								.label {
								color: #ff000030;
								}
								.num {
								color: #ff000030;
								}
							}
						}
						.comment-btn {
							margin: 8px 0 0 0;
							display: flex;
							width: 100%;
							justify-content: flex-end;
							align-items: center;
							height: 40px;
							.delBtn {
								border: 0;
								cursor: pointer;
								border-radius: 4px;
								padding: 0 20px;
								margin: 0 10px;
								outline: none;
								color: #fff;
								background: url(http://codegen.caihongy.cn/20251216/9edd1be1060643f7be9ac151744c9d60.png) no-repeat center /100% 100%;
								width: auto;
								font-size: 14px;
								line-height: 34px;
								height: 34px;
							}
						}
					}
				}
			}
		}
	}
</style>
