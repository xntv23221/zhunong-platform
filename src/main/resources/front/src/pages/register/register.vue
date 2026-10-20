<template>
	<div>

		<div class="container" :style="{'backgroundImage': indexBgUrl?`url(${$config.baseUrl + indexBgUrl})`:''}">
			<el-form class='rgs-form animate__animated animate__' v-if="pageFlag=='register'" ref="registerForm" :model="registerForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">高校助农互联网商城注册</p></div>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="yonghuzhanghao">
						<div class="label" :class="changeRules('yonghuzhanghao')?'required':''">用户账号：</div>
						<el-input v-model="registerForm.yonghuzhanghao" :readonly="ro.yonghuzhanghao" placeholder="请输入用户账号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="yonghuxingming">
						<div class="label" :class="changeRules('yonghuxingming')?'required':''">用户姓名：</div>
						<el-input v-model="registerForm.yonghuxingming" :readonly="ro.yonghuxingming" placeholder="请输入用户姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="xingbie">
						<div class="label" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select filterable v-model="registerForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in yonghuxingbieOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="lianxidianhua">
						<div class="label" :class="changeRules('lianxidianhua')?'required':''">联系电话：</div>
						<el-input v-model="registerForm.lianxidianhua" :readonly="ro.lianxidianhua" placeholder="请输入联系电话" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="yonghutouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'" prop="nongminzhanghao">
						<div class="label" :class="changeRules('nongminzhanghao')?'required':''">农民账号：</div>
						<el-input v-model="registerForm.nongminzhanghao" :readonly="ro.nongminzhanghao" placeholder="请输入农民账号" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'" prop="nongminxingming">
						<div class="label" :class="changeRules('nongminxingming')?'required':''">农民姓名：</div>
						<el-input v-model="registerForm.nongminxingming" :readonly="ro.nongminxingming" placeholder="请输入农民姓名" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'" prop="xingbie">
						<div class="label" :class="changeRules('xingbie')?'required':''">性别：</div>
						<el-select filterable v-model="registerForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in nongminxingbieOptions"
								:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'" prop="lianxidianhua">
						<div class="label" :class="changeRules('lianxidianhua')?'required':''">联系电话：</div>
						<el-input v-model="registerForm.lianxidianhua" :readonly="ro.lianxidianhua" placeholder="请输入联系电话" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">头像：</div>
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="nongmintouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'" prop="gaoxiaomingcheng">
						<div class="label" :class="changeRules('gaoxiaomingcheng')?'required':''">高校名称：</div>
						<el-input v-model="registerForm.gaoxiaomingcheng" :readonly="ro.gaoxiaomingcheng" placeholder="请输入高校名称" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'" prop="mima">
						<div class="label" :class="changeRules('mima')?'required':''">密码：</div>
						<el-input v-model="registerForm.mima" type="password" placeholder="请输入密码"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'" prop="mima2">
						<div class="label" :class="changeRules('mima')?'required':''">确认密码：</div>
						<el-input v-model="registerForm.mima2" type="password" placeholder="请再次输入密码" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'" prop="gaoxiaodizhi">
						<div class="label" :class="changeRules('gaoxiaodizhi')?'required':''">高校地址：</div>
						<el-input v-model="registerForm.gaoxiaodizhi" :readonly="ro.gaoxiaodizhi" placeholder="请输入高校地址" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'" prop="gaoxiaodianhua">
						<div class="label" :class="changeRules('gaoxiaodianhua')?'required':''">高校电话：</div>
						<el-input v-model="registerForm.gaoxiaodianhua" :readonly="ro.gaoxiaodianhua" placeholder="请输入高校电话" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'" prop="touxiang">
						<div class="label" :class="changeRules('touxiang')?'required':''">高校封面：</div>
						<file-upload
							tip="点击上传高校封面"
							action="file/upload"
							:limit="1"
							:multiple="true"
							:fileUrls="registerForm.touxiang?registerForm.touxiang:''"
							@change="gaoxiaotouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<el-button class="register_btn" type="primary" @click="submitForm('registerForm')">注册</el-button>
						</div>
						<div class="register-btn2">
							<router-link class="has_btn" to="/login">已有账号，直接登录</router-link>
						</div>
					</div>
				</div>
				<div class="idea1">欢迎注册</div>
				<div class="idea2"></div>
			</el-form>
		</div>
	</div>
</div>
</template>

<script>
	import 'animate.css';

