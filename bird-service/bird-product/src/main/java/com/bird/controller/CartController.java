package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.entity.product.Cart;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Author lipu
 * @Date 2020/12/24 11:29
 * @Description
 */
@RestController
@RequestMapping("/cart")
@Api(tags = "购物车模块接口")
@Slf4j
@Validated
public class CartController {


    /**
     * @Author lipu
     * @Date 2020/12/24 11:32
     * @Description 添加购物车
     */
    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public CommonResult add(@RequestBody Cart cart){
        return null;
    }

    @PostMapping("/update")
    @ApiOperation("更新购物车")
    public CommonResult update(@RequestParam Long cartId,
                            @RequestParam Long cartItemId,
                            @RequestParam Integer count){
        return null;
    }

    @PostMapping("/delete")
    @ApiOperation("删除购物车")
    public CommonResult delete(@RequestBody Cart cart){
        return null;
    }


}
