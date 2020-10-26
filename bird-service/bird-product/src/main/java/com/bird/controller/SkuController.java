package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.PageVo;
import com.bird.entity.product.Sku;
import com.bird.service.ISkuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/26 9:23
 * @Description
 */
@RestController
@RequestMapping("/sku")
@Api(tags = "商品模块接口")
@Slf4j
@Validated
public class SkuController {
    @Resource
    private ISkuService skuService;


    /**
     * @Author lipu
     * @Date 2020/10/26 10:20
     * @Description 查询所有商品信息
     */
    @ApiOperation("查询所有商品信息")
    @GetMapping("/findAll")
    public CommonResult findAll(PageVo pageVo){
        List<Sku> skuList = skuService.findAll(pageVo);
        return new CommonResult(CommonStatus.SUCCESS,skuList);
    }
}
