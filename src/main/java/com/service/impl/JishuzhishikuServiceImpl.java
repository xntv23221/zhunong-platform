package com.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.List;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.utils.PageUtils;
import com.utils.Query;


import com.dao.JishuzhishikuDao;
import com.entity.JishuzhishikuEntity;
import com.service.JishuzhishikuService;
import com.entity.vo.JishuzhishikuVO;
import com.entity.view.JishuzhishikuView;

@Service("jishuzhishikuService")
public class JishuzhishikuServiceImpl extends ServiceImpl<JishuzhishikuDao, JishuzhishikuEntity> implements JishuzhishikuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<JishuzhishikuEntity> page = this.selectPage(
                new Query<JishuzhishikuEntity>(params).getPage(),
                new EntityWrapper<JishuzhishikuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<JishuzhishikuEntity> wrapper) {
		  Page<JishuzhishikuView> page =new Query<JishuzhishikuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<JishuzhishikuVO> selectListVO(Wrapper<JishuzhishikuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public JishuzhishikuVO selectVO(Wrapper<JishuzhishikuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<JishuzhishikuView> selectListView(Wrapper<JishuzhishikuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public JishuzhishikuView selectView(Wrapper<JishuzhishikuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
