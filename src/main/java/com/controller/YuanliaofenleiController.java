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

import com.entity.YuanliaofenleiEntity;
import com.entity.view.YuanliaofenleiView;

import com.service.YuanliaofenleiService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 原料分类
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
@RestController
@RequestMapping("/yuanliaofenlei")
public class YuanliaofenleiController {
    @Autowired
    private YuanliaofenleiService yuanliaofenleiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,YuanliaofenleiEntity yuanliaofenlei,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YuanliaofenleiEntity> ew = new EntityWrapper<YuanliaofenleiEntity>();


        //查询结果
		PageUtils page = yuanliaofenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuanliaofenlei), params), params));
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
    public R list(@RequestParam Map<String, Object> params,YuanliaofenleiEntity yuanliaofenlei,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<YuanliaofenleiEntity> ew = new EntityWrapper<YuanliaofenleiEntity>();

        //查询结果
		PageUtils page = yuanliaofenleiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, yuanliaofenlei), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( YuanliaofenleiEntity yuanliaofenlei){
       	EntityWrapper<YuanliaofenleiEntity> ew = new EntityWrapper<YuanliaofenleiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( yuanliaofenlei, "yuanliaofenlei"));
        return R.ok().put("data", yuanliaofenleiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(YuanliaofenleiEntity yuanliaofenlei){
        EntityWrapper< YuanliaofenleiEntity> ew = new EntityWrapper< YuanliaofenleiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( yuanliaofenlei, "yuanliaofenlei"));
		YuanliaofenleiView yuanliaofenleiView =  yuanliaofenleiService.selectView(ew);
		return R.ok("查询原料分类成功").put("data", yuanliaofenleiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        YuanliaofenleiEntity yuanliaofenlei = yuanliaofenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yuanliaofenlei,deSens);
        return R.ok().put("data", yuanliaofenlei);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        YuanliaofenleiEntity yuanliaofenlei = yuanliaofenleiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(yuanliaofenlei,deSens);
        return R.ok().put("data", yuanliaofenlei);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody YuanliaofenleiEntity yuanliaofenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yuanliaofenleiService.selectCount(new EntityWrapper<YuanliaofenleiEntity>().eq("yuanliaofenlei", yuanliaofenlei.getYuanliaofenlei()))>0) {
            return R.error("原料分类已存在");
        }
        //ValidatorUtils.validateEntity(yuanliaofenlei);
        yuanliaofenleiService.insert(yuanliaofenlei);
        return R.ok().put("data",yuanliaofenlei.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody YuanliaofenleiEntity yuanliaofenlei, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(yuanliaofenleiService.selectCount(new EntityWrapper<YuanliaofenleiEntity>().eq("yuanliaofenlei", yuanliaofenlei.getYuanliaofenlei()))>0) {
            return R.error("原料分类已存在");
        }
        //ValidatorUtils.validateEntity(yuanliaofenlei);
        yuanliaofenleiService.insert(yuanliaofenlei);
        return R.ok().put("data",yuanliaofenlei.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody YuanliaofenleiEntity yuanliaofenlei, HttpServletRequest request){
        //ValidatorUtils.validateEntity(yuanliaofenlei);
        //验证字段唯一性，否则返回错误信息
        if(yuanliaofenleiService.selectCount(new EntityWrapper<YuanliaofenleiEntity>().ne("id", yuanliaofenlei.getId()).eq("yuanliaofenlei", yuanliaofenlei.getYuanliaofenlei()))>0) {
            return R.error("原料分类已存在");
        }
        //全部更新
        yuanliaofenleiService.updateById(yuanliaofenlei);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        yuanliaofenleiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
