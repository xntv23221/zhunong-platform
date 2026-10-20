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
 * 采购评价
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
@TableName("caigoupingjia")
public class CaigoupingjiaEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public CaigoupingjiaEntity() {
		
	}
	
	public CaigoupingjiaEntity(T t) {
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
	 * 高校名称
	 */
					
	private String gaoxiaomingcheng;
	
	/**
	 * 评价状态
	 */
					
	private String pingjiazhuangtai;
	
	/**
	 * 评价时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date pingjiashijian;
	
	/**
	 * 评价内容
	 */
					
	private String pingjianeirong;
	
	/**
	 * 农民账号
	 */
					
	private String nongminzhanghao;
	
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
	 * 设置：评价状态
	 */
	public void setPingjiazhuangtai(String pingjiazhuangtai) {
		this.pingjiazhuangtai = pingjiazhuangtai;
	}
	/**
	 * 获取：评价状态
	 */
	public String getPingjiazhuangtai() {
		return pingjiazhuangtai;
	}
	/**
	 * 设置：评价时间
	 */
	public void setPingjiashijian(Date pingjiashijian) {
		this.pingjiashijian = pingjiashijian;
	}
	/**
	 * 获取：评价时间
	 */
	public Date getPingjiashijian() {
		return pingjiashijian;
	}
	/**
	 * 设置：评价内容
	 */
	public void setPingjianeirong(String pingjianeirong) {
		this.pingjianeirong = pingjianeirong;
	}
	/**
	 * 获取：评价内容
	 */
	public String getPingjianeirong() {
		return pingjianeirong;
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
}
