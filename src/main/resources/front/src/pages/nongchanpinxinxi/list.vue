<template>
	<div>
		<div class="breadcrumb-preview">
			<el-breadcrumb :separator="'-'">
				<el-breadcrumb-item class="item1" to="/"><a>首页</a></el-breadcrumb-item>
				<el-breadcrumb-item class="item2" v-for="(item, index) in breadcrumbItem" :key="index"><a>{{item.name}}</a></el-breadcrumb-item>
			</el-breadcrumb>
		</div>
		<div v-if="centerType " class="back_box">
			<el-button class="backBtn" size="mini" @click="backClick">
				<span class="icon iconfont icon-fanhui12"></span>
				<span class="text">返回</span>
			</el-button>
		</div>
		<div class="list-preview">
			<el-form :inline="true" :model="formSearch" class="list-form-pv">
				<el-form-item class="list-item" >
					<div class="lable">农产品名称：</div>
					<el-input v-model="formSearch.nongchanpinmingcheng" placeholder="农产品名称" @keydown.enter.native="getList(1, curFenlei)" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item" >
					<div class="lable">农产品类型：</div>
					<el-select v-model="formSearch.nongchanpinleixing" placeholder="请选择农产品类型" :clearable="true">
						<el-option v-for="(item, index) in nongchanpinleixingOptions" :key="index" :label="item" :value="item"></el-option>
					</el-select>
				</el-form-item>
				<el-form-item class="list-item" >
					<div class="lable">价格：</div>
					<el-input v-model="formSearch.pricestart" placeholder="最小价格" clearable></el-input>
				</el-form-item>
				<el-form-item class="list-item" >
					<el-input v-model="formSearch.priceend" placeholder="最大价格" clearable></el-input>
				</el-form-item>
				<div class="list-btn-box">
					<el-button class="list-search-btn" v-if=" true " type="primary" @click="getList(1, curFenlei)">
						<span class="icon iconfont icon-fangdajing07"></span>
						搜索
					</el-button>
					<el-button class="list-add-btn" v-if="btnAuth('nongchanpinxinxi','新增')" type="primary" @click="add('/index/nongchanpinxinxiAdd')">
						<span class="icon iconfont icon-tianjia7"></span>
						新增
					</el-button>
  
				</div>
			</el-form>
			<div class="sort_view">
				<el-button class="price-sort-btn" @click="sortClick('price')">
					<span class="icon iconfont icon-chujia13" v-if="sortType!='price'"></span>
					<span class="icon iconfont icon-jiantou35" v-else-if="sortType=='price'&&sortOrder=='desc'"></span>
					<span class="icon iconfont icon-jiantou36" v-else-if="sortType=='price'&&sortOrder=='asc'"></span>
					<span class="text">价格</span>
				</el-button>
				<el-button class="collect-sort-btn" @click="sortClick('storeupnum')">
					<span class="icon iconfont icon-shoucang10" v-if="sortType!='storeupnum'"></span>
					<span class="icon iconfont icon-jiantou35" v-else-if="sortType=='storeupnum'&&sortOrder=='desc'"></span>
					<span class="icon iconfont icon-jiantou36" v-else-if="sortType=='storeupnum'&&sortOrder=='asc'"></span>
					<span class="text">收藏数</span>
				</el-button>
			</div>
			<div class="list">
				<div class="list5">
					<div v-for="(item,index) in dataList" :key="index" class="list-item" @click.stop="toDetail(item)" >
						<div class="imgbox">
							<img @click.stop="imgPreView(item.nongchanpinzhaopian)" v-if="item.nongchanpinzhaopian && item.nongchanpinzhaopian.substr(0,4)=='http'&&item.nongchanpinzhaopian.split(',w').length>1" :src="item.nongchanpinzhaopian" class="image" />
							<img @click.stop="imgPreView(item.nongchanpinzhaopian.split(',')[0])" v-else-if="item.nongchanpinzhaopian && item.nongchanpinzhaopian.substr(0,4)=='http'" :src="item.nongchanpinzhaopian.split(',')[0]" class="image" />
							<img @click.stop="imgPreView(baseUrl + (item.nongchanpinzhaopian?item.nongchanpinzhaopian.split(',')[0]:''))" v-else :src="baseUrl + (item.nongchanpinzhaopian?item.nongchanpinzhaopian.split(',')[0]:'')" class="image" />
						</div>
						<div class="infoBox">
							<div class="name">{{item.nongchanpinmingcheng}}</div>
							<div class="name">{{item.nongchanpinleixing}}</div>
							<div class="price">￥<span class="price_text">{{item.price}}</span></div>
							<div class="bottomInfo">
								<div class="time_item">
									<span class="icon iconfont icon-shijian21"></span>
									<span class="label">发布时间：</span>
									<span class="text">{{item.addtime.split(' ')[0]}}</span>
								</div>
								<div class="publisher_item">
									<span class="icon iconfont icon-geren16"></span>
									<span class="label">发布人：</span>
									<span class="text">{{item.nongminzhanghao}}</span>
								</div>
								<div class="collect_item">
									<span class="icon iconfont icon-shoucang10"></span>
									<span class="label">收藏量：</span>
									<span class="text">{{item.storeupnum}}</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

	
			<el-pagination
				background
				id="pagination"
				class="pagination"
				:pager-count="7"
				:page-size="pageSize"
				prev-text="<"
				next-text=">"
				:hide-on-single-page="true"
				:layout='["total","prev","pager","next","sizes","jumper"].join()'
				:total="total"
				:page-sizes="pageSizes"
				@current-change="curChange"
				@size-change="sizeChange"
				@prev-click="prevClick"
				@next-click="nextClick"
				></el-pagination>
		</div>
		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>
