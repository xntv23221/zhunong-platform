package com.dao;

import com.entity.YuanliaofenleiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.vo.YuanliaofenleiVO;
import com.entity.view.YuanliaofenleiView;


/**
 * 原料分类
 * 
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public interface YuanliaofenleiDao extends BaseMapper<YuanliaofenleiEntity> {
	
	List<YuanliaofenleiVO> selectListVO(@Param("ew") Wrapper<YuanliaofenleiEntity> wrapper);
	
	YuanliaofenleiVO selectVO(@Param("ew") Wrapper<YuanliaofenleiEntity> wrapper);
	
	List<YuanliaofenleiView> selectListView(@Param("ew") Wrapper<YuanliaofenleiEntity> wrapper);

	List<YuanliaofenleiView> selectListView(Pagination page,@Param("ew") Wrapper<YuanliaofenleiEntity> wrapper);

	
	YuanliaofenleiView selectView(@Param("ew") Wrapper<YuanliaofenleiEntity> wrapper);
	

}
