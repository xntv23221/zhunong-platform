<template>
	<div class="home-content" :style="{'backgroundImage': indexBgUrl?`url(${$base.url + indexBgUrl})`:''}">
		<!-- title -->
		<div id="home-title" class="home-title animate__animated">
			<div class="titles" >
				<span>欢迎使用</span>
				{{this.$project.projectName}}
			</div>
		</div>
		<!-- statis -->
		<div class="statis-box">
			<div id="statis1" class="statis1 animate__animated" v-if="isAuth('yuanliaocaigou','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-liulan12"></span>
				</div>
				<div class="right">
					<div class="num">{{yuanliaocaigouCount}}</div>
					<div class="name">原料采购总数</div>
				</div>
			</div>
			<div id="statis2" class="statis2 animate__animated" v-if="isAuth('orders','首页总数')">
				<div class="left">
					<span class="icon iconfont icon-liulan12"></span>
				</div>
				<div class="right">
					<div class="num">{{ordersCount}}</div>
					<div class="name">订单总数</div>
				</div>
			</div>
		</div>
		<!-- statis -->
	
		<!-- echarts -->
		<!-- 10 -->
		<div class="type10">
			<div class="echarts1 animate__animated" v-if="isAuth('yuanliaocaigou','首页统计',2)">
				<div id="yuanliaocaigouChart1" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts2 animate__animated" v-if="isAuth('orders','首页统计',2)">
				<el-select
					v-if="changeStatQuery(['users'])"
					v-model="orderschartQuery1.goodname" placeholder="商品名称" @change="ordersChat1" clearable>
					<el-option v-for="item in ordersChartOptions1" :label="item" :value="item"></el-option>
				</el-select>
				<div id="ordersChart1" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts3 animate__animated" v-if="isAuth('orders','首页统计',2)">
				<el-select
					v-if="changeStatQuery(['users'])"
					v-model="orderschartQuery2.goodname" placeholder="商品名称" @change="ordersChat2" clearable>
					<el-option v-for="item in ordersChartOptions2" :label="item" :value="item"></el-option>
				</el-select>
				<div id="ordersChart2" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts4 animate__animated" v-if="isAuth('orders','首页统计',2)">
				<el-select
					v-if="changeStatQuery(['users'])"
					v-model="orderschartQuery3.goodname" placeholder="商品名称" @change="ordersChat3" clearable>
					<el-option v-for="item in ordersChartOptions3" :label="item" :value="item"></el-option>
				</el-select>
				<div id="ordersChart3" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts5 animate__animated" v-if="isAuth('orders','首页统计',2)">
				<el-select
					v-if="changeStatQuery(['users'])"
					v-model="orderschartQuery4.goodname" placeholder="商品名称" @change="ordersChat4" clearable>
					<el-option v-for="item in ordersChartOptions4" :label="item" :value="item"></el-option>
				</el-select>
				<div id="ordersChart4" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts6 animate__animated" v-if="isAuth('orders','首页统计',2)">
				<el-date-picker
					v-if="changeStatQuery(['users'])"
					v-model="orderschartQuery5.addtimeArr"
					type="datetimerange"
					range-separator="至"
					value-format="yyyy-MM-dd HH:mm:ss"
					format="yyyy-MM-dd HH:mm:ss"
					start-placeholder="开始创建时间"
					end-placeholder="结束创建时间"
					@change="ordersChat5">
				</el-date-picker>
				<div id="ordersChart5" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts7 animate__animated" v-if="isAuth('orders','首页统计',2)">
				<el-date-picker
					v-if="changeStatQuery(['users'])"
					v-model="orderschartQuery6.addtimeArr"
					type="datetimerange"
					range-separator="至"
					value-format="yyyy-MM-dd HH:mm:ss"
					format="yyyy-MM-dd HH:mm:ss"
					start-placeholder="开始创建时间"
					end-placeholder="结束创建时间"
					@change="ordersChat6">
				</el-date-picker>
				<div id="ordersChart6" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts8 animate__animated" v-if="isAuth('orders','首页统计',2)">
				<el-date-picker
					v-if="changeStatQuery(['users'])"
					v-model="orderschartQuery7.addtimeArr"
					type="datetimerange"
					range-separator="至"
					value-format="yyyy-MM-dd HH:mm:ss"
					format="yyyy-MM-dd HH:mm:ss"
					start-placeholder="开始创建时间"
					end-placeholder="结束创建时间"
					@change="ordersChat7">
				</el-date-picker>
				<div id="ordersChart7" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts9 animate__animated" v-if="isAuth('orders','首页统计',2)">
				<el-date-picker
					v-if="changeStatQuery(['users'])"
					v-model="orderschartQuery8.addtimeArr"
					type="datetimerange"
					range-separator="至"
					value-format="yyyy-MM-dd HH:mm:ss"
					format="yyyy-MM-dd HH:mm:ss"
					start-placeholder="开始创建时间"
					end-placeholder="结束创建时间"
					@change="ordersChat8">
				</el-date-picker>
				<div id="ordersChart8" style="width: 100%;height: 100%"></div>
			</div>
			<div class="echarts10 animate__animated" v-if="isAuth('orders','首页统计',2)">
				<el-date-picker
					v-if="changeStatQuery(['users'])"
					v-model="orderschartQuery9.addtimeArr"
					type="datetimerange"
					range-separator="至"
					value-format="yyyy-MM-dd HH:mm:ss"
					format="yyyy-MM-dd HH:mm:ss"
					start-placeholder="开始创建时间"
					end-placeholder="结束创建时间"
					@change="ordersChat9">
				</el-date-picker>
				<div id="ordersChart9" style="width: 100%;height: 100%"></div>
			</div>
		</div>
		<!-- echarts -->
	</div>
