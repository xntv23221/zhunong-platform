  
<template>
	<div class="main-content" :style='{"minHeight":"100vh","padding":"10px 20px","background":" linear-gradient( 180deg, #EFFFEF 0%, #F8F8F8 100%)"}'>
		<!-- 列表页 -->
		<template v-if="showFlag ">
			<el-form class="center-form-pv" :style='{"width":"100%","padding":"0","margin":"0 0 30px 0","borderRadius":"16px","background":" linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%)"}' :inline="true" :model="searchForm">
				<el-row :style='{"width":"100%","padding":"30px","position":"relative","flexWrap":"wrap","display":"flex"}' >
					<div  :style='{"margin":"0 1% 10px 0","display":"flex"}'>
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#333333","lineHeight":"40px","fontSize":"16px","fontWeight":"400","height":"40px"}' class="item-label">知识名称</label>
						<el-input v-model="searchForm.zhishimingcheng" placeholder="知识名称" @keydown.enter.native="search()" clearable></el-input>
					</div>
					<div  :style='{"margin":"0 1% 10px 0","display":"flex"}' class="select" label="知识分类" prop="zhishifenlei">
						<label :style='{"margin":"0 10px 0 0","whiteSpace":"nowrap","color":"#333333","lineHeight":"40px","fontSize":"16px","fontWeight":"400","height":"40px"}' class="item-label">知识分类</label>
						<el-select clearable v-model="searchForm.zhishifenlei" placeholder="请选择知识分类" >
							<el-option v-for="(item,index) in zhishifenleiOptions" v-bind:key="index" :label="item" :value="item"></el-option>
						</el-select>
					</div>
					<el-button class="search" type="success" @click="search()">
						<span class="icon iconfont icon-fangdajing01" :style='{"margin":"0 0px","fontSize":"16px","color":"#fff","height":"40px"}'></span>
						查询
					</el-button>
				</el-row>

				<el-row class="actions" :style='{"width":"100%","padding":"0 30px 30px 30px","flexWrap":"wrap","display":"flex"}'>
					<el-button class="add" v-if="isAuth('jishuzhishiku','新增')" type="success" @click="addOrUpdateHandler()">
						<span class="icon iconfont icon-tianjia1" :style='{"margin":"0 0px","fontSize":"16px","color":"#fff","height":"auto"}'></span>
						新增
					</el-button>
					<el-button class="del" v-if="isAuth('jishuzhishiku','删除')" :disabled="dataListSelections.length?false:true" type="danger" @click="deleteHandler()">
						<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"16px","color":"#fff","height":"auto"}'></span>
						删除
					</el-button>


				</el-row>
			</el-form>
			<div :style='{"padding":"20px","borderColor":"#212D3F","borderRadius":"16px","background":"linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%)","borderWidth":"0","width":"100%","borderStyle":"solid"}'>
				<el-table class="tables"
					:stripe='false'
					:style='{"padding":"0","borderColor":"#e7e8fc","borderRadius":"0","borderWidth":"0px 0 0 0px","background":"none","width":"100%","borderStyle":"solid"}' 
					:border='false'
					v-if="isAuth('jishuzhishiku','查看')"
					:data="dataList"
					v-loading="dataListLoading"
					@selection-change="selectionChangeHandler">
					<el-table-column :resizable='true' type="selection" align="center" width="50"></el-table-column>
					<el-table-column :resizable='true' :sortable='true' label="序号" type="index" width="50" />
					<el-table-column :resizable='true' :sortable='true'
												prop="zhishimingcheng"
						label="知识名称">
						<template slot-scope="scope">
							{{scope.row.zhishimingcheng}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="zhishifenlei"
						label="知识分类">
						<template slot-scope="scope">
							{{scope.row.zhishifenlei}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="zhuanyelingyu"
						label="专业领域">
						<template slot-scope="scope">
							{{scope.row.zhuanyelingyu}}
						</template>
					</el-table-column>
					<el-table-column  :resizable='true' prop="zhishizhaopian" width="200" label="知识照片">
						<template slot-scope="scope">
							<div v-if="scope.row.zhishizhaopian">
								<img v-if="scope.row.zhishizhaopian.substring(0,4)=='http'&&scope.row.zhishizhaopian.split(',w').length>1" :src="scope.row.zhishizhaopian" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.zhishizhaopian)">
								<img v-else-if="scope.row.zhishizhaopian.substring(0,4)=='http'" :src="scope.row.zhishizhaopian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView(scope.row.zhishizhaopian.split(',')[0])">
								<img v-else :src="$base.url+scope.row.zhishizhaopian.split(',')[0]" width="100" height="100" style="object-fit: cover" @click="imgPreView($base.url+scope.row.zhishizhaopian.split(',')[0])">
							</div>
							<div v-else>无图片</div>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' prop="zhishifujian" label="知识附件">
						<template slot-scope="scope">
							<el-button v-if="scope.row.zhishifujian" type="text" size="small" @click="download(scope.row.zhishifujian )">下载</el-button>
							<span v-else >无</span>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' prop="zhishishipin" label="知识视频">
						<template slot-scope="scope">
							<el-button v-if="scope.row.zhishishipin" type="text" size="small" @click="preClick(scope.row.zhishishipin)">预览</el-button>
							<span v-else >无</span>
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="gaoxiaomingcheng"
						label="高校名称">
						<template slot-scope="scope">
							{{scope.row.gaoxiaomingcheng}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="discussnum"
						label="评论数">
						<template slot-scope="scope">
							{{scope.row.discussnum}}
						</template>
					</el-table-column>
					<el-table-column :resizable='true' :sortable='true'
												prop="storeupnum"
						label="收藏数">
						<template slot-scope="scope">
							{{scope.row.storeupnum}}
						</template>
					</el-table-column>
					<el-table-column width="300" label="操作">
						<template slot-scope="scope">
							<el-button class="view" v-if=" isAuth('jishuzhishiku','查看')" type="success" @click="addOrUpdateHandler(scope.row.id,'info')">
								<span class="icon iconfont icon-chakan2" :style='{"margin":"0 0px","fontSize":"14px","color":"#333","display":"none","height":"40px"}'></span>
								查看
							</el-button>
							<el-button class="edit" v-if=" isAuth('jishuzhishiku','修改') " type="success" @click="addOrUpdateHandler(scope.row.id)">
								<span class="icon iconfont icon-xiugai13" :style='{"margin":"0 0px","fontSize":"14px","color":"rgba(255, 140, 0, 1)","display":"none","height":"40px"}'></span>
								修改
							</el-button>

							<el-button class="view" v-if="isAuth('jishuzhishiku','查看评论')" type="success" @click="disscussListHandler(scope.row.id)">
								<span class="icon iconfont icon-chakan2" :style='{"margin":"0 0px","fontSize":"14px","color":"#333","display":"none","height":"40px"}'></span>
								查看评论
							</el-button>



							<el-button class="del" v-if="isAuth('jishuzhishiku','删除')" type="primary" @click="deleteHandler(scope.row.id)">
								<span class="icon iconfont icon-shanchu6" :style='{"margin":"0 0px","fontSize":"14px","color":"rgba(220, 38, 38, 1)","display":"none","height":"40px"}'></span>
								删除
							</el-button>
						</template>
					</el-table-column>
				</el-table>
			</div>
			<el-pagination
				@size-change="sizeChangeHandle"
				@current-change="currentChangeHandle"
				:current-page="pageIndex"
				background
				:page-sizes="[10, 50, 100, 200]"
				:page-size="pageSize"
				:layout="layouts.join()"
				:total="totalPage"
				prev-text="< "
				next-text="> "
				:hide-on-single-page="false"
				:style='{"padding":"20px 0","margin":"0","whiteSpace":"nowrap","color":"#333","display":"flex","width":"100%","fontWeight":"500","justifyContent":"flex-end"}'
			></el-pagination>
		</template>
		<!-- 添加/修改页面  将父组件的search方法传递给子组件-->
		<add-or-update v-if="addOrUpdateFlag" :parent="this" ref="addOrUpdate"></add-or-update>





		<el-dialog title="预览图" :visible.sync="previewVisible" width="50%">
			<img :src="previewImg" alt="" style="width: 100%;">
		</el-dialog>
	</div>
</template>

<script>
	import * as echarts from 'echarts'
	import chinaJson from "@/components/echarts/china.json";
	import axios from 'axios';
	import AddOrUpdate from "./add-or-update";
	import {
		Loading
	} from 'element-ui';
	export default {
		data() {
			return {
				indexQueryCondition: '',
				zhishifenleiOptions: [],
				searchForm: {
					key: ""
				},
				form:{},
				dataList: [],
				pageIndex: 1,
				pageSize: 10,
				totalPage: 0,
				dataListLoading: false,
				dataListSelections: [],
				showFlag: true,
				addOrUpdateFlag:false,
				layouts: ["total","prev","pager","next","sizes","jumper"],
				previewImg: '',
				previewVisible: false,
			};
		},
		created() {
			if(this.statType) {
				return false
			}
			this.init();
			this.getDataList();
		},
		mounted() {
		},
		watch: {
		},
		filters: {
			htmlfilter: function (val) {
				return val.replace(/<[^>]*>/g).replace(/undefined/g,'');
			}
		},
		computed: {
			tablename(){
				return this.$storage.get('sessionTable')
			},
			role(){
				return this.$storage.get('role')
			},
		},
		components: {
			AddOrUpdate,
		},
		methods: {
			queryChange(arr){
				for(let x in arr) {
					if(arr[x] == this.role) {
						return true
					}
				}
				return false
			},
			imgPreView(url){
				this.previewImg = url
				this.previewVisible = true
				
			},
			init () {
				this.$http({
					url: `option/zhishifenlei/zhishifenlei`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.zhishifenleiOptions = data.data;
					} else {
						this.$message.error(data.msg);
					}
				});
			},
			search() {
				this.pageIndex = 1;
				this.getDataList();
			},

			// 获取数据列表
			getDataList() {
				this.dataListLoading = true;
				let params = {
					page: this.pageIndex,
					limit: this.pageSize,
					sort: 'id',
					order: 'desc',
				}
				if(this.searchForm.zhishimingcheng!='' && this.searchForm.zhishimingcheng!=undefined){
					params['zhishimingcheng'] = '%' + this.searchForm.zhishimingcheng + '%'
				}
				if(this.searchForm.zhishifenlei!='' && this.searchForm.zhishifenlei!=undefined){
					params['zhishifenlei'] = this.searchForm.zhishifenlei
				}
				let user = JSON.parse(this.$storage.getObj('userForm'))
				this.$http({
					url: "jishuzhishiku/page",
					method: "get",
					params: params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						this.dataList = data.data.list;
						this.totalPage = data.data.total;
					} else {
						this.dataList = [];
						this.totalPage = 0;
					}
					this.dataListLoading = false;
				});
			},
			// 每页数
			sizeChangeHandle(val) {
				this.pageSize = val;
				this.pageIndex = 1;
				this.getDataList();
			},
			// 当前页
			currentChangeHandle(val) {
				this.pageIndex = val;
				this.getDataList();
			},
			// 多选
			selectionChangeHandler(val) {
				this.dataListSelections = val;
			},
			// 添加/修改
			addOrUpdateHandler(id,type) {
				this.showFlag = false;
				this.addOrUpdateFlag = true;
				this.crossAddOrUpdateFlag = false;
				if(type!='info'&&type!='msg'){
					type = 'else';
				}
				this.$nextTick(() => {
					this.$refs.addOrUpdate.init(id,type );
				});
			},
			// 查看评论
			disscussListHandler(id,type) {
				this.$router.push({path:'/discussjishuzhishiku',query:{refid:id}});
			},
			// 下载
			download(file,type=1){
				let arr = file.replace(new RegExp('upload/', "g"), "")
				axios.get(this.$base.url + 'file/download?fileName=' + arr, {
					headers: {
						token: this.$storage.get('Token')
					},
					responseType: "blob"
				}).then(({
					data
				}) => {
					const binaryData = [];
					binaryData.push(data);
					const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
						type: 'application/pdf;chartset=UTF-8'
					}))
					const a = document.createElement('a')
					a.href = objectUrl
					a.download = arr
					// a.click()
					// 下面这个写法兼容火狐
					a.dispatchEvent(new MouseEvent('click', {
						bubbles: true,
						cancelable: true,
						view: window
					}))
					window.URL.revokeObjectURL(data)
				},err=>{
					axios.get((location.href.split(this.$base.name).length>1 ? location.href.split(this.$base.name)[0] :'') + this.$base.name + 'file/download?fileName=' + arr, {
						headers: {
							token: this.$storage.get('Token')
						},
						responseType: "blob"
					}).then(({
						data
					}) => {
						const binaryData = [];
						binaryData.push(data);
						const objectUrl = window.URL.createObjectURL(new Blob(binaryData, {
							type: 'application/pdf;chartset=UTF-8'
						}))
						const a = document.createElement('a')
						a.href = objectUrl
						a.download = arr
						// a.click()
						// 下面这个写法兼容火狐
						a.dispatchEvent(new MouseEvent('click', {
							bubbles: true,
							cancelable: true,
							view: window
						}))
						window.URL.revokeObjectURL(data)
					})
				})
			},
			// 预览
			preClick(file){
				if(!file){
					return false
				}
				window.open((location.href.split(this.$base.name).length>1 ? location.href.split(this.$base.name)[0] + this.$base.name + '/' + file :this.$base.url + file))
			},
			// 删除
			async deleteHandler(id ) {
				var ids = id? [Number(id)]: this.dataListSelections.map(item => {
					return Number(item.id);
				});
				await this.$confirm(`确定进行[${id ? "删除" : "批量删除"}]操作?`, "提示", {
					confirmButtonText: "确定",
					cancelButtonText: "取消",
					type: "warning"
				}).then(async () => {
					await this.$http({
						url: "jishuzhishiku/delete",
						method: "post",
						data: ids
					}).then(async ({ data }) => {
						if (data && data.code === 0) {
							for(let x in ids){
								await this.$http.get('storeup/list',{params: {
									page: 1,
									limit: 100,
									refid: ids[x],
									tablename: 'jishuzhishiku'
								}}).then(async obj=>{
									if(obj.data&&obj.data.code==0){
										if(obj.data.data.list.length){
											let arr = []
											for(let i in obj.data.data.list){
												arr.push(obj.data.data.list[i].id)
											}
											await this.$http.post('storeup/delete',arr).then(()=>{})
										}
									}
								})
							}
							this.$message({
								message: "操作成功",
								type: "success",
								duration: 1500,
								onClose: () => {
									this.search();
								}
							});
			
						} else {
							this.$message.error(data.msg);
						}
					});
				});
			},


		}

	};
