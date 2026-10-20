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

import com.entity.NongminEntity;
import com.entity.view.NongminView;

import com.service.NongminService;
import com.utils.PageUtils;
import com.utils.R;
import com.utils.MPUtil;
import com.utils.MapUtils;
import com.utils.CommonUtil;
import java.io.IOException;

/**
 * 农民
 * 后端接口
 * @author 
 * @email 
 * @date 2026-01-15 21:42:11
 */
@RestController
@RequestMapping("/nongmin")
public class NongminController {
    @Autowired
    private NongminService nongminService;







	@Autowired
	private TokenService tokenService;

	/**
	 * 登录
	 */
	@IgnoreAuth
	@RequestMapping(value = "/login")
	public R login(String username, String password, String captcha, HttpServletRequest request) {
		// 根据登录查询用户信息
        NongminEntity u = nongminService.selectOne(new EntityWrapper<NongminEntity>().eq("nongminzhanghao", username));
        if(u==null || !u.getMima().equals(password)) {
            //账号或密码不正确提示
			return R.error("账号或密码不正确");
		}
        // 获取登录token
        String token = tokenService.generateToken(u.getId(), username,"nongmin",  "农民" );
        //返回token
		return R.ok().put("token", token);
	}



	/**
     * 注册
     */
	@IgnoreAuth
    @RequestMapping("/register")
    public R register(@RequestBody NongminEntity nongmin){
    	//ValidatorUtils.validateEntity(nongmin);
        //根据登录账号获取用户信息判断是否存在该用户，否则返回错误信息
    	NongminEntity u = nongminService.selectOne(new EntityWrapper<NongminEntity>().eq("nongminzhanghao", nongmin.getNongminzhanghao()));
		if(u!=null) {
			return R.error("注册用户已存在");
		}
        //判断是否存在相同农民账号，否则返回错误信息
        if(nongminService.selectCount(new EntityWrapper<NongminEntity>().eq("nongminzhanghao", nongmin.getNongminzhanghao()))>0) {
            return R.error("农民账号已存在");
        }
		Long uId = new Date().getTime();
		nongmin.setId(uId);
        //保存用户
        nongminService.insert(nongmin);
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
        NongminEntity u = nongminService.selectById(id);
        return R.ok().put("data", u);
    }

    /**
     * 密码重置
     */
    @IgnoreAuth
	@RequestMapping(value = "/resetPass")
    public R resetPass(String username, HttpServletRequest request){
    	//根据登录账号判断是否存在用户信息，否则返回错误信息
        NongminEntity u = nongminService.selectOne(new EntityWrapper<NongminEntity>().eq("nongminzhanghao", username));
    	if(u==null) {
    		return R.error("账号不存在");
    	}
        //重置密码为123456
        u.setMima("123456");
        nongminService.updateById(u);
        return R.ok("密码已重置为：123456");
    }

