package com.entity.model;

import com.entity.CaigoupeisongEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 采购配送
 * 接收传参的实体类  
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了） 
 * 取自ModelAndView 的model名称
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public class CaigoupeisongModel  implements Serializable {
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
	 * 采购数量
	 */
	
	private Integer kucun;
		
	/**
	 * 采购总价
	 */
	
	private Double caigouzongjia;
		
	/**
	 * 高校名称
	 */
	
	private String gaoxiaomingcheng;
		
	/**
	 * 配送时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date peisongshijian;
		
	/**
	 * 配送备注
	 */
	
	private String peisongbeizhu;
		
	/**
	 * 农民账号
	 */
	
	private String nongminzhanghao;
		
	/**
	 * 配送状态
	 */
	
	private String peisongzhuangtai;
		
	/**
	 * 跨表用户id
	 */
	
	private Long crossuserid;
		
	/**
	 * 跨表主键id
	 */
	
	private Long crossrefid;
				
	
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
	 * 设置：采购数量
	 */
	 
	public void setKucun(Integer kucun) {
		this.kucun = kucun;
	}
	
	/**
	 * 获取：采购数量
	 */
	public Integer getKucun() {
		return kucun;
	}
				
	
	/**
	 * 设置：采购总价
	 */
	 
	public void setCaigouzongjia(Double caigouzongjia) {
		this.caigouzongjia = caigouzongjia;
	}
	
	/**
	 * 获取：采购总价
	 */
	public Double getCaigouzongjia() {
		return caigouzongjia;
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
	 * 设置：配送时间
	 */
	 
	public void setPeisongshijian(Date peisongshijian) {
		this.peisongshijian = peisongshijian;
	}
	
	/**
	 * 获取：配送时间
	 */
	public Date getPeisongshijian() {
		return peisongshijian;
	}
				
	
	/**
	 * 设置：配送备注
	 */
	 
	public void setPeisongbeizhu(String peisongbeizhu) {
		this.peisongbeizhu = peisongbeizhu;
	}
	
	/**
	 * 获取：配送备注
	 */
	public String getPeisongbeizhu() {
		return peisongbeizhu;
	}
				
	
	/**
	 * 设置：农民账号
	 */
	 
	public void setNongminzhanghao(String nongminzhanghao) {
		this.nongminzhanghao = nongminzhanghao;
	}
	
	/**
	 * 获取：农民账号
	 */
	public String getNongminzhanghao() {
		return nongminzhanghao;
	}
				
	
	/**
	 * 设置：配送状态
	 */
	 
	public void setPeisongzhuangtai(String peisongzhuangtai) {
		this.peisongzhuangtai = peisongzhuangtai;
	}
	
	/**
	 * 获取：配送状态
	 */
	public String getPeisongzhuangtai() {
		return peisongzhuangtai;
	}
				
	
	/**
	 * 设置：跨表用户id
	 */
	 
	public void setCrossuserid(Long crossuserid) {
		this.crossuserid = crossuserid;
	}
	
	/**
	 * 获取：跨表用户id
	 */
	public Long getCrossuserid() {
		return crossuserid;
	}
				
	
	/**
	 * 设置：跨表主键id
	 */
	 
	public void setCrossrefid(Long crossrefid) {
		this.crossrefid = crossrefid;
	}
	
	/**
	 * 获取：跨表主键id
	 */
	public Long getCrossrefid() {
		return crossrefid;
	}
			
}
