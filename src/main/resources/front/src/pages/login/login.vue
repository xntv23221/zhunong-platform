<template>
		<div>
			<div class="login-container" :style="{'backgroundImage': indexBgUrl?`url(${$config.baseUrl + indexBgUrl})`:''}">
				<el-form ref="loginForm" :model="loginForm" :rules="rules" class="login_form animate__animated animate__">
					<div class="brand-panel">
						<img class="brand-logo" :src="logoUrl" alt="高校助农互联网商城logo">
						<div class="brand-name">高校助农互联网商城</div>
						<div class="brand-copy">连接高校技术与农产品消费场景，聚焦助农交易与服务协同。</div>
					</div>
					<div class="login_form2">
						<div class="login-title">高校助农互联网商城</div>
					<div v-if="loginType==1" class="list-item" prop="username">
						<div class="lable">
							账号：
						</div>
						<input v-model="loginForm.username" placeholder="请输入账号：">
					</div>
					<div v-if="loginType==1" class="list-item" prop="password">
						<div class="lable">
							密码：
						</div>
						<div class="password-box">
							<input v-model="loginForm.password" placeholder="请输入密码：" :type="showPassword?'text':'password'">
							<span class="icon iconfont" :class="showPassword?'icon-liulan13':'icon-liulan17'" @click="showPassword=!showPassword"></span>
						</div>
					</div>

					<div class="list-item" v-if="roles.length>1&&loginType<=2">
						<div class="lable">
							角色：
						</div>
						<div class="list-type" prop="role">
							<el-radio v-model="loginForm.tableName" :label="item.tableName" v-for="(item, index) in roles" :key="index" @change.native="getCurrentRow(item)">{{item.roleName}}</el-radio>
						</div>
					</div>

			
					<div class="list-btn">
						<el-button class="login_btn" v-if="loginType==1||loginType==3||loginType==4" @click="submitForm('loginForm')">登录</el-button>

						<div class="list-btn2">
							<router-link class="register_btn" :to="{path: '/register', query: {role: item.tableName,pageFlag:'register'}}" v-if="item.hasFrontRegister=='是'" v-for="(item, index) in roles2" :key="index">注册{{item.roleName.replace('注册','')}}</router-link>
						</div>
					</div>
				</div>
				</el-form>
			</div>
		</div>
</template>

<script>
	import 'animate.css';
