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

<style lang="scss" scoped>
.login-container { min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 40px 20px; background: radial-gradient(1200px 600px at 10% 10%, #dcfce7 0%, transparent 60%), radial-gradient(900px 500px at 90% 90%, #bbf7d0 0%, transparent 60%), #f5f7f6; background-size: cover; }
.login_form { width: 960px; max-width: 100%; display: grid; grid-template-columns: 1.05fr 1fr; background: #fff; border-radius: 24px; overflow: hidden; box-shadow: 0 30px 80px rgba(20, 83, 45, .18); }
.brand-panel { position: relative; padding: 56px 44px; color: #fff; display: flex; flex-direction: column; justify-content: flex-end; min-height: 520px;
  background: linear-gradient(160deg, rgba(20,83,45,.25), rgba(5,46,22,.88)), url('http://localhost:8014/zhunong79djy629/upload/food1.jpg') center/cover;
  .brand-logo { position: absolute; top: 40px; left: 44px; width: 52px; height: 52px; border-radius: 14px; background: #fff; padding: 4px; }
  .brand-name { font-size: 30px; font-weight: 800; letter-spacing: 2px; margin-bottom: 12px; }
  .brand-copy { font-size: 15px; line-height: 1.8; opacity: .88; } }
.login_form2 { padding: 56px 48px; display: flex; flex-direction: column; justify-content: center; }
.login-title { font-size: 26px; font-weight: 800; color: #0f172a; margin-bottom: 6px; &::after { content: '欢迎回来，请登录您的账号'; display: block; font-size: 14px; font-weight: 400; color: #64748b; margin-top: 8px; } }
.list-item { margin-top: 22px;
  .lable { font-size: 13px; color: #475569; margin-bottom: 8px; font-weight: 600; }
  input { width: 100%; height: 46px; border: 1px solid #dfe7e2; border-radius: 12px; padding: 0 14px; font-size: 14px; outline: none; transition: .2s; box-sizing: border-box; background: #f8faf9;
    &:focus { border-color: #16a34a; background: #fff; box-shadow: 0 0 0 4px rgba(22,163,74,.12); } } }
.password-box { position: relative; .icon { position: absolute; right: 14px; top: 13px; color: #94a3b8; cursor: pointer; } }
.list-type { display: flex; flex-wrap: wrap; gap: 8px; ::v-deep .el-radio { margin: 0; padding: 8px 14px; border: 1px solid #dfe7e2; border-radius: 10px; } ::v-deep .el-radio.is-checked { border-color: #16a34a; background: #f0fdf4; } }
.list-btn { margin-top: 30px; }
.login_btn { width: 100%; height: 48px; border: 0 !important; border-radius: 12px !important; font-size: 16px; font-weight: 700; letter-spacing: 4px; color: #fff !important; background: linear-gradient(90deg, #16a34a, #22c55e) !important; box-shadow: 0 10px 24px rgba(22,163,74,.3); }
.list-btn2 { margin-top: 16px; text-align: center; color: #64748b; font-size: 14px; .register_btn { color: #16a34a; margin: 0 8px; text-decoration: none; font-weight: 600; } }
@media (max-width: 800px) { .login_form { grid-template-columns: 1fr; } .brand-panel { min-height: 200px; } }
</style>
