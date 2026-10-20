<template>
	<div class="home-preview">




		<!-- 商品推荐 -->
		<div id="animate_recommendnongchanpinxinxi" class="recommend animate__animated">
			<div class="recommend_title_box">
				<span class="recommend_title">农产品信息推荐</span>
				<span class="recommend_subhead">{{'nongchanpinxinxi'.toUpperCase()}} RECOMMEND</span>
			</div>
			<!-- 样式二 -->
			<div class="list list2 index-pv1">
				<div v-for="(item,index) in nongchanpinxinxiRecommend" class="list-item animation-box" :key="index" @click="toDetail('nongchanpinxinxiDetail', item)">
					<img v-if="preHttp(item.nongchanpinzhaopian)" :src="item.nongchanpinzhaopian.split(',')[0]" alt="" />
					<img v-else :src="baseUrl + (item.nongchanpinzhaopian?item.nongchanpinzhaopian.split(',')[0]:'')" alt="" />
					<div class="item-info">
						<div class="name line1">{{item.nongchanpinmingcheng}}</div>
						<div class="name line1">{{item.nongchanpinleixing}}</div>
						<div class="price">￥{{item.price}}</div>
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
			<div class="moreBtn" @click="moreBtn('nongchanpinxinxi')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-gengduo1"></i>
			</div>
		</div>
		<!-- 商品推荐 -->
		<!-- 特价商品 -->
		<div id="animate_listjishuzhishiku" class="lists animate__animated">
			<div class="list_title_box">
				<span class="list_title">技术知识库展示</span>
				<span class="list_subhead">{{'jishuzhishiku'.toUpperCase()}} SHOW</span>
			</div>
			<!-- 样式二 -->
			<div class="list list2 index-pv1">
				<div v-for="(item,index) in jishuzhishikuList" class="list-item animation-box" :key="index" @click="toDetail('jishuzhishikuDetail', item)">
					<img v-if="preHttp(item.zhishizhaopian)" :src="item.zhishizhaopian.split(',')[0]" alt="" />
					<img v-else :src="baseUrl + (item.zhishizhaopian?item.zhishizhaopian.split(',')[0]:'')" alt="" />
					<div class="item-info">
						<div class="name line1">{{item.zhishimingcheng}}</div>
						<div class="name line1">{{item.zhishifenlei}}</div>
						<div class="time_item">
							<span class="icon iconfont icon-shijian21"></span>
							<span class="label">发布时间：</span>
							<span class="text">{{item.addtime.split(' ')[0]}}</span>
						</div>
						<div class="publisher_item">
							<span class="icon iconfont icon-geren16"></span>
							<span class="label">发布人：</span>
							<span class="text">{{item.gaoxiaomingcheng}}</span>
						</div>
						<div class="collect_item">
							<span class="icon iconfont icon-shoucang10"></span>
							<span class="label">收藏量：</span>
							<span class="text">{{item.storeupnum}}</span>
						</div>
					</div>
				</div>
			</div>
			<div class="moreBtn" @click="moreBtn('jishuzhishiku')">
				<span class="text">查看更多</span>
				<i class="icon iconfont icon-zhankai16"></i>
			</div>
	

		</div>
		<!-- 特价商品 -->
	</div>
</template>

