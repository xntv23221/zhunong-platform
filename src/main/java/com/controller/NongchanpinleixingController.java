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

import com.entity.NongchanpinleixingEntity;
import com.entity.view.NongchanpinleixingView;

import com.service.NongchanpinleixingService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 农产品类型
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
@RestController
@RequestMapping("/nongchanpinleixing")
public class NongchanpinleixingController {
    @Autowired
    private NongchanpinleixingService nongchanpinleixingService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,NongchanpinleixingEntity nongchanpinleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<NongchanpinleixingEntity> ew = new EntityWrapper<NongchanpinleixingEntity>();


        //查询结果
		PageUtils page = nongchanpinleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpinleixing), params), params));
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
    public R list(@RequestParam Map<String, Object> params,NongchanpinleixingEntity nongchanpinleixing,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<NongchanpinleixingEntity> ew = new EntityWrapper<NongchanpinleixingEntity>();

        //查询结果
		PageUtils page = nongchanpinleixingService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongchanpinleixing), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( NongchanpinleixingEntity nongchanpinleixing){
       	EntityWrapper<NongchanpinleixingEntity> ew = new EntityWrapper<NongchanpinleixingEntity>();
      	ew.allEq(MPUtil.allEQMapPre( nongchanpinleixing, "nongchanpinleixing"));
        return R.ok().put("data", nongchanpinleixingService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(NongchanpinleixingEntity nongchanpinleixing){
        EntityWrapper< NongchanpinleixingEntity> ew = new EntityWrapper< NongchanpinleixingEntity>();
 		ew.allEq(MPUtil.allEQMapPre( nongchanpinleixing, "nongchanpinleixing"));
		NongchanpinleixingView nongchanpinleixingView =  nongchanpinleixingService.selectView(ew);
		return R.ok("查询农产品类型成功").put("data", nongchanpinleixingView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NongchanpinleixingEntity nongchanpinleixing = nongchanpinleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(nongchanpinleixing,deSens);
        return R.ok().put("data", nongchanpinleixing);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        NongchanpinleixingEntity nongchanpinleixing = nongchanpinleixingService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(nongchanpinleixing,deSens);
        return R.ok().put("data", nongchanpinleixing);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NongchanpinleixingEntity nongchanpinleixing, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(nongchanpinleixingService.selectCount(new EntityWrapper<NongchanpinleixingEntity>().eq("nongchanpinleixing", nongchanpinleixing.getNongchanpinleixing()))>0) {
            return R.error("农产品类型已存在");
        }
        //ValidatorUtils.validateEntity(nongchanpinleixing);
        nongchanpinleixingService.insert(nongchanpinleixing);
        return R.ok().put("data",nongchanpinleixing.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody NongchanpinleixingEntity nongchanpinleixing, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(nongchanpinleixingService.selectCount(new EntityWrapper<NongchanpinleixingEntity>().eq("nongchanpinleixing", nongchanpinleixing.getNongchanpinleixing()))>0) {
            return R.error("农产品类型已存在");
        }
        //ValidatorUtils.validateEntity(nongchanpinleixing);
        nongchanpinleixingService.insert(nongchanpinleixing);
        return R.ok().put("data",nongchanpinleixing.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody NongchanpinleixingEntity nongchanpinleixing, HttpServletRequest request){
        //ValidatorUtils.validateEntity(nongchanpinleixing);
        //验证字段唯一性，否则返回错误信息
        if(nongchanpinleixingService.selectCount(new EntityWrapper<NongchanpinleixingEntity>().ne("id", nongchanpinleixing.getId()).eq("nongchanpinleixing", nongchanpinleixing.getNongchanpinleixing()))>0) {
            return R.error("农产品类型已存在");
        }
        //全部更新
        nongchanpinleixingService.updateById(nongchanpinleixing);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        nongchanpinleixingService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
