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

import com.entity.DiscussnongchanpinxinxiEntity;
import com.entity.view.DiscussnongchanpinxinxiView;

import com.service.DiscussnongchanpinxinxiService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 农产品信息评论
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:14
 */
@RestController
@RequestMapping("/discussnongchanpinxinxi")
public class DiscussnongchanpinxinxiController {
    @Autowired
    private DiscussnongchanpinxinxiService discussnongchanpinxinxiService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussnongchanpinxinxiEntity discussnongchanpinxinxi,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussnongchanpinxinxiEntity> ew = new EntityWrapper<DiscussnongchanpinxinxiEntity>();


        //查询结果
		PageUtils page = discussnongchanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussnongchanpinxinxi), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussnongchanpinxinxiEntity discussnongchanpinxinxi,
                @RequestParam(required = false) Double thumbsupnumstart,
                @RequestParam(required = false) Double thumbsupnumend,
                @RequestParam(required = false) Double crazilynumstart,
                @RequestParam(required = false) Double crazilynumend,
                @RequestParam(required = false) Double istopstart,
                @RequestParam(required = false) Double istopend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussnongchanpinxinxiEntity> ew = new EntityWrapper<DiscussnongchanpinxinxiEntity>();
        if(thumbsupnumstart!=null) ew.ge("thumbsupnum", thumbsupnumstart);
        if(thumbsupnumend!=null) ew.le("thumbsupnum", thumbsupnumend);
        if(crazilynumstart!=null) ew.ge("crazilynum", crazilynumstart);
        if(crazilynumend!=null) ew.le("crazilynum", crazilynumend);
        if(istopstart!=null) ew.ge("istop", istopstart);
        if(istopend!=null) ew.le("istop", istopend);

        //查询结果
		PageUtils page = discussnongchanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussnongchanpinxinxi), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussnongchanpinxinxiEntity discussnongchanpinxinxi){
       	EntityWrapper<DiscussnongchanpinxinxiEntity> ew = new EntityWrapper<DiscussnongchanpinxinxiEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussnongchanpinxinxi, "discussnongchanpinxinxi"));
        return R.ok().put("data", discussnongchanpinxinxiService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussnongchanpinxinxiEntity discussnongchanpinxinxi){
        EntityWrapper< DiscussnongchanpinxinxiEntity> ew = new EntityWrapper< DiscussnongchanpinxinxiEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussnongchanpinxinxi, "discussnongchanpinxinxi"));
		DiscussnongchanpinxinxiView discussnongchanpinxinxiView =  discussnongchanpinxinxiService.selectView(ew);
		return R.ok("查询农产品信息评论成功").put("data", discussnongchanpinxinxiView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussnongchanpinxinxiEntity discussnongchanpinxinxi = discussnongchanpinxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussnongchanpinxinxi,deSens);
        return R.ok().put("data", discussnongchanpinxinxi);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussnongchanpinxinxiEntity discussnongchanpinxinxi = discussnongchanpinxinxiService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussnongchanpinxinxi,deSens);
        return R.ok().put("data", discussnongchanpinxinxi);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussnongchanpinxinxiEntity discussnongchanpinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussnongchanpinxinxi);
        discussnongchanpinxinxiService.insert(discussnongchanpinxinxi);
        return R.ok().put("data",discussnongchanpinxinxi.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussnongchanpinxinxiEntity discussnongchanpinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussnongchanpinxinxi);
        discussnongchanpinxinxiService.insert(discussnongchanpinxinxi);
        return R.ok().put("data",discussnongchanpinxinxi.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussnongchanpinxinxiEntity discussnongchanpinxinxi = discussnongchanpinxinxiService.selectOne(new EntityWrapper<DiscussnongchanpinxinxiEntity>().eq("", username));
        return R.ok().put("data", discussnongchanpinxinxi);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussnongchanpinxinxiEntity discussnongchanpinxinxi, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussnongchanpinxinxi);
        //全部更新
        discussnongchanpinxinxiService.updateById(discussnongchanpinxinxi);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussnongchanpinxinxiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussnongchanpinxinxiEntity discussnongchanpinxinxi, HttpServletRequest request,String pre){
        EntityWrapper<DiscussnongchanpinxinxiEntity> ew = new EntityWrapper<DiscussnongchanpinxinxiEntity>();
        Map<String, Object> newMap = new HashMap<String, Object>();
        Map<String, Object> param = new HashMap<String, Object>();
        // 组装参数
		Iterator<Map.Entry<String, Object>> it = param.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, Object> entry = it.next();
			String key = entry.getKey();
			String newKey = entry.getKey();
			if (pre.endsWith(".")) {
				newMap.put(pre + newKey, entry.getValue());
			} else if (StringUtils.isEmpty(pre)) {
				newMap.put(newKey, entry.getValue());
			} else {
				newMap.put(pre + "." + newKey, entry.getValue());
			}
		}
		params.put("sort", "clicktime");
        params.put("order", "desc");

		PageUtils page = discussnongchanpinxinxiService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussnongchanpinxinxi), params), params));
        return R.ok().put("data", page);
    }









}
