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

<style rel="stylesheet/scss" lang="scss" scoped>
	.container {
		background-repeat: no-repeat;
		background-size: 100% 100% !important;
		background: url(http://codegen.caihongy.cn/20251215/7b78273542ca44c7907bec2473e6db02.png);
		display: flex;
		width: 100%;
		min-height: 100vh;
		justify-content: center;
		align-items: center;
		background-position: center center;
		position: relative;
		background: url(http://codegen.caihongy.cn/20251215/7b78273542ca44c7907bec2473e6db02.png);
		.rgs-form {
			border: 1px solid #707070;
			border-radius: 10px;
			padding: 40px 40px;
			margin: 0px auto;
			z-index: 10;
			background:  rgba(255,255,255,0.8);
			display: flex;
			width: 1100px;
			height: auto;
			.rgs-form2 {
				width: 70%;
				.title {
					padding: 0;
					margin: 0 0 10px 0;
					color: #000000;
					white-space: nowrap;
					font-weight: 600;
					font-size: 20px;
					border-color: #04a44b50;
					line-height: 56px;
					text-shadow: none;
					background: none;
					width: 100%;
					border-width: 0px 0 0px;
					border-style: solid;
					text-align: left;
					height: 60px;
				}
				.subtitle {
					margin: 0 0 10px 0;
					text-shadow: 4px 4px 2px rgba(64, 158, 255, .5);
					color: rgba(64, 158, 255, 1);
					width: 100%;
					font-size: 20px;
					line-height: 44px;
					text-align: center;
				}
				.list-item {
					padding: 0;
					margin: 15px auto 0;
					width: 100%;
					border-color: #ddd;
					border-width: 0 0 0px;
					position: relative;
					border-style: solid;
					height: auto;
					/deep/.el-form-item__content {
						padding: 0 0 0 120px;
						display: block;
						width: 100%;
						.label {
							padding: 0 5px 0 0;
							z-index: 9;
							color: #333;
							left: 0;
							width: 120px;
							font-size: 16px;
							line-height: 40px;
							position: absolute !important;
							text-align: right;
						}
						
						.required {
							position: relative;
						}
						.required::after{
							color: red;
							left: 110px;
							position: absolute;
							content: "*";
						}
						.el-input {
							border-radius: 100px;
							flex: 1;
							background: rgba(255,255,255,0.3);
							width: 100%;
						}
						.el-input .el-input__inner {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							background: none;
							width: 100%;
							font-size: 14px;
							height: 40px;
						}
						.el-input .el-input__inner:focus {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							background: none;
							width: 100%;
							font-size: 14px;
							height: 40px;
						}
						.el-input-number {
							border-radius: 100px;
							flex: 1;
							background: rgba(255,255,255,0.3);
							width: 100%;
						}
						.el-input-number .el-input__inner {
							text-align: left;
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							background: none;
							width: 100%;
							font-size: 14px;
							height: 40px;
						}
						.el-input-number .el-input-number__decrease {
							display: none;
						}
						.el-input-number .el-input-number__increase {
							display: none;
						}
						.el-select {
							border-radius: 100px;
							flex: 1;
							background: rgba(255,255,255,0.3);
							width: 100%;
						}
						.el-select .el-input__inner {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							background: none;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-select .el-input__inner:focus {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							background: none;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-date-editor {
							border-radius: 100px;
							flex: 1;
							background: rgba(255,255,255,0.3);
							width: 100%;
						}
						.el-date-editor .el-input__inner {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px 0 30px;
							box-shadow: none;
							outline: none;
							color: #666;
							background: none;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-date-editor .el-input__inner:focus {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px 0 30px;
							box-shadow: none;
							outline: none;
							color: #666;
							background: none;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-upload--picture-card {
							background: transparent;
							border: 0;
							border-radius: 0;
							width: auto;
							height: auto;
							line-height: initial;
							vertical-align: middle;
						}
						.upload .upload-img {
							border: 1px solid #ddd;
							cursor: pointer;
							border-radius: 4px;
							color: #999;
							background: rgba(255,255,255,.2);
							object-fit: cover;
							width: 60px;
							font-size: 22px;
							line-height: 60px;
							text-align: center;
							height: 60px;
						}
						.el-upload-list .el-upload-list__item {
							border: 1px solid #ddd;
							cursor: pointer;
							border-radius: 4px;
							color: #999;
							background: rgba(255,255,255,.2);
							object-fit: cover;
							width: 60px;
							font-size: 22px;
							line-height: 60px;
							text-align: center;
							height: 60px;
							font-size: 14px;
							line-height: 1.8;
						}
						.el-upload .el-icon-plus {
							border: 1px solid #ddd;
							cursor: pointer;
							border-radius: 4px;
							color: #999;
							background: rgba(255,255,255,.2);
							object-fit: cover;
							width: 60px;
							font-size: 22px;
							line-height: 60px;
							text-align: center;
							height: 60px;
						}
						.el-upload__tip {
							color: #999;
							font-size: 16px;
						}
						.emailInput {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							margin: 0;
							outline: none;
							color: #666;
							background: none;
							flex: 1;
							width: 100%;
							font-size: 14px;
							height: 40px;
						}
						.emailInput:focus {
							border: 0;
							border-radius: 0 20px 20px 0;
							padding: 0 10px;
							box-shadow: none;
							outline: none;
							color: #666;
							flex: 1;
							background: none;
							width: 100%;
							font-size: 16px;
							height: 40px;
						}
						.el-btn {
							border: 0;
							cursor: pointer;
							padding: 0 15px;
							margin: 0 0 5px;
							color: #fff;
							font-size: 15px;
							float: right;
							border-radius: 2px;
							box-shadow: 0 0 0px rgba(64, 158, 255, .5);
							outline: none;
							background: #ABDE53;
							width: auto;
							height: 40px;
						}
						.el-btn:hover {
							opacity: 0.8;
						}
						
						.el-input__inner::placeholder {
							color: #999;
							font-size: 16px;
						}
						input::placeholder {
							color: #999;
							font-size: 16px;
						}
						.editor {
							border-radius: 50px;
							padding: 10px;
							margin: 20px;
							background: rgba(255,255,255,0.3);
							width: 100%;
							height: auto;
						}
						.editor .ql-toolbar {
							border: none;
							box-shadow: none;
							background: none;
						}
						.editor .ql-container {
							border: none;
							background: none;
						}
						.editor .ql-container .ql-blank::before {
							color: #999;
						}
					}
				}
				.register-btn {
					padding: 0;
					margin: 20px 0 0;
					width: 100%;
					text-align: center;
				}
				.register-btn1 {
					display: inline-block;
					width: 50%;
					text-align: center;
				}
				.register-btn2 {
					display: inline-block;
					width: 100%;
					text-align: center;
				}
				.register_btn {
					cursor: pointer;
					padding: 0 20px;
					margin: 0 20px 20px 0;
					color: #fff;
					display: inline-block;
					text-decoration: none;
					font-size: 20px;
					border-color: #ff6f3c50;
					line-height: 50px;
					border-radius: 2px;
					background: #ABDE53;
					width: 100%;
					border-width: 0 0 0px;
					border-style: solid;
					min-width: 120px;
					height: 50px;
				}
				.register_btn:hover {
					opacity: 0.8;
				}
				.has_btn {
					cursor: pointer;
					padding: 0;
					color: #000;
					text-decoration: none;
					display: inline-block;
					font-size: 16px;
				}
				.has_btn:hover {
					opacity: 0.8;
				}
			}
			.idea1 {
				padding: 30px 0;
				margin: auto;
				background: url(http://codegen.caihongy.cn/20251215/2ec7a3d6d1674e20b4846a89e8a284e9.png)no-repeat  top left;
				font-weight: 500;
				display: block;
				width: 30%;
				font-size: 40px;
				height: 40px;
				order: -1;
			}
			.idea2 {
				background: blue;
				display: none;
				width: 100%;
				height: 40px;
			}
		}
	}
	
	::-webkit-scrollbar {
		display: none;
	}

	.container {
		background: url('/zhunong79djy629/upload/Mountain.jpg') no-repeat center center / cover;
		padding: 32px;
		.rgs-form {
			border: 1px solid rgba(15, 23, 42, 0.08);
			border-radius: 28px;
			padding: 32px;
			background: rgba(255,255,255,0.94);
			box-shadow: 0 28px 60px rgba(15, 23, 42, 0.12);
			backdrop-filter: blur(18px);
			width: calc(100vw - 64px);
			max-width: 1100px;
			.rgs-form2 {
				width: 100%;
				.title {
					margin: 0 0 24px;
					color: #111827;
					font-size: 34px;
					line-height: 1.3;
					height: auto;
				}
				.list-item {
					margin-top: 14px;
					/deep/.el-form-item__content {
						padding-left: 110px;
						.label {
							color: #111827;
							width: 110px;
						}
						.required::after {
							left: 100px;
						}
						.el-input,
						.el-select {
							background: #fff;
						}
						.el-input .el-input__inner,
						.el-select .el-input__inner {
							border: 1px solid #e5e7eb;
							border-radius: 999px;
							height: 52px;
							line-height: 52px;
							background: #fff;
							color: #111827;
						}
					}
				}
				.register-btn {
					margin-top: 28px;
					.register_btn {
						border-radius: 999px;
						background: #111827;
						border-color: #111827;
						height: 54px;
						line-height: 54px;
						box-shadow: 0 14px 28px rgba(15, 23, 42, 0.18);
					}
					.has_btn {
						color: #374151;
					}
				}
			}
			.idea1,
			.idea2 {
				display: none;
			}
		}
	}
	@media screen and (max-width: 960px) {
		.container {
			padding: 18px;
			.rgs-form {
				padding: 22px;
				width: calc(100vw - 36px);
				max-width: 720px;
				.rgs-form2 {
					.title {
						font-size: 28px;
					}
					.list-item /deep/.el-form-item__content {
						padding-left: 0;
						.label {
							position: static !important;
							width: 100%;
							text-align: left;
						}
						.required::after {
							left: auto;
							right: auto;
							margin-left: 4px;
						}
					}
				}
			}
		}
	}
</style>
