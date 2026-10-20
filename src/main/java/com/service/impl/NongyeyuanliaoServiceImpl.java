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


import com.dao.NongyeyuanliaoDao;
import com.entity.NongyeyuanliaoEntity;
import com.service.NongyeyuanliaoService;
import com.entity.vo.NongyeyuanliaoVO;
import com.entity.view.NongyeyuanliaoView;

@Service("nongyeyuanliaoService")
public class NongyeyuanliaoServiceImpl extends ServiceImpl<NongyeyuanliaoDao, NongyeyuanliaoEntity> implements NongyeyuanliaoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NongyeyuanliaoEntity> page = this.selectPage(
                new Query<NongyeyuanliaoEntity>(params).getPage(),
                new EntityWrapper<NongyeyuanliaoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<NongyeyuanliaoEntity> wrapper) {
		  Page<NongyeyuanliaoView> page =new Query<NongyeyuanliaoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<NongyeyuanliaoVO> selectListVO(Wrapper<NongyeyuanliaoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public NongyeyuanliaoVO selectVO(Wrapper<NongyeyuanliaoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<NongyeyuanliaoView> selectListView(Wrapper<NongyeyuanliaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public NongyeyuanliaoView selectView(Wrapper<NongyeyuanliaoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
