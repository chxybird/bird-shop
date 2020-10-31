package com.bird.feign;

import com.bird.common.CommonResult;
import com.bird.entity.product.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lipu
 * @Date 2020/10/31 14:51
 * @Description
 */
@RestController
@FeignClient("bird-sku")
@RequestMapping("/sku")
public interface ISkuFeign {

    @GetMapping("/findById")
    CommonResult<Sku> findById(@RequestParam("id") Long id);

}
