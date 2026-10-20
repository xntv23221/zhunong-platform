package com.dao;

import com.entity.CaigoupeisongEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CaigoupeisongVO;
import com.entity.view.CaigoupeisongView;


/**
 * 采购配送
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public interface CaigoupeisongDao extends BaseMapper<CaigoupeisongEntity> {
	
	List<CaigoupeisongVO> selectListVO(@Param("ew") Wrapper<CaigoupeisongEntity> wrapper);
	
	CaigoupeisongVO selectVO(@Param("ew") Wrapper<CaigoupeisongEntity> wrapper);
	
	List<CaigoupeisongView> selectListView(@Param("ew") Wrapper<CaigoupeisongEntity> wrapper);

	List<CaigoupeisongView> selectListView(Pagination page,@Param("ew") Wrapper<CaigoupeisongEntity> wrapper);

	
	CaigoupeisongView selectView(@Param("ew") Wrapper<CaigoupeisongEntity> wrapper);
	

}