export default {
    //数据集合
    data() {
		return {
            pageFlag : '',
			tableName: '',
			registerForm: {},
			forgetForm: {},
			rules: {},
			ro: {},
			requiredRules: {},
            yonghuxingbieOptions: [],
            nongminxingbieOptions: [],
			indexBgUrl: '',
		}
    },
	mounted() {
		if(this.$route.query.pageFlag=='register'){
			this.tableName = this.$route.query.role;
			if(this.tableName=='yonghu'){
				this.registerForm = {
					yonghuzhanghao: '',
					mima: '',
					mima2: '',
					yonghuxingming: '',
					xingbie: '',
					lianxidianhua: '',
					touxiang: '',
					money: '',
				}
				this.ro = {
					yonghuzhanghao: false,
					mima: false,
					yonghuxingming: false,
					xingbie: false,
					lianxidianhua: false,
					touxiang: false,
					money: false,
				}
			}
			if(this.tableName=='nongmin'){
				this.registerForm = {
					nongminzhanghao: '',
					mima: '',
					mima2: '',
					nongminxingming: '',
					xingbie: '',
					lianxidianhua: '',
					touxiang: '',
					money: '',
				}
				this.ro = {
					nongminzhanghao: false,
					mima: false,
					nongminxingming: false,
					xingbie: false,
					lianxidianhua: false,
					touxiang: false,
					money: false,
				}
			}
			if(this.tableName=='gaoxiao'){
				this.registerForm = {
					gaoxiaomingcheng: '',
					mima: '',
					mima2: '',
					gaoxiaodizhi: '',
					gaoxiaodianhua: '',
					touxiang: '',
					gaoxiaojianjie: '',
					money: '',
				}
				this.ro = {
					gaoxiaomingcheng: false,
					mima: false,
					gaoxiaodizhi: false,
					gaoxiaodianhua: false,
					touxiang: false,
					gaoxiaojianjie: false,
					money: false,
				}
			}
			if ('yonghu' == this.tableName) {
				this.rules.yonghuzhanghao = [{ required: true, message: '请输入用户账号', trigger: 'blur' }];
				this.requiredRules.yonghuzhanghao = [{ required: true, message: '请输入用户账号', trigger: 'blur' }]
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }];
				this.requiredRules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
				this.rules.yonghuxingming = [{ required: true, message: '请输入用户姓名', trigger: 'blur' }];
				this.requiredRules.yonghuxingming = [{ required: true, message: '请输入用户姓名', trigger: 'blur' }]
				this.yonghuxingbieOptions = "男,女".split(',');
				this.rules.lianxidianhua = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
				this.rules.money = [{ required: true, validator: this.$validate.isNumber, trigger: 'blur' }];
			}
			if ('nongmin' == this.tableName) {
				this.rules.nongminzhanghao = [{ required: true, message: '请输入农民账号', trigger: 'blur' }];
				this.requiredRules.nongminzhanghao = [{ required: true, message: '请输入农民账号', trigger: 'blur' }]
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }];
				this.requiredRules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
				this.rules.nongminxingming = [{ required: true, message: '请输入农民姓名', trigger: 'blur' }];
				this.requiredRules.nongminxingming = [{ required: true, message: '请输入农民姓名', trigger: 'blur' }]
				this.nongminxingbieOptions = "男,女".split(',');
				this.rules.lianxidianhua = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
				this.rules.money = [{ required: true, validator: this.$validate.isNumber, trigger: 'blur' }];
			}
			if ('gaoxiao' == this.tableName) {
				this.rules.gaoxiaomingcheng = [{ required: true, message: '请输入高校名称', trigger: 'blur' }];
				this.requiredRules.gaoxiaomingcheng = [{ required: true, message: '请输入高校名称', trigger: 'blur' }]
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }];
				this.requiredRules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
				this.rules.gaoxiaodianhua = [{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }];
				this.rules.money = [{ required: true, validator: this.$validate.isNumber, trigger: 'blur' }];
			}
		}
	},
    created() {
		this.$http.get('config/info?name=fRegisterBackgroudImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		this.pageFlag = this.$route.query.pageFlag;
    },
    //方法集合
    methods: {
		changeRules(name){
			if(this.requiredRules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
        // 下二随
		yonghutouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},
		nongmintouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},
		gaoxiaotouxiangUploadChange(fileUrls) {
			this.registerForm.touxiang = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
		},

		// 多级联动参数


		submitForm(formName) {
			this.$refs[formName].validate((valid) => {
				if (valid) {
					var url=this.tableName+"/register";
					if(`yonghu` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if(`nongmin` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					if(`gaoxiao` == this.tableName && this.registerForm.mima!=this.registerForm.mima2) {
						this.$message.error(`两次密码输入不一致`);
						return
					}
					this.$http.post(url, this.registerForm).then(res => {
						if (res.data.code === 0) {
							this.$message({
								message: '注册成功',
								type: 'success',
								duration: 1500,
								onClose: () => {
									this.$router.push('/login');
								}
							});
						} else {
							this.$message.error(res.data.msg);
						}
					});
				} else {
					return false;
				}
			});
		},
		resetForm(formName) {
			this.$refs[formName].resetFields();
		},
    }
}
</script>

<style lang="scss" scoped>
.container { min-height: 100vh; padding: 48px 20px; box-sizing: border-box; background: radial-gradient(1200px 600px at 10% 10%, #dcfce7 0%, transparent 60%), radial-gradient(900px 500px at 90% 90%, #bbf7d0 0%, transparent 60%), #f5f7f6; }
.rgs-form { width: 720px; max-width: 100%; margin: 0 auto; background: #fff; border-radius: 24px; box-shadow: 0 30px 80px rgba(20,83,45,.15); overflow: hidden; }
.rgs-form2 { padding: 40px 48px; display: grid; grid-template-columns: 1fr 1fr; column-gap: 20px; }
.title { grid-column: 1 / -1; font-size: 24px; font-weight: 800; color: #0f172a; padding-bottom: 18px; margin-bottom: 8px; border-bottom: 1px solid #e5ece8; &::before { content: '🌾 '; } }
.list-item { margin-bottom: 18px; .label { font-size: 13px; color: #475569; margin-bottom: 6px; font-weight: 600; } .required::before { content: '*'; color: #ef4444; margin-right: 3px; } ::v-deep .el-select { width: 100%; } }
.register-btn, .r-btn, ::v-deep .el-button--primary { width: 100%; }
.rgs-form2 > div:last-child, .rgs-form2 > .register-btn, .rgs-form2 > .r-login { grid-column: 1 / -1; }
::v-deep .el-input__inner { height: 44px; }
.idea1, .idea2 { display: none; }
.register-btn { grid-column: 1 / -1; text-align: center; .has_btn { display: inline-block; margin-top: 14px; color: #16a34a; } }
@media (max-width: 700px) { .rgs-form2 { grid-template-columns: 1fr; padding: 28px; } }
</style>
