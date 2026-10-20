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


import com.dao.DiscussjishuzhishikuDao;
import com.entity.DiscussjishuzhishikuEntity;
import com.service.DiscussjishuzhishikuService;
import com.entity.vo.DiscussjishuzhishikuVO;
import com.entity.view.DiscussjishuzhishikuView;

@Service("discussjishuzhishikuService")
public class DiscussjishuzhishikuServiceImpl extends ServiceImpl<DiscussjishuzhishikuDao, DiscussjishuzhishikuEntity> implements DiscussjishuzhishikuService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<DiscussjishuzhishikuEntity> page = this.selectPage(
                new Query<DiscussjishuzhishikuEntity>(params).getPage(),
                new EntityWrapper<DiscussjishuzhishikuEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<DiscussjishuzhishikuEntity> wrapper) {
		  Page<DiscussjishuzhishikuView> page =new Query<DiscussjishuzhishikuView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<DiscussjishuzhishikuVO> selectListVO(Wrapper<DiscussjishuzhishikuEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public DiscussjishuzhishikuVO selectVO(Wrapper<DiscussjishuzhishikuEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<DiscussjishuzhishikuView> selectListView(Wrapper<DiscussjishuzhishikuEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public DiscussjishuzhishikuView selectView(Wrapper<DiscussjishuzhishikuEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
