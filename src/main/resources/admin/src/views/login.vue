<template>
			<div>
				<div class="login-container" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
					<el-form class="login_form animate__animated animate__">
						<div class="login_form2">
							<div class="title-container">
								<img class="title-logo" :src="logoUrl" alt="高校助农互联网商城logo">
								<span>高校助农互联网商城登录</span>
							</div>
					<div v-if="loginType==1" class="list-item">
						<input placeholder="请输入账号：" name="username" type="text" v-model="rulesForm.username">
					</div>
					<div v-if="loginType==1" class="list-item">
						<div class="password-box">
							<input placeholder="请输入密码：" name="password" :type="showPassword?'text':'password'" v-model="rulesForm.password">
							<span class="icon iconfont" :class="showPassword?'icon-liulan13':'icon-liulan17'" @click="showPassword=!showPassword"></span>
						</div>
					</div>

					<div class="list-item select" v-if="roles.length>1&&loginType<=2">
						<el-select v-model="rulesForm.role" placeholder="请选择角色：">
							<el-option v-if="loginType==1||(loginType==2&&item.role!='users')" v-for="item in roles" :key="item.roleName" :label="item.roleName" :value="item.roleName" />
						</el-select>
					</div>

		
					<div class="login-btn">
						<div class="login-btn1">
							<el-button v-if="loginType==1||loginType==3||loginType==4" type="primary" @click="login()" class="loginInBt">登录</el-button>
						</div>
						<div class="login-btn2">
							<el-button type="primary" @click="register('nongmin')" class="register">
								农民注册							</el-button>
							<el-button type="primary" @click="register('gaoxiao')" class="register">
								高校注册							</el-button>
						</div>
						<div class="login-btn3">
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>
<script>
	import 'animate.css'
	import menu from "@/utils/menu";
	export default {
		data() {
			return {
				verifyCheck2: false,
				flag: false,
				baseUrl:this.$base.url,
				loginType: 1,
				rulesForm: {
					username: "",
					password: "",
					role: "",
				},
				menus: [],
				roles: [],
				tableName: "",
				showPassword: false,
				indexBgUrl: '',
				logoUrl: `${window.location.origin}/zhunong79djy629/upload/project-logo.png`,
			};
		},
		mounted() {
			let menus = menu.list();
			this.menus = menus;

			for (let i = 0; i < this.menus.length; i++) {
				if (this.menus[i].hasBackLogin=='是') {
					this.roles.push(this.menus[i])
				}
			}

		},
		created() {
			this.$http.get('config/info?name=bLoginBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		},
		destroyed() {
		},
		components: {
		},
		methods: {

			//注册
			register(tableName){
				this.$storage.set("loginTable", tableName);
				this.$router.push({path:'/register',query:{pageFlag:'register'}})
			},
			// 登陆
			login() {
				if(this.loginType==1) {

					if (!this.rulesForm.username) {
						this.$message.error("请输入用户名");
						return;
					}
					if (!this.rulesForm.password) {
						this.$message.error("请输入密码");
						return;
					}
					if(this.roles.length>1) {
						if (!this.rulesForm.role) {
							this.$message.error("请选择角色");
							return;
						}
					
						for (let i = 0; i < this.roles.length; i++) {
							if (this.roles[i].roleName == this.rulesForm.role) {
								this.tableName = this.roles[i].tableName;
							}
						}
					} else {
						this.tableName = this.roles[0].tableName;
						this.rulesForm.role = this.roles[0].roleName;
					}
				}
		
				this.loginPost()
			},
			loginPost() {
				this.$http({
					url: `${this.tableName}/login?username=${this.rulesForm.username}&password=${this.rulesForm.password}`,
					method: "post"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.$storage.set("Token", data.token);
						this.$storage.set("role", this.rulesForm.role);
						this.$storage.set("sessionTable", this.tableName);
						this.$storage.set("adminName", this.rulesForm.username);
						this.$nextTick(()=>{
							this.$http({
								url: this.tableName + '/session',
								method: "get"
							}).then(({
								data
							}) => {
								if (data && data.code === 0) {
									if(this.tableName == 'yonghu') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'nongmin') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'gaoxiao') {
										this.$storage.set('headportrait',data.data.touxiang)
									}
									if(this.tableName == 'users') {
										this.$storage.set('headportrait',data.data.image)
									}
									this.$storage.set('userForm',JSON.stringify(data.data))
									this.$storage.set('userid',data.data.id);
								} else {
									let message = this.$message
									message.error(data.msg);
								}
								this.$router.replace({ path: "/" });
							});
						})
					}
					else {
						this.$message.error(data.msg);
					}
				});
			},
		}
	}
