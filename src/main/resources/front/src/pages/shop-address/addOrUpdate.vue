<template>
	<div :style='{"width":"100%","padding":"20px 10% 40px","margin":"10px auto","position":"relative","background":"none"}'>
		<el-form class="add-update-preview" ref="form" :model="form" :rules="rules" label-width="200px">
			<el-form-item :style='{"border":"1px solid #707070","padding":"0","margin":"0 0 24px","background":"none"}' label="联系人" prop="name">
				<el-input v-model="form.name" placeholder="联系人"></el-input>
			</el-form-item>
			<el-form-item :style='{"border":"1px solid #707070","padding":"0","margin":"0 0 24px","background":"none"}' label="手机号码" prop="phone">
				<el-input v-model="form.phone" placeholder="手机号码"></el-input>
			</el-form-item>
			<el-form-item :style='{"border":"1px solid #707070","padding":"0","margin":"0 0 24px","background":"none"}' label="默认地址">
				<el-radio-group v-model="form.isdefault">
					<el-radio label="是"></el-radio>
					<el-radio label="否"></el-radio>
				</el-radio-group>
			</el-form-item>
			<el-form-item :style='{"border":"1px solid #707070","padding":"0","margin":"0 0 24px","background":"none"}' label="地址">
				<el-cascader v-model="value" :options="options" @change="handleChange" :props="{value:'label'}" />
			</el-form-item>
			<el-form-item :style='{"border":"1px solid #707070","padding":"0","margin":"0 0 24px","background":"none"}' label="详细地址">
				<el-input v-model="address" placeholder="详细地址"></el-input>
			</el-form-item>
			<el-form-item :style='{"width":"100%","padding":"0","margin":"20px auto","textAlign":"center"}'>
				<el-button class="submitBtn" type="primary" @click="onSubmit('form')">
					<span class="icon iconfont icon-xiugai13"></span>
					<span class="text">添加</span>
				</el-button>
				<el-button v-if="!isBuy" class="closeBtn" @click="goBack">
					<span class="icon iconfont icon-shanchu7"></span>
					<span class="text">取消</span>
				</el-button>
			</el-form-item>
		</el-form>
	</div>
</template>

<script>
	import { Loading } from 'element-ui';
	export default {
		//数据集合
		data() {
			return {
				form: {
					userid: Number(localStorage.getItem('frontUserid')),
					name: localStorage.getItem('username'),
					phone: '',
					isdefault: '是',
					address: ''
				},
				rules: {
					name: [{ required: true, message: '请输入联系人', trigger: 'blur' }],
					phone: [
						{ required: true, message: '请输入手机号码', trigger: 'blur' },
						{ required: true, validator: this.$validate.isMobile, trigger: 'blur' }
					],
					address: [{ required: true, message: '请选择收货地址', trigger: 'blur' }]
				},
				value: [],
				options: this.$config.cityList,
				address: '',
				isEdit: false,
				isBuy: false
			}
		},
		created() {
			if (this.$route.query.id != undefined) {
				this.isEdit = true;
				this.form = Object.assign({}, JSON.parse(this.$route.query.editObj));
				if(this.form.address) {
					let arr = this.form.address.split('-')
					this.value = [arr[0],arr[1],arr[2]]
					this.address = arr[3]
				}
			}
		},
		//方法集合
		methods: {
			buyAdd(){
				this.isBuy = true
			},
			onSubmit(formName) {
				if(!this.value.length) {
					this.$message.error('请选择省市区')
					return false
				}
				if(!this.address) {
					this.$message.error('请输入详细地址')
					return false
				}
				this.form.address = this.value.join('-') + '-' + this.address
				this.$refs[formName].validate((valid) => {
					if (valid) {
						this.$http.post(`address/${this.isEdit ? 'update' : 'add'}`, this.form).then(res => {
							if (res.data.code == 0) {
								this.$message({
									message: `${this.isEdit ? '更新' : '添加'}成功`,
									type: 'success',
									duration: 1500,
									onClose: () => {
										if(this.isBuy){
											this.$emit('change')
										}else{
											this.$router.go(-1);
									  }
									}
								});
							}
						});
					} else {
						return false;
					}
				});
			},
			getAddr() {
				if (this.address == '') {
					this.$message({
						message: '地址不能为空',
						type: 'error',
						duration: 1500
					});
					return;
				}
				this.form.address = this.address;
				this.dialogVisible = false;
			},
			goBack() {
				this.$router.go(-1);
			},
		}
	}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
	.add-update-preview .el-form-item /deep/ .el-form-item__label {
		padding: 0 0px 0 10px;
		color: #333;
		white-space: nowrap;
		font-weight: 500;
		width: 200px;
		font-size: 16px;
		line-height: 40px;
		text-align: left;
	}
	
	.add-update-preview .el-form-item /deep/ .el-form-item__content {
		margin-left: 200px;
	}
	
	.add-update-preview .el-input /deep/ .el-input__inner {
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
	
	.add-update-preview .el-select /deep/ .el-input__inner {
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
	
	.add-update-preview .el-date-editor /deep/ .el-input__inner {
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
	
	.add-update-preview /deep/ .el-upload-list .el-upload-list__item {
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
	
	.add-update-preview /deep/ .el-upload .el-icon-plus {
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
	
	.add-update-preview .el-textarea /deep/ .el-textarea__inner {
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
    .map{
        height: 50vh;
    }
	.add-update-preview .submitBtn {
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
	.add-update-preview .submitBtn:hover {
		opacity: 0.7;
		.icon {
			color: #fff;
		}
		.text {
			color: #fff;
		}
	}
	.add-update-preview .closeBtn {
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
	.add-update-preview .closeBtn:hover {
		opacity: 0.7;
		.icon {
		}
		.text {
		}
	}
</style>
