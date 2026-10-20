package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CaigoupingjiaEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CaigoupingjiaVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CaigoupingjiaView;


/**
 * 采购评价
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public interface CaigoupingjiaService extends IService<CaigoupingjiaEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CaigoupingjiaVO> selectListVO(Wrapper<CaigoupingjiaEntity> wrapper);
   	
   	CaigoupingjiaVO selectVO(@Param("ew") Wrapper<CaigoupingjiaEntity> wrapper);
   	
   	List<CaigoupingjiaView> selectListView(Wrapper<CaigoupingjiaEntity> wrapper);
   	
   	CaigoupingjiaView selectView(@Param("ew") Wrapper<CaigoupingjiaEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CaigoupingjiaEntity> wrapper);

   	

}

