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
 * 农业原料
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
@TableName("nongyeyuanliao")
public class NongyeyuanliaoEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public NongyeyuanliaoEntity() {
		
	}
	
	public NongyeyuanliaoEntity(T t) {
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
	 * 原料名称
	 */
					
	private String yuanliaomingcheng;
	
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
	 * 设置：原料名称
	 */
	public void setYuanliaomingcheng(String yuanliaomingcheng) {
		this.yuanliaomingcheng = yuanliaomingcheng;
	}
	/**
	 * 获取：原料名称
	 */
	public String getYuanliaomingcheng() {
		return yuanliaomingcheng;
	}
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
