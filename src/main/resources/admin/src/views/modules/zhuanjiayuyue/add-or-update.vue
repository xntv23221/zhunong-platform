


















<template>
	<div class="addEdit-block">
		<el-form
			class="add-update-preview"
			ref="ruleForm"
			:model="ruleForm"
			:rules="rules"
			label-width="180px"
		>
			<template >
				<el-form-item class="input" v-if="type!='info'"  label="高校名称" prop="gaoxiaomingcheng" >
					<el-input v-model="ruleForm.gaoxiaomingcheng" placeholder="高校名称" clearable  :readonly="ro.gaoxiaomingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="高校名称" prop="gaoxiaomingcheng" >
					<el-input v-model="ruleForm.gaoxiaomingcheng" placeholder="高校名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="专家名称" prop="zhuanjiamingcheng" >
					<el-input v-model="ruleForm.zhuanjiamingcheng" placeholder="专家名称" clearable  :readonly="ro.zhuanjiamingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="专家名称" prop="zhuanjiamingcheng" >
					<el-input v-model="ruleForm.zhuanjiamingcheng" placeholder="专家名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.zhuanjiazhaopian" label="专家照片" prop="zhuanjiazhaopian" >
					<file-upload
						tip="点击上传专家照片"
						action="file/upload"
						:limit="3"
						:disabled="ro.zhuanjiazhaopian"
						:multiple="true"
						:fileUrls="ruleForm.zhuanjiazhaopian?ruleForm.zhuanjiazhaopian:''"
						@change="zhuanjiazhaopianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.zhuanjiazhaopian" label="专家照片" prop="zhuanjiazhaopian" >
					<img v-if="ruleForm.zhuanjiazhaopian.substring(0,4)=='http'&&ruleForm.zhuanjiazhaopian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.zhuanjiazhaopian" width="100" height="100" @click="imgPreView(ruleForm.tupian)">
					<img v-else-if="ruleForm.zhuanjiazhaopian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.zhuanjiazhaopian.split(',')[0]" width="100" height="100" @click="imgPreView(ruleForm.tupian.split(',')[0])">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.zhuanjiazhaopian.split(',')" :src="$base.url+item" width="100" height="100" @click="imgPreView($base.url+item)">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="专业方向" prop="zhuanyefangxiang" >
					<el-input v-model="ruleForm.zhuanyefangxiang" placeholder="专业方向" clearable  :readonly="ro.zhuanyefangxiang"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="专业方向" prop="zhuanyefangxiang" >
					<el-input v-model="ruleForm.zhuanyefangxiang" placeholder="专业方向" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="预约时间" prop="yuyueshijian" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.yuyueshijian" 
						type="datetime"
						:readonly="ro.yuyueshijian"
						placeholder="预约时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.yuyueshijian" label="预约时间" prop="yuyueshijian" >
					<el-input v-model="ruleForm.yuyueshijian" placeholder="预约时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="农民账号" prop="nongminzhanghao" >
					<el-input v-model="ruleForm.nongminzhanghao" placeholder="农民账号" clearable  :readonly="ro.nongminzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="农民账号" prop="nongminzhanghao" >
					<el-input v-model="ruleForm.nongminzhanghao" placeholder="农民账号" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="咨询内容" prop="zixunneirong" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="咨询内容"
					v-model="ruleForm.zixunneirong" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.zixunneirong" label="咨询内容" prop="zixunneirong"  class="textBox">
				<span class="text">{{ruleForm.zixunneirong}}</span>
			</el-form-item>
			<el-form-item class="btn">
				<el-button class="btn3"  v-if="type!='info'" type="success" @click="onSubmit">
					<span class="icon iconfont icon-queren15"></span>
					确定 
				</el-button>
				<el-button class="btn4" v-if="type!='info'" type="success" @click="back()">
					<span class="icon iconfont icon-guanbi2"></span>
					取消
				</el-button>
				<el-button class="btn5" v-if="type=='info'" type="success" @click="back()">
					<span class="icon iconfont icon-fanhui13"></span>
					返回
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
				type: '',
			
			
				ro:{
					gaoxiaomingcheng : false,
					zhuanjiamingcheng : false,
					zhuanjiazhaopian : false,
					zhuanyefangxiang : false,
					yuyueshijian : false,
					zixunneirong : false,
					nongminzhanghao : false,
					sfsh : false,
					shhf : false,
				},
			
				ruleForm: {
					gaoxiaomingcheng: '',
					zhuanjiamingcheng: '',
					zhuanjiazhaopian: '',
					zhuanyefangxiang: '',
					yuyueshijian: '',
					zixunneirong: '',
					nongminzhanghao: '',
					shhf: '',
				},

				rules: {
					gaoxiaomingcheng: [
					],
					zhuanjiamingcheng: [
					],
					zhuanjiazhaopian: [
					],
					zhuanyefangxiang: [
					],
					yuyueshijian: [
					],
					zixunneirong: [
					],
					nongminzhanghao: [
					],
					sfsh: [
					],
					shhf: [
					],
				},
			};
		},
		props: ["parent"],
		computed: {
			sessionForm() {
				return JSON.parse(this.$storage.getObj('userForm'))
			},
			sessionTable() {
				return this.$storage.get('sessionTable')
			},



		},
		components: {
		},
		created() {
			this.ruleForm.yuyueshijian = this.getCurDateTime()
		},
		methods: {
			imgPreView(url){
				this.$parent.imgPreView(url)
			},
			// 下载
			download(file){
				window.open(`${file}`)
			},
			// 初始化
			init(id,type ) {
				if (id) {
					this.id = id;
					this.type = type;
				}
				if(this.type=='info'||this.type=='else'||this.type=='msg'){
					this.info(id);
				}else if(this.type=='logistics'){
					for(let x in this.ro) {
						this.ro[x] = true
					}
					this.logistics=false;
					this.info(id);
				}else if(this.type=='cross'){
					var obj = this.$storage.getObj('crossObj');
					for (var o in obj){
						if(o=='gaoxiaomingcheng'){
							this.ruleForm.gaoxiaomingcheng = obj[o];
							this.ro.gaoxiaomingcheng = true;
							continue;
						}
						if(o=='zhuanjiamingcheng'){
							this.ruleForm.zhuanjiamingcheng = obj[o];
							this.ro.zhuanjiamingcheng = true;
							continue;
						}
						if(o=='zhuanjiazhaopian'){
							this.ruleForm.zhuanjiazhaopian = obj[o];
							this.ro.zhuanjiazhaopian = true;
							continue;
						}
						if(o=='zhuanyefangxiang'){
							this.ruleForm.zhuanyefangxiang = obj[o];
							this.ro.zhuanyefangxiang = true;
							continue;
						}
						if(o=='yuyueshijian'){
							this.ruleForm.yuyueshijian = obj[o];
							this.ro.yuyueshijian = true;
							continue;
						}
						if(o=='zixunneirong'){
							this.ruleForm.zixunneirong = obj[o];
							this.ro.zixunneirong = true;
							continue;
						}
						if(o=='nongminzhanghao'){
							this.ruleForm.nongminzhanghao = obj[o];
							this.ro.nongminzhanghao = true;
							continue;
						}
					}
				}
				// 获取用户信息
				this.$http({
					url: `${this.sessionTable}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.nongminzhanghao!=''&&json.nongminzhanghao) || json.nongminzhanghao==0) && this.sessionTable!="users"){
							this.ruleForm.nongminzhanghao = json.nongminzhanghao
							this.ro.nongminzhanghao = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			async info(id) {
				await this.$http({
					url: `zhuanjiayuyue/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.zhuanjiazhaopian!=null) {
						this.ruleForm.zhuanjiazhaopian = this.ruleForm.zhuanjiazhaopian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					if(!this.ruleForm.id) {
						delete this.ruleForm.userid
					}
					await this.$refs["ruleForm"].validate(async valid => {
						if (valid) {
							if(this.type=='cross'){
								var statusColumnName = this.$storage.get('statusColumnName');
								var statusColumnValue = this.$storage.get('statusColumnValue');
								if(statusColumnName!='') {
									var obj = this.$storage.getObj('crossObj');
									if(statusColumnName && !statusColumnName.startsWith("[")) {
										for (var o in obj){
											if(o==statusColumnName){
												obj[o] = statusColumnValue;
											}
										}
										var table = this.$storage.get('crossTable');
										await this.$http({
											url: `${table}/update`,
											method: "post",
											data: obj
										}).then(({ data }) => {});
									}
								}
							}
							await this.$http({
								url: `zhuanjiayuyue/${!this.ruleForm.id ? "save" : "update"}`,
								method: "post",
								data: this.ruleForm
							}).then(async ({ data }) => {
								if (data && data.code === 0) {
									this.$message({
										message: "操作成功",
										type: "success",
										duration: 1500,
										onClose: () => {
											this.parent.showFlag = true;
											this.parent.addOrUpdateFlag = false;
											this.parent.zhuanjiayuyueCrossAddOrUpdateFlag = false;
											this.parent.search();
										}
									});
								} else {
									this.$message.error(data.msg);
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
				this.parent.showFlag = true;
				this.parent.addOrUpdateFlag = false;
				this.parent.zhuanjiayuyueCrossAddOrUpdateFlag = false;
			},
			zhuanjiazhaopianUploadChange(fileUrls) {
				this.ruleForm.zhuanjiazhaopian = fileUrls;
			},
		}
	};
</script>
<style lang="scss" scoped>
	.addEdit-block {
		padding: 10px 20px;
		background:  linear-gradient( 180deg, #EFFFEF 0%, #F8F8F8 100%);
		min-height: 100vh;
	}
	.add-update-preview {
		border: 0px solid #ddd;
		border-radius: 16px;
		padding: 50px 30px;
		background: linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
		display: flex;
		flex-wrap: wrap;
	}
	.amap-wrapper {
		width: 100%;
		height: 500px;
	}
	
	.search-box {
		position: absolute;
	}
	
	.el-date-editor.el-input {
		width: auto;
	}
	.add-update-preview /deep/ .el-form-item {
		margin: 10px 0px 10px 0 ;
		display: flex;
		width: calc(50% - 0px);
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
	.add-update-preview /deep/ .el-form-item.editorBox {
		margin: 10px 0px 10px 0 ;
		display: flex;
		width: 100%;
	}
	.add-update-preview .el-form-item.editorBox /deep/ .el-form-item__label {
		padding: 0 10px;
		color: #000000;
		white-space: nowrap;
		font-weight: 400;
		width: 180px;
		font-size: 16px;
		line-height: 64px;
		text-align: right;
		height: 64px;
	}
	
	.add-update-preview .el-form-item.editorBox /deep/ .el-form-item__content {
		margin-left: auto !important;
		padding: 0;
		margin: auto !important;
		flex: 1;
		display: flex;
		width: 100%;
		justify-content: flex-start;
		align-items: flex-start;
		flex-wrap: wrap;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor {
		border: 1px solid #29C41F;
		border-radius: 4px;
		color: #29C41F;
		max-width: 100% !important;
		align-content: flex-start;
		flex: 1;
		background: #fff;
		display: flex;
		align-items: flex-start;
		flex-wrap: wrap;
		height: auto;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor .ql-toolbar {
		border: 0px solid #555555;
		background: none;
		width: 100%;
		border-width: 0;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor .ql-container {
		border: 0px solid #555555;
		background: none;
		width: 100%;
		min-height: 200px;
	}
	.add-update-preview /deep/.el-form-item.editorBox .editor .ql-container .ql-blank::before {
		color: #999;
	}
	
	.add-update-preview /deep/ .el-form-item.textBox {
		margin: 10px 0px 10px 0 ;
		display: flex;
		width: calc(50% - 0px);
	}
	.add-update-preview .el-form-item.textBox /deep/ .el-form-item__label {
		padding: 0 10px;
		color: #000000;
		white-space: nowrap;
		background: none;
		font-weight: 400;
		width: 180px;
		font-size: 16px;
		line-height: 45px;
		text-align: right;
		height: 45px;
	}
	
	.add-update-preview .el-form-item.textBox /deep/ .el-form-item__content {
		margin-left: auto !important;
		padding: 0;
		margin: auto !important;
		flex: 1;
		display: flex;
		width: 100%;
		justify-content: flex-start;
		align-items: flex-start;
		flex-wrap: wrap;
	}
	.add-update-preview /deep/.el-form-item.textBox span.text {
		border: 1px solid #29C41F;
		border-radius: 4px;
		padding: 6px 12px;
		outline: none;
		color: #29C41F;
		background: #fff;
		flex: 1;
		width: 100%;
		font-size: 16px;
		height: auto;
	}
	
	.add-update-preview .el-input {
		width: 100%;
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
	.add-update-preview .el-input /deep/ .el-input__inner[readonly="readonly"] {
		border:  0px solid #29C41F;
		border-radius: 4px;
		padding: 0 12px;
		outline: none;
		color: #333;
		flex: 1;
		background: none;
		width: 100%;
		font-size: 16px;
		height: auto;
	}
	.add-update-preview .el-input-number {
		text-align: left;
		width: 100%;
	}
	.add-update-preview .el-input-number /deep/ .el-input__inner {
		text-align: left;
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
	.add-update-preview .el-input-number /deep/ .is-disabled .el-input__inner {
		text-align: left;
		border:  0px solid #29C41F;
		border-radius: 4px;
		padding: 0 12px;
		outline: none;
		color: #333;
		flex: 1;
		background: none;
		width: 100%;
		font-size: 16px;
		height: auto;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__decrease {
		display: none;
	}
	.add-update-preview .el-input-number /deep/ .el-input-number__increase {
		display: none;
	}
	.add-update-preview .el-select {
		width: 100%;
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
	.add-update-preview .el-select /deep/ .is-disabled .el-input__inner {
		border:  0px solid #29C41F;
		border-radius: 4px;
		padding: 0 12px;
		outline: none;
		color: #333;
		flex: 1;
		background: none;
		width: 100%;
		font-size: 16px;
		height: auto;
	}
	.add-update-preview .el-date-editor {
		width: 100%;
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
	.add-update-preview .el-date-editor /deep/ .el-input__inner[readonly="readonly"] {
		border:  0px solid #29C41F;
		border-radius: 4px;
		padding: 0 30px;
		outline: none;
		color: #333;
		flex: 1;
		background: none;
		width: 100%;
		font-size: 16px;
		height: auto;
	}
	.add-update-preview .viewBtn {
		border: 0px solid #555555;
		cursor: pointer;
		padding: 0 15px;
		margin: 0;
		color: #FFFFFF;
		font-weight: 600;
		font-size: 14px;
		line-height: 45px;
		border-radius: 4px;
		outline: none;
		background: #29C41F;
		text-align: center;
		min-width: 120px;
		height: 45px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .viewBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .downBtn {
		border: 0px solid #555555;
		cursor: pointer;
		padding: 0 15px;
		margin: 0;
		color: #FFFFFF;
		font-weight: 600;
		font-size: 14px;
		line-height: 45px;
		border-radius: 4px;
		outline: none;
		background: #29C41F;
		text-align: center;
		min-width: 120px;
		height: 45px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .downBtn:hover {
		opacity: 0.8;
	}
	.add-update-preview .unBtn {
		border: 0px solid #555555;
		cursor: pointer;
		padding: 0 15px;
		margin: 0;
		color: #FFFFFF;
		font-weight: 600;
		font-size: 14px;
		line-height: 45px;
		border-radius: 4px;
		outline: none;
		background: #29C41F;
		text-align: center;
		min-width: 120px;
		height: 45px;
		.iconfont {
			margin: 0 2px;
			color: #fff;
			display: none;
			font-size: 14px;
			height: 40px;
		}
	}
	.add-update-preview .unBtn:hover {
		opacity: 0.8;
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
	
	.add-update-preview /deep/ .upload .upload-img {
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
	.add-update-preview /deep/ .el-upload__tip {
		padding: 0 10px;
		color: #000000;
		font-size: 15px;
	}
	.add-update-preview /deep/ .el-form-item.fileupload {
		margin: 10px 0px 10px 0 ;
		display: flex;
		width: calc(50% - 0px);
	}
	.add-update-preview .el-form-item.fileupload /deep/ .el-form-item__label {
		padding: 0 10px;
		color: #000000;
		white-space: nowrap;
		font-weight: 400;
		width: 180px;
		font-size: 16px;
		line-height: 64px ;
		text-align: right;
		height: 64px ;
	}
	
	.add-update-preview .el-form-item.fileupload /deep/ .el-form-item__content {
		margin-left: auto !important;
		margin: auto !important;
		flex: 1;
		display: flex;
		width: 100%;
		justify-content: flex-start;
		align-items: flex-start;
		flex-wrap: wrap;
	}
	.add-update-preview .el-form-item.fileupload /deep/ .el-upload-dragger {
		border-radius: 4px;
		outline: none;
		color: #333;
		display: flex;
		width: 100%;
		font-size: 16px;
		border-color: #29C41F;
		border-width:  1px;
		align-items: center;
		border-style: solid;
		flex-wrap: wrap;
		height: 100px;
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger /deep/ .el-icon-upload {
		margin: 0;
		color: #206cb480;
		width: 100%;
		font-size: 66px;
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger /deep/ .el-upload__text {
		color: #606266;
		textalign: center;
		width: 100%;
		fontsize: 14px;
		line-height: 1;
	}
	.add-update-preview .el-form-item.fileupload .el-upload-dragger /deep/ .el-upload__text em {
		fontstyle: normal;
		color: #409EFF;
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
	.add-update-preview .el-textarea /deep/ .el-textarea__inner[readonly="readonly"] {
		border-radius: 4px;
		padding: 12px;
		outline: none;
		color: #29C41F;
		background: none;
		width: 100%;
		font-size: 16px;
		border-color: #29C41F;
		border-width: 0px;
		border-style: solid;
		height: auto;
	}
	.add-update-preview /deep/ .el-form-item.btn {
		padding: 0 30px;
		margin: 30px  auto 0 auto;
		background: none;
		display: block;
		width: 100%;
		justify-content: center;
		flex-wrap: wrap;
		text-align: center;
		.btn1 {
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
		.btn1:hover {
			opacity: 0.8;
		}
		.btn2 {
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
		.btn2:hover {
			opacity: 0.8;
		}
		.btn3 {
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
		.btn3:hover {
			opacity: 0.8;
		}
		.btn4 {
			border: 0px solid #206cb4;
			cursor: pointer;
			padding: 0 24px;
			margin: 4px;
			color: #333;
			font-weight: bold;
			font-size: 16px;
			border-radius: 24px  24px  24px  24px;
			outline: none;
			background: #D8D8D8;
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
		.btn4:hover {
			opacity: 0.8;
		}
		.btn5 {
			border: 0px solid #206cb4;
			cursor: pointer;
			padding: 0 24px;
			margin: 4px;
			color: #333;
			font-weight: bold;
			font-size: 16px;
			border-radius: 24px  24px  24px  24px;
			outline: none;
			background: #D8D8D8;
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
		.btn5:hover {
			opacity: 0.8;
		}
	}
	.add-update-preview .el-form-item.btn /deep/ .el-form-item__label {
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
	
	.add-update-preview .el-form-item.btn /deep/ .el-form-item__content {
		margin-left: auto !important;
		margin: auto !important;
		flex: 1;
		display: block;
		width: 100%;
		align-items: flex-start;
		flex-wrap: wrap;
	}
</style>
