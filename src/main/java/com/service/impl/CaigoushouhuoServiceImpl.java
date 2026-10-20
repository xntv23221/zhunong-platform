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


import com.dao.CaigoushouhuoDao;
import com.entity.CaigoushouhuoEntity;
import com.service.CaigoushouhuoService;
import com.entity.vo.CaigoushouhuoVO;
import com.entity.view.CaigoushouhuoView;

@Service("caigoushouhuoService")
public class CaigoushouhuoServiceImpl extends ServiceImpl<CaigoushouhuoDao, CaigoushouhuoEntity> implements CaigoushouhuoService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CaigoushouhuoEntity> page = this.selectPage(
                new Query<CaigoushouhuoEntity>(params).getPage(),
                new EntityWrapper<CaigoushouhuoEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CaigoushouhuoEntity> wrapper) {
		  Page<CaigoushouhuoView> page =new Query<CaigoushouhuoView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CaigoushouhuoVO> selectListVO(Wrapper<CaigoushouhuoEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CaigoushouhuoVO selectVO(Wrapper<CaigoushouhuoEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CaigoushouhuoView> selectListView(Wrapper<CaigoushouhuoEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CaigoushouhuoView selectView(Wrapper<CaigoushouhuoEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
