




























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
				<el-form-item class="input" v-if="type!='info'"  label="原料名称" prop="yuanliaomingcheng" >
					<el-input v-model="ruleForm.yuanliaomingcheng" placeholder="原料名称" clearable  :readonly="ro.yuanliaomingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="原料名称" prop="yuanliaomingcheng" >
					<el-input v-model="ruleForm.yuanliaomingcheng" placeholder="原料名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="select" v-if="type!='info'"  label="原料分类" prop="yuanliaofenlei" >
					<el-select :disabled="ro.yuanliaofenlei" v-model="ruleForm.yuanliaofenlei" placeholder="请选择原料分类"  filterable>
						<el-option
							v-for="(item,index) in yuanliaofenleiOptions"
							v-bind:key="index"
							:label="item"
							:value="item">
						</el-option>
					</el-select>
				</el-form-item>
				<el-form-item v-else class="input" label="原料分类" prop="yuanliaofenlei" >
					<el-input v-model="ruleForm.yuanliaofenlei"
						placeholder="原料分类" readonly></el-input>
				</el-form-item>
				<el-form-item class="upload" v-if="type!='info' && !ro.yuanliaozhaopian" label="原料照片" prop="yuanliaozhaopian" >
					<file-upload
						tip="点击上传原料照片"
						action="file/upload"
						:limit="3"
						:disabled="ro.yuanliaozhaopian"
						:multiple="true"
						:fileUrls="ruleForm.yuanliaozhaopian?ruleForm.yuanliaozhaopian:''"
						@change="yuanliaozhaopianUploadChange"
					></file-upload>
				</el-form-item>
				<el-form-item class="upload" v-else-if="ruleForm.yuanliaozhaopian" label="原料照片" prop="yuanliaozhaopian" >
					<img v-if="ruleForm.yuanliaozhaopian.substring(0,4)=='http'&&ruleForm.yuanliaozhaopian.split(',w').length>1" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.yuanliaozhaopian" width="100" height="100" @click="imgPreView(ruleForm.tupian)">
					<img v-else-if="ruleForm.yuanliaozhaopian.substring(0,4)=='http'" class="upload-img" style="margin-right:20px;" v-bind:key="index" :src="ruleForm.yuanliaozhaopian.split(',')[0]" width="100" height="100" @click="imgPreView(ruleForm.tupian.split(',')[0])">
					<img v-else class="upload-img" style="margin-right:20px;" v-bind:key="index" v-for="(item,index) in ruleForm.yuanliaozhaopian.split(',')" :src="$base.url+item" width="100" height="100" @click="imgPreView($base.url+item)">
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="有效成分" prop="youxiaochengfen" >
					<el-input v-model="ruleForm.youxiaochengfen" placeholder="有效成分" clearable  :readonly="ro.youxiaochengfen"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="有效成分" prop="youxiaochengfen" >
					<el-input v-model="ruleForm.youxiaochengfen" placeholder="有效成分" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="价格" prop="jiage" >
					<el-input-number v-model="ruleForm.jiage" placeholder="价格" :disabled="ro.jiage"></el-input-number>
				</el-form-item>
				<el-form-item v-else class="input" label="价格" prop="jiage" >
					<el-input v-model="ruleForm.jiage" placeholder="价格" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="采购数量" prop="kucun" >
					<el-input v-model.number="ruleForm.kucun" placeholder="采购数量" clearable  :readonly="ro.kucun"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="采购数量" prop="kucun" >
					<el-input v-model="ruleForm.kucun" placeholder="采购数量" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'" label="采购总价" prop="caigouzongjia" >
					<el-input v-model="caigouzongjia" placeholder="采购总价" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.caigouzongjia" label="采购总价" prop="caigouzongjia" >
					<el-input v-model="ruleForm.caigouzongjia" placeholder="采购总价" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="高校名称" prop="gaoxiaomingcheng" >
					<el-input v-model="ruleForm.gaoxiaomingcheng" placeholder="高校名称" clearable  :readonly="ro.gaoxiaomingcheng"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="高校名称" prop="gaoxiaomingcheng" >
					<el-input v-model="ruleForm.gaoxiaomingcheng" placeholder="高校名称" readonly></el-input>
				</el-form-item>
				<el-form-item class="date" v-if="type!='info'" label="采购时间" prop="caigoushijian" >
					<el-date-picker
						value-format="yyyy-MM-dd HH:mm:ss"
						v-model="ruleForm.caigoushijian" 
						type="datetime"
						:readonly="ro.caigoushijian"
						placeholder="采购时间"
					></el-date-picker>
				</el-form-item>
				<el-form-item class="input" v-else-if="ruleForm.caigoushijian" label="采购时间" prop="caigoushijian" >
					<el-input v-model="ruleForm.caigoushijian" placeholder="采购时间" readonly></el-input>
				</el-form-item>
				<el-form-item class="input" v-if="type!='info'"  label="农民账号" prop="nongminzhanghao" >
					<el-input v-model="ruleForm.nongminzhanghao" placeholder="农民账号" clearable  :readonly="ro.nongminzhanghao"></el-input>
				</el-form-item>
				<el-form-item v-else class="input" label="农民账号" prop="nongminzhanghao" >
					<el-input v-model="ruleForm.nongminzhanghao" placeholder="农民账号" readonly></el-input>
				</el-form-item>
			</template>
			<el-form-item class="textarea" v-if="type!='info'" label="采购备注" prop="caigoubeizhu" >
				<el-input
					style="min-width: 200px; max-width: 600px;"
					type="textarea"
					:rows="8"
					placeholder="采购备注"
					v-model="ruleForm.caigoubeizhu" >
				</el-input>
			</el-form-item>
			<el-form-item v-else-if="ruleForm.caigoubeizhu" label="采购备注" prop="caigoubeizhu"  class="textBox">
				<span class="text">{{ruleForm.caigoubeizhu}}</span>
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
					yuanliaomingcheng : false,
					yuanliaofenlei : false,
					yuanliaozhaopian : false,
					youxiaochengfen : false,
					jiage : false,
					kucun : false,
					caigouzongjia : false,
					gaoxiaomingcheng : false,
					caigoushijian : false,
					caigoubeizhu : false,
					nongminzhanghao : false,
					sfsh : false,
					shhf : false,
					ispay : false,
				},
			
				ruleForm: {
					yuanliaomingcheng: '',
					yuanliaofenlei: '',
					yuanliaozhaopian: '',
					youxiaochengfen: '',
					jiage: '',
					kucun: '',
					caigouzongjia: '',
					gaoxiaomingcheng: '',
					caigoushijian: '',
					caigoubeizhu: '',
					nongminzhanghao: '',
					shhf: '',
				},
				yuanliaofenleiOptions: [],

				rules: {
					yuanliaomingcheng: [
					],
					yuanliaofenlei: [
					],
					yuanliaozhaopian: [
					],
					youxiaochengfen: [
					],
					jiage: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					kucun: [
						{ validator: validateIntNumber, trigger: 'blur' },
					],
					caigouzongjia: [
						{ validator: validateNumber, trigger: 'blur' },
					],
					gaoxiaomingcheng: [
					],
					caigoushijian: [
					],
					caigoubeizhu: [
					],
					nongminzhanghao: [
					],
					sfsh: [
					],
					shhf: [
					],
					ispay: [
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
			caigouzongjia : {
				get: function () {
					let c = this.ruleForm
					let a = c.jiage*c.kucun
					this.ruleForm.caigouzongjia = a?Number(Number(a).toFixed(2)):0
					return a?Number(Number(a).toFixed(2)):0
				}
			},



		},
		components: {
		},
		created() {
			this.ruleForm.caigoushijian = this.getCurDateTime()
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
						if(o=='yuanliaomingcheng'){
							this.ruleForm.yuanliaomingcheng = obj[o];
							this.ro.yuanliaomingcheng = true;
							continue;
						}
						if(o=='yuanliaofenlei'){
							this.ruleForm.yuanliaofenlei = obj[o];
							this.ro.yuanliaofenlei = true;
							continue;
						}
						if(o=='yuanliaozhaopian'){
							this.ruleForm.yuanliaozhaopian = obj[o];
							this.ro.yuanliaozhaopian = true;
							continue;
						}
						if(o=='youxiaochengfen'){
							this.ruleForm.youxiaochengfen = obj[o];
							this.ro.youxiaochengfen = true;
							continue;
						}
						if(o=='jiage'){
							this.ruleForm.jiage = obj[o];
							this.ro.jiage = true;
							continue;
						}
						if(o=='kucun'){
							this.ruleForm.kucun = obj[o];
							this.ro.kucun = true;
							continue;
						}
						if(o=='caigouzongjia'){
							this.ruleForm.caigouzongjia = obj[o];
							this.ro.caigouzongjia = true;
							continue;
						}
						if(o=='gaoxiaomingcheng'){
							this.ruleForm.gaoxiaomingcheng = obj[o];
							this.ro.gaoxiaomingcheng = true;
							continue;
						}
						if(o=='caigoushijian'){
							this.ruleForm.caigoushijian = obj[o];
							this.ro.caigoushijian = true;
							continue;
						}
						if(o=='caigoubeizhu'){
							this.ruleForm.caigoubeizhu = obj[o];
							this.ro.caigoubeizhu = true;
							continue;
						}
						if(o=='nongminzhanghao'){
							this.ruleForm.nongminzhanghao = obj[o];
							this.ro.nongminzhanghao = true;
							continue;
						}
					}
					this.ruleForm.kucun = 0
					this.ro.kucun = false
				}
				// 获取用户信息
				this.$http({
					url: `${this.sessionTable}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						var json = data.data;
						if(((json.gaoxiaomingcheng!=''&&json.gaoxiaomingcheng) || json.gaoxiaomingcheng==0) && this.sessionTable!="users"){
							this.ruleForm.gaoxiaomingcheng = json.gaoxiaomingcheng
							this.ro.gaoxiaomingcheng = true;
						}
						if(((json.nongminzhanghao!=''&&json.nongminzhanghao) || json.nongminzhanghao==0) && this.sessionTable!="users"){
							this.ruleForm.nongminzhanghao = json.nongminzhanghao
							this.ro.nongminzhanghao = true;
						}
					} else {
						this.$message.error(data.msg);
					}
				});
				this.$http({
					url: `option/yuanliaofenlei/yuanliaofenlei`,
					method: "get",
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.yuanliaofenleiOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			
			},
			// 多级联动参数

			async info(id) {
				await this.$http({
					url: `yuanliaocaigou/info/${id}`,
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
					if(!this.ruleForm.id) {
						this.ruleForm.ispay = '未支付'
					}
					if(this.ruleForm.caigouzongjia==0){
						this.$message.error('采购总价不能为空')
						return false
					}
					if(this.ruleForm.yuanliaozhaopian!=null) {
						this.ruleForm.yuanliaozhaopian = this.ruleForm.yuanliaozhaopian.replace(new RegExp(this.$base.url,"g"),"");
					}
					var objcross = this.$storage.getObj('crossObj');
					var table = this.$storage.getObj('crossTable');
					if(objcross!=null) {
						if(!this.ruleForm.kucun&&this.ruleForm.kucun!=0){
							this.$message.error("采购数量不能为空");
							return
						}
						if(objcross.kucun<this.ruleForm.kucun){
							this.$message.error("采购数量不足");
							return
						}
					}
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
								// 跨表计算
								var objcross = this.$storage.getObj('crossObj');
								var table = this.$storage.get('crossTable');
								objcross.kucun = Number((parseFloat(objcross.kucun) - parseFloat(this.ruleForm.kucun)).toFixed(2))
								await this.$http({
									url: `${table}/update`,
									method: "post",
									data: objcross
								}).then(({ data }) => {});
							}
							await this.$http({
								url: `yuanliaocaigou/${!this.ruleForm.id ? "save" : "update"}`,
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
											this.parent.yuanliaocaigouCrossAddOrUpdateFlag = false;
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
				this.parent.yuanliaocaigouCrossAddOrUpdateFlag = false;
			},
			yuanliaozhaopianUploadChange(fileUrls) {
				this.ruleForm.yuanliaozhaopian = fileUrls;
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
