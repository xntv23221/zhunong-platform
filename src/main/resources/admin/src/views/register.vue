<template>
	<div>
		<div class="register-container" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
			<el-form v-if="pageFlag=='register'" ref="ruleForm" class="rgs-form animate__animated animate__" :model="ruleForm" :rules="rules">
				<div class="rgs-form2">
					<div class="title">高校助农互联网商城注册</div>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<el-input  v-model="ruleForm.yonghuzhanghao" :readonly="ro.yonghuzhanghao" autocomplete="off" placeholder="用户账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<el-input  v-model="ruleForm.mima" :readonly="ro.mima" autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" :readonly="ro.mima" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<el-input  v-model="ruleForm.yonghuxingming" :readonly="ro.yonghuxingming" autocomplete="off" placeholder="用户姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<el-select filterable v-model="ruleForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in yonghuxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<el-input  v-model="ruleForm.lianxidianhua" :readonly="ro.lianxidianhua" autocomplete="off" placeholder="联系电话"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='yonghu'">
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="yonghutouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'">
						<el-input  v-model="ruleForm.nongminzhanghao" :readonly="ro.nongminzhanghao" autocomplete="off" placeholder="农民账号"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'">
						<el-input  v-model="ruleForm.mima" :readonly="ro.mima" autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'">
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" :readonly="ro.mima" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'">
						<el-input  v-model="ruleForm.nongminxingming" :readonly="ro.nongminxingming" autocomplete="off" placeholder="农民姓名"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'">
						<el-select filterable v-model="ruleForm.xingbie" placeholder="请选择性别" :disabled="ro.xingbie">
							<el-option
								v-for="(item,index) in nongminxingbieOptions"
								v-bind:key="index"
								:label="item"
								:value="item">
							</el-option>
						</el-select>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'">
						<el-input  v-model="ruleForm.lianxidianhua" :readonly="ro.lianxidianhua" autocomplete="off" placeholder="联系电话"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='nongmin'">
						<file-upload
							tip="点击上传头像"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="nongmintouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'">
						<el-input  v-model="ruleForm.gaoxiaomingcheng" :readonly="ro.gaoxiaomingcheng" autocomplete="off" placeholder="高校名称"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'">
						<el-input  v-model="ruleForm.mima" :readonly="ro.mima" autocomplete="off" placeholder="密码"  type="password"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'">
						<el-input  v-model="ruleForm.mima2" autocomplete="off" placeholder="确认密码" type="password" :readonly="ro.mima" />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'">
						<el-input  v-model="ruleForm.gaoxiaodizhi" :readonly="ro.gaoxiaodizhi" autocomplete="off" placeholder="高校地址"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'">
						<el-input  v-model="ruleForm.gaoxiaodianhua" :readonly="ro.gaoxiaodianhua" autocomplete="off" placeholder="高校电话"  type="text"  />
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'">
						<file-upload
							tip="点击上传高校封面"
							action="file/upload"
							:limit="3"
							:multiple="true"
							:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
							@change="gaoxiaotouxiangUploadChange"
						></file-upload>
					</el-form-item>
					<el-form-item class="list-item" v-if="tableName=='gaoxiao'">
						<el-input type="textarea" v-model="ruleForm.gaoxiaojianjie" autocomplete="off" placeholder="高校简介" :readonly="ro.gaoxiaojianjie" />
					</el-form-item>
					<div class="register-btn">
						<div class="register-btn1">
							<button type="button" class="r-btn" @click="login()">注册</button>
						</div>
						<div class="register-btn2">
							<div class="r-login" @click="close()"> 已有账号，立即登录</div>
						</div>
					</div>
				</div>
			</el-form>
		</div>
	</div>
</template>

<script>
	import 'animate.css'
