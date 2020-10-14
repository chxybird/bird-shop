package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.feign.ICategoryFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2020/10/14 9:32
 * @Description
 */
@RestController
@RequestMapping("/order")
@Api(tags = "订单模块")
public class OrderController {

    public static String BIRD_PRODUCT="http://bird-product";

    @Resource
    private ICategoryFeign categoryFeign;
    @Resource
    private RestTemplate restTemplate;

    /**
     * @Author lipu
     * @Date 2020/10/14 9:52
     * @Description 测试分类远程调用
     */
    @ApiOperation("远程调用测试分类")
    @GetMapping("/findCategory")
    public CommonResult findCategory(){
        //Ribbon测试
//        CommonResult<Integer> commonResult = restTemplate.getForObject(BIRD_PRODUCT + "/category/find", CommonResult.class);
        //feign测试
        CommonResult<Integer> commonResult = categoryFeign.find();
        return commonResult;
    }
}
