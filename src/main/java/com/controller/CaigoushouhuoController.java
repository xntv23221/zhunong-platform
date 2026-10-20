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

import com.entity.CaigoushouhuoEntity;
import com.entity.view.CaigoushouhuoView;

import com.service.CaigoushouhuoService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 采购收货
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
@RestController
@RequestMapping("/caigoushouhuo")
public class CaigoushouhuoController {
    @Autowired
    private CaigoushouhuoService caigoushouhuoService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CaigoushouhuoEntity caigoushouhuo,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("gaoxiao")) {
			caigoushouhuo.setGaoxiaomingcheng((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("nongmin")) {
			caigoushouhuo.setNongminzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CaigoushouhuoEntity> ew = new EntityWrapper<CaigoushouhuoEntity>();


        //查询结果
		PageUtils page = caigoushouhuoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caigoushouhuo), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CaigoushouhuoEntity caigoushouhuo,
                @RequestParam(required = false) Double jiagestart,
                @RequestParam(required = false) Double jiageend,
                @RequestParam(required = false) Double kucunstart,
                @RequestParam(required = false) Double kucunend,
                @RequestParam(required = false) Double caigouzongjiastart,
                @RequestParam(required = false) Double caigouzongjiaend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shouhuoshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date shouhuoshijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CaigoushouhuoEntity> ew = new EntityWrapper<CaigoushouhuoEntity>();
        if(jiagestart!=null) ew.ge("jiage", jiagestart);
        if(jiageend!=null) ew.le("jiage", jiageend);
        if(kucunstart!=null) ew.ge("kucun", kucunstart);
        if(kucunend!=null) ew.le("kucun", kucunend);
        if(caigouzongjiastart!=null) ew.ge("caigouzongjia", caigouzongjiastart);
        if(caigouzongjiaend!=null) ew.le("caigouzongjia", caigouzongjiaend);
        if(shouhuoshijianstart!=null) ew.ge("shouhuoshijian", shouhuoshijianstart);
        if(shouhuoshijianend!=null) ew.le("shouhuoshijian", shouhuoshijianend);

        //查询结果
		PageUtils page = caigoushouhuoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caigoushouhuo), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CaigoushouhuoEntity caigoushouhuo){
       	EntityWrapper<CaigoushouhuoEntity> ew = new EntityWrapper<CaigoushouhuoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( caigoushouhuo, "caigoushouhuo"));
        return R.ok().put("data", caigoushouhuoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CaigoushouhuoEntity caigoushouhuo){
        EntityWrapper< CaigoushouhuoEntity> ew = new EntityWrapper< CaigoushouhuoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( caigoushouhuo, "caigoushouhuo"));
		CaigoushouhuoView caigoushouhuoView =  caigoushouhuoService.selectView(ew);
		return R.ok("查询采购收货成功").put("data", caigoushouhuoView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CaigoushouhuoEntity caigoushouhuo = caigoushouhuoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(caigoushouhuo,deSens);
        return R.ok().put("data", caigoushouhuo);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CaigoushouhuoEntity caigoushouhuo = caigoushouhuoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(caigoushouhuo,deSens);
        return R.ok().put("data", caigoushouhuo);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CaigoushouhuoEntity caigoushouhuo, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caigoushouhuo);
        caigoushouhuoService.insert(caigoushouhuo);
        return R.ok().put("data",caigoushouhuo.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CaigoushouhuoEntity caigoushouhuo, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caigoushouhuo);
        caigoushouhuoService.insert(caigoushouhuo);
        return R.ok().put("data",caigoushouhuo.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CaigoushouhuoEntity caigoushouhuo, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caigoushouhuo);
        //全部更新
        caigoushouhuoService.updateById(caigoushouhuo);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        caigoushouhuoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
