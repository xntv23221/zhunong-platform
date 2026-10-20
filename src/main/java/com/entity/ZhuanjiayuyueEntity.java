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
 * 专家预约
 * 数据库通用操作实体类（普通增删改查）
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
@TableName("zhuanjiayuyue")
public class ZhuanjiayuyueEntity<T> implements Serializable {
	private static final long serialVersionUID = 1L;


	public ZhuanjiayuyueEntity() {
		
	}
	
	public ZhuanjiayuyueEntity(T t) {
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
	 * 专业方向
	 */
					
	private String zhuanyefangxiang;
	
	/**
	 * 预约时间
	 */
				
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 		
	private Date yuyueshijian;
	
	/**
	 * 咨询内容
	 */
					
	private String zixunneirong;
	
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
	 * 设置：预约时间
	 */
	public void setYuyueshijian(Date yuyueshijian) {
		this.yuyueshijian = yuyueshijian;
	}
	/**
	 * 获取：预约时间
	 */
	public Date getYuyueshijian() {
		return yuyueshijian;
	}
	/**
	 * 设置：咨询内容
	 */
	public void setZixunneirong(String zixunneirong) {
		this.zixunneirong = zixunneirong;
	}
	/**
	 * 获取：咨询内容
	 */
	public String getZixunneirong() {
		return zixunneirong;
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
}
