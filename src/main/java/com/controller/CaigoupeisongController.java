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

import com.entity.CaigoupeisongEntity;
import com.entity.view.CaigoupeisongView;

import com.service.CaigoupeisongService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 采购配送
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
@RestController
@RequestMapping("/caigoupeisong")
public class CaigoupeisongController {
    @Autowired
    private CaigoupeisongService caigoupeisongService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CaigoupeisongEntity caigoupeisong,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("gaoxiao")) {
			caigoupeisong.setGaoxiaomingcheng((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("nongmin")) {
			caigoupeisong.setNongminzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CaigoupeisongEntity> ew = new EntityWrapper<CaigoupeisongEntity>();


        //查询结果
		PageUtils page = caigoupeisongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caigoupeisong), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CaigoupeisongEntity caigoupeisong,
                @RequestParam(required = false) Double jiagestart,
                @RequestParam(required = false) Double jiageend,
                @RequestParam(required = false) Double kucunstart,
                @RequestParam(required = false) Double kucunend,
                @RequestParam(required = false) Double caigouzongjiastart,
                @RequestParam(required = false) Double caigouzongjiaend,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date peisongshijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date peisongshijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CaigoupeisongEntity> ew = new EntityWrapper<CaigoupeisongEntity>();
        if(jiagestart!=null) ew.ge("jiage", jiagestart);
        if(jiageend!=null) ew.le("jiage", jiageend);
        if(kucunstart!=null) ew.ge("kucun", kucunstart);
        if(kucunend!=null) ew.le("kucun", kucunend);
        if(caigouzongjiastart!=null) ew.ge("caigouzongjia", caigouzongjiastart);
        if(caigouzongjiaend!=null) ew.le("caigouzongjia", caigouzongjiaend);
        if(peisongshijianstart!=null) ew.ge("peisongshijian", peisongshijianstart);
        if(peisongshijianend!=null) ew.le("peisongshijian", peisongshijianend);

        //查询结果
		PageUtils page = caigoupeisongService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caigoupeisong), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CaigoupeisongEntity caigoupeisong){
       	EntityWrapper<CaigoupeisongEntity> ew = new EntityWrapper<CaigoupeisongEntity>();
      	ew.allEq(MPUtil.allEQMapPre( caigoupeisong, "caigoupeisong"));
        return R.ok().put("data", caigoupeisongService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CaigoupeisongEntity caigoupeisong){
        EntityWrapper< CaigoupeisongEntity> ew = new EntityWrapper< CaigoupeisongEntity>();
 		ew.allEq(MPUtil.allEQMapPre( caigoupeisong, "caigoupeisong"));
		CaigoupeisongView caigoupeisongView =  caigoupeisongService.selectView(ew);
		return R.ok("查询采购配送成功").put("data", caigoupeisongView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CaigoupeisongEntity caigoupeisong = caigoupeisongService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(caigoupeisong,deSens);
        return R.ok().put("data", caigoupeisong);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CaigoupeisongEntity caigoupeisong = caigoupeisongService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(caigoupeisong,deSens);
        return R.ok().put("data", caigoupeisong);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CaigoupeisongEntity caigoupeisong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caigoupeisong);
        caigoupeisongService.insert(caigoupeisong);
        return R.ok().put("data",caigoupeisong.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CaigoupeisongEntity caigoupeisong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caigoupeisong);
        caigoupeisongService.insert(caigoupeisong);
        return R.ok().put("data",caigoupeisong.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CaigoupeisongEntity caigoupeisong, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caigoupeisong);
        //全部更新
        caigoupeisongService.updateById(caigoupeisong);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        caigoupeisongService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