export default {
	data() {
		return {
			ruleForm: {
			},
			forgetForm: {},
            pageFlag : '',
			tableName:"",
			rules: {},
			ro: {},
            yonghuxingbieOptions: [],
            nongminxingbieOptions: [],
			indexBgUrl: '',
		};
	},
	mounted(){
		this.pageFlag = this.$route.query.pageFlag
		if(this.$route.query.pageFlag=='register'){
			
			let table = this.$storage.get("loginTable");
			this.tableName = table;
			if(this.tableName=='yonghu'){
				this.ruleForm = {
					yonghuzhanghao: '',
					mima: '',
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
				this.ruleForm = {
					nongminzhanghao: '',
					mima: '',
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
				this.ruleForm = {
					gaoxiaomingcheng: '',
					mima: '',
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
				this.rules.yonghuzhanghao = [{ required: true, message: '请输入用户账号', trigger: 'blur' }]
			}
			if ('yonghu' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('yonghu' == this.tableName) {
				this.rules.yonghuxingming = [{ required: true, message: '请输入用户姓名', trigger: 'blur' }]
			}
			if ('nongmin' == this.tableName) {
				this.rules.nongminzhanghao = [{ required: true, message: '请输入农民账号', trigger: 'blur' }]
			}
			if ('nongmin' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			if ('nongmin' == this.tableName) {
				this.rules.nongminxingming = [{ required: true, message: '请输入农民姓名', trigger: 'blur' }]
			}
			if ('gaoxiao' == this.tableName) {
				this.rules.gaoxiaomingcheng = [{ required: true, message: '请输入高校名称', trigger: 'blur' }]
			}
			if ('gaoxiao' == this.tableName) {
				this.rules.mima = [{ required: true, message: '请输入密码', trigger: 'blur' }]
			}
			this.yonghuxingbieOptions = "男,女".split(',')
			this.nongminxingbieOptions = "男,女".split(',')
		}
	},
	created() {
		this.$http.get('config/info?name=bRegisterBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
	},
	destroyed() {
		  	},
	methods: {
		changeRules(name){
			if(this.rules[name]){
				return true
			}
			return false
		},
		// 获取uuid
		getUUID () {
			return new Date().getTime();
		},
		close(){
			this.$router.push({ path: "/login" });
		},
        yonghutouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        nongmintouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },
        gaoxiaotouxiangUploadChange(fileUrls) {
            this.ruleForm.touxiang = fileUrls;
        },

        // 多级联动参数


		// 注册
		login() {
			var url=this.tableName+"/register";
			if((!this.ruleForm.yonghuzhanghao) && `yonghu` == this.tableName){
				this.$message.error(`用户账号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `yonghu` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `yonghu` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.yonghuxingming) && `yonghu` == this.tableName){
				this.$message.error(`用户姓名不能为空`);
				return
			}
			if(`yonghu` == this.tableName && this.ruleForm.lianxidianhua &&(!this.$validate.isMobile(this.ruleForm.lianxidianhua))){
				this.$message.error(`联系电话应输入手机格式`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if((!this.ruleForm.nongminzhanghao) && `nongmin` == this.tableName){
				this.$message.error(`农民账号不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `nongmin` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `nongmin` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if((!this.ruleForm.nongminxingming) && `nongmin` == this.tableName){
				this.$message.error(`农民姓名不能为空`);
				return
			}
			if(`nongmin` == this.tableName && this.ruleForm.lianxidianhua &&(!this.$validate.isMobile(this.ruleForm.lianxidianhua))){
				this.$message.error(`联系电话应输入手机格式`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			if((!this.ruleForm.gaoxiaomingcheng) && `gaoxiao` == this.tableName){
				this.$message.error(`高校名称不能为空`);
				return
			}
			if((!this.ruleForm.mima) && `gaoxiao` == this.tableName){
				this.$message.error(`密码不能为空`);
				return
			}
			if((this.ruleForm.mima!=this.ruleForm.mima2) && `gaoxiao` == this.tableName){
				this.$message.error(`两次密码输入不一致`);
				return
			}
			if(`gaoxiao` == this.tableName && this.ruleForm.gaoxiaodianhua &&(!this.$validate.isMobile(this.ruleForm.gaoxiaodianhua))){
				this.$message.error(`高校电话应输入手机格式`);
				return
			}
            if(this.ruleForm.touxiang!=null) {
                this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
            }
			this.$http({
				url: url,
				method: "post",
				data:this.ruleForm
			}).then(({ data }) => {
				if (data && data.code === 0) {
					this.$message({
						message: "注册成功",
						type: "success",
						duration: 1500,
						onClose: () => {
							this.$router.replace({ path: "/login" });
						}
					});
				} else {
					this.$message.error(data.msg);
				}
			});
		},
	}
};
</script>

<style lang="scss" scoped>
.register-container {
	position: relative;
	background: url(http://codegen.caihongy.cn/20250926/183867aad2b6479eb1d73d908e898f72.png)  no-repeat  center   center /  100% 100%;
	background: url(http://codegen.caihongy.cn/20250926/183867aad2b6479eb1d73d908e898f72.png)  no-repeat  center   center /  100% 100%;
	display: flex;
	width: 100%;
	min-height: 100vh;
	justify-content: center;
	align-items: center;
	position: relative;
	.rgs-form {
		.rgs-form2 {
		border-radius: 24px;
		padding: 0 57px 0 57px;
		margin: auto 98px auto 0;
		display: flex;
		width: 673px;
		flex-wrap: wrap;
		}
		border-radius: 0;
		padding: 0 7% 0 0;
		z-index: 1;
		flex-direction: column;
		display: flex;
		width: 100%;
		align-items: flex-end;
		flex-wrap: wrap;
		height: auto;
		.title {
			margin: 10px 0 20px 0;
			text-shadow: none;
			color: #29C41F;
			background: none;
			font-weight: 600;
			width: 100%;
			font-size: 26px;
			text-align: center;
		}
		.list-item {
			border-radius: 5px;
			padding: 0;
			margin: 10px 0;
			width: 100%;
			::v-deep .el-form-item__content {
				display: block;
			}
			.el-input {
				width: 100%;
			}
			.el-input ::v-deep .el-input__inner {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			.el-input ::v-deep .el-input__inner:focus {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			.el-input-number {
				width: 100%;
			}
			.el-input-number ::v-deep .el-input__inner {
				text-align: center;
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			.el-input-number ::v-deep .el-input__inner:focus {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			.el-input-number ::v-deep .el-input-number__decrease {
				display: none;
			}
			.el-input-number ::v-deep .el-input-number__increase {
				display: none;
			}
			.el-select {
				width: 100%;
			}
			.el-select ::v-deep .el-input__inner {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			.el-select ::v-deep .el-input__inner:focus {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			.el-date-editor {
				width: 100%;
			}
			.el-date-editor ::v-deep .el-input__inner {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 30px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			.el-date-editor ::v-deep .el-input__inner:focus {
				border: 1px solid #DADFE6;
				border-radius: 4px;
				padding: 0 30px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			.el-date-editor.el-input {
				width: 100%;
			}
			::v-deep .el-upload--picture-card {
				background: transparent;
				border: 0;
				border-radius: 0;
				width: auto;
				height: auto;
				line-height: initial;
				vertical-align: middle;
			}
			::v-deep .upload .upload-img {
				border: 1px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				margin: 10px 0 0;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			::v-deep .el-upload-list .el-upload-list__item {
				border: 1px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				margin: 10px 0 0;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			::v-deep .el-upload .el-icon-plus {
				border: 1px solid #ddd;
				cursor: pointer;
				border-radius: 4px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				margin: 10px 0 0;
				color: #999;
				background: #fff;
				width: 90px;
				font-size: 26px;
				line-height: 60px;
				text-align: center;
				height: 60px;
			}
			::v-deep .el-upload__tip {
				color: #666;
				font-size: 16px;
			}
			::v-deep .el-input__inner::placeholder {
				color: #9E9E9E;
				font-size: 16px;
			}
			.required {
				position: relative;
			}
			.required::after{
				color: red;
				left: 120px;
				position: absolute;
				content: "*";
			}
			.editor {
				border: 1px solid  #DADFE6;
				border-radius: 4px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				background: #fff;
				width: 100%!important;
				height: auto;
			}
			.editor>.avatar-uploader {
				line-height: 0;
				height: 0;
			}
		}
		.list-item.email {
			input {
				border: 1px solid #DADFE6;
				border-radius: 4px 0 0 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				flex: 1;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			input:focus {
				border: 1px solid #DADFE6;
				border-radius: 4px 0 0 4px;
				padding: 0 10px;
				box-shadow: inset 0px 3px 6px 1px rgba(0,0,0,0.16);
				color: #333;
				flex: 1;
				background: #fff;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
			input::placeholder {
				color: #9E9E9E;
				font-size: 16px;
			}
			button {
				border: 0;
				cursor: pointer;
				padding: 0 10px;
				margin: 0;
				color: #fff;
				font-size: 16px;
				border-color: #d1d1d1;
				border-radius: 0 4px 4px 0;
				box-shadow: none;
				outline: none;
				background: #29C41F;
				width: 130px;
				border-width: 0 0 0px;
				border-style: solid;
				height: 45px;
			}
			button:hover {
				color: #4c7bf5;
				border-color: #4c7bf5;
			}
		}
		.register-btn {
			margin: 10px 0;
			display: flex;
			width: 100%;
			justify-content: center;
			flex-wrap: wrap;
		}
		.register-btn1 {
			margin: 0 20px 0 0;
			width: 100%;
		}
		.register-btn2 {
			width: 100%;
		}
		.r-btn {
			border: 0;
			cursor: pointer;
			border-radius: 24px;
			outline: none;
			color: #fff;
			background: #29C41F;
			font-weight: bold;
			width: 100%;
			font-size: 20px;
			height: 50px;
		}
		.r-btn:hover {
			opacity: 0.5;
		}
		.r-login {
			border: 0;
			cursor: pointer;
			padding: 0 24px;
			margin: 10px auto;
			color: #0D1818;
			font-weight: bold;
			font-size: 16px;
			line-height: 45px;
			border-radius: 24px;
			outline: none;
			background: none;
			width: 100%;
			text-align: center;
			height: 45px;
		}
		.r-login:hover {
			opacity: 0.8;
		}
	}
}
	
	::-webkit-scrollbar {
	  display: none;
	}
</style>
