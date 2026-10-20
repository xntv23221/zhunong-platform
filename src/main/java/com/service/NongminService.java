package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.NongminEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.NongminVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.NongminView;


/**
 * 农民
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:11
 */
public interface NongminService extends IService<NongminEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<NongminVO> selectListVO(Wrapper<NongminEntity> wrapper);
   	
   	NongminVO selectVO(@Param("ew") Wrapper<NongminEntity> wrapper);
   	
   	List<NongminView> selectListView(Wrapper<NongminEntity> wrapper);
   	
   	NongminView selectView(@Param("ew") Wrapper<NongminEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<NongminEntity> wrapper);

   	

}

