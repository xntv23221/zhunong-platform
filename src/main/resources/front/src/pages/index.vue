<template>
	<div class="main-containers">
		<header class="ag-header">
			<div class="ag-header-inner">
				<div class="ag-brand" @click="goMenu('/index/home')">
					<img :src="projectLogoUrl" alt="logo">
					<div class="ag-brand-text">
						<b>高校助农互联网商城</b>
						<small>从田间到餐桌 · 高校助农</small>
					</div>
				</div>
				<nav class="ag-nav">
					<a :class="{active: activeMenu=='/index/home'}" @click="goMenu('/index/home')"><i class="el-icon-house"></i>首页</a>
					<a v-for="(menu, index) in menuList" :key="index" :class="{active: activeMenu==menu.url}" @click="goMenu(menu.url)"><i :class="iconArr[index]"></i>{{menu.name}}</a>
				</nav>
				<div class="ag-actions">
					<a v-if="Token" class="ag-icon-btn" title="购物车" @click="goMenu('/index/cart')"><i class="el-icon-shopping-cart-2"></i><span>购物车</span></a>
					<a v-if="Token" class="ag-icon-btn" title="我的订单" @click="goMenu('/index/shop-order/order')"><i class="el-icon-tickets"></i><span>订单</span></a>
					<el-dropdown v-if="Token" @command="handleCommand" trigger="click">
						<div class="ag-user">
							<img :src="headportrait?baseUrl + headportrait:require('@/assets/avator.png')">
							<span>{{username}}</span><i class="el-icon-arrow-down"></i>
						</div>
						<el-dropdown-menu slot="dropdown">
							<el-dropdown-item v-if="notAdmin" icon="el-icon-user" command="user">个人中心</el-dropdown-item>
							<el-dropdown-item icon="el-icon-switch-button" command="register">退出登录</el-dropdown-item>
						</el-dropdown-menu>
					</el-dropdown>
					<template v-else>
						<el-button size="small" round @click="toLogin">登录</el-button>
						<el-button size="small" round type="primary" @click="$router.push('/register?role=yonghu')">注册</el-button>
					</template>
				</div>
			</div>
		</header>

		<section class="ag-hero" v-if="activeMenu=='/index/home' && carouselList.length">
			<div class="swiper-container mySwiper3">
				<div class="swiper-wrapper">
					<div class="swiper-slide" v-for="item in carouselList" :key="item.id">
						<el-image @click="carouselClick(item.url)" :src="preHttp(item.value)?item.value:baseUrl + item.value" fit="cover"></el-image>
					</div>
				</div>
				<div class="swiper-pagination"></div>
			</div>
			<div class="ag-hero-mask">
				<div class="ag-hero-text">
					<span class="ag-chip">🌾 高校助农 · 产地直供</span>
					<h1>让好农货<br>走出田间地头</h1>
					<p>汇集农户优质农产品、农业技术知识与专家服务，连接农户、高校与消费者。</p>
					<div class="ag-hero-btns">
						<el-button type="primary" round @click="goMenu('/index/nongchanpinxinxi')">逛逛农产品 <i class="el-icon-right"></i></el-button>
						<el-button round class="ghost" @click="goMenu('/index/jishuzhishiku')">技术知识库</el-button>
					</div>
				</div>
			</div>
		</section>
		<section class="ag-pagehead" v-else>
			<div class="ag-pagehead-inner">
				<h2>{{ pageTitle }}</h2>
				<p>从田间到餐桌 · 高校助农服务平台</p>
			</div>
		</section>

		<main class="ag-main">
			<router-view id="scrollView"></router-view>
		</main>

		<footer class="ag-footer">
			<div class="ag-footer-inner">
				<div>
					<div class="ag-footer-brand">高校助农互联网商城</div>
					<p>连接农户、高校与消费者的助农服务平台。</p>
				</div>
				<div>
					<h4>快速入口</h4>
					<a @click="goMenu('/index/nongchanpinxinxi')">农产品</a>
					<a @click="goMenu('/index/jishuzhishiku')">技术知识库</a>
					<a @click="goMenu('/index/cart')">购物车</a>
				</div>
				<div>
					<h4>支付说明</h4>
					<p>平台支付为<b>模拟微信支付</b>，仅用于课程演示，不产生真实扣款。</p>
				</div>
			</div>
			<div class="ag-footer-copy" v-html="bottomContent || '© 高校助农互联网商城'"></div>
		</footer>

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
			this.mySwiper3Timer = new Swiper(".mySwiper3", {"navigation":{"nextEl":".swiper-button-next","prevEl":".swiper-button-prev"},"pagination":{"el":".swiper-pagination","clickable":true},"slidesPerView":1,"loop":true,"speed":600,"autoplay":{"delay":4000,"disableOnInteraction":false},"effect":"fade","fadeEffect":{"crossFade":true}})
		}, 500)

	},
	computed: {
		pageTitle() {
			const p = this.$route.path
			const m = (this.menuList||[]).find(x => p.indexOf(x.url) === 0)
			const map = {'/index/cart':'购物车','/index/center':'个人中心','/index/shop-order/order':'我的订单','/index/shop-order/orderConfirm':'确认订单','/index/wxpay/cashier':'微信支付收银台（模拟）','/index/storeup':'我的收藏','/index/shop-address/list':'收货地址'}
			return map[p] || (m ? m.name : '高校助农互联网商城')
		},
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

<style lang="scss" scoped>
$g: #16a34a; $gd: #166534;
.main-containers { min-height: 100vh; background: var(--ag-bg); display: flex; flex-direction: column; }
.ag-header { position: sticky; top: 0; z-index: 1000; background: rgba(255,255,255,.88); backdrop-filter: saturate(180%) blur(12px); border-bottom: 1px solid #e7efe9; }
.ag-header-inner { max-width: 1240px; margin: 0 auto; height: 68px; padding: 0 24px; display: flex; align-items: center; gap: 32px; }
.ag-brand { display: flex; align-items: center; gap: 10px; cursor: pointer; flex-shrink: 0;
  img { width: 40px; height: 40px; border-radius: 12px; object-fit: cover; }
  b { display: block; font-size: 17px; color: #0f172a; letter-spacing: .5px; }
  small { color: #64748b; font-size: 12px; } }
.ag-nav { display: flex; gap: 4px; flex: 1;
  a { display: inline-flex; align-items: center; gap: 6px; padding: 8px 14px; border-radius: 999px; color: #334155; cursor: pointer; font-size: 14px; transition: .2s;
    &:hover { background: #f0fdf4; color: $g; }
    &.active { background: $g; color: #fff; } } }
.ag-actions { display: flex; align-items: center; gap: 8px; }
.ag-icon-btn { display: inline-flex; align-items: center; gap: 4px; padding: 8px 12px; border-radius: 10px; color: #334155; cursor: pointer; font-size: 14px;
  i { font-size: 18px; } &:hover { background: #f0fdf4; color: $g; } }
.ag-user { display: flex; align-items: center; gap: 8px; padding: 4px 10px 4px 4px; border-radius: 999px; border: 1px solid #e2e8f0; cursor: pointer; background: #fff;
  img { width: 30px; height: 30px; border-radius: 50%; object-fit: cover; } span { font-size: 14px; color: #0f172a; } }
.ag-hero { position: relative; height: 460px; overflow: hidden;
  .swiper-container { height: 100%; } .swiper-slide { height: 460px; } .el-image { width: 100%; height: 100%; }
  ::v-deep .swiper-pagination-bullet-active { background: #fff; } }
.ag-hero-mask { position: absolute; inset: 0; z-index: 2; pointer-events: none; background: linear-gradient(90deg, rgba(5,46,22,.82) 0%, rgba(5,46,22,.45) 45%, rgba(0,0,0,0) 75%); }
.ag-hero-text { pointer-events: auto; max-width: 1240px; margin: 0 auto; padding: 0 24px; height: 100%; display: flex; flex-direction: column; justify-content: center; color: #fff;
  h1 { font-size: 46px; line-height: 1.2; margin: 18px 0 14px; font-weight: 800; letter-spacing: 2px; }
  p { max-width: 460px; color: rgba(255,255,255,.85); font-size: 16px; line-height: 1.8; margin: 0 0 26px; } }
.ag-chip { align-self: flex-start; padding: 6px 14px; border-radius: 999px; background: rgba(255,255,255,.16); border: 1px solid rgba(255,255,255,.3); font-size: 13px; }
.ag-hero-btns .ghost { background: rgba(255,255,255,.12); color: #fff; border-color: rgba(255,255,255,.5); }
.ag-pagehead { background: linear-gradient(120deg, #14532d, #16a34a 60%, #4ade80); color: #fff;
  .ag-pagehead-inner { max-width: 1240px; margin: 0 auto; padding: 28px 24px; }
  h2 { margin: 0 0 4px; font-size: 24px; } p { margin: 0; opacity: .8; font-size: 13px; } }
.ag-main { flex: 1; width: 100%; max-width: 1240px; margin: 0 auto; padding: 24px; box-sizing: border-box; }
.ag-footer { background: #0b2b19; color: #a7c4b2; margin-top: 40px;
  .ag-footer-inner { max-width: 1240px; margin: 0 auto; padding: 40px 24px 24px; display: grid; grid-template-columns: 2fr 1fr 1.5fr; gap: 32px; }
  .ag-footer-brand { color: #fff; font-size: 18px; font-weight: 700; margin-bottom: 8px; }
  h4 { color: #fff; margin: 0 0 12px; font-size: 15px; } a { display: block; color: #a7c4b2; cursor: pointer; margin-bottom: 8px; &:hover { color: #4ade80; } }
  p { font-size: 13px; line-height: 1.8; margin: 0; } b { color: #4ade80; }
  .ag-footer-copy { border-top: 1px solid rgba(255,255,255,.08); text-align: center; padding: 16px; font-size: 12px; } }
</style>
