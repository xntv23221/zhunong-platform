package com.entity.vo;

import com.entity.YuanliaocaigouEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 原料采购
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public class YuanliaocaigouVO  implements Serializable {
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
	 * 采购时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date caigoushijian;
		
	/**
	 * 采购备注
	 */
	
	private String caigoubeizhu;
		
	/**
	 * 农民账号
	 */
	
	private String nongminzhanghao;
		
	/**
	 * 是否审核
	 */
	
	private String sfsh;
		
	/**
	 * 审核回复
	 */
	
	private String shhf;
		
	/**
	 * 是否支付
	 */
	
	private String ispay;
				
	
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
	 * 设置：采购时间
	 */
	 
	public void setCaigoushijian(Date caigoushijian) {
		this.caigoushijian = caigoushijian;
	}
	
	/**
	 * 获取：采购时间
	 */
	public Date getCaigoushijian() {
		return caigoushijian;
	}
				
	
	/**
	 * 设置：采购备注
	 */
	 
	public void setCaigoubeizhu(String caigoubeizhu) {
		this.caigoubeizhu = caigoubeizhu;
	}
	
	/**
	 * 获取：采购备注
	 */
	public String getCaigoubeizhu() {
		return caigoubeizhu;
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
	 * 设置：是否审核
	 */
	 
	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}
	
	/**
	 * 获取：是否审核
	 */
	public String getSfsh() {
		return sfsh;
	}
				
	
	/**
	 * 设置：审核回复
	 */
	 
	public void setShhf(String shhf) {
		this.shhf = shhf;
	}
	
	/**
	 * 获取：审核回复
	 */
	public String getShhf() {
		return shhf;
	}
				
	
	/**
	 * 设置：是否支付
	 */
	 
	public void setIspay(String ispay) {
		this.ispay = ispay;
	}
	
	/**
	 * 获取：是否支付
	 */
	public String getIspay() {
		return ispay;
	}
			
}
