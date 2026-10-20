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


import com.dao.CaigoupeisongDao;
import com.entity.CaigoupeisongEntity;
import com.service.CaigoupeisongService;
import com.entity.vo.CaigoupeisongVO;
import com.entity.view.CaigoupeisongView;

@Service("caigoupeisongService")
public class CaigoupeisongServiceImpl extends ServiceImpl<CaigoupeisongDao, CaigoupeisongEntity> implements CaigoupeisongService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CaigoupeisongEntity> page = this.selectPage(
                new Query<CaigoupeisongEntity>(params).getPage(),
                new EntityWrapper<CaigoupeisongEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CaigoupeisongEntity> wrapper) {
		  Page<CaigoupeisongView> page =new Query<CaigoupeisongView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CaigoupeisongVO> selectListVO(Wrapper<CaigoupeisongEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CaigoupeisongVO selectVO(Wrapper<CaigoupeisongEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CaigoupeisongView> selectListView(Wrapper<CaigoupeisongEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CaigoupeisongView selectView(Wrapper<CaigoupeisongEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
