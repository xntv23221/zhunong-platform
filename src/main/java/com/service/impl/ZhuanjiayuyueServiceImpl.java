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


import com.dao.ZhuanjiayuyueDao;
import com.entity.ZhuanjiayuyueEntity;
import com.service.ZhuanjiayuyueService;
import com.entity.vo.ZhuanjiayuyueVO;
import com.entity.view.ZhuanjiayuyueView;

@Service("zhuanjiayuyueService")
public class ZhuanjiayuyueServiceImpl extends ServiceImpl<ZhuanjiayuyueDao, ZhuanjiayuyueEntity> implements ZhuanjiayuyueService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<ZhuanjiayuyueEntity> page = this.selectPage(
                new Query<ZhuanjiayuyueEntity>(params).getPage(),
                new EntityWrapper<ZhuanjiayuyueEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<ZhuanjiayuyueEntity> wrapper) {
		  Page<ZhuanjiayuyueView> page =new Query<ZhuanjiayuyueView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<ZhuanjiayuyueVO> selectListVO(Wrapper<ZhuanjiayuyueEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public ZhuanjiayuyueVO selectVO(Wrapper<ZhuanjiayuyueEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<ZhuanjiayuyueView> selectListView(Wrapper<ZhuanjiayuyueEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public ZhuanjiayuyueView selectView(Wrapper<ZhuanjiayuyueEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
