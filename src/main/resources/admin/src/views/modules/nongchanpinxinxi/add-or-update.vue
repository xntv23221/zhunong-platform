






























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
				<el-form-item class="input" v-if="type!='info'"  label="农产品名称" prop="nongchanpinmingcheng" >
					<el-input v-model="ruleForm.nongchanpinmingcheng" placeholder="农产品名称" clearable  :readonly="ro.nongchanpinmingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="农产品名称" prop="nongchanpinmingcheng" >
					<el-input v-model="ruleForm.nongchanpinmingcheng" placeholder="农产品名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="农产品类型" prop="nongchanpinleixing" >
					<el-select :disabled="ro.nongchanpinleixing" v-model="ruleForm.nongchanpinleixing" placeholder="请选择农产品类型"  filterable>
						<el-option
							v-for="(item,index) in nongchanpinleixingOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="农产品类型" prop="nongchanpinleixing" >
					<el-input v-model="ruleForm.nongchanpinleixing"
						placeholder="农产品类型" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.nongchanpinzhaopian" label="农产品照片" prop="nongchanpinzhaopian" >
					<file-upload
						tip="点击上传农产品照片"
						action="file/upload"
						:limit="3"
						:disabled="ro.nongchanpinzhaopian"
						:multiple="true"
						:fileUrls="ruleForm.nongchanpinzhaopian?ruleForm.nongchanpinzhaopian:''"
						@change="nongchanpinzhaopianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.nongchanpinzhaopian" label="农产品照片" prop="nongchanpinzhaopian" >
					<img v-if="ruleForm.nongchanpinzhaopian.substring(0,4)=='http'&&ruleForm.nongchanpinzhaopian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.nongchanpinzhaopian" width="100" height="100" @click="imgPreView(ruleForm.tupian)">
					<img v-else-if="ruleForm.nongchanpinzhaopian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.nongchanpinzhaopian.split(',')[0]" width="100" height="100" @click="imgPreView(ruleForm.tupian.split(',')[0])">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.nongchanpinzhaopian.split(',')" :src="$base.url+item" width="100" height="100" @click="imgPreView($base.url+item)">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="产品规格" prop="chanpinguige" >
					<el-input v-model="ruleForm.chanpinguige" placeholder="产品规格" clearable  :readonly="ro.chanpinguige"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="产品规格" prop="chanpinguige" >
					<el-input v-model="ruleForm.chanpinguige" placeholder="产品规格" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="营养成分" prop="yingyangchengfen" >
					<el-input v-model="ruleForm.yingyangchengfen" placeholder="营养成分" clearable  :readonly="ro.yingyangchengfen"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="营养成分" prop="yingyangchengfen" >
					<el-input v-model="ruleForm.yingyangchengfen" placeholder="营养成分" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="外观特征" prop="waiguantezheng" >
					<el-input v-model="ruleForm.waiguantezheng" placeholder="外观特征" clearable  :readonly="ro.waiguantezheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="外观特征" prop="waiguantezheng" >
					<el-input v-model="ruleForm.waiguantezheng" placeholder="外观特征" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="农民账号" prop="nongminzhanghao" >
					<el-input v-model="ruleForm.nongminzhanghao" placeholder="农民账号" clearable  :readonly="ro.nongminzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="农民账号" prop="nongminzhanghao" >
					<el-input v-model="ruleForm.nongminzhanghao" placeholder="农民账号" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="单限" prop="onelimittimes" >
					<el-input v-model.number="ruleForm.onelimittimes" placeholder="单限" clearable  :readonly="ro.onelimittimes"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="单限" prop="onelimittimes" >
					<el-input v-model="ruleForm.onelimittimes" placeholder="单限" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="库存" prop="alllimittimes" >
					<el-input v-model.number="ruleForm.alllimittimes" placeholder="库存" clearable  :readonly="ro.alllimittimes"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="库存" prop="alllimittimes" >
					<el-input v-model="ruleForm.alllimittimes" placeholder="库存" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="价格" prop="price" >
					<el-input-number v-model="ruleForm.price" placeholder="价格" :disabled="ro.price"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="价格" prop="price" >
					<el-input v-model="ruleForm.price" placeholder="价格" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item v-if="type!='info'" class="editorBox" label="产品介绍" prop="chanpinjieshao" >
				<editor 
					style="min-width: 200px; max-width: 600px;"
					v-model="ruleForm.chanpinjieshao" 
					class="editor"
					myQuillEditor="chanpinjieshao"
					action="file/upload">
				</editor>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.chanpinjieshao" label="产品介绍" prop="chanpinjieshao"  class="textBox">
				<span class="text ql-snow ql-editor" v-html="ruleForm.chanpinjieshao"></span>
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
	import { 
		isNumber,
		isIntNumer,
	} from "@/utils/validate";
	export default {
		data() {
			var validateNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isNumber(value)) {
					callback(new Error("请输入数字"));
				} else {
					callback();
				}
			};
			var validateIntNumber = (rule, value, callback) => {
				if(!value){
					callback();
				} else if (!isIntNumer(value)) {
					callback(new Error("请输入整数"));
				} else {
					callback();
				}
			};
			return {
				id: '',
				type: '',
			
			
				ro:{
					nongchanpinmingcheng : false,
					nongchanpinleixing : false,
					nongchanpinzhaopian : false,
					chanpinguige : false,
					yingyangchengfen : false,
					waiguantezheng : false,
					chanpinjieshao : false,
					nongminzhanghao : false,
					onelimittimes : false,
					alllimittimes : false,
					price : false,
					clicktime : false,
					discussnum : false,
					onshelves : false,
					storeupnum : false,
				},
			
				ruleForm: {
					nongchanpinmingcheng: '',
					nongchanpinleixing: '',
					nongchanpinzhaopian: '',
					chanpinguige: '',
					yingyangchengfen: '',
					waiguantezheng: '',
					chanpinjieshao: '',
					nongminzhanghao: '',
					clicktime: '',
					onshelves: 1,
				},
				nongchanpinleixingOptions: [],

				rules: {
					nongchanpinmingcheng: [
					],
					nongchanpinleixing: [
					],
					nongchanpinzhaopian: [
					],
					chanpinguige: [
					],
					yingyangchengfen: [
					],
					waiguantezheng: [
					],
					chanpinjieshao: [
					],
					nongminzhanghao: [
					],
					onelimittimes: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					alllimittimes: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					price: [
						{ required: true, message: '价格不能为空', trigger: 'blur' },
						{ validator: validateNumber, trigger: 'blur' },
					],
					clicktime: [
					],
					discussnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					onshelves: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					storeupnum: [
						{ validator: validateIntNumber, trigger: 'blur' },
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
						if(o=='nongchanpinmingcheng'){
							this.ruleForm.nongchanpinmingcheng = obj[o];
							this.ro.nongchanpinmingcheng = true;
							continue;
						}
						if(o=='nongchanpinleixing'){
							this.ruleForm.nongchanpinleixing = obj[o];
							this.ro.nongchanpinleixing = true;
							continue;
						}
						if(o=='nongchanpinzhaopian'){
							this.ruleForm.nongchanpinzhaopian = obj[o];
							this.ro.nongchanpinzhaopian = true;
							continue;
						}
						if(o=='chanpinguige'){
							this.ruleForm.chanpinguige = obj[o];
							this.ro.chanpinguige = true;
							continue;
						}
						if(o=='yingyangchengfen'){
							this.ruleForm.yingyangchengfen = obj[o];
							this.ro.yingyangchengfen = true;
							continue;
						}
						if(o=='waiguantezheng'){
							this.ruleForm.waiguantezheng = obj[o];
							this.ro.waiguantezheng = true;
							continue;
						}
						if(o=='chanpinjieshao'){
							this.ruleForm.chanpinjieshao = obj[o];
							this.ro.chanpinjieshao = true;
							continue;
						}
						if(o=='nongminzhanghao'){
							this.ruleForm.nongminzhanghao = obj[o];
							this.ro.nongminzhanghao = true;
							continue;
						}
						if(o=='onelimittimes'){
							this.ruleForm.onelimittimes = obj[o];
							this.ro.onelimittimes = true;
							continue;
						}
						if(o=='alllimittimes'){
							this.ruleForm.alllimittimes = obj[o];
							this.ro.alllimittimes = true;
							continue;
						}
						if(o=='price'){
							this.ruleForm.price = obj[o];
							this.ro.price = true;
							continue;
						}
						if(o=='clicktime'){
							this.ruleForm.clicktime = obj[o];
							this.ro.clicktime = true;
							continue;
						}
						if(o=='discussnum'){
							this.ruleForm.discussnum = obj[o];
							this.ro.discussnum = true;
							continue;
						}
						if(o=='onshelves'){
							this.ruleForm.onshelves = obj[o];
							this.ro.onshelves = true;
							continue;
						}
						if(o=='storeupnum'){
							this.ruleForm.storeupnum = obj[o];
							this.ro.storeupnum = true;
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
				this.$http({
					url: `option/nongchanpinleixing/nongchanpinleixing`,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.nongchanpinleixingOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			async info(id) {
				await this.$http({
					url: `nongchanpinxinxi/info/${id}`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.ruleForm = data.data;
						//解决前台上传图片后台不显示的问题
						let reg=new RegExp('../../../upload','g')//g代表全部
						this.ruleForm.chanpinjieshao = this.ruleForm.chanpinjieshao.replace(reg,'../../../zhunong79djy629/upload');
					} else {
						this.$message.error(data.msg);
					}
				});
			},

			// 提交
			async onSubmit() {
					if(this.ruleForm.nongchanpinzhaopian!=null) {
						this.ruleForm.nongchanpinzhaopian = this.ruleForm.nongchanpinzhaopian.replace(new RegExp(this.$base.url,"g"),"");
					}
					if(this.ruleForm.price<0){
						this.$message.error("价格不能输入负数");
						return
					}
					if(this.ruleForm.alllimittimes<0){
						this.$message.error("库存不能输入负数");
						return
					}
					if(this.ruleForm.onelimittimes<0){
						this.$message.error("单次购买不能输入负数");
						return
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
								url: `nongchanpinxinxi/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.nongchanpinxinxiCrossAddOrUpdateFlag = false;
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
				this.parent.nongchanpinxinxiCrossAddOrUpdateFlag = false;
			},
			nongchanpinzhaopianUploadChange(fileUrls) {
				this.ruleForm.nongchanpinzhaopian = fileUrls;
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
