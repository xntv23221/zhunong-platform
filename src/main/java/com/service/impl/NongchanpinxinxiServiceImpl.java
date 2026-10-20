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


import com.dao.NongchanpinxinxiDao;
import com.entity.NongchanpinxinxiEntity;
import com.service.NongchanpinxinxiService;
import com.entity.vo.NongchanpinxinxiVO;
import com.entity.view.NongchanpinxinxiView;

@Service("nongchanpinxinxiService")
public class NongchanpinxinxiServiceImpl extends ServiceImpl<NongchanpinxinxiDao, NongchanpinxinxiEntity> implements NongchanpinxinxiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NongchanpinxinxiEntity> page = this.selectPage(
                new Query<NongchanpinxinxiEntity>(params).getPage(),
                new EntityWrapper<NongchanpinxinxiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<NongchanpinxinxiEntity> wrapper) {
		  Page<NongchanpinxinxiView> page =new Query<NongchanpinxinxiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<NongchanpinxinxiVO> selectListVO(Wrapper<NongchanpinxinxiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public NongchanpinxinxiVO selectVO(Wrapper<NongchanpinxinxiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<NongchanpinxinxiView> selectListView(Wrapper<NongchanpinxinxiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public NongchanpinxinxiView selectView(Wrapper<NongchanpinxinxiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
