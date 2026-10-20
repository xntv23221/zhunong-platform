import storage from './storage';
import menu from './menu';
/**
 * 是否有权限
 * @param {*} key
 */
export function isAuth(tableName,key,index=1) {
	let role = storage.get("role");
	if(!role){
		role = '管理员';
	}
	let menus = menu.list();
	for(let i=0;i<menus.length;i++){
		if(menus[i].roleName==role){
			for(let j=0;j<menus[i].backMenu.length;j++){
				for(let k=0;k<menus[i].backMenu[j].child.length;k++){
					if(index==2) {
						if(tableName=='orders'&&menus[i].backMenu[j].child[k].tableName.indexOf(tableName) != -1) {
							var buttons = menus[i].backMenu[j].child[k].buttons.join(',');
							if(buttons.indexOf(key) !== -1) {
								return true
							}
						}
					}
					if(tableName==menus[i].backMenu[j].child[k].tableName){
						let buttons = menus[i].backMenu[j].child[k].buttons
						for(let n in buttons) {
							if(buttons[n] == key) {
								return true
							}
						}
					}
				}
			}
		}
	}
	return false;
}

/**
 *  * 获取当前时间（yyyy-MM-dd hh:mm:ss）
 *   */
export function getCurDateTime() {
	let currentTime = new Date(),
	year = currentTime.getFullYear(),
	month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
	day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate(),
	hour = currentTime.getHours(),
	minute = currentTime.getMinutes(),
	second = currentTime.getSeconds();
	return year + "-" + month + "-" + day + " " +hour +":" +minute+":"+second;
}

/**
 *  * 获取当前日期（yyyy-MM-dd）
 *   */
export function getCurDate() {
	let currentTime = new Date(),
	year = currentTime.getFullYear(),
	month = currentTime.getMonth() + 1 < 10 ? '0' + (currentTime.getMonth() + 1) : currentTime.getMonth() + 1,
	day = currentTime.getDate() < 10 ? '0' + currentTime.getDate() : currentTime.getDate();
	return year + "-" + month + "-" + day;
}

export function changeReturnGoods() {
	let menus = menu.list()
	let type = false
	for(let i=0;i<menus.length;i++){
		for(let j=0;j<menus[i].backMenu.length;j++){
			for(let k=0;k<menus[i].backMenu[j].child.length;k++){
				if('orders/已完成'==menus[i].backMenu[j].child[k].tableName){
					let buttons = menus[i].backMenu[j].child[k].buttons.join(',');
					if(buttons.indexOf('退货审核') !== -1) {
						type = true
					}
				}
			}
		}
	}
	return type;
}
