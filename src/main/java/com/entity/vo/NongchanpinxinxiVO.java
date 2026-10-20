package com.entity.vo;

import com.entity.NongchanpinxinxiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
 

/**
 * 农产品信息
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public class NongchanpinxinxiVO  implements Serializable {
	private static final long serialVersionUID = 1L;

	 			
	/**
	 * 农产品类型
	 */
	
	private String nongchanpinleixing;
		
	/**
	 * 农产品照片
	 */
	
	private String nongchanpinzhaopian;
		
	/**
	 * 产品规格
	 */
	
	private String chanpinguige;
		
	/**
	 * 营养成分
	 */
	
	private String yingyangchengfen;
		
	/**
	 * 外观特征
	 */
	
	private String waiguantezheng;
		
	/**
	 * 产品介绍
	 */
	
	private String chanpinjieshao;
		
	/**
	 * 农民账号
	 */
	
	private String nongminzhanghao;
		
	/**
	 * 单限
	 */
	
	private Integer onelimittimes;
		
	/**
	 * 库存
	 */
	
	private Integer alllimittimes;
		
	/**
	 * 价格
	 */
	
	private Double price;
		
	/**
	 * 最近点击时间
	 */
		
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat 
	private Date clicktime;
		
	/**
	 * 评论数
	 */
	
	private Integer discussnum;
		
	/**
	 * 是否上架(1:上架，0:下架)
	 */
	
	private Integer onshelves;
		
	/**
	 * 收藏数
	 */
	
	private Integer storeupnum;
				
	
	/**
	 * 设置：农产品类型
	 */
	 
	public void setNongchanpinleixing(String nongchanpinleixing) {
		this.nongchanpinleixing = nongchanpinleixing;
	}
	
	/**
	 * 获取：农产品类型
	 */
	public String getNongchanpinleixing() {
		return nongchanpinleixing;
	}
				
	
	/**
	 * 设置：农产品照片
	 */
	 
	public void setNongchanpinzhaopian(String nongchanpinzhaopian) {
		this.nongchanpinzhaopian = nongchanpinzhaopian;
	}
	
	/**
	 * 获取：农产品照片
	 */
	public String getNongchanpinzhaopian() {
		return nongchanpinzhaopian;
	}
				
	
	/**
	 * 设置：产品规格
	 */
	 
	public void setChanpinguige(String chanpinguige) {
		this.chanpinguige = chanpinguige;
	}
	
	/**
	 * 获取：产品规格
	 */
	public String getChanpinguige() {
		return chanpinguige;
	}
				
	
	/**
	 * 设置：营养成分
	 */
	 
	public void setYingyangchengfen(String yingyangchengfen) {
		this.yingyangchengfen = yingyangchengfen;
	}
	
	/**
	 * 获取：营养成分
	 */
	public String getYingyangchengfen() {
		return yingyangchengfen;
	}
				
	
	/**
	 * 设置：外观特征
	 */
	 
	public void setWaiguantezheng(String waiguantezheng) {
		this.waiguantezheng = waiguantezheng;
	}
	
	/**
	 * 获取：外观特征
	 */
	public String getWaiguantezheng() {
		return waiguantezheng;
	}
				
	
	/**
	 * 设置：产品介绍
	 */
	 
	public void setChanpinjieshao(String chanpinjieshao) {
		this.chanpinjieshao = chanpinjieshao;
	}
	
	/**
	 * 获取：产品介绍
	 */
	public String getChanpinjieshao() {
		return chanpinjieshao;
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
	 * 设置：单限
	 */
	 
	public void setOnelimittimes(Integer onelimittimes) {
		this.onelimittimes = onelimittimes;
	}
	
	/**
	 * 获取：单限
	 */
	public Integer getOnelimittimes() {
		return onelimittimes;
	}
				
	
	/**
	 * 设置：库存
	 */
	 
	public void setAlllimittimes(Integer alllimittimes) {
		this.alllimittimes = alllimittimes;
	}
	
	/**
	 * 获取：库存
	 */
	public Integer getAlllimittimes() {
		return alllimittimes;
	}
				
	
	/**
	 * 设置：价格
	 */
	 
	public void setPrice(Double price) {
		this.price = price;
	}
	
	/**
	 * 获取：价格
	 */
	public Double getPrice() {
		return price;
	}
				
	
	/**
	 * 设置：最近点击时间
	 */
	 
	public void setClicktime(Date clicktime) {
		this.clicktime = clicktime;
	}
	
	/**
	 * 获取：最近点击时间
	 */
	public Date getClicktime() {
		return clicktime;
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
	 * 设置：是否上架(1:上架，0:下架)
	 */
	 
	public void setOnshelves(Integer onshelves) {
		this.onshelves = onshelves;
	}
	
	/**
	 * 获取：是否上架(1:上架，0:下架)
	 */
	public Integer getOnshelves() {
		return onshelves;
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
