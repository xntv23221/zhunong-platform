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

import com.entity.ZhuanjiaEntity;
import com.entity.view.ZhuanjiaView;

import com.service.ZhuanjiaService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 专家
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
@RestController
@RequestMapping("/zhuanjia")
public class ZhuanjiaController {
    @Autowired
    private ZhuanjiaService zhuanjiaService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,ZhuanjiaEntity zhuanjia,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("gaoxiao")) {
			zhuanjia.setGaoxiaomingcheng((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<ZhuanjiaEntity> ew = new EntityWrapper<ZhuanjiaEntity>();


        //查询结果
		PageUtils page = zhuanjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuanjia), params), params));
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
    public R list(@RequestParam Map<String, Object> params,ZhuanjiaEntity zhuanjia,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<ZhuanjiaEntity> ew = new EntityWrapper<ZhuanjiaEntity>();

        //查询结果
		PageUtils page = zhuanjiaService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, zhuanjia), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( ZhuanjiaEntity zhuanjia){
       	EntityWrapper<ZhuanjiaEntity> ew = new EntityWrapper<ZhuanjiaEntity>();
      	ew.allEq(MPUtil.allEQMapPre( zhuanjia, "zhuanjia"));
        return R.ok().put("data", zhuanjiaService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(ZhuanjiaEntity zhuanjia){
        EntityWrapper< ZhuanjiaEntity> ew = new EntityWrapper< ZhuanjiaEntity>();
 		ew.allEq(MPUtil.allEQMapPre( zhuanjia, "zhuanjia"));
		ZhuanjiaView zhuanjiaView =  zhuanjiaService.selectView(ew);
		return R.ok("查询专家成功").put("data", zhuanjiaView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        ZhuanjiaEntity zhuanjia = zhuanjiaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuanjia,deSens);
        return R.ok().put("data", zhuanjia);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        ZhuanjiaEntity zhuanjia = zhuanjiaService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(zhuanjia,deSens);
        return R.ok().put("data", zhuanjia);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody ZhuanjiaEntity zhuanjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuanjia);
        zhuanjiaService.insert(zhuanjia);
        return R.ok().put("data",zhuanjia.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody ZhuanjiaEntity zhuanjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuanjia);
        zhuanjiaService.insert(zhuanjia);
        return R.ok().put("data",zhuanjia.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody ZhuanjiaEntity zhuanjia, HttpServletRequest request){
        //ValidatorUtils.validateEntity(zhuanjia);
        //全部更新
        zhuanjiaService.updateById(zhuanjia);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        zhuanjiaService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
