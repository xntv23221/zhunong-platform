package com.entity.model;

import com.entity.ZhuanjiaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 专家
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public class ZhuanjiaModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 专家名称
	 */
	
	private String zhuanjiamingcheng;
		
	/**
	 * 专家照片
	 */
	
	private String zhuanjiazhaopian;
		
	/**
	 * 性别
	 */
	
	private String xingbie;
		
	/**
	 * 专业方向
	 */
	
	private String zhuanyefangxiang;
		
	/**
	 * 从业年限
	 */
	
	private String congyenianxian;
		
	/**
	 * 研究领域
	 */
	
	private String yanjiulingyu;
		
	/**
	 * 个人简介
	 */
	
	private String gerenjianjie;
				
	
	/**
	 * 设置：专家名称
	 */
	 
	public void setZhuanjiamingcheng(String zhuanjiamingcheng) {
		this.zhuanjiamingcheng = zhuanjiamingcheng;
	}
	
	/**
	 * 获取：专家名称
	 */
	public String getZhuanjiamingcheng() {
		return zhuanjiamingcheng;
	}
				
	
	/**
	 * 设置：专家照片
	 */
	 
	public void setZhuanjiazhaopian(String zhuanjiazhaopian) {
		this.zhuanjiazhaopian = zhuanjiazhaopian;
	}
	
	/**
	 * 获取：专家照片
	 */
	public String getZhuanjiazhaopian() {
		return zhuanjiazhaopian;
	}
				
	
	/**
	 * 设置：性别
	 */
	 
	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}
	
	/**
	 * 获取：性别
	 */
	public String getXingbie() {
		return xingbie;
	}
				
	
	/**
	 * 设置：专业方向
	 */
	 
	public void setZhuanyefangxiang(String zhuanyefangxiang) {
		this.zhuanyefangxiang = zhuanyefangxiang;
	}
	
	/**
	 * 获取：专业方向
	 */
	public String getZhuanyefangxiang() {
		return zhuanyefangxiang;
	}
				
	
	/**
	 * 设置：从业年限
	 */
	 
	public void setCongyenianxian(String congyenianxian) {
		this.congyenianxian = congyenianxian;
	}
	
	/**
	 * 获取：从业年限
	 */
	public String getCongyenianxian() {
		return congyenianxian;
	}
				
	
	/**
	 * 设置：研究领域
	 */
	 
	public void setYanjiulingyu(String yanjiulingyu) {
		this.yanjiulingyu = yanjiulingyu;
	}
	
	/**
	 * 获取：研究领域
	 */
	public String getYanjiulingyu() {
		return yanjiulingyu;
	}
				
	
	/**
	 * 设置：个人简介
	 */
	 
	public void setGerenjianjie(String gerenjianjie) {
		this.gerenjianjie = gerenjianjie;
	}
	
	/**
	 * 获取：个人简介
	 */
	public String getGerenjianjie() {
		return gerenjianjie;
	}
			
}
