package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.ZhuanjiayuyueEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.ZhuanjiayuyueVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.ZhuanjiayuyueView;


/**
 * 专家预约
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public interface ZhuanjiayuyueService extends IService<ZhuanjiayuyueEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<ZhuanjiayuyueVO> selectListVO(Wrapper<ZhuanjiayuyueEntity> wrapper);
   	
   	ZhuanjiayuyueVO selectVO(@Param("ew") Wrapper<ZhuanjiayuyueEntity> wrapper);
   	
   	List<ZhuanjiayuyueView> selectListView(Wrapper<ZhuanjiayuyueEntity> wrapper);
   	
   	ZhuanjiayuyueView selectView(@Param("ew") Wrapper<ZhuanjiayuyueEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<ZhuanjiayuyueEntity> wrapper);

   	

}

