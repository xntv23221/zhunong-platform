import VueRouter from 'vue-router'
//引入组件
import Index from '../pages'
import Home from '../pages/home/home'
import Login from '../pages/login/login'
import Register from '../pages/register/register'
import Center from '../pages/center/center'
import Storeup from '../pages/storeup/list'
import AddrList from '../pages/shop-address/list'
import AddrAdd from '../pages/shop-address/addOrUpdate'
import Order from '../pages/shop-order/list'
import OrderConfirm from '../pages/shop-order/confirm'
import Cart from '../pages/shop-cart/list'
import payList from '../pages/pay'

import yonghuList from '../pages/yonghu/list'
import yonghuDetail from '../pages/yonghu/detail'
import yonghuAdd from '../pages/yonghu/add'
import nongminList from '../pages/nongmin/list'
import nongminDetail from '../pages/nongmin/detail'
import nongminAdd from '../pages/nongmin/add'
import gaoxiaoList from '../pages/gaoxiao/list'
import gaoxiaoDetail from '../pages/gaoxiao/detail'
import gaoxiaoAdd from '../pages/gaoxiao/add'
import zhishifenleiList from '../pages/zhishifenlei/list'
import zhishifenleiDetail from '../pages/zhishifenlei/detail'
import zhishifenleiAdd from '../pages/zhishifenlei/add'
import yuanliaofenleiList from '../pages/yuanliaofenlei/list'
import yuanliaofenleiDetail from '../pages/yuanliaofenlei/detail'
import yuanliaofenleiAdd from '../pages/yuanliaofenlei/add'
import nongchanpinleixingList from '../pages/nongchanpinleixing/list'
import nongchanpinleixingDetail from '../pages/nongchanpinleixing/detail'
import nongchanpinleixingAdd from '../pages/nongchanpinleixing/add'
import jishuzhishikuList from '../pages/jishuzhishiku/list'
import jishuzhishikuDetail from '../pages/jishuzhishiku/detail'
import jishuzhishikuAdd from '../pages/jishuzhishiku/add'
import zhuanjiaList from '../pages/zhuanjia/list'
import zhuanjiaDetail from '../pages/zhuanjia/detail'
import zhuanjiaAdd from '../pages/zhuanjia/add'
import zhuanjiayuyueList from '../pages/zhuanjiayuyue/list'
import zhuanjiayuyueDetail from '../pages/zhuanjiayuyue/detail'
import zhuanjiayuyueAdd from '../pages/zhuanjiayuyue/add'
import nongyeyuanliaoList from '../pages/nongyeyuanliao/list'
import nongyeyuanliaoDetail from '../pages/nongyeyuanliao/detail'
import nongyeyuanliaoAdd from '../pages/nongyeyuanliao/add'
import yuanliaocaigouList from '../pages/yuanliaocaigou/list'
import yuanliaocaigouDetail from '../pages/yuanliaocaigou/detail'
import yuanliaocaigouAdd from '../pages/yuanliaocaigou/add'
import caigoupeisongList from '../pages/caigoupeisong/list'
import caigoupeisongDetail from '../pages/caigoupeisong/detail'
import caigoupeisongAdd from '../pages/caigoupeisong/add'
import caigoushouhuoList from '../pages/caigoushouhuo/list'
import caigoushouhuoDetail from '../pages/caigoushouhuo/detail'
import caigoushouhuoAdd from '../pages/caigoushouhuo/add'
import caigoupingjiaList from '../pages/caigoupingjia/list'
import caigoupingjiaDetail from '../pages/caigoupingjia/detail'
import caigoupingjiaAdd from '../pages/caigoupingjia/add'
import nongchanpinxinxiList from '../pages/nongchanpinxinxi/list'
import nongchanpinxinxiDetail from '../pages/nongchanpinxinxi/detail'
import nongchanpinxinxiAdd from '../pages/nongchanpinxinxi/add'
import chargerecordList from '../pages/chargerecord/list'
import chargerecordDetail from '../pages/chargerecord/detail'
import chargerecordAdd from '../pages/chargerecord/add'
import discussjishuzhishikuList from '../pages/discussjishuzhishiku/list'
import discussjishuzhishikuDetail from '../pages/discussjishuzhishiku/detail'
import discussjishuzhishikuAdd from '../pages/discussjishuzhishiku/add'
import discussnongchanpinxinxiList from '../pages/discussnongchanpinxinxi/list'
import discussnongchanpinxinxiDetail from '../pages/discussnongchanpinxinxi/detail'
import discussnongchanpinxinxiAdd from '../pages/discussnongchanpinxinxi/add'

const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
	return originalPush.call(this, location).catch(err => err)
}

