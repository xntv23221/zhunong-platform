package com.entity.vo;

import com.entity.GaoxiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 高校
 * @author 
 * @email 
 * @date 2026-01-15 21:42:11
 */
public class GaoxiaoVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 密码
	 */
	
	private String mima;
		
	/**
	 * 高校地址
	 */
	
	private String gaoxiaodizhi;
		
	/**
	 * 高校电话
	 */
	
	private String gaoxiaodianhua;
		
	/**
	 * 高校封面
	 */
	
	private String touxiang;
		
	/**
	 * 高校简介
	 */
	
	private String gaoxiaojianjie;
		
	/**
	 * 余额
	 */
	
	private Double money;
				
	
	/**
	 * 设置：密码
	 */
	 
	public void setMima(String mima) {
		this.mima = mima;
	}
	
	/**
	 * 获取：密码
	 */
	public String getMima() {
		return mima;
	}
				
	
	/**
	 * 设置：高校地址
	 */
	 
	public void setGaoxiaodizhi(String gaoxiaodizhi) {
		this.gaoxiaodizhi = gaoxiaodizhi;
	}
	
	/**
	 * 获取：高校地址
	 */
	public String getGaoxiaodizhi() {
		return gaoxiaodizhi;
	}
				
	
	/**
	 * 设置：高校电话
	 */
	 
	public void setGaoxiaodianhua(String gaoxiaodianhua) {
		this.gaoxiaodianhua = gaoxiaodianhua;
	}
	
	/**
	 * 获取：高校电话
	 */
	public String getGaoxiaodianhua() {
		return gaoxiaodianhua;
	}
				
	
	/**
	 * 设置：高校封面
	 */
	 
	public void setTouxiang(String touxiang) {
		this.touxiang = touxiang;
	}
	
	/**
	 * 获取：高校封面
	 */
	public String getTouxiang() {
		return touxiang;
	}
				
	
	/**
	 * 设置：高校简介
	 */
	 
	public void setGaoxiaojianjie(String gaoxiaojianjie) {
		this.gaoxiaojianjie = gaoxiaojianjie;
	}
	
	/**
	 * 获取：高校简介
	 */
	public String getGaoxiaojianjie() {
		return gaoxiaojianjie;
	}
				
	
	/**
	 * 设置：余额
	 */
	 
	public void setMoney(Double money) {
		this.money = money;
	}
	
	/**
	 * 获取：余额
	 */
	public Double getMoney() {
		return money;
	}
			
}
