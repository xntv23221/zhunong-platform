package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.YuanliaofenleiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.YuanliaofenleiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.YuanliaofenleiView;


/**
 * 原料分类
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public interface YuanliaofenleiService extends IService<YuanliaofenleiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<YuanliaofenleiVO> selectListVO(Wrapper<YuanliaofenleiEntity> wrapper);
   	
   	YuanliaofenleiVO selectVO(@Param("ew") Wrapper<YuanliaofenleiEntity> wrapper);
   	
   	List<YuanliaofenleiView> selectListView(Wrapper<YuanliaofenleiEntity> wrapper);
   	
   	YuanliaofenleiView selectView(@Param("ew") Wrapper<YuanliaofenleiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<YuanliaofenleiEntity> wrapper);

   	

}

