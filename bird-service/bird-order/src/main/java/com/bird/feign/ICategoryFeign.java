package com.bird.feign;

import com.bird.common.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2020/10/14 9:44
 * @Description
 */
@RestController
@FeignClient("bird-product")
@RequestMapping("/category")
public interface ICategoryFeign {

    @GetMapping("/find")
    CommonResult find();
}
