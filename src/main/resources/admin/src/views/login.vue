<template>
	<div class="lg-wrap">
		<div class="lg-card">
			<div class="lg-brand">
				<img class="lg-logo" :src="logoUrl" alt="logo">
				<div>
					<h1>高校助农互联网商城</h1>
					<p>管理后台 · 农户 / 高校 / 管理员统一入口</p>
					<ul><li>农产品与订单管理</li><li>原料采购与配送协同</li><li>模拟微信支付流水与退款</li></ul>
				</div>
			</div>
			<div class="lg-form">
				<h2>账号登录</h2>
				<p class="sub">请选择角色并输入账号密码</p>
				<div v-if="loginType==1" class="lg-item"><label>账号</label>
					<input placeholder="请输入账号" name="username" type="text" v-model="rulesForm.username"></div>
				<div v-if="loginType==1" class="lg-item"><label>密码</label>
					<div class="pw"><input placeholder="请输入密码" name="password" :type="showPassword?'text':'password'" v-model="rulesForm.password" @keyup.enter="login()">
					<i :class="showPassword?'el-icon-view':'el-icon-lock'" @click="showPassword=!showPassword"></i></div></div>
				<div class="lg-item" v-if="roles.length>1&&loginType<=2"><label>角色</label>
					<div class="roles">
						<span v-for="item in roles" v-if="loginType==1||(loginType==2&&item.role!='users')" :key="item.roleName" :class="{on: rulesForm.role==item.roleName}" @click="rulesForm.role=item.roleName">{{item.roleName}}</span>
					</div>
				</div>
				<el-button v-if="loginType==1||loginType==3||loginType==4" type="primary" @click="login()" class="lg-btn">登 录</el-button>
				<div class="lg-reg">还没有账号？<a @click="register('nongmin')">农民注册</a><a @click="register('gaoxiao')">高校注册</a></div>
			</div>
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
.lg-wrap { min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 30px; background: radial-gradient(1000px 500px at 0% 0%, #bbf7d0, transparent 60%), radial-gradient(900px 500px at 100% 100%, #a7f3d0, transparent 60%), #ecfdf5; }
.lg-card { width: 980px; max-width: 100%; display: grid; grid-template-columns: 1fr 1fr; background: #fff; border-radius: 26px; overflow: hidden; box-shadow: 0 30px 80px rgba(6,78,59,.18); }
.lg-brand { padding: 54px 46px; color: #fff; display: flex; flex-direction: column; justify-content: space-between; min-height: 540px; background: linear-gradient(160deg, #065f46 0%, #15803d 55%, #22c55e 100%);
  .lg-logo { width: 54px; height: 54px; border-radius: 14px; background: #fff; padding: 4px; }
  h1 { font-size: 30px; margin: 0 0 10px; letter-spacing: 2px; } p { opacity: .85; margin: 0 0 24px; }
  ul { list-style: none; padding: 0; margin: 0; li { padding: 10px 0 10px 28px; position: relative; opacity: .95; &::before { content: '✓'; position: absolute; left: 0; width: 18px; height: 18px; border-radius: 50%; background: rgba(255,255,255,.25); font-size: 12px; text-align: center; line-height: 18px; top: 12px; } } } }
.lg-form { padding: 54px 48px; display: flex; flex-direction: column; justify-content: center;
  h2 { margin: 0; font-size: 26px; color: #0f172a; } .sub { color: #64748b; margin: 8px 0 18px; font-size: 14px; } }
.lg-item { margin-top: 16px; label { display: block; font-size: 13px; font-weight: 600; color: #475569; margin-bottom: 8px; }
  input { width: 100%; height: 46px; border: 1px solid #dfe7e2; background: #f8faf9; border-radius: 12px; padding: 0 14px; font-size: 14px; outline: none; box-sizing: border-box; transition: .2s; &:focus { border-color: #16a34a; background: #fff; box-shadow: 0 0 0 4px rgba(22,163,74,.12); } }
  .pw { position: relative; i { position: absolute; right: 14px; top: 15px; color: #94a3b8; cursor: pointer; } } }
.roles { display: flex; flex-wrap: wrap; gap: 8px; span { padding: 8px 16px; border: 1.5px solid #e2e8f0; border-radius: 10px; cursor: pointer; font-size: 14px; color: #334155; transition: .15s; &.on { border-color: #16a34a; color: #16a34a; background: #f0fdf4; font-weight: 600; } } }
.lg-btn { margin-top: 28px; width: 100%; height: 48px; border: 0 !important; border-radius: 12px !important; font-size: 16px; font-weight: 700; background: linear-gradient(90deg, #16a34a, #22c55e) !important; box-shadow: 0 10px 24px rgba(22,163,74,.3); }
.lg-reg { margin-top: 16px; text-align: center; color: #64748b; font-size: 14px; a { color: #16a34a; margin: 0 6px; cursor: pointer; font-weight: 600; } }
@media (max-width: 820px) { .lg-card { grid-template-columns: 1fr; } .lg-brand { min-height: 0; } }
</style>
