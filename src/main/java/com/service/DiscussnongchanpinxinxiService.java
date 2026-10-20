package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.DiscussnongchanpinxinxiEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.DiscussnongchanpinxinxiVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.DiscussnongchanpinxinxiView;


/**
 * 农产品信息评论
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:14
 */
public interface DiscussnongchanpinxinxiService extends IService<DiscussnongchanpinxinxiEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<DiscussnongchanpinxinxiVO> selectListVO(Wrapper<DiscussnongchanpinxinxiEntity> wrapper);
   	
   	DiscussnongchanpinxinxiVO selectVO(@Param("ew") Wrapper<DiscussnongchanpinxinxiEntity> wrapper);
   	
   	List<DiscussnongchanpinxinxiView> selectListView(Wrapper<DiscussnongchanpinxinxiEntity> wrapper);
   	
   	DiscussnongchanpinxinxiView selectView(@Param("ew") Wrapper<DiscussnongchanpinxinxiEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<DiscussnongchanpinxinxiEntity> wrapper);

   	

}

