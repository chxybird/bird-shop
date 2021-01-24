package com.bird.feign;

import com.bird.common.CommonResult;
import com.bird.entity.product.Sku;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lipu
 * @Date 2021/1/24 13:46
 * @Description
 */
@RestController
@FeignClient("bird-product")
public interface IProductFeign {

    @GetMapping("/sku/findBatch")
    CommonResult<List<Sku>> findBatch(@RequestParam("idList") List<Long> idList);
}
