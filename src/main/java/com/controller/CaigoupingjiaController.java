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

import com.entity.CaigoupingjiaEntity;
import com.entity.view.CaigoupingjiaView;

import com.service.CaigoupingjiaService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 采购评价
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:13
 */
@RestController
@RequestMapping("/caigoupingjia")
public class CaigoupingjiaController {
    @Autowired
    private CaigoupingjiaService caigoupingjiaService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,CaigoupingjiaEntity caigoupingjia,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("gaoxiao")) {
			caigoupingjia.setGaoxiaomingcheng((String)request.getSession().getAttribute("username"));
		}
		if(tableName.equals("nongmin")) {
			caigoupingjia.setNongminzhanghao((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<CaigoupingjiaEntity> ew = new EntityWrapper<CaigoupingjiaEntity>();


        //查询结果
		PageUtils page = caigoupingjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caigoupingjia), params), params));
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
    public R list(@RequestParam Map<String, Object> params,CaigoupingjiaEntity caigoupingjia,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date pingjiashijianstart,
                @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") Date pingjiashijianend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<CaigoupingjiaEntity> ew = new EntityWrapper<CaigoupingjiaEntity>();
        if(pingjiashijianstart!=null) ew.ge("pingjiashijian", pingjiashijianstart);
        if(pingjiashijianend!=null) ew.le("pingjiashijian", pingjiashijianend);

        //查询结果
		PageUtils page = caigoupingjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, caigoupingjia), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( CaigoupingjiaEntity caigoupingjia){
       	EntityWrapper<CaigoupingjiaEntity> ew = new EntityWrapper<CaigoupingjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( caigoupingjia, "caigoupingjia"));
        return R.ok().put("data", caigoupingjiaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(CaigoupingjiaEntity caigoupingjia){
        EntityWrapper< CaigoupingjiaEntity> ew = new EntityWrapper< CaigoupingjiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( caigoupingjia, "caigoupingjia"));
		CaigoupingjiaView caigoupingjiaView =  caigoupingjiaService.selectView(ew);
		return R.ok("查询采购评价成功").put("data", caigoupingjiaView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        CaigoupingjiaEntity caigoupingjia = caigoupingjiaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(caigoupingjia,deSens);
        return R.ok().put("data", caigoupingjia);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        CaigoupingjiaEntity caigoupingjia = caigoupingjiaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(caigoupingjia,deSens);
        return R.ok().put("data", caigoupingjia);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CaigoupingjiaEntity caigoupingjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caigoupingjia);
        caigoupingjiaService.insert(caigoupingjia);
        return R.ok().put("data",caigoupingjia.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody CaigoupingjiaEntity caigoupingjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caigoupingjia);
        caigoupingjiaService.insert(caigoupingjia);
        return R.ok().put("data",caigoupingjia.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody CaigoupingjiaEntity caigoupingjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(caigoupingjia);
        //全部更新
        caigoupingjiaService.updateById(caigoupingjia);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        caigoupingjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