import menu from '@/config/menu'
export default {
	//数据集合
	data() {
		return {
            baseUrl: this.$config.baseUrl,
            loginType: 1,
			roleMenus: [],
			loginForm: {
				username: '',
				password: '',
				tableName: '',
			},
			role: '',
			roles: [],
			roles2: [],
			rules: {
				username: [
					{ required: true, message: '请输入账号', trigger: 'blur' }
				],
				password: [
					{ required: true, message: '请输入密码', trigger: 'blur' }
				]
			},
			codes: [{
				num: 1,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}, {
				num: 2,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}, {
				num: 3,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}, {
				num: 4,
				color: '#000',
				rotate: '10deg',
				size: '16px'
			}],
			flag: false,
			verifyCheck2: false,
			showPassword: false,
			indexBgUrl: '',
			logoUrl: `${window.location.origin}/zhunong79djy629/upload/project-logo.png`
		}
	},
	components: {
	},
	created() {
		this.$http.get('config/info?name=fLoginBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		this.roleMenus = menu.list()
		for(let item in this.roleMenus) {
			if(this.roleMenus[item].hasFrontLogin=='是') {
				this.roles.push(this.roleMenus[item]);
			}
			if(this.roleMenus[item].hasFrontRegister=='是') {
				this.roles2.push(this.roleMenus[item]);
			}
		}
		
	},
	mounted() {
	},
	//方法集合
	methods: {
		randomString() {
			var len = 4;
			var chars = [
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
				'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2',
				'3', '4', '5', '6', '7', '8', '9'
			]
			var colors = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f']
			var sizes = ['14', '15', '16', '17', '18']
			
			var output = []
			for (var i = 0; i < len; i++) {
				// 随机验证码
				var key = Math.floor(Math.random() * chars.length)
				this.codes[i].num = chars[key]
				// 随机验证码颜色
				var code = '#'
				for (var j = 0; j < 6; j++) {
					var key = Math.floor(Math.random() * colors.length)
					code += colors[key]
				}
				this.codes[i].color = code
				// 随机验证码方向
				var rotate = Math.floor(Math.random() * 45)
				var plus = Math.floor(Math.random() * 2)
				if (plus == 1) rotate = '-' + rotate
				this.codes[i].rotate = 'rotate(' + rotate + 'deg)'
				// 随机验证码字体大小
				var size = Math.floor(Math.random() * sizes.length)
				this.codes[i].size = sizes[size] + 'px'
			}
		},
		getCurrentRow(row) {
			this.role = row.roleName;
		},
		submitForm(formName) {
			if(this.loginType==1) {
				if (this.roles.length!=1) {
					if (!this.role) {
						this.$message.error("请选择登录用户类型");
						return false;
					}
				} else {
					this.role = this.roles[0].roleName;
					this.loginForm.tableName = this.roles[0].tableName;
				}
				if (!this.loginForm.username) {
					this.$message.error("请输入用户名");
					return;
				}
				if (!this.loginForm.password) {
					this.$message.error("请输入密码");
					return;
				}
			}

			this.loginPost(formName)
		},
		loginPost(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					this.$http.get(`${this.loginForm.tableName}/login`, {params: this.loginForm}).then(res => {
						if (res.data.code === 0) {
							localStorage.setItem('frontToken', res.data.token);
							localStorage.setItem('UserTableName', this.loginForm.tableName);
							localStorage.setItem('username', this.loginForm.username);
							localStorage.setItem('frontSessionTable', this.loginForm.tableName);
							localStorage.setItem('frontRole', this.role);
							localStorage.setItem('keyPath', 0);
							this.$router.push('/');
							this.$message({
								message: '登录成功',
								type: 'success',
								duration: 1500,
							});
						} 
						else {
							this.$message.error(res.data.msg);
						}
					});
				} else {
					return false;
				}
			});
		},
    }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.login-container {
		background-repeat: no-repeat;
		background-size: cover !important;
		background: url(http://codegen.caihongy.cn/20251215/7b78273542ca44c7907bec2473e6db02.png);
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		background-position: center center;
		position: relative;
		background: url(http://codegen.caihongy.cn/20251215/7b78273542ca44c7907bec2473e6db02.png);
		padding: 32px;
		.login_form {
			border: 1px solid rgba(255,255,255,0.45);
			border-radius: 28px;
			padding: 32px;
			margin: 0px auto;
			z-index: 10;
			background: rgba(247,250,244,0.88);
			backdrop-filter: blur(18px);
			box-shadow: 0 24px 60px rgba(38, 59, 29, 0.18);
			display: flex;
			gap: 40px;
			width: calc(100vw - 64px);
			max-width: 1120px;
			height: auto;
			.brand-panel {
				width: 34%;
				min-width: 260px;
				padding: 20px 12px 20px 8px;
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: flex-start;
				.brand-logo {
					width: 96px;
					height: 96px;
					object-fit: contain;
					border-radius: 50%;
					background: rgba(255,255,255,0.72);
					box-shadow: 0 12px 24px rgba(61, 126, 88, 0.18);
					padding: 6px;
				}
				.brand-name {
					margin-top: 20px;
					color: #1f3523;
					font-size: 38px;
					font-weight: 700;
					line-height: 1.2;
				}
				.brand-copy {
					margin-top: 14px;
					color: rgba(31,53,35,0.76);
					font-size: 15px;
					line-height: 1.8;
				}
			}
			.login_form2 {
				width: 66%;
				padding: 24px 10px 0 10px;
				.login-title {
					padding: 0;
					margin: 0 0 24px 0;
					color: #1f3523;
					white-space: normal;
					font-weight: 600;
					font-size: 34px;
					line-height: 1.3;
					text-shadow: none;
					background: none;
					width: 100%;
					text-align: left;
				}
				.list-item {
					margin: 0 auto 18px;
					display: flex;
					width: 100%;
					align-items: center;
					gap: 14px;
					.lable {
						color: #1f3523;
						width: 78px;
						font-size: 16px;
						font-weight: 500;
						line-height: 1.5;
						text-align: right;
					}
					input {
						border: 1px solid rgba(150,183,138,0.42);
						border-radius: 100px;
						padding: 0 20px;
						box-shadow: inset 0 1px 2px rgba(34, 60, 28, 0.04);
						color: #666;
						flex: 1;
						background: rgba(255,255,255,0.92);
						width: calc(100% - 92px);
						font-size: 16px;
						height: 56px;
					}
					input:focus {
						border: 1px solid #65b54d;
						border-radius: 100px;
						padding: 0 20px;
						box-shadow: 0 0 0 4px rgba(101,181,77,0.12);
						color: #666;
						flex: 1;
						background: rgba(255,255,255,0.98);
						width: calc(100% - 92px);
						font-size: 16px;
						height: 56px;
					}
					.password-box {
						flex: 1;
						display: flex;
						width: calc(100% - 92px);
						position: relative;
						align-items: center;
						input {
							border: 1px solid rgba(150,183,138,0.42);
							border-radius: 100px;
							padding: 0 20px;
							box-shadow: inset 0 1px 2px rgba(34, 60, 28, 0.04);
							color: #666;
							flex: 1;
							background: rgba(255,255,255,0.92);
							width: 100%;
							font-size: 16px;
							height: 56px;
						}
						input:focus {
							border: 1px solid #65b54d;
							border-radius: 100px;
							padding: 0 20px;
							box-shadow: 0 0 0 4px rgba(101,181,77,0.12);
							color: #666;
							flex: 1;
							background: rgba(255,255,255,0.98);
							width: 100%;
							font-size: 16px;
							height: 56px;
						}
						.iconfont {
							cursor: pointer;
							z-index: 1;
							color: #48623d;
							top: 6px;
							font-size: 16px;
							line-height: 44px;
							position: absolute;
							right: 18px;
						}
					}
					input::placeholder {
						color: #999;
						font-size: 16px;
					}
				}
				.list-type {
					flex: 1;
					width: 100%;
					line-height: 60px;
					height: 60px;
					/deep/ .el-radio__input .el-radio__inner {
						background: rgba(53, 53, 53, 0);
						border-color: #333;
					}
					/deep/ .el-radio__input.is-checked .el-radio__inner {
						background: #000000;
						border-color: #FFFFFF;
					}
					/deep/ .el-radio__label {
						color: #333;
						font-size: 16px;
					}
					/deep/ .el-radio__input.is-checked+.el-radio__label {
						color: #000000;
						font-size: 16px;
					}
				}
				.list-btn {
					padding: 0;
					margin: 36px auto 0;
					align-content: center;
					display: flex;
					width: 100%;
					justify-content: flex-start;
					align-items: center;
					flex-wrap: wrap;
					text-align: left;
					.login_btn {
						border: 0;
						cursor: pointer;
						padding: 0 28px;
						margin: 0 0 18px 0;
						color: #fff;
						display: inline-block;
						font-size: 20px;
						font-weight: 600;
						line-height: 54px;
						transition: all 0s;
						border-radius: 999px;
						outline: none;
						background: linear-gradient(135deg, #77c952 0%, #4ea93c 100%);
						width: 260px;
						min-width: 180px;
						height: 54px;
						box-shadow: 0 14px 28px rgba(90, 170, 67, 0.28);
					}
					.login_btn:hover {
						opacity: 0.8;
					}
					.list-btn2 {
						padding: 0;
						margin: 0;
						display: flex;
						width: 100%;
						justify-content: flex-start;
						align-items: center;
						flex-wrap: wrap;
						gap: 18px;
						text-align: left;
						.register_btn {
							cursor: pointer;
							padding: 0px;
							margin: 0 0 8px 0;
							color: #33552c;
							display: inline-block;
							text-decoration: none;
							font-size: 16px;
							line-height: 28px;
							border-radius: 2px;
							background: none;
							border-width: 0px;
							border-style: solid;
							height: auto;
						}
						.register_btn:hover {
							opacity: 0.8;
						}
						.resetpwd_btn {
							cursor: pointer;
							margin: 0 0px 20px 0;
							color: #000000;
							display: inline-block;
							font-size: 16px;
							text-align: center;
						}
						.resetpwd_btn:hover {
							opacity: 0.8;
						}
					}
				}
			}
		}
	}
	@media screen and (max-width: 960px) {
		.login-container {
			padding: 18px;
			.login_form {
				padding: 22px;
				gap: 24px;
				width: calc(100vw - 36px);
				max-width: 720px;
				flex-direction: column;
				.brand-panel,
				.login_form2 {
					width: 100%;
					min-width: 0;
				}
				.brand-panel {
					padding: 0;
					.brand-logo {
						width: 72px;
						height: 72px;
					}
					.brand-name {
						font-size: 28px;
					}
				}
				.login_form2 {
					padding: 0;
					.login-title {
						font-size: 28px;
					}
					.list-item {
						flex-wrap: wrap;
						.lable {
							width: 100%;
							text-align: left;
						}
						input,
						.password-box,
						.password-box input {
							width: 100%;
						}
					}
					.list-btn {
						.login_btn {
							width: 100%;
						}
					}
				}
			}
		}
	}

	.login-container {
		background: url('/zhunong79djy629/upload/Mountain.jpg') no-repeat center center / cover;
		.login_form {
			background: rgba(255,255,255,0.94);
			border: 1px solid rgba(15, 23, 42, 0.08);
			box-shadow: 0 28px 60px rgba(15, 23, 42, 0.12);
			.brand-panel {
				.brand-name {
					color: #111827;
				}
				.brand-copy {
					color: #4b5563;
				}
			}
			.login_form2 {
				.login-title {
					color: #111827;
				}
				.list-item {
					.lable {
						color: #111827;
					}
					input,
					.password-box input {
						border-color: #e5e7eb;
						background: #fff;
						color: #111827;
					}
					input:focus,
					.password-box input:focus {
						border-color: #cbd5e1;
						box-shadow: 0 0 0 4px rgba(148, 163, 184, 0.12);
					}
				}
				.list-btn {
					.login_btn {
						background: #111827;
						box-shadow: 0 14px 28px rgba(15, 23, 42, 0.18);
					}
					.list-btn2 .register_btn {
						color: #374151;
					}
				}
			}
		}
	}
</style>
