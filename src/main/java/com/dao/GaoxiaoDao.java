package com.dao;

import com.entity.GaoxiaoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.GaoxiaoVO;
import com.entity.view.GaoxiaoView;


/**
 * 高校
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:11
 */
public interface GaoxiaoDao extends BaseMapper<GaoxiaoEntity> {
	
	List<GaoxiaoVO> selectListVO(@Param("ew") Wrapper<GaoxiaoEntity> wrapper);
	
	GaoxiaoVO selectVO(@Param("ew") Wrapper<GaoxiaoEntity> wrapper);
	
	List<GaoxiaoView> selectListView(@Param("ew") Wrapper<GaoxiaoEntity> wrapper);

	List<GaoxiaoView> selectListView(Pagination page,@Param("ew") Wrapper<GaoxiaoEntity> wrapper);

	
	GaoxiaoView selectView(@Param("ew") Wrapper<GaoxiaoEntity> wrapper);
	

}
