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

import com.entity.NongyeyuanliaoEntity;
import com.entity.view.NongyeyuanliaoView;

import com.service.NongyeyuanliaoService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 农业原料
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
@RestController
@RequestMapping("/nongyeyuanliao")
public class NongyeyuanliaoController {
    @Autowired
    private NongyeyuanliaoService nongyeyuanliaoService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,NongyeyuanliaoEntity nongyeyuanliao,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("gaoxiao")) {
			nongyeyuanliao.setGaoxiaomingcheng((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<NongyeyuanliaoEntity> ew = new EntityWrapper<NongyeyuanliaoEntity>();


        //查询结果
		PageUtils page = nongyeyuanliaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongyeyuanliao), params), params));
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
    public R list(@RequestParam Map<String, Object> params,NongyeyuanliaoEntity nongyeyuanliao,
                @RequestParam(required = false) Double jiagestart,
                @RequestParam(required = false) Double jiageend,
                @RequestParam(required = false) Double kucunstart,
                @RequestParam(required = false) Double kucunend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<NongyeyuanliaoEntity> ew = new EntityWrapper<NongyeyuanliaoEntity>();
        if(jiagestart!=null) ew.ge("jiage", jiagestart);
        if(jiageend!=null) ew.le("jiage", jiageend);
        if(kucunstart!=null) ew.ge("kucun", kucunstart);
        if(kucunend!=null) ew.le("kucun", kucunend);

        //查询结果
		PageUtils page = nongyeyuanliaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongyeyuanliao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( NongyeyuanliaoEntity nongyeyuanliao){
       	EntityWrapper<NongyeyuanliaoEntity> ew = new EntityWrapper<NongyeyuanliaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( nongyeyuanliao, "nongyeyuanliao"));
        return R.ok().put("data", nongyeyuanliaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(NongyeyuanliaoEntity nongyeyuanliao){
        EntityWrapper< NongyeyuanliaoEntity> ew = new EntityWrapper< NongyeyuanliaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( nongyeyuanliao, "nongyeyuanliao"));
		NongyeyuanliaoView nongyeyuanliaoView =  nongyeyuanliaoService.selectView(ew);
		return R.ok("查询农业原料成功").put("data", nongyeyuanliaoView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NongyeyuanliaoEntity nongyeyuanliao = nongyeyuanliaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(nongyeyuanliao,deSens);
        return R.ok().put("data", nongyeyuanliao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        NongyeyuanliaoEntity nongyeyuanliao = nongyeyuanliaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(nongyeyuanliao,deSens);
        return R.ok().put("data", nongyeyuanliao);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NongyeyuanliaoEntity nongyeyuanliao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(nongyeyuanliao);
        nongyeyuanliaoService.insert(nongyeyuanliao);
        return R.ok().put("data",nongyeyuanliao.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody NongyeyuanliaoEntity nongyeyuanliao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(nongyeyuanliao);
        nongyeyuanliaoService.insert(nongyeyuanliao);
        return R.ok().put("data",nongyeyuanliao.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody NongyeyuanliaoEntity nongyeyuanliao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(nongyeyuanliao);
        //全部更新
        nongyeyuanliaoService.updateById(nongyeyuanliao);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        nongyeyuanliaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
