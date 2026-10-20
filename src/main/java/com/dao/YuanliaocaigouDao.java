package com.dao;

import com.entity.YuanliaocaigouEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YuanliaocaigouVO;
import com.entity.view.YuanliaocaigouView;


/**
 * 原料采购
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public interface YuanliaocaigouDao extends BaseMapper<YuanliaocaigouEntity> {
	
	List<YuanliaocaigouVO> selectListVO(@Param("ew") Wrapper<YuanliaocaigouEntity> wrapper);
	
	YuanliaocaigouVO selectVO(@Param("ew") Wrapper<YuanliaocaigouEntity> wrapper);
	
	List<YuanliaocaigouView> selectListView(@Param("ew") Wrapper<YuanliaocaigouEntity> wrapper);

	List<YuanliaocaigouView> selectListView(Pagination page,@Param("ew") Wrapper<YuanliaocaigouEntity> wrapper);

	
	YuanliaocaigouView selectView(@Param("ew") Wrapper<YuanliaocaigouEntity> wrapper);
	

    List<Map<String, Object>> selectValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuanliaocaigouEntity> wrapper);

    List<Map<String, Object>> selectTimeStatValue(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuanliaocaigouEntity> wrapper);

    List<Map<String, Object>> selectGroup(@Param("params") Map<String, Object> params,@Param("ew") Wrapper<YuanliaocaigouEntity> wrapper);



}