</template>
<script>
import 'animate.css'
//10
import router from '@/router/router-static'
import * as echarts from 'echarts'
export default {
	data() {
		return {
			yuanliaocaigouCount: 0,
			ordersCount: 0,
			orderschartQuery1: {},
			ordersChartOptions1: [],
			orderschartQuery2: {},
			ordersChartOptions2: [],
			orderschartQuery3: {},
			ordersChartOptions3: [],
			orderschartQuery4: {},
			ordersChartOptions4: [],
			orderschartQuery5: {},
			orderschartQuery6: {},
			orderschartQuery7: {},
			orderschartQuery8: {},
			orderschartQuery9: {},
			line: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":15,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"minInterval":1,"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":30,"padding":0,"shadowOffsetX":0,"margin":10,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"truncate","borderRadius":0,"borderWidth":0,"width":120,"interval":0,"fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#29C41F","#E5D6C9","#FBC88C","#4FB7F1","#2CD89E","#89e6d8","#4495ac","#9a60b4","#ea7ccc"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"animationDuration":6000,"symbol":"","label":{"color":"#725BFF","show":false,"position":"top"},"symbolSize":[15,15],"symbolOffset":[0,1],"animation":true},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"}},
			bar: {"backgroundColor":"transparent","yAxis":{"axisLabel":{"borderType":"solid","rotate":0,"padding":0,"shadowOffsetX":0,"margin":12,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"width":"","fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#666","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"minInterval":1,"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,0.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"xAxis":{"axisLabel":{"borderType":"solid","rotate":30,"padding":0,"shadowOffsetX":0,"margin":10,"backgroundColor":"transparent","borderColor":"#000","shadowOffsetY":0,"color":"#333","shadowBlur":0,"show":true,"inside":false,"ellipsis":"...","overflow":"truncate","borderRadius":0,"borderWidth":0,"width":120,"interval":0,"fontSize":12,"lineHeight":24,"shadowColor":"transparent","fontWeight":"normal","height":""},"axisTick":{"show":true,"length":5,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"inside":false},"splitLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":false},"minInterval":1,"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"cap":"butt","color":"#333","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"rgba(0,0,0,.5)"},"show":true},"splitArea":{"show":false,"areaStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"rgba(25,25,25,.3)","opacity":1,"shadowBlur":10,"shadowColor":"rgba(0,0,0,.5)"}}},"color":["#1f614f","#91cc75","#fac858","#ee6666","#73c0de","#3ba272","#fc8452","#9a60b4","#ea7ccc"],"legend":{"padding":0,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":14,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":20,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"grid":{"x":"20%","y":"15%","y2":"15%","x2":"5%"},"series":{"barWidth":"auto","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"colorBy":"data","barCategoryGap":"20%"},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"borderType":"solid","padding":0,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"base":{"animate":false,"interval":2000}},
			pie: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#29C41F","#00E6A7","#FDCE5F","#BD8CE8","#C500FF","#FD0B6A","#9ADBC4","#FFAA00","#000000"],"title":{"borderType":"solid","padding":[5,0,0,0],"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"left","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":14,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":[5,0,0,0],"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#666","shadowOffsetY":0,"orient":"horizontal","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":0,"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"80%","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#666","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#8838FF"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#666","color":"inherit","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#666","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"#8838FF","shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#8838FF"},"length2":14,"smooth":false}}},
			funnel: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#29C41F","#00E6A7","#FDCE5F","#BD8CE8","#C500FF","#FD0B6A","#9ADBC4","#FFAA00","#000000"],"title":{"borderType":"solid","padding":2,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"shadowBlur":0,"bottom":"auto","show":true,"right":"auto","top":"auto","borderRadius":0,"left":"center","borderWidth":0,"textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":12,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"#ccc","textShadowBlur":0},"shadowColor":"transparent"},"legend":{"padding":5,"itemGap":10,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"orient":"vertical","shadowBlur":0,"bottom":"auto","itemHeight":2,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"left","borderWidth":0,"width":"auto","itemWidth":2,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":20,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{"itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"#000","shadowOffsetY":0,"color":"","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"#000"},"label":{"borderType":"solid","rotate":0,"padding":0,"textBorderWidth":0,"backgroundColor":"transparent","borderColor":"#fff","color":"","show":true,"textShadowColor":"transparent","distanceToLabelLine":5,"ellipsis":"...","overflow":"none","borderRadius":0,"borderWidth":0,"fontSize":12,"lineHeight":18,"textShadowOffsetX":0,"position":"outside","textShadowOffsetY":0,"textBorderType":"solid","textBorderColor":"#fff","textShadowBlur":0},"labelLine":{"show":true,"length":10,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"shadowBlur":0,"width":1,"type":"solid","opacity":1,"shadowColor":"#000"},"length2":14,"smooth":false}}},
			boardBase: {"funnelNum":8,"lineNum":8,"radarNum":8,"gaugeNum":8,"barNum":8,"pieNum":8},
			gauge: {"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"backgroundColor":"transparent","color":["#29C41F","#e67d29","#50DFAC","#BD8CE8","#C500FF","#FD0B6A","#9ADBC4","#FFAA00","#000000"],"title":{"top":"top","left":"left","textStyle":{"fontSize":14,"lineHeight":24,"color":"#333","fontWeight":600}},"series":{"pointer":{"offsetCenter":[0,"10%"],"icon":"path://M2.9,0.7L2.9,0.7c1.4,0,2.6,1.2,2.6,2.6v115c0,1.4-1.2,2.6-2.6,2.6l0,0c-1.4,0-2.6-1.2-2.6-2.6V3.3C0.3,1.9,1.4,0.7,2.9,0.7z","width":8,"length":"80%"},"axisLine":{"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"opacity":0.5,"shadowBlur":1,"shadowColor":"#000"},"roundCap":true},"anchor":{"show":true,"itemStyle":{"color":"inherit"},"size":18,"showAbove":true},"emphasis":{"disabled":false},"progress":{"show":true,"roundCap":true,"overlap":true},"splitNumber":25,"detail":{"formatter":"{value}","backgroundColor":"inherit","color":"#fff","borderRadius":3,"width":20,"fontSize":12,"height":10},"title":{"fontSize":14},"animation":true}},
			radar: {"backgroundColor":"transparent","radar":{"shape":"circle"},"color":["#365E77","#DF308C","#0CB906","#7690cb","#49ada0","#5BBAEC","#a68a28","#EE142F","#FFE9E9"],"legend":{"padding":5,"itemGap":5,"shadowOffsetX":0,"backgroundColor":"transparent","borderColor":"#ccc","shadowOffsetY":0,"orient":"vertical","shadowBlur":0,"bottom":"auto","itemHeight":4,"show":true,"icon":"roundRect","itemStyle":{"borderType":"solid","shadowOffsetX":0,"borderColor":"inherit","shadowOffsetY":0,"color":"inherit","shadowBlur":0,"borderWidth":0,"opacity":1,"shadowColor":"transparent"},"right":"auto","top":"auto","borderRadius":0,"lineStyle":{"shadowOffsetX":0,"shadowOffsetY":0,"color":"inherit","shadowBlur":0,"width":"auto","type":"inherit","opacity":1,"shadowColor":"transparent"},"left":"right","borderWidth":0,"width":"auto","itemWidth":4,"textStyle":{"textBorderWidth":0,"color":"inherit","textShadowColor":"transparent","ellipsis":"...","overflow":"none","fontSize":12,"lineHeight":24,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":500,"textBorderColor":"transparent","textShadowBlur":0},"shadowColor":"rgba(0,0,0,.3)","height":"auto"},"series":{},"tooltip":{"backgroundColor":"#123","textStyle":{"color":"#fff"}},"title":{"top":"top","left":"left","textStyle":{"textBorderWidth":0,"color":"#333","textShadowColor":"transparent","fontSize":14,"lineHeight":14,"textShadowOffsetX":0,"textShadowOffsetY":0,"textBorderType":"solid","fontWeight":600,"textBorderColor":"#666","textShadowBlur":0}}},
			indexBgUrl: '',
			indexLogoUrl: '',
		};
	},
	mounted(){
		this.init();
		this.getyuanliaocaigouCount();
		if(this.isAuth('yuanliaocaigou','首页统计',2)){
			this.yuanliaocaigouChat1();
		}
		this.getordersCount();
		if(this.isAuth('orders','首页统计',2)){
			this.getordersgoodnameOptions1()
			this.ordersChat1();
		}
		if(this.isAuth('orders','首页统计',2)){
			this.getordersgoodnameOptions2()
			this.ordersChat2();
		}
		if(this.isAuth('orders','首页统计',2)){
			this.getordersgoodnameOptions3()
			this.ordersChat3();
		}
		if(this.isAuth('orders','首页统计',2)){
			this.getordersgoodnameOptions4()
			this.ordersChat4();
		}
		if(this.isAuth('orders','首页统计',2)){
			this.ordersChat5();
		}
		if(this.isAuth('orders','首页统计',2)){
			this.ordersChat6();
		}
		if(this.isAuth('orders','首页统计',2)){
			this.ordersChat7();
		}
		if(this.isAuth('orders','首页统计',2)){
			this.ordersChat8();
		}
		if(this.isAuth('orders','首页统计',2)){
			this.ordersChat9();
		}
		window.addEventListener('scroll', this.handleScroll)
		setTimeout(()=>{
			this.handleScroll()
		},100)
	},
	created() {
		this.$http.get('config/info?name=bIndexBackgroundImg',).then(rs=>{this.indexBgUrl = rs.data.data?rs.data.data.value:''})
		this.$http.get('config/info?name=bHomeLogo',).then(rs=>{this.indexLogoUrl = rs.data.data?rs.data.data.value:''})
	},
	computed: {
		sessionForm() {
			return JSON.parse(this.$storage.getObj('userForm'))
		},
		avatar(){
			return this.$storage.get('headportrait')?this.$storage.get('headportrait'):''
		},
	},
	methods:{
		handleScroll() {
			let arr = [
				{id:'home-title',css:'animate__'},
				{id:'statis1',css:'animate__'},
				{id:'statis2',css:'animate__'},
				{id:'yuanliaocaigouChart1',css:'animate__'},
				{id:'ordersChart1',css:'animate__'},
				{id:'ordersChart2',css:'animate__'},
				{id:'ordersChart3',css:'animate__'},
				{id:'ordersChart4',css:'animate__'},
				{id:'ordersChart5',css:'animate__'},
				{id:'ordersChart6',css:'animate__'},
				{id:'ordersChart7',css:'animate__'},
				{id:'ordersChart8',css:'animate__'},
				{id:'ordersChart9',css:'animate__'},
			]
			
			for (let i in arr) {
				let doc = document.getElementById(arr[i].id)
				if (doc) {
					let top = doc.offsetTop
					let win_top = window.innerHeight + window.pageYOffset
					// console.log(top,win_top)
					if (win_top > top && doc.classList.value.indexOf(arr[i].css) < 0) {
						// console.log(doc)
						doc.classList.add(arr[i].css)
					}
				}
			}
		},
		// 统计图动画
		myChartInterval(type, xAxisData, seriesData, myChart) {
			this.$nextTick(() => {
				setInterval(() => {
					let xAxis = xAxisData.shift()
					xAxisData.push(xAxis)
					let series = seriesData.shift()
					seriesData.push(series)
				
					if (type == 1) {
						myChart.setOption({
							xAxis: [{
								data: xAxisData
							}],
							series: [{
								data: seriesData
							}]
						});
					}
					if (type == 2) {
						myChart.setOption({
							yAxis: [{
								data: xAxisData
							}],
							series: [{
								data: seriesData
							}]
						});
					}
				}, $template2.back.board.bar.base.interval);
			})
		},
		changeStatQuery(arr) {
			if(arr.length==1) {
				if(arr[0] == 'users'&&this.$storage.get("sessionTable")=='users') {
					return true
				}
			}
			let role = this.$storage.get('role')
			for(let x in arr) {
				if(arr[x] == role) {
					return true
				}
			}
			return false
		},
		init(){
			if(this.$storage.get('Token')){
				this.$http({
					url: `${this.$storage.get('sessionTable')}/session`,
					method: "get"
				}).then(({ data }) => {
					if (data && data.code != 0) {
						router.push({ name: 'login' })
					}
				});
			}else{
				router.push({ name: 'login' })
			}
		},
		getyuanliaocaigouCount() {
			this.$http({
				url: `yuanliaocaigou/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.yuanliaocaigouCount = data.data
				}
			})
		},
		yuanliaocaigouChat1(e=null) {
			this.$nextTick(()=>{
				var yuanliaocaigouChart1 = echarts.init(document.getElementById("yuanliaocaigouChart1"),'macarons');
				let params = {
				}
				if(params.conditionColumn) {
					params.conditionColumn += ';' + 'sfsh'
					params.conditionValue += ';' + '是'
				}else {
					params.conditionColumn = 'sfsh'
					params.conditionValue = '是'
				}
				if(params.conditionColumn) {
					params.conditionColumn += ';' + 'ispay'
					params.conditionValue += ';' + '已支付'
				}else {
					params.conditionColumn = 'ispay'
					params.conditionValue = '已支付'
				}
				this.$http({
					url: `yuanliaocaigou/value/yuanliaomingcheng/caigouzongjia`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.barNum){
								break;
							}
							xAxis.push(res[i].yuanliaomingcheng);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].yuanliaomingcheng
							})
						}
						var option = {};
						let titleObj = this.bar.title
						titleObj.text = '采购支出'
						
						const legendObj = this.bar.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.bar.tooltip?this.bar.tooltip:{})
				
						let xAxisObj = this.bar.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.bar.yAxis
						yAxisObj.type = 'value'
						let seriesObj = {
							data: yAxis,
							type: 'bar',
						}
						seriesObj = Object.assign(seriesObj , this.bar.series)
						const gridObj = this.bar.grid
						
						option = {
							backgroundColor: this.bar.backgroundColor,
							color: this.bar.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						yuanliaocaigouChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							yuanliaocaigouChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},


		getordersCount() {
			this.$http({
				url: `orders/count`,
				method: "get"
			}).then(({
				data
			}) => {
				if (data && data.code == 0) {
					this.ordersCount = data.data
				}
			})
		},
		getordersgoodnameOptions1() {
			this.$http.get('option/orders/goodname',
			).then(rs=>{
				this.ordersChartOptions1 = rs.data.data
			})
		},
		ordersChat1(e=null) {
			if(this.changeStatQuery(['users'])) {
				document.getElementById('ordersChart1').setAttribute('style','width: 100%;height: calc(100% - 50px)')
			}
			this.$nextTick(()=>{
				var ordersChart1 = echarts.init(document.getElementById("ordersChart1"),'macarons');
				let params = {
				}
				if(this.orderschartQuery1.goodname) {
					params.conditionColumn = 'goodname'
					params.conditionValue = this.orderschartQuery1.goodname
				}
				this.$http({
					url: `orders/value/goodname/buynumber`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						res.sort((a,b)=>{
							return b.total - a.total
						})
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.barNum){
								break;
							}
							xAxis.push(res[i].goodname);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].goodname
							})
						}
						var option = {};
						let titleObj = this.bar.title
						titleObj.text = '商品销量'
						
						const legendObj = this.bar.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.bar.tooltip?this.bar.tooltip:{})
				
						let xAxisObj = this.bar.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.bar.yAxis
						yAxisObj.type = 'value'
						let seriesObj = {
							data: yAxis,
							type: 'bar',
						}
						seriesObj = Object.assign(seriesObj , this.bar.series)
						const gridObj = this.bar.grid
						
						option = {
							backgroundColor: this.bar.backgroundColor,
							color: this.bar.color,
							title: titleObj,
							legend: legendObj,
							grid: gridObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj]
						};
						// 使用刚指定的配置项和数据显示图表。
						ordersChart1.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							ordersChart1.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},

		getordersgoodnameOptions2() {
			this.$http.get('option/orders/goodname',
			).then(rs=>{
				this.ordersChartOptions2 = rs.data.data
			})
		},
		ordersChat2(e=null) {
			if(this.changeStatQuery(['users'])) {
				document.getElementById('ordersChart2').setAttribute('style','width: 100%;height: calc(100% - 50px)')
			}
			this.$nextTick(()=>{

				var ordersChart2 = echarts.init(document.getElementById("ordersChart2"),'macarons');
				let params = {
				}
				if(this.orderschartQuery2.goodname) {
					params.conditionColumn = 'goodname'
					params.conditionValue = this.orderschartQuery2.goodname
				}
				this.$http({
					url: `orders/value/goodname/total`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						res.sort((a,b)=>{
							return b.total - a.total
						})
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.funnelNum){
								break;
							}
							xAxis.push(res[i].goodname);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].goodname
							})
						}
						var option = {};
						let titleObj = this.funnel.title
						titleObj.text = '商品销售额'
						
						let legendObj = {
							data: xAxis,
						}
						legendObj = Object.assign(legendObj , this.funnel.legend)
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.funnel.tooltip?this.funnel.tooltip:{})
						let seriesObj = {
							name: '商品销售额',
							data: pArray,
							type: 'funnel',
							left: '10%',
							top: 60,
							bottom: 60,
							width: '80%',
							minSize: '0%',
							maxSize: '100%',
						}
						seriesObj = Object.assign(seriesObj , this.funnel.series)
						const gridObj = this.funnel.grid
						option = {
							backgroundColor: this.funnel.backgroundColor,
							color: this.funnel.color,
							title: titleObj,
							legend: legendObj,
							tooltip: tooltipObj,
							series: seriesObj,
							grid: gridObj
						}
						// 使用刚指定的配置项和数据显示图表。
						ordersChart2.setOption(option);
				
						//根据窗口的大小变动图表
						window.onresize = function() {
							ordersChart2.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},

		getordersgoodnameOptions3() {
			this.$http.get('option/orders/goodname',
			).then(rs=>{
				this.ordersChartOptions3 = rs.data.data
			})
		},
		ordersChat3(e=null) {
			if(this.changeStatQuery(['users'])) {
				document.getElementById('ordersChart3').setAttribute('style','width: 100%;height: calc(100% - 50px)')
			}
			this.$nextTick(()=>{

				var ordersChart3 = echarts.init(document.getElementById("ordersChart3"),'macarons');
				let params = {
				}
				if(this.orderschartQuery3.goodname) {
					params.conditionColumn = 'goodname'
					params.conditionValue = this.orderschartQuery3.goodname
				}
				this.$http({
					url: `orders/value/goodtype/buynumber`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						res.sort((a,b)=>{
							return b.total - a.total
						})
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.pieNum){
								break;
							}
							xAxis.push(res[i].goodtype);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].goodtype
							})
						}
						var option = {};
						let titleObj = this.pie.title
						titleObj.text = '商品类销量'
						
						const legendObj = this.pie.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
						tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})
						
						let seriesObj = {
							type: 'pie',
							radius: '55%',
							center: ['50%', '60%'],
							data: pArray,
							emphasis: {
								itemStyle: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
						seriesObj = Object.assign(seriesObj , this.pie.series)
						const gridObj = this.pie.grid
						
						option = {
							backgroundColor: this.pie.backgroundColor,
							color: this.pie.color,
							title: titleObj,
							legend: legendObj,
							tooltip: tooltipObj,
							series: [seriesObj],
							grid: gridObj
						};
						// 使用刚指定的配置项和数据显示图表。
						ordersChart3.setOption(option);
						//根据窗口的大小变动图表
						window.onresize = function() {
							ordersChart3.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},
		getordersgoodnameOptions4() {
			this.$http.get('option/orders/goodname',
			).then(rs=>{
				this.ordersChartOptions4 = rs.data.data
			})
		},
		ordersChat4(e=null) {
			if(this.changeStatQuery(['users'])) {
				document.getElementById('ordersChart4').setAttribute('style','width: 100%;height: calc(100% - 50px)')
			}
			this.$nextTick(()=>{

				var ordersChart4 = echarts.init(document.getElementById("ordersChart4"),'macarons');
				let params = {
				}
				if(this.orderschartQuery4.goodname) {
					params.conditionColumn = 'goodname'
					params.conditionValue = this.orderschartQuery4.goodname
				}
				this.$http({
					url: `orders/value/goodtype/total`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						res.sort((a,b)=>{
							return b.total - a.total
						})
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.pieNum){
								break;
							}
							xAxis.push(res[i].goodtype);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].goodtype
							})
						}
						var option = {};
						let titleObj = this.pie.title
						titleObj.text = '商品类销售额'
						
						const legendObj = this.pie.legend
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c} ({d}%)'}
						tooltipObj = Object.assign(tooltipObj , this.pie.tooltip?this.pie.tooltip:{})
						
						let seriesObj = {
							type: 'pie',
							radius: ['25%', '55%'],
							center: ['50%', '60%'],
							data: pArray,
							emphasis: {
								itemStyle: {
									shadowBlur: 10,
									shadowOffsetX: 0,
									shadowColor: 'rgba(0, 0, 0, 0.5)'
								}
							}
						}
						seriesObj = Object.assign(seriesObj , this.pie.series)
						const gridObj = this.pie.grid
						option = {
							backgroundColor: this.pie.backgroundColor,
							color: this.pie.color,
							title: titleObj,
							legend: legendObj,
							tooltip: tooltipObj,
							series: [seriesObj],
							grid: gridObj
						};
						// 使用刚指定的配置项和数据显示图表。
						ordersChart4.setOption(option);

						//根据窗口的大小变动图表
						window.onresize = function() {
							ordersChart4.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},
		ordersChat5(e=null) {
			if(this.changeStatQuery(['users'])) {
				document.getElementById('ordersChart5').setAttribute('style','width: 100%;height: calc(100% - 50px)')
			}
			this.$nextTick(()=>{

				var ordersChart5 = echarts.init(document.getElementById("ordersChart5"),'macarons');
				let params = {
				}
				if(this.orderschartQuery5.addtimeArr&&this.orderschartQuery5.addtimeArr.length) {
					params.conditionColumn = 'addtime,addtime'
					params.conditionValue = this.orderschartQuery5.addtimeArr.join(',')
				}
				if(this.orderschartQuery5.addtime) {
					params.conditionColumn = 'addtime'
					params.conditionValue = this.orderschartQuery5.addtime
				}
				this.$http({
					url: `orders/value/addtime/buynumber/日`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						res.sort((a,b)=>{
							return b.total - a.total
						})
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.lineNum){
								break;
							}
							xAxis.push(res[i].addtime);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].addtime
							})
						}
						var option = {};
						let titleObj = this.line.title
						titleObj.text = '日销售量'
						
						const legendObj = this.line.legend
						let tooltipObj = { trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.line.tooltip?this.line.tooltip:{})
						
						let xAxisObj = this.line.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.line.yAxis
						yAxisObj.type = 'value'
						
						let seriesObj = {
							data: yAxis,
							type: 'line',
						}
						seriesObj = Object.assign(seriesObj , this.line.series)
						const gridObj = this.line.grid
						option = {
							backgroundColor: this.line.backgroundColor,
							color: this.line.color,
							title: titleObj,
							legend: legendObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj],
							grid: gridObj
						};
						// 使用刚指定的配置项和数据显示图表。
						ordersChart5.setOption(option);
						//根据窗口的大小变动图表
						window.onresize = function() {
							ordersChart5.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},
		ordersChat6(e=null) {
			if(this.changeStatQuery(['users'])) {
				document.getElementById('ordersChart6').setAttribute('style','width: 100%;height: calc(100% - 50px)')
			}
			this.$nextTick(()=>{

				var ordersChart6 = echarts.init(document.getElementById("ordersChart6"),'macarons');
				let params = {
				}
				if(this.orderschartQuery6.addtimeArr&&this.orderschartQuery6.addtimeArr.length) {
					params.conditionColumn = 'addtime,addtime'
					params.conditionValue = this.orderschartQuery6.addtimeArr.join(',')
				}
				if(this.orderschartQuery6.addtime) {
					params.conditionColumn = 'addtime'
					params.conditionValue = this.orderschartQuery6.addtime
				}
				this.$http({
					url: `orders/value/addtime/total/日`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						res.sort((a,b)=>{
							return b.total - a.total
						})
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.barNum){
								break;
							}
							xAxis.push(res[i].addtime);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].addtime
							})
						}
						var option = {};
						let titleObj = this.bar.title
						titleObj.text = '日销售额'
						
						const legendObj = this.bar.legend
						
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.bar.tooltip?this.bar.tooltip:{})
						let xAxisObj = this.bar.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.bar.yAxis
						yAxisObj.type = 'value'
				
						let seriesObj = {
							data: yAxis,
							type: 'bar',
						}
						seriesObj = Object.assign(seriesObj , this.bar.series)
						const gridObj = this.bar.grid
						option = {
							backgroundColor: this.bar.backgroundColor,
							color: this.bar.color,
							title: titleObj,
							legend: legendObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj],
							grid: gridObj
						};
						// 使用刚指定的配置项和数据显示图表。
						ordersChart6.setOption(option);
						//根据窗口的大小变动图表
						window.onresize = function() {
							ordersChart6.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},
		ordersChat7(e=null) {
			if(this.changeStatQuery(['users'])) {
				document.getElementById('ordersChart7').setAttribute('style','width: 100%;height: calc(100% - 50px)')
			}
			this.$nextTick(()=>{

				var ordersChart7 = echarts.init(document.getElementById("ordersChart7"),'macarons');
				let params = {
				}
				if(this.orderschartQuery7.addtimeArr&&this.orderschartQuery7.addtimeArr.length) {
					params.conditionColumn = 'addtime,addtime'
					params.conditionValue = this.orderschartQuery7.addtimeArr.join(',')
				}
				if(this.orderschartQuery7.addtime) {
					params.conditionColumn = 'addtime'
					params.conditionValue = this.orderschartQuery7.addtime
				}
				this.$http({
					url: `orders/value/addtime/buynumber/月`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						res.sort((a,b)=>{
							return b.total - a.total
						})
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.lineNum){
								break;
							}
							xAxis.push(res[i].addtime);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].addtime
							})
						}
						var option = {};
						let titleObj = this.line.title
						titleObj.text = '月销售量'
						
						const legendObj = this.line.legend
						let tooltipObj = { trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.line.tooltip?this.line.tooltip:{})
						
						let xAxisObj = this.line.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.line.yAxis
						yAxisObj.type = 'value'
						
						let seriesObj = {
							data: yAxis,
							type: 'line',
						}
						seriesObj = Object.assign(seriesObj , this.line.series)
						const gridObj = this.line.grid
						option = {
							backgroundColor: this.line.backgroundColor,
							color: this.line.color,
							title: titleObj,
							legend: legendObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj],
							grid: gridObj
						};
						// 使用刚指定的配置项和数据显示图表。
						ordersChart7.setOption(option);
						//根据窗口的大小变动图表
						window.onresize = function() {
							ordersChart7.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},
		ordersChat8(e=null) {
			if(this.changeStatQuery(['users'])) {
				document.getElementById('ordersChart8').setAttribute('style','width: 100%;height: calc(100% - 50px)')
			}
			this.$nextTick(()=>{

				var ordersChart8 = echarts.init(document.getElementById("ordersChart8"),'macarons');
				let params = {
				}
				if(this.orderschartQuery8.addtimeArr&&this.orderschartQuery8.addtimeArr.length) {
					params.conditionColumn = 'addtime,addtime'
					params.conditionValue = this.orderschartQuery8.addtimeArr.join(',')
				}
				if(this.orderschartQuery8.addtime) {
					params.conditionColumn = 'addtime'
					params.conditionValue = this.orderschartQuery8.addtime
				}
				this.$http({
					url: `orders/value/addtime/total/月`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						res.sort((a,b)=>{
							return b.total - a.total
						})
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.barNum){
								break;
							}
							xAxis.push(res[i].addtime);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].addtime
							})
						}
						var option = {};
						let titleObj = this.bar.title
						titleObj.text = '月销售额'
						
						const legendObj = this.bar.legend
						
						let tooltipObj = {trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.bar.tooltip?this.bar.tooltip:{})
						let xAxisObj = this.bar.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.bar.yAxis
						yAxisObj.type = 'value'
				
						let seriesObj = {
							data: yAxis,
							type: 'bar',
						}
						seriesObj = Object.assign(seriesObj , this.bar.series)
						const gridObj = this.bar.grid
						option = {
							backgroundColor: this.bar.backgroundColor,
							color: this.bar.color,
							title: titleObj,
							legend: legendObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj],
							grid: gridObj
						};
						// 使用刚指定的配置项和数据显示图表。
						ordersChart8.setOption(option);
						//根据窗口的大小变动图表
						window.onresize = function() {
							ordersChart8.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},
		ordersChat9(e=null) {
			if(this.changeStatQuery(['users'])) {
				document.getElementById('ordersChart9').setAttribute('style','width: 100%;height: calc(100% - 50px)')
			}
			this.$nextTick(()=>{

				var ordersChart9 = echarts.init(document.getElementById("ordersChart9"),'macarons');
				let params = {
				}
				if(this.orderschartQuery9.addtimeArr&&this.orderschartQuery9.addtimeArr.length) {
					params.conditionColumn = 'addtime,addtime'
					params.conditionValue = this.orderschartQuery9.addtimeArr.join(',')
				}
				if(this.orderschartQuery9.addtime) {
					params.conditionColumn = 'addtime'
					params.conditionValue = this.orderschartQuery9.addtime
				}
				this.$http({
					url: `orders/value/addtime/buynumber/年`,
					method: "get",
					params
				}).then(({ data }) => {
					if (data && data.code === 0) {
						let res = data.data||[];
						let xAxis = [];
						let yAxis = [];
						let pArray = []
						res.sort((a,b)=>{
							return b.total - a.total
						})
						for(let i=0;i<res.length;i++){
							if(this.boardBase&&i==this.boardBase.lineNum){
								break;
							}
							xAxis.push(res[i].addtime);
							yAxis.push(parseFloat((res[i].total)));
							pArray.push({
								value: parseFloat((res[i].total)),
								name: res[i].addtime
							})
						}
						var option = {};
						let titleObj = this.line.title
						titleObj.text = '年销售量'
						
						const legendObj = this.line.legend
						let tooltipObj = { trigger: 'item',formatter: '{b} : {c}'}
						tooltipObj = Object.assign(tooltipObj , this.line.tooltip?this.line.tooltip:{})
						
						let xAxisObj = this.line.xAxis
						xAxisObj.type = 'category'
						xAxisObj.data = xAxis
						
						let yAxisObj = this.line.yAxis
						yAxisObj.type = 'value'
						
						let seriesObj = {
							data: yAxis,
							type: 'line',
						}
						seriesObj = Object.assign(seriesObj , this.line.series)
						const gridObj = this.line.grid
						option = {
							backgroundColor: this.line.backgroundColor,
							color: this.line.color,
							title: titleObj,
							legend: legendObj,
							tooltip: tooltipObj,
							xAxis: xAxisObj,
							yAxis: yAxisObj,
							series: [seriesObj],
							grid: gridObj
						};
						// 使用刚指定的配置项和数据显示图表。
						ordersChart9.setOption(option);
						//根据窗口的大小变动图表
						window.onresize = function() {
							ordersChart9.resize();
						};
					}else{
						this.$message({
							message: data.msg,
							type: "warning",
							duration: 1500,
						})
					}
				});
			})
		},
	}
};
</script>
<style lang="scss" scoped>
	.home-content {
		padding: 0 30px 30px 0;
		background: url(http://codegen.caihongy.cn/20250927/2b9794cbcba54a2294c5770ce8440751.png) no-repeat center center / 100% 100%;
		display: flex;
		width: 100%;
		flex-wrap: wrap;
		.home-title {
			padding: 10px 0 0;
			box-shadow: none;
			margin: 10px 0 0;
			display: none;
			width: 100%;
			justify-content: center;
			align-items: center;
			transition: 0.3s;
			.titles {
				padding: 0;
				color: #333;
				font-size: 30px;
				line-height: 60px;
				span {
				}
			}
		}
		.home-title:hover {
			transform: translate3d(0, 0px, 0);
		}
		.statis-box {
			margin: 20px auto;
			background: none;
			display: flex;
			width: 100%;
			justify-content: center;
			align-items: center;
			flex-wrap: wrap;
			.statis1 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis1:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.statis2 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis2:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.statis3 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis3:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.statis4 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis4:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.statis5 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis5:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.statis6 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis6:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.statis7 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis7:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.statis8 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis8:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.statis9 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis9:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.statis10 {
				border: 0px solid #e5f0f9;
				border-radius: 24px;
				padding: 20px 0;
				box-shadow: 0px 3px 6px 1px #EFF2FE;
				margin: 10px;
				background: #FFFFFF;
				display: flex;
				width: calc(15% - 20px);
				justify-content: center;
				align-items: center;
				transition: 0.3s;
				height: auto;
				.left {
					background: url(http://codegen.caihongy.cn/20251014/0282336157bb451d9c958df10272e682.png) no-repeat center center / 100% 100%;
					width: 48px;
					justify-content: center;
					align-items: center;
					height: 48px;
					.iconfont {
						border-radius: 0;
						padding: 0;
						color: #599BFF;
						background: none;
						font-weight: bold;
						display: none;
						width: 48px;
						font-size: 32px;
						line-height: 40px;
						text-align: center;
						height: 40px;
					}
				}
				.right {
					display: flex;
					flex-wrap: wrap;
					.num {
						color: #212D3F;
						font-weight: 400;
						display: flex;
						width: 100%;
						font-size: 30px;
						justify-content: center;
						order: 2;
					}
					.name {
						color: #29C41F;
						font-weight: bold;
						display: flex;
						width: 100%;
						font-size: 16px;
						justify-content: center;
					}
				}
			}
			.statis10:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
		}
		// echarts10
		.type10 {
			padding: 0;
			align-content: flex-start;
			background: none;
			display: flex;
			width: 100%;
			justify-content: space-between;
			flex-wrap: wrap;
			height: auto;
			.echarts1 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts1:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.echarts2 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts2:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.echarts3 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts3:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.echarts4 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts4:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.echarts5 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts5:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.echarts6 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts6:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.echarts7 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts7:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.echarts8 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts8:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.echarts9 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts9:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
			.echarts10 {
				border-radius: 16px;
				padding: 20px;
				box-shadow:  0px 3px 6px 1px #CBCBCB;
				margin: 10px;
				background:   linear-gradient( 180deg, #D5F5D3 0%, #FFFFFF 100%);
				width: calc(50% - 20px);
				transition: 0.3s;
				height: 400px;
			}
			.echarts10:hover {
				transform: translate3d(0, -6px, 0);
				z-index: 1;
			}
		}
	}
	
	.echarts-flag-2 {
		display: flex;
		flex-wrap: wrap;
		justify-content: space-between;
		padding: 10px 20px;
		background: rebeccapurple;
	
		&>div {
			width: 32%;
			height: 300px;
			margin: 10px 0;
			background: rgba(255,255,255,.1);
			border-radius: 8px;
			padding: 10px 20px;
		}
	}
	.animate__animated {
		animation-fill-mode: none;
	}
</style>
