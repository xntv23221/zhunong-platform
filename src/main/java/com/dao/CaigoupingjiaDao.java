package com.dao;

import com.entity.CaigoupingjiaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.CaigoupingjiaVO;
import com.entity.view.CaigoupingjiaView;


/**
 * 采购评价
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
public interface CaigoupingjiaDao extends BaseMapper<CaigoupingjiaEntity> {
	
	List<CaigoupingjiaVO> selectListVO(@Param("ew") Wrapper<CaigoupingjiaEntity> wrapper);
	
	CaigoupingjiaVO selectVO(@Param("ew") Wrapper<CaigoupingjiaEntity> wrapper);
	
	List<CaigoupingjiaView> selectListView(@Param("ew") Wrapper<CaigoupingjiaEntity> wrapper);

	List<CaigoupingjiaView> selectListView(Pagination page,@Param("ew") Wrapper<CaigoupingjiaEntity> wrapper);

	
	CaigoupingjiaView selectView(@Param("ew") Wrapper<CaigoupingjiaEntity> wrapper);
	

}
