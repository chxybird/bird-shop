package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.user.Staff;
import com.bird.service.IStaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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


    /**
     * @Author lipu
     * @Date 2020/10/26 17:10
     * @Description 根据id查询用户信息
     */
    @GetMapping("/findById")
    @ApiOperation("根据id查询用户信息")
    public CommonResult<Staff> findById(@RequestParam("id") Long id){
        Staff staff = staffService.findById(id);
        return new CommonResult<Staff>(CommonStatus.SUCCESS,staff);
    }

    /**
     * @Author lipu
     * @Date 2020/10/27 10:37
     * @Description 获取用户下拉列表
     */
    @GetMapping("/selectList")
    @ApiOperation("获取用户下拉列表")
    public CommonResult selectList(){
        List<Staff> selectList = staffService.selectList();
        return new CommonResult(CommonStatus.SUCCESS,selectList);
    }

    /**
     * @Author lipu
     * @Date 2020/12/22 14:24
     * @Description 发送验证码
     */
    @PostMapping("/sendSms")
    public CommonResult sendSms(){
        return null;
    }

}
