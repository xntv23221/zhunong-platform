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
import com.service.TokenService;
import com.entity.TokenEntity;
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

import com.entity.GaoxiaoEntity;
import com.entity.view.GaoxiaoView;

import com.service.GaoxiaoService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 高校
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:11
 */
@RestController
@RequestMapping("/gaoxiao")
public class GaoxiaoController {
    @Autowired
    private GaoxiaoService gaoxiaoService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        GaoxiaoEntity u = gaoxiaoService.selectOne(new EntityWrapper<GaoxiaoEntity>().eq("gaoxiaomingcheng", username));
        if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
        String token = tokenService.generateToken(u.getId(), username,"gaoxiao",  "高校" );
        //返回token
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody GaoxiaoEntity gaoxiao){
    	//ValidatorUtils.validateEntity(gaoxiao);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	GaoxiaoEntity u = gaoxiaoService.selectOne(new EntityWrapper<GaoxiaoEntity>().eq("gaoxiaomingcheng", gaoxiao.getGaoxiaomingcheng()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同高校名称，否则返回错误信息
        if(gaoxiaoService.selectCount(new EntityWrapper<GaoxiaoEntity>().eq("gaoxiaomingcheng", gaoxiao.getGaoxiaomingcheng()))>0) {
            return R.error("高校名称已存在");
        }
		Long uId = new Date().getTime();
		gaoxiao.setId(uId);
        //保存用户
        gaoxiaoService.insert(gaoxiao);
        return R.ok();
    }



	/**
	 * 退出
	 */
	@RequestMapping("/logout")
	public R logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return R.ok("退出成功");
	}

	/**
     * 获取用户的session用户信息
     */
    @RequestMapping("/session")
    public R getCurrUser(HttpServletRequest request){
    	Long id = (Long)request.getSession().getAttribute("userId");
        GaoxiaoEntity u = gaoxiaoService.selectById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        GaoxiaoEntity u = gaoxiaoService.selectOne(new EntityWrapper<GaoxiaoEntity>().eq("gaoxiaomingcheng", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        gaoxiaoService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,GaoxiaoEntity gaoxiao){
        EntityWrapper<GaoxiaoEntity> ew = new EntityWrapper<GaoxiaoEntity>();
        Wrapper<GaoxiaoEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gaoxiao), params), params);
        List<Map> list = gaoxiaoService.selectList(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getGaoxiaomingcheng());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,GaoxiaoEntity gaoxiao,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GaoxiaoEntity> ew = new EntityWrapper<GaoxiaoEntity>();


        //查询结果
		PageUtils page = gaoxiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gaoxiao), params), params));
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
    public R list(@RequestParam Map<String, Object> params,GaoxiaoEntity gaoxiao,
                @RequestParam(required = false) Double moneystart,
                @RequestParam(required = false) Double moneyend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<GaoxiaoEntity> ew = new EntityWrapper<GaoxiaoEntity>();
        if(moneystart!=null) ew.ge("money", moneystart);
        if(moneyend!=null) ew.le("money", moneyend);

        //查询结果
		PageUtils page = gaoxiaoService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, gaoxiao), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( GaoxiaoEntity gaoxiao){
       	EntityWrapper<GaoxiaoEntity> ew = new EntityWrapper<GaoxiaoEntity>();
      	ew.allEq(MPUtil.allEQMapPre( gaoxiao, "gaoxiao"));
        return R.ok().put("data", gaoxiaoService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(GaoxiaoEntity gaoxiao){
        EntityWrapper< GaoxiaoEntity> ew = new EntityWrapper< GaoxiaoEntity>();
 		ew.allEq(MPUtil.allEQMapPre( gaoxiao, "gaoxiao"));
		GaoxiaoView gaoxiaoView =  gaoxiaoService.selectView(ew);
		return R.ok("查询高校成功").put("data", gaoxiaoView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        GaoxiaoEntity gaoxiao = gaoxiaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gaoxiao,deSens);
        return R.ok().put("data", gaoxiao);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        GaoxiaoEntity gaoxiao = gaoxiaoService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(gaoxiao,deSens);
        return R.ok().put("data", gaoxiao);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GaoxiaoEntity gaoxiao, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(gaoxiaoService.selectCount(new EntityWrapper<GaoxiaoEntity>().eq("gaoxiaomingcheng", gaoxiao.getGaoxiaomingcheng()))>0) {
            return R.error("高校名称已存在");
        }
        //ValidatorUtils.validateEntity(gaoxiao);
        //验证账号唯一性，否则返回错误信息
        GaoxiaoEntity u = gaoxiaoService.selectOne(new EntityWrapper<GaoxiaoEntity>().eq("gaoxiaomingcheng", gaoxiao.getGaoxiaomingcheng()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	gaoxiao.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		gaoxiao.setId(new Date().getTime());
        gaoxiaoService.insert(gaoxiao);
        return R.ok().put("data",gaoxiao.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody GaoxiaoEntity gaoxiao, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(gaoxiaoService.selectCount(new EntityWrapper<GaoxiaoEntity>().eq("gaoxiaomingcheng", gaoxiao.getGaoxiaomingcheng()))>0) {
            return R.error("高校名称已存在");
        }
        //ValidatorUtils.validateEntity(gaoxiao);
        //验证账号唯一性，否则返回错误信息
        GaoxiaoEntity u = gaoxiaoService.selectOne(new EntityWrapper<GaoxiaoEntity>().eq("gaoxiaomingcheng", gaoxiao.getGaoxiaomingcheng()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	gaoxiao.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		gaoxiao.setId(new Date().getTime());
        gaoxiaoService.insert(gaoxiao);
        return R.ok().put("data",gaoxiao.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody GaoxiaoEntity gaoxiao, HttpServletRequest request){
        //ValidatorUtils.validateEntity(gaoxiao);
        //验证字段唯一性，否则返回错误信息
        if(gaoxiaoService.selectCount(new EntityWrapper<GaoxiaoEntity>().ne("id", gaoxiao.getId()).eq("gaoxiaomingcheng", gaoxiao.getGaoxiaomingcheng()))>0) {
            return R.error("高校名称已存在");
        }
        //全部更新
        gaoxiaoService.updateById(gaoxiao);
        if(null!=gaoxiao.getGaoxiaomingcheng())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(gaoxiao.getGaoxiaomingcheng());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", gaoxiao.getId()));
        }
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        gaoxiaoService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
