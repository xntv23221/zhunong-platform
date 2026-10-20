<template>
	<div :style='{"minHeight":"100vh","padding":"10px 20px","background":" linear-gradient( 180deg, #EFFFEF 0%, #F8F8F8 100%)"}'>
		<el-form
			:style='{"border":"0px solid #ddd","padding":"50px 30px","borderRadius":"16px","flexWrap":"wrap","background":"linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%)","display":"flex"}'
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			label-width="180px"
		>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='yonghu'"  label="用户账号" prop="yonghuzhanghao">
					<el-input v-model="ruleForm.yonghuzhanghao" :readonly="ro.yonghuzhanghao" placeholder="用户账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='yonghu'"  label="用户姓名" prop="yonghuxingming">
					<el-input v-model="ruleForm.yonghuxingming" :readonly="ro.yonghuxingming" placeholder="用户姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}' v-if="flag=='yonghu'"  label="性别" prop="xingbie">
					<el-select filterable v-model="ruleForm.xingbie" :disabled="ro.xingbie" placeholder="请选择性别">
						<el-option
							v-for="(item,index) in yonghuxingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='yonghu'"  label="联系电话" prop="lianxidianhua">
					<el-input v-model="ruleForm.lianxidianhua" :readonly="ro.lianxidianhua" placeholder="联系电话" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}' v-if="flag=='yonghu'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="yonghutouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='nongmin'"  label="农民账号" prop="nongminzhanghao">
					<el-input v-model="ruleForm.nongminzhanghao" :readonly="ro.nongminzhanghao" placeholder="农民账号" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='nongmin'"  label="农民姓名" prop="nongminxingming">
					<el-input v-model="ruleForm.nongminxingming" :readonly="ro.nongminxingming" placeholder="农民姓名" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}' v-if="flag=='nongmin'"  label="性别" prop="xingbie">
					<el-select filterable v-model="ruleForm.xingbie" :disabled="ro.xingbie" placeholder="请选择性别">
						<el-option
							v-for="(item,index) in nongminxingbieOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='nongmin'"  label="联系电话" prop="lianxidianhua">
					<el-input v-model="ruleForm.lianxidianhua" :readonly="ro.lianxidianhua" placeholder="联系电话" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}' v-if="flag=='nongmin'" label="头像" prop="touxiang">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="nongmintouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='gaoxiao'"  label="高校名称" prop="gaoxiaomingcheng">
					<el-input v-model="ruleForm.gaoxiaomingcheng" :readonly="ro.gaoxiaomingcheng" placeholder="高校名称" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='gaoxiao'"  label="高校地址" prop="gaoxiaodizhi">
					<el-input v-model="ruleForm.gaoxiaodizhi" :readonly="ro.gaoxiaodizhi" placeholder="高校地址" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='gaoxiao'"  label="高校电话" prop="gaoxiaodianhua">
					<el-input v-model="ruleForm.gaoxiaodianhua" :readonly="ro.gaoxiaodianhua" placeholder="高校电话" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}' v-if="flag=='gaoxiao'" label="高校封面" prop="touxiang">
					<file-upload
						tip="点击上传高校封面"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.touxiang?ruleForm.touxiang:''"
						@change="gaoxiaotouxiangUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}'   v-if="flag=='gaoxiao'"  label="高校简介" prop="gaoxiaojianjie">
					<el-input v-model="ruleForm.gaoxiaojianjie" :readonly="ro.gaoxiaojianjie" placeholder="高校简介" clearable></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}' v-if="flag=='users'" label="用户名" prop="username">
					<el-input v-model="ruleForm.username" placeholder="用户名"></el-input>
				</el-form-item>
				<el-form-item :style='{"width":"calc(50% - 0px)","margin":"10px 0px 10px 0 ","display":"flex"}' v-if="flag=='users'" label="头像" prop="image">
					<file-upload
						tip="点击上传头像"
						action="file/upload"
						:limit="1"
						:multiple="false"
						:fileUrls="ruleForm.image?ruleForm.image:''"
						@change="usersimageUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item :style='{"padding":"0 30px","margin":"30px  auto 0 auto","flexWrap":"wrap","textAlign":"center","background":"none","display":"block","width":"100%","justifyContent":"center"}'>
					<el-button class="btn3" type="primary" @click="onUpdateHandler">
						<span class="icon iconfont icon-queren15"></span>
						确定 
					</el-button>
				</el-form-item>
		</el-form>
	</div>