<script>
import 'animate.css'
import Swiper from "swiper";

	export default {
		//数据集合
		data() {
			return {
				baseUrl: '',
				newsList: [],
				nongchanpinxinxiRecommend: [],

				jishuzhishikuList: [],




			}
		},
		created() {
			this.baseUrl = this.$config.baseUrl;
			this.getList();
		},
		mounted() {
			window.addEventListener('scroll', this.handleScroll)
			setTimeout(()=>{
				this.handleScroll()
			},100)
			
			this.swiperChanges()
		},
		beforeDestroy() {
			window.removeEventListener('scroll', this.handleScroll)
		},
		computed: {
			username() {
				return localStorage.getItem('username')
			}
		},
		//方法集合
		methods: {
			swiperChanges() {
				setTimeout(()=>{
				},750)
			},


			handleScroll() {
				let arr = [
					{id:'about',css:'animate__'},
					{id:'system',css:'animate__'},
					{id:'animate_listjishuzhishiku',css:'animate__'},
					{id:'animate_recommendnongchanpinxinxi',css:'animate__'},
				]
			
				for (let i in arr) {
					let doc = document.getElementById(arr[i].id)
					if (doc) {
						let top = doc.offsetTop
						let win_top = window.innerHeight + window.pageYOffset
						// console.log(top,win_top)
						if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
							// console.log(doc)
							doc.classList.add(arr[i].css)
						}
					}
				}
			},
			preHttp(str) {
				return str && str.substr(0,4)=='http';
			},
			preHttp2(str) {
				return str && str.split(',w').length>1;
			},
			getList() {
				let autoSortUrl = "";
				let data = {}
				autoSortUrl = "nongchanpinxinxi/autoSort";
				if(localStorage.getItem('frontToken')) {
					autoSortUrl = "nongchanpinxinxi/autoSort2";
				}
				data = {
					page: 1,
					limit: 6,
					onshelves: 1,
				}
				this.$http.get(autoSortUrl, {params: data}).then(res => {
					if (res.data.code == 0) {
						this.nongchanpinxinxiRecommend = res.data.data.list;
					}
				});
			
				data = {
					page: 1,
					limit: 5,
				}

				this.$http.get('jishuzhishiku/list', {params: data}).then(res => {
					if (res.data.code == 0) {
						this.jishuzhishikuList = res.data.data.list;
					}
				});
			},
			toDetail(path, item) {
				this.$router.push({path: '/index/' + path, query: {id: item.id, storeupType: 1}});
			},
			moreBtn(path) {
				this.$router.push({path: '/index/' + path});
			}
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.home-preview {
		margin: 0 auto;
		overflow: hidden;
		display: flex;
		width: 100%;
		justify-content: space-between;
		flex-wrap: wrap;
		.recommend {
			padding: 20px 10% 60px;
			margin: 0 auto 0;
			background: url(http://codegen.caihongy.cn/20251215/50d99423b8904b1691da6dcb45ec88af.png)no-repeat;
			width: 100%;
			position: relative;
			order: 4;
			.recommend_title_box {
				padding: 50px ;
				margin: 20px auto;
				background: url(http://codegen.caihongy.cn/20251216/09375e11e7514aa59e4c60da9de5e935.png) no-repeat center/100% 100%;
				display: flex;
				width: 100%;
				justify-content: flex-start;
				flex-wrap: wrap;
				.recommend_title {
					padding: 0;
					color: #FFFFFF;
					background: none;
					display: block;
					width: 100%;
					font-size: 18px;
					line-height: 1.5;
					text-align: left;
					order: 2;
				}
				.recommend_subhead {
					margin: 0;
					color: #FFFFFF;
					font-weight: 500;
					display: block;
					width: auto;
					font-size: 30px;
					line-height: 30px;
					text-align: left;
					order: 1;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -4px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list2 {
				margin: -50px 0 0 0px;
				color: #666;
				background: none;
				display: flex;
				width: calc(100%);
				font-size: 14px;
				flex-wrap: wrap;
				height: auto;
				.list-item {
					cursor: pointer;
					border-radius: 20px;
					padding: 20px;
					margin: 0 10px 20px;
					background: #fff;
					display: flex;
					width: calc(50% - 20px);
					position: relative;
					height: auto;
					img {
						object-fit: cover;
						display: inline-block;
						width: 200px;
						height: 200px;
					}
					.item-info {
						padding: 10px;
						overflow: hidden;
						align-content: center;
						flex: 1;
						display: flex;
						width: 280px;
						align-items: center;
						flex-wrap: wrap;
						height: auto;
						.name {
							padding: 0 10px;
							overflow: hidden;
							color: #333;
							white-space: nowrap;
							font-weight: 600;
							width: 100%;
							font-size: 16px;
							line-height: 32px;
							text-overflow: ellipsis;
						}
						.price {
							padding: 0 10px;
							margin: 10px 0;
							color: #000000;
							font-weight: bold;
							width: 100%;
							font-size: 30px;
							line-height: 30px;
						}
						.time_item {
							padding: 0 10px;
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
							padding: 0 10px;
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
							padding: 0 10px;
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
							padding: 0 10px;
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
							padding: 0 10px;
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
			.moreBtn {
				border: 0px solid #ddd;
				cursor: pointer;
				border-radius: 0px;
				margin: 0px auto 0;
				top: 0;
				background: #6aac5a;
				display: none;
				width: 120px;
				line-height: 40px;
				justify-content: center;
				position: absolute;
				right: 7%;
				.text {
					margin: 0 5px 0 0;
					color: #fff;
					font-size: 16px;
					order: 2;
				}
				.icon {
					margin: 0 10px 0 0;
					color: #fff;
					display: none;
					width: auto;
					font-size: 18px;
					height: 7px;
				}
			}
		}
		.lists {
			padding: 30px 10% 40px;
			margin: 0 auto;
			background: url(http://codegen.caihongy.cn/20251215/50d99423b8904b1691da6dcb45ec88af.png) no-repeat;
			width: 100%;
			position: relative;
			order: 1;
			.list_title_box {
				padding: 50px ;
				margin: 20px auto;
				background: url(http://codegen.caihongy.cn/20251219/f2e3dab977e74fa494a6301b27cb1d95.png) no-repeat center/100% 100%;
				display: flex;
				width: 100%;
				justify-content: flex-start;
				flex-wrap: wrap;
				.list_title {
					padding: 0px 0 0 0;
					color: #BFF267;
					background: none;
					display: block;
					width: 100%;
					font-size: 18px;
					line-height: 1.5;
					text-align: left;
					order: 2;
				}
				.list_subhead {
					margin: 0;
					color: #fff;
					font-weight: 500;
					display: block;
					width: 100%;
					font-size: 30px;
					line-height: 2;
					text-align: left;
					order: 1;
				}
			}
			.index-pv1 .animation-box {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				z-index: initial;
			}
			
			.index-pv1 .animation-box:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, -5px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
				z-index: 1;
			}
			
			.index-pv1 .animation-box img {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
			}
			
			.index-pv1 .animation-box img:hover {
				transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0px, 0px);
				-webkit-perspective: 1000px;
				perspective: 1000px;
				transition: 0.3s;
			}
			.list2 {
				padding: 20px 2% 60px;
				margin: -20px 0 0 0px;
				background: #FFF;
				display: flex;
				width: calc(100%);
				justify-content: space-between;
				position: relative;
				flex-wrap: wrap;
				height: auto;
				.list-item {
					cursor: pointer;
					border: 0px solid #FFFFFF;
					border-radius: 14px;
					margin: 20px 40px 20px;
					background: none;
					display: block;
					width: calc(25% - 80px);
					position: relative;
					height: auto;
					img {
						object-fit: cover;
						display: inline-block;
						width: 100%;
						height: 300px;
					}
					.item-info {
						padding: 20px 20px 30px;
						overflow: hidden;
						align-content: flex-end;
						left: 0;
						bottom: 0;
						background: url(http://codegen.caihongy.cn/20251219/2eb5232cdc35497298853407649e6a2e.png) no-repeat bottom /100% 30%;
						display: flex;
						width: 100%;
						align-items: flex-end;
						position: absolute;
						flex-wrap: wrap;
						height: 100%;
						.name {
							padding: 0 10px;
							margin: 0 0 10px;
							overflow: hidden;
							color: #000;
							white-space: nowrap;
							background: url(http://codegen.caihongy.cn/20251219/e0adac1ca601475f9eca711f8f5babf1.png) no-repeat center/100% 100%;
							font-weight: 500;
							width: 100%;
							font-size: 18px;
							text-overflow: ellipsis;
							text-align: center;
						}
						.price {
							border-radius: 20px;
							padding: 0 10px;
							margin: 0 auto;
							color: #FFF;
							background: #E80015;
							display: none;
							width: 80%;
							font-size: 16px;
							line-height: 2;
							text-align: center;
						}
						.time_item {
							padding: 0 10px;
							display: none;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.publisher_item {
							padding: 0 10px;
							display: none;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.like_item {
							padding: 0 10px;
							display: none;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.collect_item {
							padding: 0 10px;
							display: none;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
						.view_item {
							padding: 0 10px;
							display: none;
							.icon {
								margin: 0 2px 0 0;
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.label {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
							.text {
								color: #666;
								font-size: 12px;
								line-height: 1.5;
							}
						}
					}
				}
			}
			.moreBtn {
				border: 0px solid #ddd;
				cursor: pointer;
				margin: 0px auto;
				background-size: 100% 100%;
				bottom: 40px;
				display: block;
				line-height: 40px;
				border-radius: 0px;
				background: url(http://codegen.caihongy.cn/20251215/641e07a045254367a1ab75ae2949c1ab.png) no-repeat;
				width: 80%;
				justify-content: center;
				position: absolute;
				text-align: center;
				.text {
					margin: 0 5px 0 0;
					color: #BFF267;
					font-size: 16px;
					order: 2;
				}
				.icon {
					color: #BFF267;
					background: none;
					display: block;
					width: 23px;
					font-size: 16px;
					height: 7px;
				}
			}
		}
	}

	.home-preview {
		background: #fff;
		.recommend,
		.lists {
			padding: 36px 28px 28px;
			background: #fff;
			width: 100%;
			.recommend_title_box,
			.list_title_box {
				padding: 0 0 18px;
				margin: 0 auto 18px;
				background: #fff;
				border-bottom: 1px solid #ececec;
				.recommend_title,
				.list_title {
					color: #111827;
					font-size: 28px;
					font-weight: 700;
				}
				.recommend_subhead,
				.list_subhead {
					margin-top: 6px;
					color: #94a3b8;
					font-size: 13px;
					line-height: 1.6;
					letter-spacing: 0;
				}
			}
		}
		.recommend {
			.list2 {
				margin: 0;
				.list-item {
					border: 1px solid #ececec;
					border-radius: 18px;
					background: #fff;
					box-shadow: 0 10px 24px rgba(15, 23, 42, 0.04);
					.item-info {
						.name,
						.price,
						.time_item,
						.publisher_item,
						.collect_item {
							color: #334155;
						}
						.price {
							color: #111827;
						}
					}
				}
			}
		}
		.lists {
			.list2 {
				padding: 0;
				margin: 0;
				background: #fff;
				.list-item {
					border: 1px solid #ececec;
					border-radius: 18px;
					background: #fff;
					box-shadow: 0 10px 24px rgba(15, 23, 42, 0.04);
					margin: 12px;
					width: calc(25% - 24px);
					img {
						border-radius: 18px 18px 0 0;
					}
					.item-info {
						position: static;
						background: #fff;
						padding: 14px 16px 20px;
						height: auto;
						.name {
							background: none;
							color: #111827;
							padding: 0;
							margin: 0 0 8px;
							text-align: left;
						}
					}
				}
			}
		}
		.moreBtn {
			position: static !important;
			width: 160px !important;
			margin: 18px 0 0 auto !important;
			background: #fff !important;
			border: 1px solid #d1d5db !important;
			border-radius: 999px !important;
			.text,
			.icon {
				color: #111827 !important;
			}
		}
	}

	@media screen and (min-width: 1280px) {
		.home-preview .lists .list2 {
			display: grid;
			grid-template-columns: repeat(3, minmax(0, 1fr));
			gap: 24px;
		}
		.home-preview .lists .list2 .list-item {
			width: 100%;
			margin: 0;
		}
	}
	@media screen and (max-width: 1279px) {
		.home-preview .lists .list2 {
			display: grid;
			grid-template-columns: repeat(2, minmax(0, 1fr));
			gap: 20px;
		}
		.home-preview .lists .list2 .list-item {
			width: 100%;
			margin: 0;
		}
	}
	@media screen and (max-width: 768px) {
		.home-preview .lists .list2 {
			grid-template-columns: 1fr;
		}
	}
</style>
