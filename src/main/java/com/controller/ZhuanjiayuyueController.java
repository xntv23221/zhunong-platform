package com.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;
import java.lang.*;
import java.math.*;
import com.utils.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.annotation.IgnoreAuth;

import com.entity.ZhuanjiayuyueEntity;
import com.entity.view.ZhuanjiayuyueView;

import com.service.ZhuanjiayuyueService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 专家预约
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
@RestController
@RequestMapping("/zhuanjiayuyue")
public class ZhuanjiayuyueController {
    @Autowired
    private ZhuanjiayuyueService zhuanjiayuyueService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhuanjiayuyueEntity zhuanjiayuyue,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("gaoxiao")) {
			zhuanjiayuyue.setGaoxiaomingcheng((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("nongmin")) {
			zhuanjiayuyue.setNongminzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ZhuanjiayuyueEntity> ew = new EntityWrapper<ZhuanjiayuyueEntity>();


        //查询结果
		PageUtils page = zhuanjiayuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuanjiayuyue), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }


    /**
     * 前台列表
     */
	@IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params,ZhuanjiayuyueEntity zhuanjiayuyue,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date yuyueshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date yuyueshijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhuanjiayuyueEntity> ew = new EntityWrapper<ZhuanjiayuyueEntity>();
        if(yuyueshijianstart!=null) ew.ge("yuyueshijian", yuyueshijianstart);
        if(yuyueshijianend!=null) ew.le("yuyueshijian", yuyueshijianend);

        //查询结果
		PageUtils page = zhuanjiayuyueService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuanjiayuyue), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhuanjiayuyueEntity zhuanjiayuyue){
       	EntityWrapper<ZhuanjiayuyueEntity> ew = new EntityWrapper<ZhuanjiayuyueEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhuanjiayuyue, "zhuanjiayuyue"));
        return R.ok().put("data", zhuanjiayuyueService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhuanjiayuyueEntity zhuanjiayuyue){
        EntityWrapper< ZhuanjiayuyueEntity> ew = new EntityWrapper< ZhuanjiayuyueEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhuanjiayuyue, "zhuanjiayuyue"));
		ZhuanjiayuyueView zhuanjiayuyueView =  zhuanjiayuyueService.selectView(ew);
		return R.ok("查询专家预约成功").put("data", zhuanjiayuyueView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhuanjiayuyueEntity zhuanjiayuyue = zhuanjiayuyueService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuanjiayuyue,deSens);
        return R.ok().put("data", zhuanjiayuyue);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhuanjiayuyueEntity zhuanjiayuyue = zhuanjiayuyueService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuanjiayuyue,deSens);
        return R.ok().put("data", zhuanjiayuyue);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhuanjiayuyueEntity zhuanjiayuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuanjiayuyue);
        zhuanjiayuyueService.insert(zhuanjiayuyue);
        return R.ok().put("data",zhuanjiayuyue.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhuanjiayuyueEntity zhuanjiayuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuanjiayuyue);
        zhuanjiayuyueService.insert(zhuanjiayuyue);
        return R.ok().put("data",zhuanjiayuyue.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhuanjiayuyueEntity zhuanjiayuyue, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuanjiayuyue);
        //全部更新
        zhuanjiayuyueService.updateById(zhuanjiayuyue);
        return R.ok();
    }

    /**
     * 审核
     */
    @RequestMapping("/shBatch")
    @Transactional
    public R update(@RequestBody Long[] ids, @RequestParam String sfsh, @RequestParam String shhf){
        List<ZhuanjiayuyueEntity> list = new ArrayList<ZhuanjiayuyueEntity>();
        for(Long id : ids) {
            ZhuanjiayuyueEntity zhuanjiayuyue = zhuanjiayuyueService.selectById(id);
            zhuanjiayuyue.setSfsh(sfsh);
            zhuanjiayuyue.setShhf(shhf);
            list.add(zhuanjiayuyue);
        }
        zhuanjiayuyueService.updateBatchById(list);
        return R.ok();
    }




    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhuanjiayuyueService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
