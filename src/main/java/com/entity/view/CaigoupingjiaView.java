package com.entity.view;

import com.entity.CaigoupingjiaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import java.math.BigDecimal;

import java.io.Serializable;
import com.utils.EncryptUtil;
 

/**
 * 采购评价
 * 后端返回视图实体辅助类   
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
@TableName("caigoupingjia")
public class CaigoupingjiaView  extends CaigoupingjiaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public CaigoupingjiaView(){
	}
 
 	public CaigoupingjiaView(CaigoupingjiaEntity caigoupingjiaEntity){
 	try {
			BeanUtils.copyProperties(this, caigoupingjiaEntity);
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
	}


}
