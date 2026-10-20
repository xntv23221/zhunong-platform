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


import com.dao.YuanliaofenleiDao;
import com.entity.YuanliaofenleiEntity;
import com.service.YuanliaofenleiService;
import com.entity.vo.YuanliaofenleiVO;
import com.entity.view.YuanliaofenleiView;

@Service("yuanliaofenleiService")
public class YuanliaofenleiServiceImpl extends ServiceImpl<YuanliaofenleiDao, YuanliaofenleiEntity> implements YuanliaofenleiService {
	
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<YuanliaofenleiEntity> page = this.selectPage(
                new Query<YuanliaofenleiEntity>(params).getPage(),
                new EntityWrapper<YuanliaofenleiEntity>()
        );
        return new PageUtils(page);
    }
    
    @Override
	public PageUtils queryPage(Map<String, Object> params, Wrapper<YuanliaofenleiEntity> wrapper) {
		  Page<YuanliaofenleiView> page =new Query<YuanliaofenleiView>(params).getPage();
	        page.setRecords(baseMapper.selectListView(page,wrapper));
	    	PageUtils pageUtil = new PageUtils(page);
	    	return pageUtil;
 	}

    
    @Override
	public List<YuanliaofenleiVO> selectListVO(Wrapper<YuanliaofenleiEntity> wrapper) {
 		return baseMapper.selectListVO(wrapper);
	}
	
	@Override
	public YuanliaofenleiVO selectVO(Wrapper<YuanliaofenleiEntity> wrapper) {
 		return baseMapper.selectVO(wrapper);
	}
	
	@Override
	public List<YuanliaofenleiView> selectListView(Wrapper<YuanliaofenleiEntity> wrapper) {
		return baseMapper.selectListView(wrapper);
	}

	@Override
	public YuanliaofenleiView selectView(Wrapper<YuanliaofenleiEntity> wrapper) {
		return baseMapper.selectView(wrapper);
	}


}
