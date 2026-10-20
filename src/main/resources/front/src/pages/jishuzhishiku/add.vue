




















<template>
	<div class="add-update-preview">
		<el-form
			class="add-update-form"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="200px"
			>
			<el-form-item class="add-item" label="知识名称" prop="zhishimingcheng">
				<el-input v-model="ruleForm.zhishimingcheng" 
					placeholder="知识名称" clearable :readonly="ro.zhishimingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item"  label="知识分类" prop="zhishifenlei">
				<el-select v-model="ruleForm.zhishifenlei" placeholder="请选择知识分类" :disabled="ro.zhishifenlei"  filterable>
					<el-option
						v-for="(item,index) in zhishifenleiOptions"
						:key="index"
						:label="item"
						:value="item">
					</el-option>
				</el-select>
			</el-form-item>
			<el-form-item class="add-item" label="专业领域" prop="zhuanyelingyu">
				<el-input v-model="ruleForm.zhuanyelingyu" 
					placeholder="专业领域" clearable :readonly="ro.zhuanyelingyu"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="知识照片" v-if="type!='cross' || (type=='cross' && !ro.zhishizhaopian)" prop="zhishizhaopian">
				<file-upload
					tip="点击上传知识照片"
					action="file/upload"
					:limit="3"
					:multiple="true"
					:disabled="ro.zhishizhaopian"
					:fileUrls="ruleForm.zhishizhaopian?ruleForm.zhishizhaopian:''"
					@change="zhishizhaopianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="知识照片" prop="zhishizhaopian">
				<img v-if="ruleForm.zhishizhaopian.substring(0,4)=='http'" class="upload-img" v-bind:key="index" :src="ruleForm.zhishizhaopian.split(',')[0]">
				<img v-else class="upload-img" v-bind:key="index" v-for="(item,index) in ruleForm.zhishizhaopian.split(',')" :src="baseUrl+item">
			</el-form-item>
			<el-form-item class="add-item" label="知识附件" prop="zhishifujian">
				<file-upload
					tip="点击上传知识附件"
					action="file/upload"
					:limit="1"
					:type="3"
					:multiple="true"
					:disabled="ro.zhishifujian"
					:fileUrls="ruleForm.zhishifujian?ruleForm.zhishifujian:''"
					@change="zhishifujianUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" label="知识视频" v-if="type!='cross' || (type=='cross' && !ro.zhishishipin)" prop="zhishishipin">
				<file-upload
					tip="点击上传知识视频"
					action="file/upload"
					:limit="1"
					:type="2"
					:multiple="true"
					:disabled="ro.zhishishipin"
					:fileUrls="ruleForm.zhishishipin?ruleForm.zhishishipin:''"
					@change="zhishishipinUploadChange"
					></file-upload>
			</el-form-item>
			<el-form-item class="add-item" v-else label="知识视频" prop="zhishishipin">
				<div class="viewBtn" v-if="ruleForm.zhishishipin" @click="download(baseUrl + ruleForm.zhishishipin )" type="success">预览</div>
				<div class="unviewBtn" v-else disabled>暂无</div>
			</el-form-item>
			<el-form-item class="add-item" label="高校名称" prop="gaoxiaomingcheng">
				<el-input v-model="ruleForm.gaoxiaomingcheng" 
					placeholder="高校名称" clearable :readonly="ro.gaoxiaomingcheng"></el-input>
			</el-form-item>
			<el-form-item class="add-item" label="知识内容" prop="zhishineirong">
				<editor 
					v-model="ruleForm.zhishineirong" 
					class="editor" 
					myQuillEditor="zhishineirong"
					action="file/upload">
				</editor>
			</el-form-item>

			<el-form-item class="add-btn-item">
				<el-button class="submitBtn"  type="primary" @click="onSubmit">
					<span class="icon iconfont icon-xiugai13"></span>
					<span class="text">提交信息</span>
				</el-button>
				<el-button class="closeBtn" @click="back()">
					<span class="icon iconfont icon-shanchu7"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				id: '',
				baseUrl: '',
				ro:{
					zhishimingcheng : false,
					zhishifenlei : false,
					zhuanyelingyu : false,
					zhishizhaopian : false,
					zhishifujian : false,
					zhishishipin : false,
					zhishineirong : false,
					gaoxiaomingcheng : false,
					discussnum : false,
					storeupnum : false,
				},
				type: '',
				userTableName: localStorage.getItem('UserTableName'),
				ruleForm: {
					zhishimingcheng: '',
					zhishifenlei: '',
					zhuanyelingyu: '',
					zhishizhaopian: '',
					zhishifujian: '',
					zhishishipin: '',
					zhishineirong: '',
					gaoxiaomingcheng: '',
					discussnum: '',
					storeupnum: '',
				},
				zhishifenleiOptions: [],

				rules: {
					zhishimingcheng: [
					],
					zhishifenlei: [
					],
					zhuanyelingyu: [
					],
					zhishizhaopian: [
					],
					zhishifujian: [
					],
					zhishishipin: [
					],
					zhishineirong: [
					],
					gaoxiaomingcheng: [
					],
					discussnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: this.$validate.isIntNumer, trigger: 'blur' },
					],
				},
				centerType: false,
			};
		},
		computed: {
			sessionForm() {
				return JSON.parse(localStorage.getItem('sessionForm'))
			},



		},
		components: {
		},
		created() {
			if(this.$route.query.centerType){
				this.centerType = true
			}
			//this.bg();
			let type = this.$route.query.type ? this.$route.query.type : '';
			this.init(type);
			this.baseUrl = this.$config.baseUrl;
		},
		methods: {
			getMakeZero(s) {
				return s < 10 ? '0' + s : s;
			},
			// 下载
			download(file ){
				window.open(`${file}`)
			},
			// 初始化
			init(type) {
				this.type = type;
				if(type=='cross'){
					var obj = JSON.parse(localStorage.getItem('crossObj'));
					for (var o in obj){
						if(o=='zhishimingcheng'){
							this.ruleForm.zhishimingcheng = obj[o];
							this.ro.zhishimingcheng = true;
							continue;
						}
						if(o=='zhishifenlei'){
							this.ruleForm.zhishifenlei = obj[o];
							this.ro.zhishifenlei = true;
							continue;
						}
						if(o=='zhuanyelingyu'){
							this.ruleForm.zhuanyelingyu = obj[o];
							this.ro.zhuanyelingyu = true;
							continue;
						}
						if(o=='zhishizhaopian'){
							this.ruleForm.zhishizhaopian = obj[o]?obj[o].split(",")[0]:'';
							this.ro.zhishizhaopian = true;
							continue;
						}
						if(o=='zhishifujian'){
							this.ruleForm.zhishifujian = obj[o];
							this.ro.zhishifujian = true;
							continue;
						}
						if(o=='zhishishipin'){
							this.ruleForm.zhishishipin = obj[o];
							this.ro.zhishishipin = true;
							continue;
						}
						if(o=='zhishineirong'){
							this.ruleForm.zhishineirong = obj[o];
							this.ro.zhishineirong = true;
							continue;
						}
						if(o=='gaoxiaomingcheng'){
							this.ruleForm.gaoxiaomingcheng = obj[o];
							this.ro.gaoxiaomingcheng = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
							continue;
						}
					}
				}else if(type=='edit'){
					this.info()
				}
				// 获取用户信息
				this.$http.get(this.userTableName + '/session', {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						var json = res.data.data;
						if((json.gaoxiaomingcheng!=''&&json.gaoxiaomingcheng) || json.gaoxiaomingcheng==0){
							this.ruleForm.gaoxiaomingcheng = json.gaoxiaomingcheng;
							this.ro.gaoxiaomingcheng = true;
						}
					}
				});
				this.$http.get('option/zhishifenlei/zhishifenlei', {}).then(res => {
					if (res.data.code == 0) {
						this.zhishifenleiOptions = res.data.data;
					}
				});

				if (localStorage.getItem('raffleType') && localStorage.getItem('raffleType') != null) {
					localStorage.removeItem('raffleType')
					setTimeout(() => {
						this.onSubmit()
					}, 300)
				}
			},

			// 多级联动参数
			// 多级联动参数
			async info() {
				await this.$http.get(`jishuzhishiku/detail/${this.$route.query.id}`, {emulateJSON: true}).then(res => {
					if (res.data.code == 0) {
						this.ruleForm = res.data.data;
					}
				});
			},
			// 提交
			async onSubmit() {
				if(!this.ruleForm.id) {
					delete this.ruleForm.userid
				}
				await this.$refs["ruleForm"].validate(async valid => {
					if(valid) {
						if(this.type=='cross'){
							var statusColumnName = localStorage.getItem('statusColumnName');
							var statusColumnValue = localStorage.getItem('statusColumnValue');
							if(statusColumnName && statusColumnName!='') {
								var obj = JSON.parse(localStorage.getItem('crossObj'));
								if(!statusColumnName.startsWith("[")) {
									for (var o in obj){
										if(o==statusColumnName){
											obj[o] = statusColumnValue;
										}
									}
									var table = localStorage.getItem('crossTable');
									await this.$http.post(table+'/update', obj).then(res => {});
								}
							}
						}

						await this.$http.post(`jishuzhishiku/${this.ruleForm.id?'update':this.centerType?'save':'add'}`, this.ruleForm).then(async res => {
							if (res.data.code == 0) {
								await this.$message({
									message: '操作成功',
									type: 'success',
									duration: 1500,
									onClose: () => {
										this.$router.go(-1);
										
									}
								});
							} else {
								this.$message({
									message: res.data.msg,
									type: 'error',
									duration: 1500
								});
							}
						});
					}
				});
			},
			// 获取uuid
			getUUID () {
				return new Date().getTime();
			},
			// 返回
			back() {
				this.$router.go(-1);
			},
			zhishizhaopianUploadChange(fileUrls) {
				this.ruleForm.zhishizhaopian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
			zhishifujianUploadChange(fileUrls) {
				this.ruleForm.zhishifujian = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
			zhishishipinUploadChange(fileUrls) {
				this.ruleForm.zhishishipin = fileUrls.replace(new RegExp(this.$config.baseUrl,"g"),"");
			},
		}
	};
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview {
		padding: 20px 10% 40px;
		margin: 10px auto;
		background: none;
		width: 100%;
		position: relative;
		.add-update-form {
			border: 0px solid #eee;
			border-radius: 10px;
			padding: 40px 20% 20px 10%;
			background: none;
			width: 100%;
			position: relative;
			.add-item.el-form-item {
				border: 1px solid #707070;
				padding: 0;
				margin: 0 0 24px;
				background: none;
				/deep/ .el-form-item__label {
					padding: 0 0px 0 10px;
					color: #333;
					white-space: nowrap;
					font-weight: 500;
					width: 200px;
					font-size: 16px;
					line-height: 40px;
					text-align: left;
				}
				/deep/ .el-form-item__content {
					margin-left: 200px;
				}
				.el-input {
					width: 100%;
				}
				.el-input /deep/ .el-input__inner {
					border: 0px solid #eee;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #666;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #999;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input__inner {
					text-align: left;
					border: 0px solid #eee;
					border-radius: 0px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #666;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .is-disabled .el-input__inner {
					text-align: left;
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 12px;
					box-shadow: none;
					outline: none;
					color: #999;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-input-number /deep/ .el-input-number__decrease {
					display: none;
				}
				.el-input-number /deep/ .el-input-number__increase {
					display: none;
				}
				.el-select {
					width: 100%;
				}
				.el-select /deep/ .el-input__inner {
					border: 0px solid #eee;
					border-radius: 0px;
					padding: 0 10px;
					box-shadow: none;
					outline: none;
					color: rgba(64, 158, 255, 1);
					background: none;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-select /deep/ .is-disabled .el-input__inner {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 10px;
					box-shadow: none;
					outline: none;
					color: #999;
					background: none;
					width: 100%;
					font-size: 14px;
					height: 40px;
				}
				.el-date-editor {
					width: 100%;
				}
				.el-date-editor /deep/ .el-input__inner {
					border: 0px solid #eee;
					border-radius: 0px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					outline: none;
					color: #666;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				.el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 0 10px 0 30px;
					box-shadow: none;
					outline: none;
					color: #999;
					background: none;
					width: 100%;
					font-size: 16px;
					height: 40px;
				}
				/deep/ .el-upload--picture-card {
					background: transparent;
					border: 0;
					border-radius: 0;
					width: auto;
					height: auto;
					line-height: initial;
					vertical-align: middle;
				}
				/deep/ .upload .upload-img {
					border: 1px solid #eee;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					width: 80px;
					font-size: 26px;
					line-height: 80px;
					text-align: center;
					height: 80px;
				}
				/deep/ .el-upload-list .el-upload-list__item {
					border: 1px solid #eee;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					width: 80px;
					font-size: 26px;
					line-height: 80px;
					text-align: center;
					height: 80px;
					font-size: 14px;
					line-height: 1.8;
				}
				/deep/ .el-upload .el-icon-plus {
					border: 1px solid #eee;
					cursor: pointer;
					border-radius: 0px;
					color: #999;
					width: 80px;
					font-size: 26px;
					line-height: 80px;
					text-align: center;
					height: 80px;
				}
				/deep/ .el-upload__tip {
					color: #666;
					font-size: 16px;
				}
				.el-textarea /deep/ .el-textarea__inner {
					border: 0px solid #eee;
					border-radius: 0px;
					padding: 12px;
					box-shadow: none;
					outline: none;
					color: #666;
					background: none;
					width: 100%;
					font-size: 16px;
					height: auto;
				}
				.el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
					border: 0;
					cursor: not-allowed;
					border-radius: 4px;
					padding: 12px;
					box-shadow: none;
					outline: none;
					color: #999;
					background: none;
					width: 100%;
					font-size: 16px;
					height: auto;
				}
				/deep/ .el-input__inner::placeholder {
					color: #123;
					font-size: 16px;
				}
				/deep/ textarea::placeholder {
					color: #123;
					font-size: 16px;
				}
				.editor {
					background-color: none;
					border-radius: 0;
					padding: 0;
					box-shadow: none;
					margin: 0;
					width: 100%;
					border-color: #ccc;
					border-width: 0;
					border-style: solid;
					height: auto;
				}
				.editor /deep/.ql-toolbar {
					border: 0px solid #eee;
					background: none;
					border-width: 0px 0px 0;
				}
				.editor /deep/.ql-container {
					border: 0px solid #eee;
					background: none;
					min-height: 180px;
				}
				.editor /deep/.ql-container .ql-blank::before {
					color: #999;
					background: none;
				}
				.upload-img {
					object-fit: cover;
					width: 120px;
					height: 120px;
				}
				.viewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 10px;
					color: #fff;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: #ABDE53;
					width: auto;
					height: 34px;
				}
				.viewBtn:hover {
					opacity: 0.7;
				}
				.unviewBtn {
					border: 0;
					cursor: pointer;
					padding: 0 20px;
					margin: 10px;
					color: #666;
					display: inline-block;
					font-size: 14px;
					line-height: 34px;
					border-radius: 4px;
					outline: none;
					background: #ABDE53;
					width: auto;
					height: 34px;
				}
				.unviewBtn:hover {
					opacity: 0.8;
				}
			}
			.add-btn-item {
				padding: 0;
				margin: 20px auto;
				width: 100%;
				text-align: center;
				.submitBtn {
					border: 0;
					cursor: pointer;
					border-radius: 20px;
					padding: 0 20px;
					margin: 0 20px 0 0;
					outline: none;
					background: #ABDE53;
					display: inline-block;
					width: auto;
					font-size: 14px;
					line-height: 40px;
					height: 40px;
					.icon {
						color: rgba(255, 255, 255, 1);
					}
					.text {
						color: rgba(255, 255, 255, 1);
						font-size: 16px;
					}
				}
				.submitBtn:hover {
					opacity: 0.7;
					.icon {
						color: #fff;
					}
					.text {
						color: #fff;
					}
				}
				.closeBtn {
					border: 0px solid #ff000020;
					cursor: pointer;
					padding: 0 15px 0 10px;
					margin: 0 20px 0 0;
					display: inline-block;
					font-size: 16px;
					line-height: 40px;
					border-radius: 20px;
					outline: none;
					background: #ABDE53;
					width: auto;
					min-width: 120px;
					height: 40px;
					.icon {
						color: #FFF;
						font-size: 18px;
					}
					.text {
						color: #FFF;
						font-size: 16px;
					}
				}
				.closeBtn:hover {
					opacity: 0.7;
					.icon {
					}
					.text {
					}
				}
			}
		}
	}
	.el-date-editor.el-input {
		width: auto;
	}
</style>