</template>
<script>
// 校验引入
	import { 
		isNumber,
		isMobile,
	} from "@/utils/validate";

	export default {
		data() {
			return {
				ruleForm: {},
				flag: '',
				usersFlag: false,
				yonghuxingbieOptions: [],
				nongminxingbieOptions: [],
			};
		},
		mounted() {
			var table = this.$storage.get("sessionTable");
			this.flag = table;
			this.$http({
				url: `${this.$storage.get("sessionTable")}/session`,
				method: "get"
			}).then(({ data }) => {
				if (data && data.code === 0) {
					if(table == 'yonghu') {
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
					if(table == 'nongmin') {
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
					if(table == 'gaoxiao') {
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

					this.ruleForm = data.data;
				} else {
					this.$message.error(data.msg);
				}
			});
			this.yonghuxingbieOptions = "男,女".split(',')
			this.nongminxingbieOptions = "男,女".split(',')
		},
		methods: {
			yonghutouxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls;
			},
			nongmintouxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls;
			},
			gaoxiaotouxiangUploadChange(fileUrls) {
				this.ruleForm.touxiang = fileUrls;
			},
			usersimageUploadChange(fileUrls) {
				this.ruleForm.image = fileUrls;
			},
			onUpdateHandler() {
				if((!this.ruleForm.yonghuzhanghao)&& 'yonghu'==this.flag){
					this.$message.error('用户账号不能为空');
					return
				}
				if((!this.ruleForm.mima)&& 'yonghu'==this.flag){
					this.$message.error('密码不能为空');
					return
				}
				if((!this.ruleForm.yonghuxingming)&& 'yonghu'==this.flag){
					this.$message.error('用户姓名不能为空');
					return
				}
				if('yonghu' ==this.flag && this.ruleForm.lianxidianhua&&(!isMobile(this.ruleForm.lianxidianhua))){
					this.$message.error(`联系电话应输入手机格式`);
					return
				}
				if(this.ruleForm.touxiang!=null) {
					this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
				}
				if('yonghu' ==this.flag && this.ruleForm.money&&(!isNumber(this.ruleForm.money))){
					this.$message.error(`余额应输入数字`);
					return
				}
				if((!this.ruleForm.nongminzhanghao)&& 'nongmin'==this.flag){
					this.$message.error('农民账号不能为空');
					return
				}
				if((!this.ruleForm.mima)&& 'nongmin'==this.flag){
					this.$message.error('密码不能为空');
					return
				}
				if((!this.ruleForm.nongminxingming)&& 'nongmin'==this.flag){
					this.$message.error('农民姓名不能为空');
					return
				}
				if('nongmin' ==this.flag && this.ruleForm.lianxidianhua&&(!isMobile(this.ruleForm.lianxidianhua))){
					this.$message.error(`联系电话应输入手机格式`);
					return
				}
				if(this.ruleForm.touxiang!=null) {
					this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
				}
				if('nongmin' ==this.flag && this.ruleForm.money&&(!isNumber(this.ruleForm.money))){
					this.$message.error(`余额应输入数字`);
					return
				}
				if((!this.ruleForm.gaoxiaomingcheng)&& 'gaoxiao'==this.flag){
					this.$message.error('高校名称不能为空');
					return
				}
				if((!this.ruleForm.mima)&& 'gaoxiao'==this.flag){
					this.$message.error('密码不能为空');
					return
				}
				if('gaoxiao' ==this.flag && this.ruleForm.gaoxiaodianhua&&(!isMobile(this.ruleForm.gaoxiaodianhua))){
					this.$message.error(`高校电话应输入手机格式`);
					return
				}
				if(this.ruleForm.touxiang!=null) {
					this.ruleForm.touxiang = this.ruleForm.touxiang.replace(new RegExp(this.$base.url,"g"),"");
				}
				if('gaoxiao' ==this.flag && this.ruleForm.money&&(!isNumber(this.ruleForm.money))){
					this.$message.error(`余额应输入数字`);
					return
				}
				if('users'==this.flag && this.ruleForm.username.trim().length<1) {
					this.$message.error(`用户名不能为空`);
					return	
				}
				if(this.flag=='users'){
					this.ruleForm.image = this.ruleForm.image.replace(new RegExp(this.$base.url,"g"),"")
				}
				this.$http({
					url: `${this.$storage.get("sessionTable")}/update`,
					method: "post",
					data: this.ruleForm
				}).then(({ data }) => {
					if (data && data.code === 0) {
						if(this.flag=='users'){
							this.$storage.set('headportrait',this.ruleForm.image)
						}else {
							if(this.flag == 'yonghu') {
								this.$storage.set('headportrait',this.ruleForm.touxiang)
							}
							if(this.flag == 'nongmin') {
								this.$storage.set('headportrait',this.ruleForm.touxiang)
							}
							if(this.flag == 'gaoxiao') {
								this.$storage.set('headportrait',this.ruleForm.touxiang)
							}
						}
						this.$message({
							message: "修改信息成功",
							type: "success",
							duration: 1500,
							onClose: () => {
								window.location.reload();
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
	.el-date-editor.el-input {
		width: auto;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
				padding: 0 10px;
				color: #000000;
				white-space: nowrap;
				background: none;
				font-weight: 400;
				width: 180px;
				font-size: 16px;
				line-height: 45px;
				text-align: right;
				height: 64px;
			}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: auto !important;
		margin: auto !important;
		flex: 1;
		display: block;
		width: 100%;
		align-items: flex-start;
		flex-wrap: wrap;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
				border:  1px solid #29C41F;
				border-radius: 4px;
				padding: 0 12px;
				outline: none;
				color: #29C41F;
				background: #FFFFFF;
				flex: 1;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
	
	.add-update-preview .el-select /deep/ .el-input__inner {
				border:  1px solid #29C41F;
				border-radius: 4px;
				padding: 0 12px;
				outline: none;
				color: #29C41F;
				flex: 1;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
				border:  1px solid #29C41F;
				border-radius: 4px;
				padding: 0 30px;
				outline: none;
				color: #29C41F;
				flex: 1;
				width: 100%;
				font-size: 16px;
				height: 45px;
			}
	
	.add-update-preview /deep/ .el-upload--picture-card {
		background: transparent;
		border: 0;
		border-radius: 0;
		width: auto;
		height: auto;
		line-height: initial;
		vertical-align: middle;
	}
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
				border:  1px solid #29C41F;
				cursor: pointer;
				border-radius: 4px;
				color: #29C41F;
				background: #fff;
				font-weight: 600;
				width: 80px;
				font-size: 30px;
				line-height: 80px;
				text-align: center;
				height: 80px;
			}
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
				border:  1px solid #29C41F;
				cursor: pointer;
				border-radius: 4px;
				color: #29C41F;
				background: #fff;
				font-weight: 600;
				width: 80px;
				font-size: 30px;
				line-height: 80px;
				text-align: center;
				height: 80px;
			}
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
				border-radius: 4px;
				padding: 12px;
				outline: none;
				color: #29C41F;
				width: 100%;
				font-size: 16px;
				border-color: #29C41F;
				border-width: 1px;
				border-style: solid;
				height: auto;
			}
	
	.add-update-preview .btn3 {
				border: 0px solid #206cb4;
				cursor: pointer;
				padding: 0 24px;
				margin: 4px;
				color: #fff;
				font-weight: bold;
				font-size: 16px;
				border-radius: 24px  24px  24px  24px;
				outline: none;
				background: #29C41F;
				width: auto;
				min-width: 176px;
				height: 47px;
				.iconfont {
						margin: 0 2px;
						color: #fff;
						display: none;
						font-size: 16px;
						height: 40px;
					}
	}
	
	.add-update-preview .btn3:hover {
				opacity: 0.8;
			}
	.editor>.avatar-uploader {
		line-height: 0;
		height: 0;
	}
</style>
