package com.entity.model;

import com.entity.NongyeyuanliaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 农业原料
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public class NongyeyuanliaoModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 原料分类
	 */
	
	private String yuanliaofenlei;
		
	/**
	 * 原料照片
	 */
	
	private String yuanliaozhaopian;
		
	/**
	 * 有效成分
	 */
	
	private String youxiaochengfen;
		
	/**
	 * 价格
	 */
	
	private Double jiage;
		
	/**
	 * 库存
	 */
	
	private Integer kucun;
		
	/**
	 * 使用说明
	 */
	
	private String shiyongshuoming;
		
	/**
	 * 原料介绍
	 */
	
	private String yuanliaojieshao;
		
	/**
	 * 高校名称
	 */
	
	private String gaoxiaomingcheng;
				
	
	/**
	 * 设置：原料分类
	 */
	 
	public void setYuanliaofenlei(String yuanliaofenlei) {
		this.yuanliaofenlei = yuanliaofenlei;
	}
	
	/**
	 * 获取：原料分类
	 */
	public String getYuanliaofenlei() {
		return yuanliaofenlei;
	}
				
	
	/**
	 * 设置：原料照片
	 */
	 
	public void setYuanliaozhaopian(String yuanliaozhaopian) {
		this.yuanliaozhaopian = yuanliaozhaopian;
	}
	
	/**
	 * 获取：原料照片
	 */
	public String getYuanliaozhaopian() {
		return yuanliaozhaopian;
	}
				
	
	/**
	 * 设置：有效成分
	 */
	 
	public void setYouxiaochengfen(String youxiaochengfen) {
		this.youxiaochengfen = youxiaochengfen;
	}
	
	/**
	 * 获取：有效成分
	 */
	public String getYouxiaochengfen() {
		return youxiaochengfen;
	}
				
	
	/**
	 * 设置：价格
	 */
	 
	public void setJiage(Double jiage) {
		this.jiage = jiage;
	}
	
	/**
	 * 获取：价格
	 */
	public Double getJiage() {
		return jiage;
	}
				
	
	/**
	 * 设置：库存
	 */
	 
	public void setKucun(Integer kucun) {
		this.kucun = kucun;
	}
	
	/**
	 * 获取：库存
	 */
	public Integer getKucun() {
		return kucun;
	}
				
	
	/**
	 * 设置：使用说明
	 */
	 
	public void setShiyongshuoming(String shiyongshuoming) {
		this.shiyongshuoming = shiyongshuoming;
	}
	
	/**
	 * 获取：使用说明
	 */
	public String getShiyongshuoming() {
		return shiyongshuoming;
	}
				
	
	/**
	 * 设置：原料介绍
	 */
	 
	public void setYuanliaojieshao(String yuanliaojieshao) {
		this.yuanliaojieshao = yuanliaojieshao;
	}
	
	/**
	 * 获取：原料介绍
	 */
	public String getYuanliaojieshao() {
		return yuanliaojieshao;
	}
				
	
	/**
	 * 设置：高校名称
	 */
	 
	public void setGaoxiaomingcheng(String gaoxiaomingcheng) {
		this.gaoxiaomingcheng = gaoxiaomingcheng;
	}
	
	/**
	 * 获取：高校名称
	 */
	public String getGaoxiaomingcheng() {
		return gaoxiaomingcheng;
	}
			
}
