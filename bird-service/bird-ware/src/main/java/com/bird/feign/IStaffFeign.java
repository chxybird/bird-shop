package com.bird.feign;

import com.bird.common.CommonResult;
import com.bird.entity.user.Staff;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2020/10/26 17:27
 * @Description
 */
@RestController
@FeignClient("bird-user")
@RequestMapping("/staff")
public interface IStaffFeign {

    @GetMapping("/findById")
    CommonResult<Staff> findById(@RequestParam("id") Long id);
}