    /**
     * 获取账号列表
     */
    @RequestMapping("/accountList")
    public R getAccountList(@RequestParam Map<String, Object> params,NongminEntity nongmin){
        EntityWrapper<NongminEntity> ew = new EntityWrapper<NongminEntity>();
        Wrapper<NongminEntity> wrapper =MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongmin), params), params);
        List<Map> list = nongminService.selectList(wrapper).stream().map(v -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", v.getId());
            map.put("account", v.getNongminzhanghao());
            return map;
        }).collect(Collectors.toList());
        return R.ok().put("data", list);
    }






    /**
     * 后台列表
     */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params,NongminEntity nongmin,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<NongminEntity> ew = new EntityWrapper<NongminEntity>();


        //查询结果
		PageUtils page = nongminService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongmin), params), params));
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
    public R list(@RequestParam Map<String, Object> params,NongminEntity nongmin,
                @RequestParam(required = false) Double moneystart,
                @RequestParam(required = false) Double moneyend,
		HttpServletRequest request){
        //设置查询条件
        EntityWrapper<NongminEntity> ew = new EntityWrapper<NongminEntity>();
        if(moneystart!=null) ew.ge("money", moneystart);
        if(moneyend!=null) ew.le("money", moneyend);

        //查询结果
		PageUtils page = nongminService.queryPage(params, MPUtil.sort(MPUtil.between(MPUtil.likeOrEq(ew, nongmin), params), params));
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(page,deSens);
        return R.ok().put("data", page);
    }




	/**
     * 列表
     */
    @RequestMapping("/lists")
    public R list( NongminEntity nongmin){
       	EntityWrapper<NongminEntity> ew = new EntityWrapper<NongminEntity>();
      	ew.allEq(MPUtil.allEQMapPre( nongmin, "nongmin"));
        return R.ok().put("data", nongminService.selectListView(ew));
    }

	 /**
     * 查询
     */
    @RequestMapping("/query")
    public R query(NongminEntity nongmin){
        EntityWrapper< NongminEntity> ew = new EntityWrapper< NongminEntity>();
 		ew.allEq(MPUtil.allEQMapPre( nongmin, "nongmin"));
		NongminView nongminView =  nongminService.selectView(ew);
		return R.ok("查询农民成功").put("data", nongminView);
    }

    /**
     * 后台详情
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
        NongminEntity nongmin = nongminService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(nongmin,deSens);
        return R.ok().put("data", nongmin);
    }

    /**
     * 前台详情
     */
	@IgnoreAuth
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id){
        NongminEntity nongmin = nongminService.selectById(id);
        Map<String, String> deSens = new HashMap<>();
        //给需要脱敏的字段脱敏
        DeSensUtil.desensitize(nongmin,deSens);
        return R.ok().put("data", nongmin);
    }




    /**
     * 后台保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody NongminEntity nongmin, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(nongminService.selectCount(new EntityWrapper<NongminEntity>().eq("nongminzhanghao", nongmin.getNongminzhanghao()))>0) {
            return R.error("农民账号已存在");
        }
        //ValidatorUtils.validateEntity(nongmin);
        //验证账号唯一性，否则返回错误信息
        NongminEntity u = nongminService.selectOne(new EntityWrapper<NongminEntity>().eq("nongminzhanghao", nongmin.getNongminzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	nongmin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		nongmin.setId(new Date().getTime());
        nongminService.insert(nongmin);
        return R.ok().put("data",nongmin.getId());
    }

    /**
     * 前台保存
     */
    @RequestMapping("/add")
    public R add(@RequestBody NongminEntity nongmin, HttpServletRequest request){
        //验证字段唯一性，否则返回错误信息
        if(nongminService.selectCount(new EntityWrapper<NongminEntity>().eq("nongminzhanghao", nongmin.getNongminzhanghao()))>0) {
            return R.error("农民账号已存在");
        }
        //ValidatorUtils.validateEntity(nongmin);
        //验证账号唯一性，否则返回错误信息
        NongminEntity u = nongminService.selectOne(new EntityWrapper<NongminEntity>().eq("nongminzhanghao", nongmin.getNongminzhanghao()));
        if(u!=null) {
            return R.error("用户已存在");
        }
    	nongmin.setId(new Date().getTime()+new Double(Math.floor(Math.random()*1000)).longValue());
		nongmin.setId(new Date().getTime());
        nongminService.insert(nongmin);
        return R.ok().put("data",nongmin.getId());
    }





    /**
     * 修改
     */
    @RequestMapping("/update")
    @Transactional
    public R update(@RequestBody NongminEntity nongmin, HttpServletRequest request){
        //ValidatorUtils.validateEntity(nongmin);
        //验证字段唯一性，否则返回错误信息
        if(nongminService.selectCount(new EntityWrapper<NongminEntity>().ne("id", nongmin.getId()).eq("nongminzhanghao", nongmin.getNongminzhanghao()))>0) {
            return R.error("农民账号已存在");
        }
        //全部更新
        nongminService.updateById(nongmin);
        if(null!=nongmin.getNongminzhanghao())
        {
            // 修改token
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUsername(nongmin.getNongminzhanghao());
            tokenService.update(tokenEntity, new EntityWrapper<TokenEntity>().eq("userid", nongmin.getId()));
        }
        return R.ok();
    }





    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
        nongminService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }










}
