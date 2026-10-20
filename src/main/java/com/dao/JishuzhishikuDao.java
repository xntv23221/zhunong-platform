package com.dao;

import com.entity.JishuzhishikuEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.JishuzhishikuVO;
import com.entity.view.JishuzhishikuView;


/**
 * 技术知识库
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public interface JishuzhishikuDao extends BaseMapper<JishuzhishikuEntity> {
	
	List<JishuzhishikuVO> selectListVO(@Param("ew") Wrapper<JishuzhishikuEntity> wrapper);
	
	JishuzhishikuVO selectVO(@Param("ew") Wrapper<JishuzhishikuEntity> wrapper);
	
	List<JishuzhishikuView> selectListView(@Param("ew") Wrapper<JishuzhishikuEntity> wrapper);

	List<JishuzhishikuView> selectListView(Pagination page,@Param("ew") Wrapper<JishuzhishikuEntity> wrapper);

	
	JishuzhishikuView selectView(@Param("ew") Wrapper<JishuzhishikuEntity> wrapper);
	

}
