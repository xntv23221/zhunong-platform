import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
	import yonghu from '@/views/modules/yonghu/list'
	import nongmin from '@/views/modules/nongmin/list'
	import gaoxiao from '@/views/modules/gaoxiao/list'
	import zhishifenlei from '@/views/modules/zhishifenlei/list'
	import yuanliaofenlei from '@/views/modules/yuanliaofenlei/list'
	import nongchanpinleixing from '@/views/modules/nongchanpinleixing/list'
	import jishuzhishiku from '@/views/modules/jishuzhishiku/list'
	import zhuanjia from '@/views/modules/zhuanjia/list'
	import zhuanjiayuyue from '@/views/modules/zhuanjiayuyue/list'
	import nongyeyuanliao from '@/views/modules/nongyeyuanliao/list'
	import yuanliaocaigou from '@/views/modules/yuanliaocaigou/list'
	import caigoupeisong from '@/views/modules/caigoupeisong/list'
	import caigoushouhuo from '@/views/modules/caigoushouhuo/list'
	import caigoupingjia from '@/views/modules/caigoupingjia/list'
	import nongchanpinxinxi from '@/views/modules/nongchanpinxinxi/list'
	import orders from '@/views/modules/orders/list'
	import cart from '@/views/modules/cart/list'
	import address from '@/views/modules/address/list'
	import chargerecord from '@/views/modules/chargerecord/list'
	import users from '@/views/modules/users/list'
	import discussjishuzhishiku from '@/views/modules/discussjishuzhishiku/list'
	import discussnongchanpinxinxi from '@/views/modules/discussnongchanpinxinxi/list'
import config from '@/views/modules/config/list'

//2.配置路由   注意：名字
export const routes = [{
	path: '/',
	name: '系统首页',
	component: Index,
	children: [{
		// 这里不设置值，是把main作为默认页面
		path: '/',
		name: '系统首页',
		component: Home,
		meta: {icon:'', title:'center', affix: true}
	}, {
		path: '/updatePassword',
		name: '修改密码',
		component: UpdatePassword,
		meta: {icon:'', title:'updatePassword'}
	}, {
		path: '/pay',
		name: '支付',
		component: pay,
		meta: {icon:'', title:'pay'}
	}, {
		path: '/center',
		name: '个人信息',
		component: center,
		meta: {icon:'', title:'center'}
	}
	,{
		path: '/yonghu',
		name: '消费者/采购商端',
		component: yonghu
	}
	,{
		path: '/nongmin',
		name: '农民',
		component: nongmin
	}
	,{
		path: '/gaoxiao',
		name: '高校',
		component: gaoxiao
	}
	,{
		path: '/zhishifenlei',
		name: '知识分类',
		component: zhishifenlei
	}
	,{
		path: '/yuanliaofenlei',
		name: '原料分类',
		component: yuanliaofenlei
	}
	,{
		path: '/nongchanpinleixing',
		name: '农产品类型',
		component: nongchanpinleixing
	}
	,{
		path: '/jishuzhishiku',
		name: '技术知识库',
		component: jishuzhishiku
	}
	,{
		path: '/zhuanjia',
		name: '专家',
		component: zhuanjia
	}
	,{
		path: '/zhuanjiayuyue',
		name: '专家预约',
		component: zhuanjiayuyue
	}
	,{
		path: '/nongyeyuanliao',
		name: '农业原料',
		component: nongyeyuanliao
	}
	,{
		path: '/yuanliaocaigou',
		name: '原料采购',
		component: yuanliaocaigou
	}
	,{
		path: '/caigoupeisong',
		name: '采购配送',
		component: caigoupeisong
	}
	,{
		path: '/caigoushouhuo',
		name: '采购收货',
		component: caigoushouhuo
	}
	,{
		path: '/caigoupingjia',
		name: '采购评价',
		component: caigoupingjia
	}
	,{
		path: '/nongchanpinxinxi',
		name: '农产品信息',
		component: nongchanpinxinxi
	}
	,{
		path: '/orders/:status',
		name: '订单',
		component: orders
	}
	,{
		path: '/cart',
		name: '购物车表',
		component: cart
	}
	,{
		path: '/address',
		name: '地址',
		component: address
	}
	,{
		path: '/chargerecord',
		name: '充值记录表',
		component: chargerecord
	}
	,{
		path: '/users',
		name: '管理员',
		component: users
	}
	,{
		path: '/discussjishuzhishiku',
		name: '技术知识库评论',
		component: discussjishuzhishiku
	}
	,{
		path: '/discussnongchanpinxinxi',
		name: '农产品信息评论',
		component: discussnongchanpinxinxi
	}
	,{
		path: '/config/:type',
		name: '配置管理',
		component: config
	}
	]
	},
	{
		path: '/login',
		name: 'login',
		component: Login,
		meta: {icon:'', title:'login'}
	},
	{
		path: '/register',
		name: 'register',
		component: register,
		meta: {icon:'', title:'register'}
	},
	{
		path: '*',
		component: NotFound
	}
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
	mode: 'hash',
	/*hash模式改为history*/
	routes // （缩写）相当于 routes: routes
})
const originalPush = VueRouter.prototype.push
//修改原型对象中的push方法
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}
export default router;
