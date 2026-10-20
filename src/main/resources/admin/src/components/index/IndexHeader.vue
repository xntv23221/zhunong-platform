<template>
	<div class="navbar">
		<div class="title">
			<img class="project-logo" :src="projectLogoUrl" alt="高校助农互联网商城logo">
			<span class="title-name">{{this.$project.projectName}}</span>
		</div>
		<el-dropdown class="dropdown-box" @command="handleCommand" trigger="click">
			<div class="el-dropdown-link">
				<el-image v-if="avatar" :src="avatar?this.$base.url + avatar : require('@/assets/img/avator.png')" fit="cover"></el-image>
				<span class="label">欢迎</span>
				<span class="nickname">{{this.$storage.get('adminName')}}</span>
				<span class="icon iconfont icon-xiala"></span>
			</div>
			<el-dropdown-menu class="top-el-dropdown-menu" slot="dropdown">
				<el-dropdown-item class="item1" :command="''">
					<span class="icon iconfont icon-home19"></span>
					首页
				</el-dropdown-item>
				<el-dropdown-item class="item2" :command="'center'">
					<span class="icon iconfont icon-touxiang09"></span>
					个人中心
				</el-dropdown-item>
				<el-dropdown-item v-if="this.$storage.get('sessionTable')!='users'" class="item3" :command="'front'">
					<span class="icon iconfont icon-fanhui19"></span>
					退出到前台
				</el-dropdown-item>
				<el-dropdown-item class="item4" :command="'logout'">
					<span class="icon iconfont icon-guanbi19"></span>
					退出登录
				</el-dropdown-item>
			</el-dropdown-menu>
		</el-dropdown>
	</div>
</template>

<script>
	import {
		Loading
	} from 'element-ui';
	import axios from 'axios';
	export default {
		data() {
			return {
				dialogVisible: false,
				ruleForm: {},
			user: null,
			topLogoUrl: '',
			defaultLogoUrl: `${window.location.origin}/zhunong79djy629/upload/project-logo.png`,
			};
		},
		created() {
			this.$http.get('config/info?name=bTopLogo',).then(rs=>{this.topLogoUrl = rs.data.data?rs.data.data.value:''})
		},
		computed: {
			projectLogoUrl() {
				return this.topLogoUrl ? `${this.$base.url}${this.topLogoUrl}` : this.defaultLogoUrl
			},
			avatar(){
				return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
			},
		},
		mounted() {
		},
		methods: {
			handleCommand(name) {
				if (name == 'logout') {
					this.onLogout()
				} 
				else if (name == 'front') {
					this.onIndexTap()
				}
				else {
					let router = this.$router
					name = '/'+name
					router.push(name)
				}
			},
			onLogout() {
				let storage = this.$storage
				let router = this.$router
				storage.clear()
				this.$store.dispatch('tagsView/delAllViews')
				router.replace({
					name: "login"
				});
			},
			onIndexTap(){
				localStorage.setItem("frontToken", localStorage.getItem("Token"));
				localStorage.setItem("frontRole", localStorage.getItem("role"));
				localStorage.setItem("frontSessionTable", localStorage.getItem("sessionTable"));
				localStorage.setItem("frontHeadportrait", localStorage.getItem("headportrait"));
				localStorage.setItem("UserTableName", localStorage.getItem("sessionTable"));
				localStorage.setItem("frontUserid", localStorage.getItem("userid"));
				localStorage.setItem("username", localStorage.getItem("adminName"));
				window.location.href = `${this.$base.indexUrl}`
			},
		}
	};
</script>


<style lang="scss" scoped>
	.navbar {
		.title {
			padding: 0 20px;
			margin: 0 0 0 9%;
			color: #29C41F;
			flex: 1;
			font-weight: 600;
			display: flex;
			width: auto;
			font-size: 18px;
			align-items: center;
			height: 100%;
			gap: 12px;
			.project-logo {
				width: 52px;
				height: 52px;
				min-width: 52px;
				object-fit: contain;
				border-radius: 50%;
			}
			.title-name {
				padding: 0;
				margin: 0 50px 0 0;
				color: inherit;
				font-weight: 600;
				font-size: 24px;
				line-height: 44px;
			}
		}
		.dropdown-box {
			border-radius: 4px;
			color: #FFF;
			background: #29C41F;
			display: flex;
			width: auto;
			font-size: 16px;
			align-items: center;
			order: 11;
			height: 35px;
			.el-dropdown-link {
				border-radius: 8px;
				display: flex;
				width: 200px;
				justify-content: center;
				align-items: center;
				height: 58px;
				.el-image {
					border-radius: 100%;
					margin: 0;
					object-fit: cover;
					display: inline-block;
					width: 24px;
					height: 24px;
				}
				.label {
					color: #FDC748;
					display: none;
					font-size: 16px;
					line-height: 35px;
				}
				.nickname {
					color: #FFF;
					font-size: 16px;
				}
				.iconfont {
					margin: 0 0 0 5px;
					color: #FFFFFF;
					font-size: 14px;
				}
			}
		}
	}
	@media screen and (max-width: 768px) {
		.navbar .title {
			margin-left: 0;
			padding: 0 12px;
			gap: 8px;
		}
		.navbar .title .project-logo {
			width: 40px;
			height: 40px;
			min-width: 40px;
		}
		.navbar .title .title-name {
			margin-right: 0;
			font-size: 18px;
			line-height: 1.4;
		}
	}
	.top-el-dropdown-menu {
		border: 1px solid #EBEEF5;
		border-radius: 4px;
		padding: 10px 0;
		box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
		margin: 18px 0;
		background: #FFF;
		li.el-dropdown-menu__item.item1 {
			cursor: pointer;
			padding: 0 20px;
			margin: 0;
			outline: 0;
			color: #606266;
			background: #fff;
			font-size: 14px;
			line-height: 36px;
			list-style: none;
			.iconfont {
				margin: 0 4px 0 0;
				color: #000;
				font-size: 14px;
			}
		}
		li.el-dropdown-menu__item.item1:hover {
			background: #ecf5ff;
		}
		li.el-dropdown-menu__item.item2 {
			cursor: pointer;
			padding: 0 20px;
			margin: 0;
			outline: 0;
			color: #606266;
			background: #fff;
			font-size: 14px;
			line-height: 36px;
			list-style: none;
			.iconfont {
				margin: 0 4px 0 0;
				color: #000;
				font-size: 14px;
			}
		}
		li.el-dropdown-menu__item.item2:hover {
			background: #ecf5ff;
		}
		li.el-dropdown-menu__item.item3 {
			cursor: pointer;
			padding: 0 20px;
			margin: 0;
			outline: 0;
			color: #606266;
			background: #fff;
			font-size: 14px;
			line-height: 36px;
			list-style: none;
			.iconfont {
				margin: 0 4px 0 0;
				color: #000;
				font-size: 14px;
			}
		}
		li.el-dropdown-menu__item.item3:hover {
			background: #ecf5ff;
		}
		li.el-dropdown-menu__item.item4 {
			cursor: pointer;
			padding: 0 20px;
			margin: 0;
			outline: 0;
			color: #606266;
			background: #fff;
			font-size: 14px;
			line-height: 36px;
			list-style: none;
			.iconfont {
				margin: 0 4px 0 0;
				color: #000;
				font-size: 14px;
			}
		}
		li.el-dropdown-menu__item.item4:hover {
			background: #ecf5ff;
		}
	}
</style>
