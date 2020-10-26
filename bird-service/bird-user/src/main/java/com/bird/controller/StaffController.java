package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.user.Staff;
import com.bird.service.IStaffService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

}
