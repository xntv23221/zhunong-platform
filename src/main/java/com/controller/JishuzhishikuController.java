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

import com.entity.JishuzhishikuEntity;
import com.entity.view.JishuzhishikuView;

import com.service.JishuzhishikuService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;
import com.service.StoreupService;
import com.entity.StoreupEntity;

/**
 * 技术知识库
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:12
 */
@RestController
@RequestMapping("/jishuzhishiku")
public class JishuzhishikuController {
    @Autowired
    private JishuzhishikuService jishuzhishikuService;

    @Autowired
    private StoreupService storeupService;









    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,JishuzhishikuEntity jishuzhishiku,
		HttpServletRequest request){
		String tableName = request.getSession().getAttribute("tableName").toString();
		if(tableName.equals("gaoxiao")) {
			jishuzhishiku.setGaoxiaomingcheng((String)request.getSession().getAttribute("username"));
		}
        //设置查询条件
        EntityWrapper<JishuzhishikuEntity> ew = new EntityWrapper<JishuzhishikuEntity>();


        //查询结果
		PageUtils page = jishuzhishikuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jishuzhishiku), params), params));
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
    public R list(@RequestParam Map<String, Object> params,JishuzhishikuEntity jishuzhishiku,
                @RequestParam(required = false) Double discussnumstart,
                @RequestParam(required = false) Double discussnumend,
                @RequestParam(required = false) Double storeupnumstart,
                @RequestParam(required = false) Double storeupnumend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<JishuzhishikuEntity> ew = new EntityWrapper<JishuzhishikuEntity>();
        if(discussnumstart!=null) ew.ge("discussnum", discussnumstart);
        if(discussnumend!=null) ew.le("discussnum", discussnumend);
        if(storeupnumstart!=null) ew.ge("storeupnum", storeupnumstart);
        if(storeupnumend!=null) ew.le("storeupnum", storeupnumend);

        //查询结果
		PageUtils page = jishuzhishikuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, jishuzhishiku), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( JishuzhishikuEntity jishuzhishiku){
       	EntityWrapper<JishuzhishikuEntity> ew = new EntityWrapper<JishuzhishikuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( jishuzhishiku, "jishuzhishiku"));
        return R.ok().put("data", jishuzhishikuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(JishuzhishikuEntity jishuzhishiku){
        EntityWrapper< JishuzhishikuEntity> ew = new EntityWrapper< JishuzhishikuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( jishuzhishiku, "jishuzhishiku"));
		JishuzhishikuView jishuzhishikuView =  jishuzhishikuService.selectView(ew);
		return R.ok("查询技术知识库成功").put("data", jishuzhishikuView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        JishuzhishikuEntity jishuzhishiku = jishuzhishikuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jishuzhishiku,deSens);
        return R.ok().put("data", jishuzhishiku);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        JishuzhishikuEntity jishuzhishiku = jishuzhishikuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(jishuzhishiku,deSens);
        return R.ok().put("data", jishuzhishiku);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody JishuzhishikuEntity jishuzhishiku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jishuzhishiku);
        jishuzhishikuService.insert(jishuzhishiku);
        return R.ok().put("data",jishuzhishiku.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody JishuzhishikuEntity jishuzhishiku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jishuzhishiku);
        jishuzhishikuService.insert(jishuzhishiku);
        return R.ok().put("data",jishuzhishiku.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody JishuzhishikuEntity jishuzhishiku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(jishuzhishiku);
        //全部更新
        jishuzhishikuService.updateById(jishuzhishiku);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        jishuzhishikuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
