package com.dao;

import com.entity.NongyeyuanliaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.NongyeyuanliaoVO;
import com.entity.view.NongyeyuanliaoView;


/**
 * 农业原料
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public interface NongyeyuanliaoDao extends BaseMapper<NongyeyuanliaoEntity> {
	
	List<NongyeyuanliaoVO> selectListVO(@Param("ew") Wrapper<NongyeyuanliaoEntity> wrapper);
	
	NongyeyuanliaoVO selectVO(@Param("ew") Wrapper<NongyeyuanliaoEntity> wrapper);
	
	List<NongyeyuanliaoView> selectListView(@Param("ew") Wrapper<NongyeyuanliaoEntity> wrapper);

	List<NongyeyuanliaoView> selectListView(Pagination page,@Param("ew") Wrapper<NongyeyuanliaoEntity> wrapper);

	
	NongyeyuanliaoView selectView(@Param("ew") Wrapper<NongyeyuanliaoEntity> wrapper);
	

}
