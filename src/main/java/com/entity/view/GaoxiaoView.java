package com.entity.view;

import com.entity.GaoxiaoEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 高校
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-01-15 21:42:11
 */
@TableName("gaoxiao")
public class GaoxiaoView  extends GaoxiaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public GaoxiaoView(){
	}
 
 	public GaoxiaoView(GaoxiaoEntity gaoxiaoEntity){
 	try {
			BeanUtils.copyProperties(this, gaoxiaoEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
