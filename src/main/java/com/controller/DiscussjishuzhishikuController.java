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

import com.entity.DiscussjishuzhishikuEntity;
import com.entity.view.DiscussjishuzhishikuView;

import com.service.DiscussjishuzhishikuService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 技术知识库评论
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:14
 */
@RestController
@RequestMapping("/discussjishuzhishiku")
public class DiscussjishuzhishikuController {
    @Autowired
    private DiscussjishuzhishikuService discussjishuzhishikuService;










    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,DiscussjishuzhishikuEntity discussjishuzhishiku,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussjishuzhishikuEntity> ew = new EntityWrapper<DiscussjishuzhishikuEntity>();


        //查询结果
		PageUtils page = discussjishuzhishikuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjishuzhishiku), params), params));
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
    public R list(@RequestParam Map<String, Object> params,DiscussjishuzhishikuEntity discussjishuzhishiku,
                @RequestParam(required = false) Double thumbsupnumstart,
                @RequestParam(required = false) Double thumbsupnumend,
                @RequestParam(required = false) Double crazilynumstart,
                @RequestParam(required = false) Double crazilynumend,
                @RequestParam(required = false) Double istopstart,
                @RequestParam(required = false) Double istopend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<DiscussjishuzhishikuEntity> ew = new EntityWrapper<DiscussjishuzhishikuEntity>();
        if(thumbsupnumstart!=null) ew.ge("thumbsupnum", thumbsupnumstart);
        if(thumbsupnumend!=null) ew.le("thumbsupnum", thumbsupnumend);
        if(crazilynumstart!=null) ew.ge("crazilynum", crazilynumstart);
        if(crazilynumend!=null) ew.le("crazilynum", crazilynumend);
        if(istopstart!=null) ew.ge("istop", istopstart);
        if(istopend!=null) ew.le("istop", istopend);

        //查询结果
		PageUtils page = discussjishuzhishikuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjishuzhishiku), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( DiscussjishuzhishikuEntity discussjishuzhishiku){
       	EntityWrapper<DiscussjishuzhishikuEntity> ew = new EntityWrapper<DiscussjishuzhishikuEntity>();
      	ew.allEq(MPUtil.allEQMapPre( discussjishuzhishiku, "discussjishuzhishiku"));
        return R.ok().put("data", discussjishuzhishikuService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(DiscussjishuzhishikuEntity discussjishuzhishiku){
        EntityWrapper< DiscussjishuzhishikuEntity> ew = new EntityWrapper< DiscussjishuzhishikuEntity>();
 		ew.allEq(MPUtil.allEQMapPre( discussjishuzhishiku, "discussjishuzhishiku"));
		DiscussjishuzhishikuView discussjishuzhishikuView =  discussjishuzhishikuService.selectView(ew);
		return R.ok("查询技术知识库评论成功").put("data", discussjishuzhishikuView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        DiscussjishuzhishikuEntity discussjishuzhishiku = discussjishuzhishikuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussjishuzhishiku,deSens);
        return R.ok().put("data", discussjishuzhishiku);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        DiscussjishuzhishikuEntity discussjishuzhishiku = discussjishuzhishikuService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(discussjishuzhishiku,deSens);
        return R.ok().put("data", discussjishuzhishiku);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody DiscussjishuzhishikuEntity discussjishuzhishiku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussjishuzhishiku);
        discussjishuzhishikuService.insert(discussjishuzhishiku);
        return R.ok().put("data",discussjishuzhishiku.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody DiscussjishuzhishikuEntity discussjishuzhishiku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussjishuzhishiku);
        discussjishuzhishikuService.insert(discussjishuzhishiku);
        return R.ok().put("data",discussjishuzhishiku.getId());
    }



     /**
     * 获取用户密保
     */
    @RequestMapping("/security")
    @IgnoreAuth
    public R security(@RequestParam String username){
        DiscussjishuzhishikuEntity discussjishuzhishiku = discussjishuzhishikuService.selectOne(new EntityWrapper<DiscussjishuzhishikuEntity>().eq("", username));
        return R.ok().put("data", discussjishuzhishiku);
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    @IgnoreAuth
    public R update(@RequestBody DiscussjishuzhishikuEntity discussjishuzhishiku, HttpServletRequest request){
        //ValidatorUtils.validateEntity(discussjishuzhishiku);
        //全部更新
        discussjishuzhishikuService.updateById(discussjishuzhishiku);
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        discussjishuzhishikuService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }

	/**
     * 前台智能排序
     */
	@IgnoreAuth
    @RequestMapping("/autoSort")
    public R autoSort(@RequestParam Map<String, Object> params,DiscussjishuzhishikuEntity discussjishuzhishiku, HttpServletRequest request,String pre){
        EntityWrapper<DiscussjishuzhishikuEntity> ew = new EntityWrapper<DiscussjishuzhishikuEntity>();
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

		PageUtils page = discussjishuzhishikuService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, discussjishuzhishiku), params), params));
        return R.ok().put("data", page);
    }









}
