package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.NongchanpinxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.NongchanpinxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.NongchanpinxinxiView;


/**
 * 农产品信息
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public interface NongchanpinxinxiService extends IService<NongchanpinxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<NongchanpinxinxiVO> selectListVO(Wrapper<NongchanpinxinxiEntity> wrapper);
   	
   	NongchanpinxinxiVO selectVO(@Param("ew") Wrapper<NongchanpinxinxiEntity> wrapper);
   	
   	List<NongchanpinxinxiView> selectListView(Wrapper<NongchanpinxinxiEntity> wrapper);
   	
   	NongchanpinxinxiView selectView(@Param("ew") Wrapper<NongchanpinxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<NongchanpinxinxiEntity> wrapper);

   	

}

