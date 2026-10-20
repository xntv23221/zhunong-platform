package com.service;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.JishuzhishikuEntity;
import java.util.List;
import java.util.Map;
import com.entity.vo.JishuzhishikuVO;
import org.apache.ibatis.annotations.Param;
import com.entity.view.JishuzhishikuView;


/**
 * 技术知识库
 *
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
public interface JishuzhishikuService extends IService<JishuzhishikuEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
   	List<JishuzhishikuVO> selectListVO(Wrapper<JishuzhishikuEntity> wrapper);
   	
   	JishuzhishikuVO selectVO(@Param("ew") Wrapper<JishuzhishikuEntity> wrapper);
   	
   	List<JishuzhishikuView> selectListView(Wrapper<JishuzhishikuEntity> wrapper);
   	
   	JishuzhishikuView selectView(@Param("ew") Wrapper<JishuzhishikuEntity> wrapper);
   	
   	PageUtils queryPage(Map<String, Object> params,Wrapper<JishuzhishikuEntity> wrapper);

   	

}

