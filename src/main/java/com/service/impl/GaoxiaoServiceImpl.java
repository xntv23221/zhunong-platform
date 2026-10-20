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


import com.dao.GaoxiaoDao;
import com.entity.GaoxiaoEntity;
import com.service.GaoxiaoService;
import com.entity.vo.GaoxiaoVO;
import com.entity.view.GaoxiaoView;

@Service("gaoxiaoService")
public class GaoxiaoServiceImpl extends ServiceImpl<GaoxiaoDao, GaoxiaoEntity> implements GaoxiaoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<GaoxiaoEntity> page = this.selectPage(
                new Query<GaoxiaoEntity>(params).getPage(),
                new EntityWrapper<GaoxiaoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<GaoxiaoEntity> wrapper) {
		  Page<GaoxiaoView> page =new Query<GaoxiaoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<GaoxiaoVO> selectListVO(Wrapper<GaoxiaoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public GaoxiaoVO selectVO(Wrapper<GaoxiaoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<GaoxiaoView> selectListView(Wrapper<GaoxiaoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public GaoxiaoView selectView(Wrapper<GaoxiaoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
