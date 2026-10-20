<template>
	<div class="main-containers">
		<div class="body-containers">
			<div class="top-container">
				<!-- info -->
				<div class="top_title">
					<img class="site-logo" :src="projectLogoUrl" alt="高校助农互联网商城logo" @click="goMenu('/index/home')">
					<span @click="goMenu('/index/home')">高校助农互联网商城</span>
				</div>
				<div class="top_tel"></div>
			

				<el-dropdown class="dropdown-box" @command="handleCommand" trigger="click">
					<div class="el-dropdown-link" v-show="Token">
						<img class="top_avatar2" v-show="headportrait&&Token" :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
						<span class="top_label2">欢迎</span>
						<span class="top_nickname2">{{username}}</span>
						<span class="icon iconfont icon-xiala"></span>
					</div>
					<div class="el-dropdown-link" v-show="!Token">
						<div class="login-item" @click="toLogin">
							<span class="icon iconfont icon-touxiang03"></span>
							登录
						</div>
					</div>
					<el-dropdown-menu class="top-el-dropdown-menu" slot="dropdown" v-show="Token">
						<el-dropdown-item v-show="notAdmin" class="user-item" :command="'user'">
							<span class="icon iconfont icon-touxiang09"></span>
							个人中心
						</el-dropdown-item>
						<el-dropdown-item class="register-item" :command="'register'">
							<span class="icon iconfont icon-shanchu16"></span>
							退出
						</el-dropdown-item>
					</el-dropdown-menu>
				</el-dropdown>
			</div>


			<div class="menu-preview">
				<el-scrollbar wrap-class="scrollbar-wrapper-horizontal">
					<el-menu class="el-menu-horizontal-demo" :default-active="activeMenu" :unique-opened="true" mode="horizontal" :router="true" @select="handleSelect">
						<div class="userinfo">
						  <el-image v-if="headportrait&&Token" :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')" fit="cover"></el-image>
						  <div class="nickname">{{username}}</div>
						</div>
						<el-menu-item class="home" index="/index/home" @click.native="goMenu('/index/home')">
							<span class="icon iconfont icon-shouye-zhihui"></span>
							<span>系统首页</span>
						</el-menu-item>
						<el-menu-item  class="item" v-for="(menu, index) in menuList" :index="menu.url" :key="index" @click.native="goMenu(menu.url)">
							<i class="icons" :class="iconArr[index]"></i>
							<span>{{menu.name}}</span>
						</el-menu-item>
						<el-menu-item class="item" v-if="Token" index="/index/cart" @click.native="goMenu('/index/cart')">
							<span class="icon iconfont icon-wuliu8"></span>
							<span>购物车</span>
						</el-menu-item>
					</el-menu>
				</el-scrollbar>
			</div>

			<div class="banner-preview" v-if="carouselChange()">
				<div class="swiper-container mySwiper3">
					<div class="swiper-wrapper">
						<div class="swiper-slide" v-for="item in carouselList" :key="item.id">
							<div class="swiper-item">
								<el-image v-if="preHttp(item.value)" @click="carouselClick(item.url)" :src="item.value" fit="cover"></el-image>
								<el-image v-else @click="carouselClick(item.url)" :src="baseUrl + item.value" fit="cover"></el-image>
							</div>
						</div>
					</div>
					<div class="banner-hidden">
					</div>
					<!-- Add Pagination -->
					<div class="swiper-pagination"></div>
					<!-- Add Arrows -->
					<div class="swiper-button-next">
						<span class="icon iconfont icon-jiantou18"></span>
					</div>
					<div class="swiper-button-prev">
						<span class="icon iconfont icon-jiantou39"></span>
					</div>
				</div>
			</div>
			<router-view id="scrollView"></router-view>
			
			<div class="bottom-preview">
				<div class="footer"><div v-html="bottomContent"></div></div>
			</div>
		</div>
		
		<div class="audioAnimation-box" :class="showType?'audioAnimation-box1':''" v-if="audio.length"
			:style="{'transform':audio[audioIndex].lrc&&audio[audioIndex].lrc!=''?'rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 98px, 0px)':'rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 68px, 0px)'}"
			style="width: 100%;position: fixed;bottom: 0;left: 0;z-index: 99999;" @mouseover="showmouseover">
			<div @click="suoClick"
				style="position: absolute;top: -20px;right: 40%;background: #fff;border-radius: 50% 50% 0 0;font-size: 0;width: 30px;height: 30px;display: flex;justify-content: center;align-items: center;cursor: pointer;">
				<img v-if="suoType" style="width: 20px;height: 20px;" src="../assets/suo.png">
				<img v-else style="width: 20px;height: 20px;" src="../assets/jiesuo.png">
			</div>
			<aplayer :float="true" :volume="1" repeat="repeat-all" ref="aplayer" id="aplayer" :music="audio[audioIndex]" @timeupdate="timeChange" @play="playing"
				:list="audio" :showLrc="audio[audioIndex].lrc&&audio[audioIndex].lrc!=''?true:false" :listFolded="true" :autoplay="true" listMaxHeight="180px" :lrcType="1"></aplayer>
		</div>
	</div>
