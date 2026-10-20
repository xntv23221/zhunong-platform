package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CaigoushouhuoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CaigoushouhuoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CaigoushouhuoView;


/**
 * 采购收货
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public interface CaigoushouhuoService extends IService<CaigoushouhuoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CaigoushouhuoVO> selectListVO(Wrapper<CaigoushouhuoEntity> wrapper);
   	
   	CaigoushouhuoVO selectVO(@Param("ew") Wrapper<CaigoushouhuoEntity> wrapper);
   	
   	List<CaigoushouhuoView> selectListView(Wrapper<CaigoushouhuoEntity> wrapper);
   	
   	CaigoushouhuoView selectView(@Param("ew") Wrapper<CaigoushouhuoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CaigoushouhuoEntity> wrapper);

   	

}

