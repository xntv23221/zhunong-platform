package com.entity.model;

import com.entity.JishuzhishikuEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 技术知识库
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public class JishuzhishikuModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 知识分类
	 */
	
	private String zhishifenlei;
		
	/**
	 * 专业领域
	 */
	
	private String zhuanyelingyu;
		
	/**
	 * 知识照片
	 */
	
	private String zhishizhaopian;
		
	/**
	 * 知识附件
	 */
	
	private String zhishifujian;
		
	/**
	 * 知识视频
	 */
	
	private String zhishishipin;
		
	/**
	 * 知识内容
	 */
	
	private String zhishineirong;
		
	/**
	 * 高校名称
	 */
	
	private String gaoxiaomingcheng;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：知识分类
	 */
	 
	public void setZhishifenlei(String zhishifenlei) {
		this.zhishifenlei = zhishifenlei;
	}
	
	/**
	 * 获取：知识分类
	 */
	public String getZhishifenlei() {
		return zhishifenlei;
	}
				
	
	/**
	 * 设置：专业领域
	 */
	 
	public void setZhuanyelingyu(String zhuanyelingyu) {
		this.zhuanyelingyu = zhuanyelingyu;
	}
	
	/**
	 * 获取：专业领域
	 */
	public String getZhuanyelingyu() {
		return zhuanyelingyu;
	}
				
	
	/**
	 * 设置：知识照片
	 */
	 
	public void setZhishizhaopian(String zhishizhaopian) {
		this.zhishizhaopian = zhishizhaopian;
	}
	
	/**
	 * 获取：知识照片
	 */
	public String getZhishizhaopian() {
		return zhishizhaopian;
	}
				
	
	/**
	 * 设置：知识附件
	 */
	 
	public void setZhishifujian(String zhishifujian) {
		this.zhishifujian = zhishifujian;
	}
	
	/**
	 * 获取：知识附件
	 */
	public String getZhishifujian() {
		return zhishifujian;
	}
				
	
	/**
	 * 设置：知识视频
	 */
	 
	public void setZhishishipin(String zhishishipin) {
		this.zhishishipin = zhishishipin;
	}
	
	/**
	 * 获取：知识视频
	 */
	public String getZhishishipin() {
		return zhishishipin;
	}
				
	
	/**
	 * 设置：知识内容
	 */
	 
	public void setZhishineirong(String zhishineirong) {
		this.zhishineirong = zhishineirong;
	}
	
	/**
	 * 获取：知识内容
	 */
	public String getZhishineirong() {
		return zhishineirong;
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
				
	
	/**
	 * 设置：评论数
	 */
	 
	public void setDiscussnum(Integer discussnum) {
		this.discussnum = discussnum;
	}
	
	/**
	 * 获取：评论数
	 */
	public Integer getDiscussnum() {
		return discussnum;
	}
				
	
	/**
	 * 设置：收藏数
	 */
	 
	public void setStoreupnum(Integer storeupnum) {
		this.storeupnum = storeupnum;
	}
	
	/**
	 * 获取：收藏数
	 */
	public Integer getStoreupnum() {
		return storeupnum;
	}
			
}
