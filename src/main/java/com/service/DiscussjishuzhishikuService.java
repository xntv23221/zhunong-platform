package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussjishuzhishikuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussjishuzhishikuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussjishuzhishikuView;


/**
 * 技术知识库评论
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:14
 */
public interface DiscussjishuzhishikuService extends IService<DiscussjishuzhishikuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussjishuzhishikuVO> selectListVO(Wrapper<DiscussjishuzhishikuEntity> wrapper);
   	
   	DiscussjishuzhishikuVO selectVO(@Param("ew") Wrapper<DiscussjishuzhishikuEntity> wrapper);
   	
   	List<DiscussjishuzhishikuView> selectListView(Wrapper<DiscussjishuzhishikuEntity> wrapper);
   	
   	DiscussjishuzhishikuView selectView(@Param("ew") Wrapper<DiscussjishuzhishikuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussjishuzhishikuEntity> wrapper);

   	

}

