package com.dao;

import com.entity.DiscussnongchanpinxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.DiscussnongchanpinxinxiVO;
import com.entity.view.DiscussnongchanpinxinxiView;


/**
 * 农产品信息评论
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:14
 */
public interface DiscussnongchanpinxinxiDao extends BaseMapper<DiscussnongchanpinxinxiEntity> {
	
	List<DiscussnongchanpinxinxiVO> selectListVO(@Param("ew") Wrapper<DiscussnongchanpinxinxiEntity> wrapper);
	
	DiscussnongchanpinxinxiVO selectVO(@Param("ew") Wrapper<DiscussnongchanpinxinxiEntity> wrapper);
	
	List<DiscussnongchanpinxinxiView> selectListView(@Param("ew") Wrapper<DiscussnongchanpinxinxiEntity> wrapper);

	List<DiscussnongchanpinxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<DiscussnongchanpinxinxiEntity> wrapper);

	
	DiscussnongchanpinxinxiView selectView(@Param("ew") Wrapper<DiscussnongchanpinxinxiEntity> wrapper);
	

}
