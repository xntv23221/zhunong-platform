package com.dao;

import com.entity.DiscussjishuzhishikuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussjishuzhishikuVO;
import com.entity.view.DiscussjishuzhishikuView;


/**
 * 技术知识库评论
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:14
 */
public interface DiscussjishuzhishikuDao extends BaseMapper<DiscussjishuzhishikuEntity> {
	
	List<DiscussjishuzhishikuVO> selectListVO(@Param("ew") Wrapper<DiscussjishuzhishikuEntity> wrapper);
	
	DiscussjishuzhishikuVO selectVO(@Param("ew") Wrapper<DiscussjishuzhishikuEntity> wrapper);
	
	List<DiscussjishuzhishikuView> selectListView(@Param("ew") Wrapper<DiscussjishuzhishikuEntity> wrapper);

	List<DiscussjishuzhishikuView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussjishuzhishikuEntity> wrapper);

	
	DiscussjishuzhishikuView selectView(@Param("ew") Wrapper<DiscussjishuzhishikuEntity> wrapper);
	

}