</script>
<style lang="scss" scoped>
	
	.center-form-pv {
		.el-date-editor.el-input {
			width: auto;
		}
	}
	
	.el-input {
		width: auto;
	}
	
	// form
	.center-form-pv .el-input {
		width: 100%;
	}
	.center-form-pv .el-input /deep/ .el-input__inner {
		border: 1px solid #29C41F;
		border-radius: 4px;
		padding: 0 12px;
		box-shadow: none;
		outline: none;
		color: #29C41F;
		width: 100%;
		font-size: 16px;
		height: 41px;
	}
	.center-form-pv .el-select {
		width: 100%;
	}
	.center-form-pv .el-select /deep/ .el-input__inner {
		border: 1px solid #29C41F;
		border-radius: 4px;
		padding: 0 12px;
		box-shadow: none;
		outline: none;
		color: #29C41F;
		width: 100%;
		font-size: 16px;
		height: 41px;
	}
	.center-form-pv .el-date-editor {
		width: 100%;
	}
	
	.center-form-pv .el-date-editor /deep/ .el-input__inner {
		border: 1px solid #29C41F;
		border-radius: 4px;
		padding: 0 30px;
		box-shadow: none;
		outline: none;
		color: #29C41F;
		width: 100%;
		font-size: 16px;
		height: 41px;
	}
	
	.center-form-pv .search {
		border: 0;
		cursor: pointer;
		border-radius: 24px;
		padding: 0 12px 0 10px;
		outline: none;
		color: #fff;
		background: #29C41F;
		font-weight: bold;
		width: auto;
		font-size: 16px;
		min-width: 110px;
		height: 41px;
	}
	
	.center-form-pv .search:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .add {
		border: 0;
		cursor: pointer;
		border-radius: 24px;
		padding: 0 15px;
		margin: 4px;
		outline: none;
		color: #fff;
		background: #29C41F;
		width: auto;
		font-size: 14px;
		height: 41px;
	}
	
	.center-form-pv .actions .add:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .del {
		border: 0;
		cursor: pointer;
		border-radius: 24px;
		padding: 0 15px;
		margin: 4px;
		outline: none;
		color: #fff;
		background: #29C41F;
		width: auto;
		font-size: 14px;
		height: 41px;
	}
	
	.center-form-pv .actions .del:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .statis {
		border: 0;
		cursor: pointer;
		border-radius: 24px;
		padding: 0 15px;
		margin: 4px;
		outline: none;
		color: #fff;
		background: #29C41F;
		width: auto;
		font-size: 14px;
		height: 41px;
	}
	
	.center-form-pv .actions .statis:hover {
		opacity: 0.8;
	}
	
	.center-form-pv .actions .btn18 {
		border: 0;
		cursor: pointer;
		border-radius: 24px;
		padding: 0 15px;
		margin: 4px;
		outline: none;
		color: #fff;
		background: #29C41F;
		width: auto;
		font-size: 14px;
		height: 41px;
	}
	
	.center-form-pv .actions .btn18:hover {
		opacity: 0.8;
	}
	
	// table
	.el-table /deep/ .el-table__header-wrapper thead {
		color: #000000;
		font-weight: 400;
		width: 100%;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr {
		background: none;
	}
	
	.el-table /deep/ .el-table__header-wrapper thead tr th {
		padding: 12px 0;
		background: none;
		border-color: #545454;
		border-width: 0;
		border-style: dotted;
		text-align: center;
	}

	.el-table /deep/ .el-table__header-wrapper thead tr th .cell {
		padding: 0 0 0 5px;
		word-wrap: normal;
		color: #212D3F;
		white-space: normal;
		font-weight: 400;
		display: flex;
		vertical-align: middle;
		font-size: 15px;
		line-height: 24px;
		text-overflow: ellipsis;
		word-break: break-all;
		width: 100%;
		justify-content: flex-start;
		align-items: center;
		position: relative;
		min-width: 110px;
	}

	.el-table /deep/ .el-table__body-wrapper {
		position: relative;
	}
	.el-table /deep/ .el-table__body-wrapper tbody {
		width: 100%;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr {
		color: #212D3F;
		background: none;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #000000;
		background: none;
		border-color: #29C41F;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
		
	.el-table /deep/ .el-table__body-wrapper tbody tr:hover td {
		padding: 4px 0;
		color: #fff;
		background: rgba(41, 196, 31, 0.5);
		border-color: #29C41F;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td {
		padding: 4px 0;
		color: #000000;
		background: none;
		border-color: #29C41F;
		border-width: 0 0px 1px 0;
		border-style: solid;
		text-align: left;
	}

	.el-table /deep/ .el-table__body-wrapper tbody tr td .cell {
		padding: 0 0 0 5px;
		overflow: hidden;
		word-break: break-all;
		white-space: normal;
		font-size: inherit;
		line-height: 24px;
		text-overflow: ellipsis;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view {
		border: 0px solid #8B2121;
		cursor: pointer;
		padding: 0 10px;
		margin: 4px;
		color: #000000;
		font-weight: 400;
		font-size: 14px;
		border-radius: 6px;
		outline: none;
		background: none;
		width: auto;
		height: 36px;
		order: 3;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .view:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .add:hover {
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit {
		border: 0px solid #8B2121;
		cursor: pointer;
		padding: 0 10px;
		margin: 4px;
		color: #000000;
		font-weight: 400;
		font-size: 14px;
		border-radius: 6px;
		outline: none;
		background: none;
		width: auto;
		height: 36px;
		order: 3;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .edit:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del {
		border: 0px solid #8B2121;
		cursor: pointer;
		padding: 0 10px;
		margin: 4px;
		color: #000000;
		font-weight: 400;
		font-size: 14px;
		border-radius: 6px;
		outline: none;
		background: none;
		width: auto;
		height: 36px;
		order: 3;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .del:hover {
		opacity: 0.8;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8 {
		border: 0px solid #8B2121;
		cursor: pointer;
		padding: 0 10px;
		margin: 4px;
		color: #000000;
		font-weight: 400;
		font-size: 14px;
		border-radius: 6px;
		outline: none;
		background: none;
		width: auto;
		height: 36px;
		order: 3;
	}
	
	.el-table /deep/ .el-table__body-wrapper tbody tr td .btn8:hover {
		opacity: 0.8;
	}
	
	// pagination
	.main-content .el-pagination /deep/ .el-pagination__total {
		margin: 0 20px 0 0;
		color: #333333;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 40px;
		height: 40px;
	}
	
	.main-content .el-pagination /deep/ .btn-prev {
		border: 1px solid #FDC748;
		cursor: not-allowed;
		padding: 0;
		margin: 0 5px;
		color: #FDC748;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		border-radius: 8px  8px  8px  8px;
		background: #FFFFFF;
		width: 40px;
		height: 40px;
		order: 2;
	}
	
	.main-content .el-pagination /deep/ .btn-next {
		border: 1px solid #29C41F;
		cursor: not-allowed;
		padding: 0;
		margin: 0 5px;
		color: #29C41F;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		border-radius: 8px  8px  8px  8px;
		background: #FFFFFF;
		width: 40px;
		height: 40px;
		order: 2;
	}
	
	.main-content .el-pagination /deep/ .btn-prev:disabled {
		border: 1px solid #29C41F;
		cursor: not-allowed;
		padding: 0;
		margin: 0 5px;
		color: #29C41F;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		border-radius: 8px  8px  8px  8px;
		background: #FFFFFF;
		width: 40px;
		height: 40px;
		order: 2;
	}
	
	.main-content .el-pagination /deep/ .btn-next:disabled {
		border: 1px solid #29C41F;
		cursor: not-allowed;
		padding: 0;
		margin: 0 5px;
		color: #29C41F;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		border-radius: 8px  8px  8px  8px;
		background: #FFFFFF;
		width: 40px;
		height: 40px;
		order: 2;
	}

	.main-content .el-pagination /deep/ .el-pager {
		padding: 0;
		margin: 0;
		display: inline-block;
		vertical-align: top;
		order: 2;
	}

	.main-content .el-pagination /deep/ .el-pager .number {
		border: 1px solid #29C41F;
		cursor: not-allowed;
		padding: 0;
		margin: 0 5px;
		color: #333333;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 40px;
		border-radius: 8px  8px  8px  8px;
		background: #FFFFFF;
		width: 40px;
		height: 40px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number:hover {
		border: 0px solid #333333;
		cursor: not-allowed;
		padding: 0;
		margin: 0 5px;
		color: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 40px;
		border-radius: 8px  8px  8px  8px;
		background: #29C41F;
		width: 40px;
		height: 40px;
	}
	
	.main-content .el-pagination /deep/ .el-pager .number.active {
		border: 0px solid #333333;
		cursor: not-allowed;
		padding: 0;
		margin: 0 5px;
		color: #fff;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 40px;
		border-radius: 8px  8px  8px  8px;
		background: #29C41F;
		width: 40px;
		height: 40px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes {
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 40px;
		height: 40px;
		order: 1;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input {
		margin: 0 5px;
		width: 100px;
		position: relative;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__inner {
		border: 1px solid #29C41F;
		cursor: pointer;
		padding: 0 25px 0 8px;
		color: #333333;
		display: inline-block;
		font-size: 15px;
		line-height: 40px;
		border-radius: 3px;
		outline: 0;
		background: #FFFFFF;
		width: 100%;
		text-align: center;
		height: 40px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input span.el-input__suffix {
		top: 0;
		position: absolute;
		right: 0;
		height: 100%;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__sizes .el-input .el-input__suffix .el-select__caret {
		cursor: pointer;
		color: #fff;
		width: 25px;
		font-size: 15px;
		line-height: 28px;
		text-align: center;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump {
		margin: 0 0 0 24px;
		color: #333333;
		display: inline-block;
		vertical-align: top;
		font-size: 15px;
		line-height: 40px;
		height: 40px;
		order: 3;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input {
		border-radius: 3px;
		padding: 0 2px;
		margin: 0 2px;
		display: inline-block;
		width: 50px;
		font-size: 15px;
		line-height: 18px;
		position: relative;
		text-align: center;
		height: 28px;
	}
	
	.main-content .el-pagination /deep/ .el-pagination__jump .el-input .el-input__inner {
		border: 1px solid #29C41F;
		cursor: pointer;
		padding: 0 3px;
		color: #333333;
		display: inline-block;
		font-size: 15px;
		line-height: 40px;
		border-radius: 8px;
		outline: 0;
		background: #fff;
		width: 100%;
		text-align: center;
		height: 40px;
	}
	
	// list one
	.one .list1-view {
		border: 0;
		cursor: pointer;
		border-radius: 30px;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #481AD9;
		width: auto;
		font-size: 14px;
		min-width: 60px;
		height: 32px;
	}
	
	.one .list1-view:hover {
		opacity: 0.8;
	}
	
	.one .list1-edit {
		border: 0;
		cursor: pointer;
		border-radius: 30px;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #339933;
		width: auto;
		font-size: 14px;
		min-width: 60px;
		height: 32px;
	}
	
	.one .list1-edit:hover {
		opacity: 0.8;
	}
	
	.one .list1-del {
		border: 0;
		cursor: pointer;
		border-radius: 30px;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		outline: none;
		color: #fff;
		background: #f00;
		width: auto;
		font-size: 14px;
		min-width: 60px;
		height: 32px;
	}
	
	.one .list1-del:hover {
		opacity: 0.8;
	}
	
	.one .list1-btn8 {
		border: 0;
		cursor: pointer;
		padding: 0 10px;
		margin: 0 5px 5px 0;
		color: #fff;
		font-size: 14px;
		border-radius: 30px;
		outline: none;
		background: #333;
		width: auto;
		min-width: 60px;
		height: 32px;
		order: 11;
	}
	
	.one .list1-btn8:hover {
		opacity: 0.8;
	}
	
	.main-content .el-table .el-switch {
		display: inline-flex;
		vertical-align: middle;
		line-height: 30px;
		position: relative;
		align-items: center;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--left {
		cursor: pointer;
		margin: 0 10px 0 0;
		color: #333;
		font-weight: 500;
		display: none;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__label--right {
		cursor: pointer;
		margin: 0 0 0 10px;
		color: #333;
		font-weight: 500;
		display: none;
		vertical-align: middle;
		font-size: 16px;
		transition: .2s;
		height: 30px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core {
		border: 1px solid #000;
		cursor: pointer;
		border-radius: 15px;
		margin: 0;
		outline: 0;
		background: #000;
		display: inline-block;
		width: 36px;
		box-sizing: border-box;
		transition: border-color .3s,background-color .3s;
		height: 18px;
	}
	.main-content .el-table .el-switch /deep/ .el-switch__core::after {
		border-radius: 100%;
		top: 1px;
		left: 2px;
		background: #FFF;
		width: 14px;
		position: absolute;
		transition: all .3s;
		height: 14px;
	}
	.main-content .el-table .el-switch.is-checked /deep/ .el-switch__core::after {
		margin: 0 0 0 -16px;
		left: 100%;
	}
	
	.main-content .el-table .el-rate /deep/ .el-rate__item {
		cursor: pointer;
		display: inline-block;
		vertical-align: middle;
		font-size: 0;
		position: relative;
	}
	.main-content .el-table .el-rate /deep/ .el-rate__item .el-rate__icon {
		margin: 0 3px;
		display: inline-block;
		font-size: 18px;
		position: relative;
		transition: .3s;
	}

	.chartDialog /deep/ .el-dialog {
		background: #FFFFFF;
	}
</style>