</template>

<script>
	import Vue from 'vue'
	import Swiper from "swiper";
	import axios from 'axios'
	const assetBase = `${window.location.origin}/zhunong79djy629/upload/`
export default {
	data() {
		return {
			activeIndex: '0',
			baseUrl: '',
			carouselList: [
				{id: 1, value: `${assetBase}food0.jpg`, url: ''},
				{id: 2, value: `${assetBase}food1.jpg`, url: ''},
				{id: 3, value: `${assetBase}food2.jpg`, url: ''},
				{id: 4, value: `${assetBase}food3.jpg`, url: ''},
			],
			carouselForm: {
				inHome: true,
				inOther: true,
			},
			mySwiper3Timer: null,
			menuList: [],
			headportrait: localStorage.getItem('frontHeadportrait')?localStorage.getItem('frontHeadportrait'):'',
			Token: localStorage.getItem('frontToken'),
			username: localStorage.getItem('username'),
			notAdmin: localStorage.getItem('frontSessionTable')!='"users"',
			iconArr: [
				'el-icon-star-off',
				'el-icon-goods',
				'el-icon-warning',
				'el-icon-question',
				'el-icon-info',
				'el-icon-help',
				'el-icon-picture-outline-round',
				'el-icon-camera-solid',
				'el-icon-video-camera-solid',
				'el-icon-video-camera',
				'el-icon-bell',
				'el-icon-s-cooperation',
				'el-icon-s-order',
				'el-icon-s-platform',
				'el-icon-s-operation',
				'el-icon-s-promotion',
				'el-icon-s-release',
				'el-icon-s-ticket',
				'el-icon-s-management',
				'el-icon-s-open',
				'el-icon-s-shop',
				'el-icon-s-marketing',
				'el-icon-s-flag',
				'el-icon-s-comment',
				'el-icon-s-finance',
				'el-icon-s-claim',
				'el-icon-s-opportunity',
				'el-icon-s-data',
				'el-icon-s-check'
			],
			bottomContent: '',
			musicType: false,
			showTimer: null,
			showType: false,
			suoType:false,
			indexBgUrl: '',
			indexLogoUrl: '',
			defaultLogoUrl: `${window.location.origin}/zhunong79djy629/upload/project-logo.png`,
		}
	},
	async created() {
		this.$http.get('config/info?name=fTopLogo',).then(rs=>{this.indexLogoUrl = rs.data.data?rs.data.data.value:''})
		this.baseUrl = this.$config.baseUrl;
		this.menuList = this.$config.indexNav;
		if(localStorage.getItem('frontToken') && localStorage.getItem('frontToken')!=null) {
			this.getSession()
		}
	},
	mounted() {
		this.activeIndex = localStorage.getItem('keyPath') || '0';
		this.musicType = localStorage.getItem('musicType') ? true : false;


		// banner
		setTimeout(()=>{
			this.mySwiper3Timer = new Swiper(".mySwiper3", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"pagination":{"el":".swiper-pagination","clickable":true},"slidesPerView":3,"speed":300,"autoplay":{"delay":2500,"disableOnInteraction":false},"effect":"fade"})
		}, 500)

	},
	computed: {
		projectLogoUrl() {
			return this.indexLogoUrl ? `${this.baseUrl}${this.indexLogoUrl}` : this.defaultLogoUrl
		},
		activeMenu() {
			const route = this.$route
			const {
				meta,
				path
			} = route
			// if st path, the sidebar will highlight the path you sete
			if (meta.activeMenu) {
				return meta.activeMenu
			}
			return path
		},
		audioIndex: {
			get() {
				return this.$store.state.app.audioIndex
			},
			set(val) {
				return this.$store.state.app.audioIndex = val
			}
		},
		audio: {
			get() {
				return this.$store.state.app.audio
			},
			set(val) {
				return this.$store.state.app.audio = val
			}
		},
	},
	watch: {
		$route(newValue) {
			let that = this
			let url = window.location.href
			let arr = url.split('#')
			for (let x in this.menuList) {
				if (newValue.path == this.menuList[x].url) {
					this.activeIndex = x
				}
			}
			this.Token = localStorage.getItem('frontToken')
			if(arr[1]!='/index/home'){
				var element = document.getElementById('scrollView');
				var distance = element.offsetTop;
				window.scrollTo( 0, distance )
			}else{
				window.scrollTo( 0, 0 )
			}
		},
		headportrait(){
			this.$forceUpdate()
		},
		audio(newValue) {
			this.audioIndex = this.$store.state.app.audioIndex
		},
		audioIndex() {
			this.showmouseover()
			this.$nextTick(() => {
				this.$refs.aplayer.play()
			})
			this.$forceUpdate()
		},
	},
	methods: {
		preHttp(str) {
			return str && str.substr(0,4)=='http';
		},

		async getSession() {
			await this.$http.get(`${localStorage.getItem('UserTableName')}/session`, {emulateJSON: true}).then(async res => {
				if (res.data.code == 0) {
					localStorage.setItem('sessionForm',JSON.stringify(res.data.data))
					localStorage.setItem('frontUserid', res.data.data.id);
					if(res.data.data.vip) {
						localStorage.setItem('vip', res.data.data.vip);
					}
					if(res.data.data.touxiang) {
						this.headportrait = res.data.data.touxiang
						localStorage.setItem('frontHeadportrait', res.data.data.touxiang);
					} else if(res.data.data.headportrait) {
						this.headportrait = res.data.data.headportrait
						localStorage.setItem('frontHeadportrait', res.data.data.headportrait);
					}
				}
			});
		},
		handleSelect(keyPath) {
			if (keyPath) {
				localStorage.setItem('keyPath', keyPath)
			}
		},
		toLogin() {
		  this.$router.push('/login');
		},
		logout() {
			localStorage.clear();
			Vue.http.headers.common['Token'] = "";
			this.$router.push('/index/home');
			this.activeIndex = '0'
			localStorage.setItem('keyPath', this.activeIndex)
			this.Token = ''
			this.$forceUpdate()
			this.$message({
				message: '登出成功',
				type: 'success',
				duration: 1000,
			});
		},
		getCarousel() {},
		// 轮播图跳转
		carouselClick(url) {
			if (url) {
				if (url.indexOf('https') != -1) {
					window.open(url)
				} else {
					this.$router.push(url)
				}
			}
		},
		carouselChange(){
			let url = window.location.href
			let arr = url.split('#')
			return this.carouselList.length && ((this.carouselForm.inHome&&arr[1]=='/index/home')||(this.carouselForm.inOther&&arr[1]!='/index/home'))
		},
		goMenu(path) {
			this.$router.push(path);
		},
		handleCommand(name){
			if(name == 'register') {
				this.logout()
			}
			else if (name == 'shop') {
				this.goMenu('/index/cart')
			}
			else if (name == 'user'){
				this.goMenu('/index/center')
			}
			else if (name == 'login'){
				this.toLogin()
			}
		},
		suoClick(){
			this.suoType = !this.suoType
			if(this.suoType){
				clearTimeout(this.showTimer)
			}else{
				this.showmouseover()
			}
		},
		showmouseover() {
			if(this.suoType){
				return false
			}
			let that = this
			clearTimeout(this.showTimer)
			this.showType = true
			this.showTimer = setTimeout(() => {
				that.$refs.aplayer.showList = false;
				that.showType = false

			}, 6000)
		},
		timeChange(e){
			let user = JSON.parse(localStorage.getItem('sessionForm'))
			if(e.target.currentTime>30&&this.audio[this.audioIndex].isfree==0&&(!user||!user.vip||(user.vip&&user.vip!='是'))){
				this.$refs.aplayer.pause()
				if(this.audioIndex==this.audio.length - 1){
					this.$store.state.app.audioIndex = 0
				}else{
					this.$store.state.app.audioIndex++
				}
			}
		},
		playing(e){
			for(let x in this.audio) {
				if(this.audio[x].id == this.$refs.aplayer.currentMusic.id) {
					this.$store.state.app.audioIndex = Number(x)
					break
				}
			}
		},
	}
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.top-el-dropdown-menu {
		border: 1px solid #EBEEF5;
		border-radius: 4px;
		padding: 10px 0;
		box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
		margin: 18px 0;
		background: #FFF;
		.shop-item {
			border: 0;
			padding: 0 8px;
			margin: 0 10px;
			color: #333;
			background: #efefef;
			width: auto;
			font-size: 14px;
			line-height: 32px;
			height: 32px;
			.icon {
				color: inherit;
				font-size: 14px;
			}
		}
		.shop-item:hover {
			color: #CA1F27;
			background: none;
		}
		.user-item {
			border: 0;
			padding: 0 8px;
			margin: 0 10px;
			color: #333;
			background: none;
			width: auto;
			font-size: 14px;
			line-height: 32px;
			height: 32px;
			.icon {
				color: inherit;
				font-size: 14px;
			}
		}
		.user-item:hover {
			color: #CA1F27;
			background: none;
		}
		.register-item {
			border: 0;
			padding: 0 8px;
			margin: 0 10px;
			color: #333;
			background: none;
			width: auto;
			font-size: 14px;
			line-height: 32px;
			height: 32px;
			.icon {
				color: inherit;
				font-size: 14px;
			}
		}
		.register-item:hover {
			color: #CA1F27;
			background: none;
		}
	}
	.main-containers {
		.body-containers {
			padding: 50px 0 0;
			margin: 0;
			background: #F2F2F2;
			min-height: 100vh;
			position: relative;
			.top-container {
				padding: 0 10% 0 10%;
				z-index: 1002;
				color: #333;
				display: flex;
				font-size: 16px;
				box-shadow: 0 0px 0px #cfd8dc;
				background-repeat: no-repeat;
				top: 0;
				background: url(http://codegen.caihongy.cn/20251215/128cb9a48591482ca3a1dc5043346722.png);
				width: 100%;
				padding: 0 28px;
				justify-content: flex-end;
				align-items: center;
				position: fixed;
				height: 74px;
				box-shadow: 0 8px 20px rgba(53, 89, 34, 0.08);
				.top_title {
					padding: 0;
					margin: 0 auto 0 0;
					display: flex;
					align-items: center;
					gap: 14px;
					.site-logo {
						width: 62px;
						height: 62px;
						min-width: 62px;
						object-fit: contain;
						border-radius: 50%;
						cursor: pointer;
					}
					span {
						padding: 0;
						color: #16341a;
						font-weight: 600;
						font-size: 22px;
						line-height: 1.3;
						cursor: pointer;
					}
				}
				.top_tel {
					margin: 0 10px;
					color: inherit;
					font-size: inherit;
				}
				.dropdown-box {
					color: inherit;
					display: flex;
					font-size: inherit;
					.el-dropdown-link {
						display: flex;
						align-items: center;
						.top_avatar2 {
							border-radius: 100%;
							margin: 0 10px;
							object-fit: cover;
							display: inline-block;
							width: 36px;
							height: 36px;
						}
						.top_label2 {
							color: inherit;
							font-size: inherit;
							line-height: 32px;
						}
						.top_nickname2 {
							color: inherit;
							font-size: inherit;
							line-height: 32px;
						}
						.icon {
							margin: 0 0 0 5px;
							color: inherit;
							font-size: inherit;
						}
						.login-item {
							border: 0;
							padding: 0 8px;
							margin: 0 10px;
							color: inherit;
							background: none;
							width: auto;
							font-size: inherit;
							line-height: 32px;
							height: 32px;
							.icon {
								margin: 0 3px 0 0;
								color: inherit;
								font-size: inherit;
							}
						}
						.login-item:hover {
							cursor: pointer;
							color: #CA1F27;
						}
					}
				}
			}
			.menu-preview {
				.el-scrollbar {
					height: 100%;
			  
					& /deep/ .scrollbar-wrapper-vertical {
						overflow-x: hidden;
					}
			  
					& /deep/ .scrollbar-wrapper-horizontal {
						overflow-y: hidden;
			  
						.el-scrollbar__view {
							white-space: nowrap;
						}
					}
				}
				padding: 0 20px;
				background: #ABDE53;
				width: 100%;
				border-color: #efefef;
				border-width: 0 0 1px 0;
				border-style: solid;
				margin-top: 74px;
				height: auto;
				.el-menu-horizontal-demo {
					border: 0;
					padding: 0;
					margin: 0;
					background: #ABDE53;
					display: flex;
					position: relative;
					list-style: none;
					.userinfo {
						padding: 6px 10px 0;
						display: none;
						width: 84px;
						height: auto;
						.el-image {
							border-radius: 20px;
							object-fit: cover;
							display: block;
							width: 100%;
							height: 32px;
						}
						.nickname {
							color: #333;
							font-size: 12px;
							line-height: 1.5;
							text-align: center;
						}
					}
					// 首页
					.el-menu-item.home {
						cursor: pointer;
						border: 0;
						padding: 0 20px;
						color: #FFF;
						white-space: nowrap;
						display: flex;
						font-size: 14px;
						line-height: 56px;
						background: none;
						align-items: center;
						position: relative;
						list-style: none;
						height: 56px;
					}
					.el-menu-item.home .icon {
						padding: 0 10px;
						margin: 0 10px 0 0;
						color: inherit;
						display: none;
						width: 14px;
						font-size: 14px;
						line-height: 56px;
						height: 56px;
					}
					
					.el-menu-item.home:hover {
						color: #fff;
						background: #97CA3F;
					}
					
					.el-menu-item.home.is-active {
						color: #fff;
						background: #97CA3F;
					}
					// 其他盒子
					.el-menu-item.item {
						cursor: pointer;
						border: 0;
						padding: 0 20px;
						color: #FFF;
						white-space: nowrap;
						display: flex;
						font-size: 14px;
						line-height: 56px;
						background: none;
						align-items: center;
						position: relative;
						list-style: none;
						height: 56px;
					}
					.el-menu-item.item .icons,.el-menu-item.item .iconfont {
						padding: 0 10px;
						margin: 0 10px 0 0;
						color: inherit;
						width: 14px;
						font-size: 14px;
						line-height: 56px;
						height: 56px;
					}
					
					.el-menu-item.item:hover {
						color: #fff;
						background: #97CA3F;
					}
					
					.el-menu-item.item.is-active {
						color: #fff;
						background: #97CA3F;
					}
				}
			}
			.banner-preview {
				padding: 0;
				margin: 0 auto;
				width: 100%;
				position: relative;
				height: 500px;
				.swiper-button-prev:after {
					display:none;
				}
				.swiper-button-next:after {
					display:none;
				}
				.swiper-slide {
					.swiper-item {
						width: 100%;
						height: 500px;
						.el-image {
							object-fit: cover;
							width: 100%;
							height: 500px;
						}
					}
				}
				@keyframes wave1 {from { left: -236px } to { left: -1233px }}
				@keyframes wave2 {from { left: 0 } to { left: -1009px }}
				.swiper-pagination {
					left: 0;
					bottom: 10px;
					width: 100%;
					/deep/ span.swiper-pagination-bullet {
						border-radius: 100%;
						margin: 0 4px;
						background: #000;
						display: inline-block;
						width: 8px;
						opacity: .2;
						height: 8px;
					}
					/deep/ span.swiper-pagination-bullet:hover {
						background: #fff;
						opacity: 1;
					}
					/deep/ span.swiper-pagination-bullet.swiper-pagination-bullet-active {
						background: #fff;
						opacity: 1;
					}
				}
				.swiper-button-next {
					margin: -12px 10% 0 0;
					top: 50%;
					.icon {
						border-radius: 100%;
						padding: 0 16px;
						color: #fff;
						background: #124b53;
						display: inline-block;
						width: auto;
						font-size: 20px;
						line-height: 48px;
						opacity: 0.6;
						height: 48px;
					}
				}
				.swiper-button-prev {
					margin: -12px 0 0 10%;
					top: 50%;
					.icon {
						border-radius: 100%;
						padding: 0 16px;
						color: #fff;
						background: #124b53;
						display: inline-block;
						width: auto;
						font-size: 20px;
						line-height: 48px;
						opacity: 0.6;
						height: 48px;
					}
				}
			}
			.bottom-preview {
				width: 100%;
				height: auto;
				.footer {
					padding: 20px 10%;
					color: #fff;
					display: flex;
					font-size: 16px;
					min-height: 170px;
					overflow: hidden;
					align-content: center;
					background: #BFF267;
					width: 100%;
					justify-content: center;
					align-items: center;
					text-align: center;
					height: auto;
				}
			}
		}
	}
	.audioAnimation-box {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 68px, 0px);
		-webkit-perspective: 1000px;
		perspective: 1000px;
		transition: 0.3s;
	}

	.audioAnimation-box1 {
		transform: rotate(0deg) scale(1) skew(0deg, 0deg) translate3d(0px, 0, 0px) !important;
	}

	.main-containers .body-containers .top-container,
	.main-containers .body-containers .menu-preview,
	.main-containers .body-containers .bottom-preview .footer {
		background: #fff;
	}
	.main-containers .body-containers .top-container {
		border-bottom: 1px solid #ececec;
		box-shadow: none;
	}
	.main-containers .body-containers .menu-preview {
		border-bottom: 1px solid #ececec;
		margin-top: 0;
	}
	.main-containers .body-containers .menu-preview .el-menu-horizontal-demo {
		background: #fff;
	}
	.main-containers .body-containers .menu-preview .el-menu-horizontal-demo .el-menu-item.home,
	.main-containers .body-containers .menu-preview .el-menu-horizontal-demo .el-menu-item.item {
		color: #334155;
	}
	.main-containers .body-containers .menu-preview .el-menu-horizontal-demo .el-menu-item.home:hover,
	.main-containers .body-containers .menu-preview .el-menu-horizontal-demo .el-menu-item.item:hover,
	.main-containers .body-containers .menu-preview .el-menu-horizontal-demo .el-menu-item.home.is-active,
	.main-containers .body-containers .menu-preview .el-menu-horizontal-demo .el-menu-item.item.is-active {
		color: #111827;
		background: #f5f5f5;
	}
	.main-containers .body-containers .banner-preview {
		padding: 28px 28px 10px;
		background: #fff;
		height: auto;
	}
	.main-containers .body-containers .banner-preview .swiper-slide .swiper-item,
	.main-containers .body-containers .banner-preview .swiper-slide .swiper-item .el-image {
		height: 420px;
		border-radius: 28px;
	}
	.main-containers .body-containers .banner-preview .swiper-button-next .icon,
	.main-containers .body-containers .banner-preview .swiper-button-prev .icon {
		background: rgba(17,24,39,.78);
	}
	.main-containers .body-containers .bottom-preview .footer {
		color: #475569;
		border-top: 1px solid #ececec;
		min-height: 96px;
	}

	.main-containers {
		.body-containers {
			padding-top: 74px;
			background: #fff;
			.top-container {
				background: #fff;
				box-shadow: none;
				border-bottom: 1px solid #ececec;
				.top_title {
					span {
						color: #1f2933;
						font-size: 18px;
						font-weight: 600;
					}
					.site-logo {
						width: 52px;
						height: 52px;
						min-width: 52px;
					}
				}
				.dropdown-box {
					color: #1f2933;
				}
			}
			.menu-preview {
				margin-top: 0;
				padding: 0 28px;
				background: #fff;
				border-bottom: 1px solid #ececec;
				.el-menu-horizontal-demo {
					background: #fff;
					.el-menu-item.home,
					.el-menu-item.item {
						color: #334155;
						height: 54px;
						line-height: 54px;
						border-radius: 0;
					}
					.el-menu-item.home:hover,
					.el-menu-item.item:hover,
					.el-menu-item.home.is-active,
					.el-menu-item.item.is-active {
						color: #111827;
						background: #f5f5f5;
					}
				}
			}
			.banner-preview {
				padding: 24px 28px 8px;
				background: #fff;
				height: auto;
				.swiper-slide .swiper-item,
				.swiper-slide .swiper-item .el-image {
					height: 360px;
					border-radius: 24px;
				}
				.swiper-pagination /deep/ span.swiper-pagination-bullet,
				.swiper-pagination /deep/ span.swiper-pagination-bullet:hover,
				.swiper-pagination /deep/ span.swiper-pagination-bullet.swiper-pagination-bullet-active {
					background: #111827;
					opacity: .35;
				}
				.swiper-pagination /deep/ span.swiper-pagination-bullet.swiper-pagination-bullet-active {
					opacity: 1;
				}
				.swiper-button-next .icon,
				.swiper-button-prev .icon {
					background: rgba(17,24,39,.78);
					color: #fff;
				}
			}
			.bottom-preview .footer {
				background: #fff;
				border-top: 1px solid #ececec;
				color: #475569;
				min-height: 96px;
			}
		}
	}
</style>
