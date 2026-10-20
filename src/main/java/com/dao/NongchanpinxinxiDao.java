package com.dao;

import com.entity.NongchanpinxinxiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.NongchanpinxinxiVO;
import com.entity.view.NongchanpinxinxiView;


/**
 * 农产品信息
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public interface NongchanpinxinxiDao extends BaseMapper<NongchanpinxinxiEntity> {
	
	List<NongchanpinxinxiVO> selectListVO(@Param("ew") Wrapper<NongchanpinxinxiEntity> wrapper);
	
	NongchanpinxinxiVO selectVO(@Param("ew") Wrapper<NongchanpinxinxiEntity> wrapper);
	
	List<NongchanpinxinxiView> selectListView(@Param("ew") Wrapper<NongchanpinxinxiEntity> wrapper);

	List<NongchanpinxinxiView> selectListView(Pagination page,@Param("ew") Wrapper<NongchanpinxinxiEntity> wrapper);

	
	NongchanpinxinxiView selectView(@Param("ew") Wrapper<NongchanpinxinxiEntity> wrapper);
	

}
