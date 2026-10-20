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
 * 专家
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
@TableName("zhuanjia")
public class ZhuanjiaEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ZhuanjiaEntity() {
		
	}
	
	public ZhuanjiaEntity(T t) {
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
    @TableId(type = IdType.AUTO)
	private Long id;
	/**
	 * 高校名称
	 */
					
	private String gaoxiaomingcheng;
	
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
