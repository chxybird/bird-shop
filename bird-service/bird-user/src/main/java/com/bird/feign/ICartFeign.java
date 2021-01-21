package com.bird.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2021/1/21 17:29
 * @Description 商品微服务远程调用购物车模块
 */
@RestController
@FeignClient("bird-product")
@RequestMapping("/cart")
public interface ICartFeign {

    @PostMapping("/init")
    Boolean initCart(@RequestParam("staffId") Long staffId);

}
