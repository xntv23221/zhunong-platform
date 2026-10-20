package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.GaoxiaoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.GaoxiaoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.GaoxiaoView;


/**
 * 高校
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:11
 */
public interface GaoxiaoService extends IService<GaoxiaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<GaoxiaoVO> selectListVO(Wrapper<GaoxiaoEntity> wrapper);
   	
   	GaoxiaoVO selectVO(@Param("ew") Wrapper<GaoxiaoEntity> wrapper);
   	
   	List<GaoxiaoView> selectListView(Wrapper<GaoxiaoEntity> wrapper);
   	
   	GaoxiaoView selectView(@Param("ew") Wrapper<GaoxiaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<GaoxiaoEntity> wrapper);

   	

}

