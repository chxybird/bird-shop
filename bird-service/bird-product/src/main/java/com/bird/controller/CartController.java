package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.product.Cart;
import com.bird.service.ICartService;
import com.bird.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @Resource
    private ICartService cartService;

    /**
     * @Author lipu
     * @Date 2021/1/21 17:34
     * @Description 初始化购物车信息
     */
    @PostMapping("/init")
    public Boolean initCart(@RequestParam("staffId") Long staffId){
        Boolean result = cartService.init(staffId);
        return result;
    }

    /**
     * @Author lipu
     * @Date 2021/1/21 15:38
     * @Description 购物车查询
     */
    @GetMapping("/find")
    public CommonResult find(@RequestHeader("Authorization") String token){
        //获取用户认证信息
        Long staffId = JwtUtils.getStaffInfo(token);
        //根据用户查询购物车信息
        Cart cart = cartService.findByStaffId(staffId);
        return new CommonResult(CommonStatus.SUCCESS,cart);
    }


    /**
     * @Author lipu
     * @Date 2020/12/24 11:32
     * @Description 添加购物车
     */
    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public CommonResult add(@RequestBody Cart cart,@RequestHeader("Authorization")String token){
        //获取用户认证信息
        Long id = JwtUtils.getStaffInfo(token);
        //设置购物车用户绑定
        cart.setStaffId(id);
        cartService.add(cart);
        return new CommonResult(CommonStatus.SUCCESS,"添加购物车成功");
    }

    /**
     * @Author qgr
     * @Date 2021/1/21 18:59
     * @Description 购物车更新
     */
    @PostMapping("/update")
    @ApiOperation("更新购物车")
    public CommonResult update(@RequestHeader("Authorization")String token,@RequestBody Cart cart){
        //获取用户认证信息
        Long staffId = JwtUtils.getStaffInfo(token);
        //TODO 更新购物车信息
        return null;
    }

    /**
     * @Author lipu
     * @Date 2021/1/21 18:54
     * @Description 清除购物车
     */
    @PostMapping("/delete")
    @ApiOperation("清除购物车")
    public CommonResult delete(@RequestBody Cart cart,@RequestHeader("Authorization")String token){
        //获取用户认证信息
        Long staffId = JwtUtils.getStaffInfo(token);
        cartService.delete(staffId);
        return new CommonResult(CommonStatus.SUCCESS,"清除购物车成功");
    }

}
