package com.bird.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.dao.IStaffDao;
import com.bird.entity.user.Staff;
import com.bird.entity.vo.LoginVo;
import com.bird.entity.vo.RegisterVo;
import com.bird.service.IStaffService;
import com.bird.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2020/10/26 17:05
 * @Description
 */
@RestController
@RequestMapping("/staff")
@Api(tags = "用户模块接口")
@Slf4j
@Validated
public class StaffController {

    @Resource
    private IStaffService staffService;
    @Resource
    private IStaffDao staffDao;


    /**
     * @Author lipu
     * @Date 2020/10/26 17:10
     * @Description 根据id查询用户信息
     */
    @GetMapping("/findById")
    @ApiOperation("根据id查询用户信息")
    public CommonResult<Staff> findById(@RequestParam("id") Long id) {
        Staff staff = staffService.findById(id);
        return new CommonResult<Staff>(CommonStatus.SUCCESS, staff);
    }

    /**
     * @Author lipu
     * @Date 2020/10/27 10:37
     * @Description 获取用户下拉列表
     */
    @GetMapping("/selectList")
    @ApiOperation("获取用户下拉列表")
    public CommonResult selectList() {
        List<Staff> selectList = staffService.selectList();
        return new CommonResult(CommonStatus.SUCCESS, selectList);
    }

    /**
     * @Author lipu
     * @Date 2020/12/22 18:18
     * @Description 用户注册
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public CommonResult<String> register(@RequestBody RegisterVo registerVo){
        Boolean result = staffService.register(registerVo);
        return new CommonResult(CommonStatus.SUCCESS,result);
    }

    /**
     * @Author lipu
     * @Date 2020/12/23 16:07
     * @Description
     */
    @PostMapping("/login")
    @ApiOperation("/用户登录")
    public CommonResult login(@RequestBody LoginVo loginVo, HttpServletResponse response){
        Boolean login = staffService.login(loginVo);
        if (login){
         //登录成功 初始化Token
            Staff staff = staffDao.selectOne(new QueryWrapper<Staff>().eq("phone", loginVo.getPhone()));
            Map<String,Object> userInfo=new HashMap<>(16);
            userInfo.put("ID",staff.getId());
            userInfo.put("PHONE",staff.getPhone());
            userInfo.put("EMAIL",staff.getEmail());
            userInfo.put("USERNAME",staff.getUsername());
            String jwt = JwtUtils.initJwt(userInfo);
            //将jwt写入到header中
            response.setHeader(JwtUtils.HEADER_FLAG,jwt);
            //将jwt写入到cookie中
            Cookie cookie=new Cookie(JwtUtils.HEADER_FLAG,jwt);
            cookie.setDomain("127.0.0.1");
            cookie.setPath("/");
            response.addCookie(cookie);
            return new CommonResult(CommonStatus.SUCCESS,jwt);
        }else {
            return new CommonResult(CommonStatus.ERROR,"登录失败");
        }
    }

}
