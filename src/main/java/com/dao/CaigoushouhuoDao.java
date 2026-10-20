package com.dao;

import com.entity.CaigoushouhuoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CaigoushouhuoVO;
import com.entity.view.CaigoushouhuoView;


/**
 * 采购收货
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public interface CaigoushouhuoDao extends BaseMapper<CaigoushouhuoEntity> {
	
	List<CaigoushouhuoVO> selectListVO(@Param("ew") Wrapper<CaigoushouhuoEntity> wrapper);
	
	CaigoushouhuoVO selectVO(@Param("ew") Wrapper<CaigoushouhuoEntity> wrapper);
	
	List<CaigoushouhuoView> selectListView(@Param("ew") Wrapper<CaigoushouhuoEntity> wrapper);

	List<CaigoushouhuoView> selectListView(Pagination page,@Param("ew") Wrapper<CaigoushouhuoEntity> wrapper);

	
	CaigoushouhuoView selectView(@Param("ew") Wrapper<CaigoushouhuoEntity> wrapper);
	

}