//配置路由
export default new VueRouter({
	routes:[
		{
      path: '/',
      redirect: '/index/home'
    },
		{
			path: '/index',
			component: Index,
			children:[
				{
					path: 'home',
					component: Home
				},
				{
					path: 'center',
					component: Center,
				},
				{
					path: 'pay',
					component: payList,
				},
				{
					path: 'storeup',
					component: Storeup
				},
                {
                    path: 'shop-address/list',
                    component: AddrList
                },
                {
                    path: 'shop-address/addOrUpdate',
                    component: AddrAdd
                },
				{
					path: 'shop-order/order',
					component: Order
				},
				{
					path: 'cart',
					component: Cart
				},
				{
					path: 'shop-order/orderConfirm',
					component: OrderConfirm
				},
				{
					path: 'yonghu',
					component: yonghuList
				},
				{
					path: 'yonghuDetail',
					component: yonghuDetail
				},
				{
					path: 'yonghuAdd',
					component: yonghuAdd
				},
				{
					path: 'nongmin',
					component: nongminList
				},
				{
					path: 'nongminDetail',
					component: nongminDetail
				},
				{
					path: 'nongminAdd',
					component: nongminAdd
				},
				{
					path: 'gaoxiao',
					component: gaoxiaoList
				},
				{
					path: 'gaoxiaoDetail',
					component: gaoxiaoDetail
				},
				{
					path: 'gaoxiaoAdd',
					component: gaoxiaoAdd
				},
				{
					path: 'zhishifenlei',
					component: zhishifenleiList
				},
				{
					path: 'zhishifenleiDetail',
					component: zhishifenleiDetail
				},
				{
					path: 'zhishifenleiAdd',
					component: zhishifenleiAdd
				},
				{
					path: 'yuanliaofenlei',
					component: yuanliaofenleiList
				},
				{
					path: 'yuanliaofenleiDetail',
					component: yuanliaofenleiDetail
				},
				{
					path: 'yuanliaofenleiAdd',
					component: yuanliaofenleiAdd
				},
				{
					path: 'nongchanpinleixing',
					component: nongchanpinleixingList
				},
				{
					path: 'nongchanpinleixingDetail',
					component: nongchanpinleixingDetail
				},
				{
					path: 'nongchanpinleixingAdd',
					component: nongchanpinleixingAdd
				},
				{
					path: 'jishuzhishiku',
					component: jishuzhishikuList
				},
				{
					path: 'jishuzhishikuDetail',
					component: jishuzhishikuDetail
				},
				{
					path: 'jishuzhishikuAdd',
					component: jishuzhishikuAdd
				},
				{
					path: 'zhuanjia',
					component: zhuanjiaList
				},
				{
					path: 'zhuanjiaDetail',
					component: zhuanjiaDetail
				},
				{
					path: 'zhuanjiaAdd',
					component: zhuanjiaAdd
				},
				{
					path: 'zhuanjiayuyue',
					component: zhuanjiayuyueList
				},
				{
					path: 'zhuanjiayuyueDetail',
					component: zhuanjiayuyueDetail
				},
				{
					path: 'zhuanjiayuyueAdd',
					component: zhuanjiayuyueAdd
				},
				{
					path: 'nongyeyuanliao',
					component: nongyeyuanliaoList
				},
				{
					path: 'nongyeyuanliaoDetail',
					component: nongyeyuanliaoDetail
				},
				{
					path: 'nongyeyuanliaoAdd',
					component: nongyeyuanliaoAdd
				},
				{
					path: 'yuanliaocaigou',
					component: yuanliaocaigouList
				},
				{
					path: 'yuanliaocaigouDetail',
					component: yuanliaocaigouDetail
				},
				{
					path: 'yuanliaocaigouAdd',
					component: yuanliaocaigouAdd
				},
				{
					path: 'caigoupeisong',
					component: caigoupeisongList
				},
				{
					path: 'caigoupeisongDetail',
					component: caigoupeisongDetail
				},
				{
					path: 'caigoupeisongAdd',
					component: caigoupeisongAdd
				},
				{
					path: 'caigoushouhuo',
					component: caigoushouhuoList
				},
				{
					path: 'caigoushouhuoDetail',
					component: caigoushouhuoDetail
				},
				{
					path: 'caigoushouhuoAdd',
					component: caigoushouhuoAdd
				},
				{
					path: 'caigoupingjia',
					component: caigoupingjiaList
				},
				{
					path: 'caigoupingjiaDetail',
					component: caigoupingjiaDetail
				},
				{
					path: 'caigoupingjiaAdd',
					component: caigoupingjiaAdd
				},
				{
					path: 'nongchanpinxinxi',
					component: nongchanpinxinxiList
				},
				{
					path: 'nongchanpinxinxiDetail',
					component: nongchanpinxinxiDetail
				},
				{
					path: 'nongchanpinxinxiAdd',
					component: nongchanpinxinxiAdd
				},
				{
					path: 'chargerecord',
					component: chargerecordList
				},
				{
					path: 'chargerecordDetail',
					component: chargerecordDetail
				},
				{
					path: 'chargerecordAdd',
					component: chargerecordAdd
				},
				{
					path: 'discussjishuzhishiku',
					component: discussjishuzhishikuList
				},
				{
					path: 'discussjishuzhishikuDetail',
					component: discussjishuzhishikuDetail
				},
				{
					path: 'discussjishuzhishikuAdd',
					component: discussjishuzhishikuAdd
				},
				{
					path: 'discussnongchanpinxinxi',
					component: discussnongchanpinxinxiList
				},
				{
					path: 'discussnongchanpinxinxiDetail',
					component: discussnongchanpinxinxiDetail
				},
				{
					path: 'discussnongchanpinxinxiAdd',
					component: discussnongchanpinxinxiAdd
				},
			]
		},
		{
			path: '/login',
			component: Login
		},
		{
			path: '/register',
			component: Register
		},
	]
})
