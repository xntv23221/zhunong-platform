package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.NongyeyuanliaoEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.NongyeyuanliaoVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.NongyeyuanliaoView;


/**
 * 农业原料
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public interface NongyeyuanliaoService extends IService<NongyeyuanliaoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<NongyeyuanliaoVO> selectListVO(Wrapper<NongyeyuanliaoEntity> wrapper);
   	
   	NongyeyuanliaoVO selectVO(@Param("ew") Wrapper<NongyeyuanliaoEntity> wrapper);
   	
   	List<NongyeyuanliaoView> selectListView(Wrapper<NongyeyuanliaoEntity> wrapper);
   	
   	NongyeyuanliaoView selectView(@Param("ew") Wrapper<NongyeyuanliaoEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<NongyeyuanliaoEntity> wrapper);

   	

}