<script>
	import axios from 'axios';
	export default {
		//数据集合
		data() {
			return {
				layouts: '',
				swiperIndex: -1,
				baseUrl: '',
				breadcrumbItem: [
					{
						name: '农产品信息'
					}
				],
				formSearch: {
					nongchanpinmingcheng: '',
					nongchanpinleixing: '',
					price: '',
				},
				fenlei: [],
				dataList: [],
				total: 1,
				pageSize: 12,
				pageSizes: [],
				totalPage: 1,
				curFenlei: '全部',
				isPlain: false,
				indexQueryCondition: '',
				nongchanpinleixingOptions: [],
				timeRange: [],
				centerType:false,
				previewImg: '',
				previewVisible: false,
				sortType: 'id',
				sortOrder: 'desc',
			}
		},
		async created() {
			if(this.$route.query.centerType&&this.$route.query.centerType!=0){
				this.centerType = true
			}
			this.baseUrl = this.$config.baseUrl;
			await this.$http.get('option/nongchanpinleixing/nongchanpinleixing').then(res => {
				if (res.data.code == 0) {
					this.nongchanpinleixingOptions = res.data.data;
				}
			});
			if(this.centerType) {
			}
			await this.getFenlei();
			this.getList(1, '全部');
		},
		watch:{
			$route(newValue){
				this.getList(1, newValue.query.homeFenlei);
			}
		},
		computed: {
			role(){
				return localStorage.getItem('frontRole');
			},
			username() {
				return localStorage.getItem('username')
			}
		},
		//方法集合
		methods: {
			queryChange(arr){
				for(let x in arr) {
					if(arr[x] == this.role) {
						return true
					}
				}
				return false
			},
			add(path) {
				let query = {}
				if(this.centerType){
					query.centerType = 1
				}
				this.$router.push({path: path,query:query});
			},
			async getFenlei() {
			},
			getList(page, fenlei, ref = '') {
				let params = {
					page,
					limit: this.pageSize,
				};
				if(!this.centerType) {
					params.onshelves = 1
				}
				let searchWhere = {};
				if (this.formSearch.nongchanpinmingcheng != '') searchWhere.nongchanpinmingcheng = '%' + this.formSearch.nongchanpinmingcheng + '%';
				if (this.formSearch.nongchanpinleixing != '') searchWhere.nongchanpinleixing = this.formSearch.nongchanpinleixing;
				if(this.formSearch.pricestart!='' && this.formSearch.pricestart!=undefined ){
					searchWhere.pricestart = this.formSearch.pricestart
				}
				if(this.formSearch.priceend!='' && this.formSearch.priceend!=undefined){
					searchWhere.priceend = this.formSearch.priceend
				}
				let user = JSON.parse(localStorage.getItem('sessionForm'))
				if (this.sortType) searchWhere.sort = this.sortType
				if (this.sortOrder) searchWhere.order = this.sortOrder
				this.$http.get(`nongchanpinxinxi/${this.centerType?'page':'list'}`, {params: Object.assign(params, searchWhere)}).then(res => {
					if (res.data.code == 0) {
						this.dataList = res.data.data.list;
						this.total = Number(res.data.data.total);
						this.pageSize = Number(res.data.data.pageSize);
						this.totalPage = res.data.data.totalPage;
						if(this.pageSizes.length==0){
							this.pageSizes = [this.pageSize, this.pageSize*2, this.pageSize*3, this.pageSize*5];
						}
					}
				});
			},
			sortClick(type){
				if(this.sortType==type){
					if(this.sortOrder == 'desc'){
						this.sortOrder = 'asc'
					}else{
						this.sortOrder = 'desc'
					}
				}else{
					this.sortType = type
					this.sortOrder = 'desc'
				}
				this.getList(1, '全部')
			},
			curChange(page) {
				this.getList(page);
			},
			prevClick(page) {
				this.getList(page);
			},
			sizeChange(size){
				this.pageSize = size
				this.getList(1);
			},
			nextClick(page) {
				this.getList(page);
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
			},
			toDetail(item) {
				let params = {
					id: item.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/nongchanpinxinxiDetail', query: params});
			},
			btnAuth(tableName,key){
				if(this.centerType){
					return this.isBackAuth(tableName,key)
				}else{
					return this.isAuth(tableName,key)
				}
			},
			backClick() {
				this.$router.push({path: '/index/center'});
			},

			discussClick (row){
				let params = {
					refid: row.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/discussnongchanpinxinxi', query: params});
			},
			chapterClick (row){
				let params = {
					refid: row.id
				}
				if(this.centerType){
					params.centerType = 1
				}
				this.$router.push({path: '/index/chapternongchanpinxinxi', query: params});
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.list-preview {
		padding: 0 10%;
		margin: 0px auto;
		align-content: flex-start;
		background: #F2F2F2;
		display: flex;
		width: 100%;
		align-items: flex-start;
		position: relative;
		flex-wrap: wrap;
		.list-form-pv {
			padding: 30px 0;
			margin: 20px auto;
			background: url(http://codegen.caihongy.cn/20251216/36e52c1d98044722ab9c9270207179e6.png) no-repeat center/100% 100%;
			display: flex;
			width: 100%;
			border-color: #ddd;
			border-width: 0px;
			justify-content: center;
			align-items: center;
			border-style: solid;
			flex-wrap: wrap;
			height: auto;
			.list-item {
				margin: 0 5px 0 0;
				::v-deep.el-form-item__content {
					padding: 5px;
					background: #FFF;
					display: flex;
					border-color: #707070;
					border-width: 0 0 1px;
					border-style: solid;
				}
				.lable {
					padding: 0;
					color: #333;
					white-space: nowrap;
					display: inline-block;
					width: auto;
					line-height: 42px;
				}
				.el-input {
					width: 100%;
				}
				.datetimerange {
					border: 0px solid #ddd;
					border-radius: 2px;
					padding: 3px 0px;
					outline: none;
					background: #fff;
					width: auto;
					justify-content: center;
				}
				.el-input ::v-deep .el-input__inner {
					border: none;
					border-radius: 2px;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #333;
					background: none;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-select {
					width: 100%;
				}
				.el-select ::v-deep .el-input__inner {
					border: 0px solid #ddd;
					border-radius: 2px;
					padding: 0 10px;
					margin: 0;
					outline: none;
					color: #333;
					background: none;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor ::v-deep .el-input__inner {
					border: 0px solid #ddd;
					border-radius: 2px;
					padding: 0 30px;
					margin: 0;
					outline: none;
					color: #333;
					background: none;
					width: 140px;
					font-size: 14px;
					line-height: 42px;
					height: 42px;
				}
			}
			.list-btn-box {
				margin: 20px 0;
				display: flex;
				flex-wrap: wrap;
			}
			.list-search-btn {
				cursor: pointer;
				border: 0;
				border-radius: 20px;
				padding: 0px 20px;
				margin: 0 10px 10px 0;
				outline: none;
				color: #fff;
				background: #ABDE53;
				width: auto;
				font-size: 15px;
				line-height: 38px;
				height: 38px;
				.icon {
					margin: 0 3px 0 0;
					color: #fff;
					font-size: 14px;
				}
			}
			.list-add-btn {
				cursor: pointer;
				border: 0;
				border-radius: 20px;
				padding: 0px 20px;
				margin: 0 10px 10px 0;
				outline: none;
				color: #fff;
				background: #ABDE53;
				width: auto;
				font-size: 15px;
				line-height: 38px;
				height: 38px;
				.icon {
					margin: 0 3px 0 0;
					color: #FFF;
					font-size: 14px;
				}
			}
		}
		.sort_view {
			border: 0px solid #ddd;
			padding: 0;
			margin: 10px auto;
			color: #333;
			background: none;
			width: 100%;
			font-size: 16px;
			text-align: center;
			order: 2;
			.price-sort-btn {
				border: 0;
				border-radius: 8px;
				padding: 0 10px;
				margin: 0 5px;
				color: inherit;
				background: none;
				font-size: inherit;
				.icon {
					margin: 0 2px 0 0;
					line-height: 40px;
				}
				.text {
					line-height: 40px;
				}
			}
			.collect-sort-btn {
				border: 0;
				border-radius: 8px;
				padding: 0 10px;
				margin: 0 5px;
				color: inherit;
				background: none;
				font-size: inherit;
				.icon {
					margin: 0 2px 0 0;
					line-height: 40px;
				}
				.text {
					line-height: 40px;
				}
			}
		}
		.list {
			margin: 20px 0 0;
			background: none;
			flex: 1;
			width: 100%;
			order: 4;
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
				
			.index-pv1 .animation-box:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
				
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list5 {
				margin: 0px auto 20px -10px;
				color: #999;
				display: flex;
				width: calc(100% + 20px);
				font-size: 14px;
				flex-wrap: wrap;
				.list-item {
					border: 0px solid #eee;
					border-radius: 14px;
					padding: 0px;
					margin: 0 10px 20px;
					overflow: hidden;
					background: #fff;
					display: block;
					width: calc(20% - 20px);
					position: relative;
					.imgbox {
						padding: 20px;
						overflow: hidden;
						width: auto;
						.image {
							filter: saturate(150%);
							transform: rotate(0deg);
							margin: 0 0 5px;
							object-fit: cover;
							display: block;
							width: 100%;
							opacity: 0.9;
							height: 210px;
						}
					}
					.infoBox {
						padding: 20px ;
						left: 0px;
						bottom: 0;
						background: none;
						width: 100%;
						font-size: 14px;
						position: inherit;
						transition: all 0.5s;
						.name {
							margin: 0 0 10px;
							overflow: hidden;
							color: #000;
							white-space: nowrap;
							width: 100%;
							font-size: 18px;
							line-height: 24px;
							text-overflow: ellipsis;
							text-align: center;
						}
						.price {
							color: #000000;
							width: 100%;
							font-size: 30px;
							text-align: left;
							.price_text {
								color: #000000;
								font-size: 30px;
							}
						}
						.bottomInfo {
							display: flex;
							justify-content: center;
							flex-wrap: wrap;
							.time_item {
								padding: 0;
								margin: 0 10px 0 0;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.publisher_item {
								padding: 0;
								margin: 0 10px 0 0;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.like_item {
								padding: 0;
								margin: 0 10px 0 0;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.collect_item {
								padding: 0;
								margin: 0 10px 0 0;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
							.view_item {
								padding: 0;
								.icon {
									margin: 0 2px 0 0;
									line-height: 1.5;
								}
								.label {
									line-height: 1.5;
								}
								.text {
									line-height: 1.5;
								}
							}
						}
					}
				}
				.list-item:hover {
					cursor: pointer;
					.imgbox {
						.image {
							transform: scale(1.05);
							ilter: saturate(100%);
							opacity: 1;
							transition: all 200ms linear;
						}
					}
					.infoBox {
						bottom: 0px;
						background: #FFFFFF;
						font-size: 14px;
						.name {
						}
						.price {
							.price_text {
							}
						}
						.bottomInfo {
							display: flex;
							justify-content: center;
							flex-wrap: wrap;
							.time_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
							.publisher_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
							.like_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
							.collect_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
							.view_item {
								.icon {
								}
								.label {
								}
								.text {
								}
							}
						}
					}
				}
			}
		}
	}

	.list-preview {
		padding: 24px 28px 48px;
		background: #fff;
		.list-form-pv {
			padding: 24px 28px;
			margin: 0 0 20px;
			background: #fff;
			border: 1px solid #ececec;
			border-radius: 20px;
			justify-content: flex-start;
			gap: 14px;
			box-shadow: 0 10px 24px rgba(15, 23, 42, 0.04);
			.list-item {
				margin: 0;
				::v-deep.el-form-item__content {
					padding: 0;
					background: #fff;
					border: 1px solid #e5e7eb;
					border-radius: 14px;
				}
				.lable {
					padding-left: 14px;
					color: #111827;
					font-weight: 500;
				}
				.el-input ::v-deep .el-input__inner,
				.el-select ::v-deep .el-input__inner {
					width: 180px;
					height: 46px;
					line-height: 46px;
					color: #111827;
				}
			}
			.list-btn-box {
				display: flex;
				gap: 12px;
				align-items: center;
			}
			.list-search-btn,
			.list-add-btn {
				border: 1px solid #111827;
				border-radius: 999px;
				background: #111827;
				color: #fff;
				padding: 0 22px;
				height: 46px;
			}
		}
		.sort_view {
			margin: 0 0 18px;
			width: 100%;
		}
	}
	@media screen and (max-width: 768px) {
		.list-preview {
			padding: 18px;
		}
		.list-preview .list-form-pv {
			padding: 18px;
		}
		.list-preview .list-form-pv .list-item,
		.list-preview .list-form-pv .list-btn-box {
			width: 100%;
		}
		.list-preview .list-form-pv .list-item .el-input ::v-deep .el-input__inner,
		.list-preview .list-form-pv .list-item .el-select ::v-deep .el-input__inner {
			width: 100%;
		}
	}
</style>
