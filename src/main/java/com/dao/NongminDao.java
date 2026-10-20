package com.dao;

import com.entity.NongminEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.NongminVO;
import com.entity.view.NongminView;


/**
 * 农民
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:11
 */
public interface NongminDao extends BaseMapper<NongminEntity> {
	
	List<NongminVO> selectListVO(@Param("ew") Wrapper<NongminEntity> wrapper);
	
	NongminVO selectVO(@Param("ew") Wrapper<NongminEntity> wrapper);
	
	List<NongminView> selectListView(@Param("ew") Wrapper<NongminEntity> wrapper);

	List<NongminView> selectListView(Pagination page,@Param("ew") Wrapper<NongminEntity> wrapper);

	
	NongminView selectView(@Param("ew") Wrapper<NongminEntity> wrapper);
	

}
