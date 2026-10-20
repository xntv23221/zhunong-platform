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


import com.dao.CaigoupingjiaDao;
import com.entity.CaigoupingjiaEntity;
import com.service.CaigoupingjiaService;
import com.entity.vo.CaigoupingjiaVO;
import com.entity.view.CaigoupingjiaView;

@Service("caigoupingjiaService")
public class CaigoupingjiaServiceImpl extends ServiceImpl<CaigoupingjiaDao, CaigoupingjiaEntity> implements CaigoupingjiaService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<CaigoupingjiaEntity> page = this.selectPage(
                new Query<CaigoupingjiaEntity>(params).getPage(),
                new EntityWrapper<CaigoupingjiaEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<CaigoupingjiaEntity> wrapper) {
		  Page<CaigoupingjiaView> page =new Query<CaigoupingjiaView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<CaigoupingjiaVO> selectListVO(Wrapper<CaigoupingjiaEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public CaigoupingjiaVO selectVO(Wrapper<CaigoupingjiaEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<CaigoupingjiaView> selectListView(Wrapper<CaigoupingjiaEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public CaigoupingjiaView selectView(Wrapper<CaigoupingjiaEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
