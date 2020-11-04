package com.bird.feign;

import com.bird.common.CommonResult;
import com.bird.entity.product.es.SkuModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author lipu
 * @Date 2020/11/1 19:29
 * @Description
 */
@RestController
@FeignClient("bird-search")
@RequestMapping("/sku/es")
public interface ISkuESFeign {

    @PostMapping("/saveAll")
    CommonResult<Integer> saveAll(@RequestBody List<SkuModel> skuModelList);

    @DeleteMapping("/deleteBatch")
    CommonResult<Integer> deleteBatch(@RequestBody List<SkuModel> skuModelList);
}