</script>

<style lang="scss" scoped>
.login-container {
	min-height: 100vh;
	position: relative;
	background-repeat: no-repeat;
	background-position: center center;
	background-size: cover;
	background: url(http://codegen.caihongy.cn/20250926/183867aad2b6479eb1d73d908e898f72.png)  no-repeat  center   center /  100% 100%;
	background: url(http://codegen.caihongy.cn/20250926/183867aad2b6479eb1d73d908e898f72.png)  no-repeat  center   center /  100% 100%;
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	position: relative;

	.login_form {
		border-radius: 0;
		z-index: 1;
		flex-direction: column;
		display: flex;
		width: 100%;
		align-items: flex-end;
		flex-wrap: wrap;
		height: auto;
		.login_form2 {
			border-radius: 24px;
			padding: 57px ;
			margin: 140px 98px 0 0;
			background: #FFFFFF;
			display: flex;
			width: 673px;
			flex-wrap: wrap;
		}
		.title-container {
			margin: 10px 0 20px 0;
			text-shadow: none;
			color: #29C41F;
			top: 5%;
			left: 2%;
			background: none;
			font-weight: 600;
			width: 100%;
			font-size: 34px;
			position: absolute;
			display: flex;
			align-items: center;
			gap: 14px;
			.title-logo {
				width: 54px;
				height: 54px;
				object-fit: contain;
				border-radius: 50%;
				background: rgba(255,255,255,0.92);
				box-shadow: 0 10px 20px rgba(41, 196, 31, 0.14);
				padding: 4px;
			}
		}
		.list-item {
			border-radius: 5px;
			padding: 0;
			margin: 10px 0;
			background: #fff;
			display: flex;
			width: 100%;
			align-items: center;
			order: 1;
			input {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: none;
				flex: 1;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			input:focus {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: none;
				flex: 1;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			.password-box {
				flex: 1;
				display: flex;
				width: 100%;
				position: relative;
				align-items: center;
				input {
					border: 1px solid #DADFE6;
					border-radius: 4px;
					padding: 0 10px;
					box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
					color: #333;
					background: none;
					flex: 1;
					width: 100%;
					font-size: 16px;
					height: 45px;
				}
				input:focus {
					border: 1px solid #DADFE6;
					border-radius: 4px;
					padding: 0 10px;
					box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
					color: #333;
					background: none;
					flex: 1;
					width: 100%;
					font-size: 16px;
					height: 45px;
				}
				.iconfont {
					cursor: pointer;
					z-index: 1;
					color: #000;
					top: 0;
					font-size: 16px;
					line-height: 44px;
					position: absolute;
					right: 5px;
				}
			}
			input::placeholder {
				color: #C3C7CC;
				font-size: 16px;
			}
			/deep/ .el-select {
				flex: 1;
				width: 100%;
			}
			/deep/ .el-select .el-input__inner {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: none;
				flex: 1;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			/deep/ .el-select .is-focus .el-input__inner {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: none;
				flex: 1;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			/deep/ .el-select .el-input__inner::placeholder{
				color: #C3C7CC;
				font-size: 16px;
			}
		}
		.login-btn {
			margin: 10px auto;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			order: 1;
			.login-btn1 {
				width: 100%;
				order: 1;
			}
			.login-btn2 {
				z-index: 9;
				background: none;
				display: flex;
				width: auto;
				border-color: #707070;
				border-width: 0;
				align-items: center;
				border-style: solid;
			}
			.login-btn3 {
				z-index: 1;
				flex: 1;
				width: auto;
				text-align: right;
			}
			.loginInBt {
				border: 0;
				cursor: pointer;
				border-radius: 24px;
				padding: 0 24px;
				margin: 30px 0 10px;
				outline: none;
				color: #fff;
				background: #5ED357;
				font-weight: bold;
				width: 100%;
				font-size: 20px;
				height: 50px;
			}
			.loginInBt:hover {
				opacity: 0.8;
			}
			.register {
				border: 0px solid #333;
				cursor: pointer;
				border-radius: 0px;
				padding: 10px;
				outline: none;
				color: #5ED357;
				background: none;
				text-decoration: underline;
				width: auto;
				font-size: 16px;
			}
			.register:hover {
				opacity: 0.8;
			}
			.forget {
				border: 0px solid #333;
				cursor: pointer;
				border-radius: 0px;
				padding: 10px;
				outline: none;
				color: #0D1818;
				background: none;
				font-weight: bold;
				width: auto;
				font-size: 16px;
			}
			.forget:hover {
				opacity: 0.8;
			}
		}
	}
}

</style>
