package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;


/**
 * 高校
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-15 21:42:11
 */
@TableName("gaoxiao")
public class GaoxiaoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public GaoxiaoEntity() {
		
	}
	
	public GaoxiaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/**
	 * 主键id
	 */
    @TableId
	private Long id;
	/**
	 * 高校名称
	 */
					
	private String gaoxiaomingcheng;
	
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
	
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
	private Date addtime;

	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
