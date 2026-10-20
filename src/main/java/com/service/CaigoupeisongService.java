package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.CaigoupeisongEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.CaigoupeisongVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.CaigoupeisongView;


/**
 * 采购配送
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public interface CaigoupeisongService extends IService<CaigoupeisongEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<CaigoupeisongVO> selectListVO(Wrapper<CaigoupeisongEntity> wrapper);
   	
   	CaigoupeisongVO selectVO(@Param("ew") Wrapper<CaigoupeisongEntity> wrapper);
   	
   	List<CaigoupeisongView> selectListView(Wrapper<CaigoupeisongEntity> wrapper);
   	
   	CaigoupeisongView selectView(@Param("ew") Wrapper<CaigoupeisongEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<CaigoupeisongEntity> wrapper);

   	

}

