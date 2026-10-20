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


import com.dao.NongminDao;
import com.entity.NongminEntity;
import com.service.NongminService;
import com.entity.vo.NongminVO;
import com.entity.view.NongminView;

@Service("nongminService")
public class NongminServiceImpl extends ServiceImpl<NongminDao, NongminEntity> implements NongminService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<NongminEntity> page = this.selectPage(
                new Query<NongminEntity>(params).getPage(),
                new EntityWrapper<NongminEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<NongminEntity> wrapper) {
		  Page<NongminView> page =new Query<NongminView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<NongminVO> selectListVO(Wrapper<NongminEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public NongminVO selectVO(Wrapper<NongminEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<NongminView> selectListView(Wrapper<NongminEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public NongminView selectView(Wrapper<NongminEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
