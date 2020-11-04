package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.domain.SkuModel;
import com.bird.service.ISkuESService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/11/1 19:15
 * @Description
 */
@RestController
@RequestMapping("/sku/es")
@Api(tags = "ES商品模块接口")
@Slf4j
@Validated
public class SkuESController {
    @Resource
    private ISkuESService skuESService;


    /**
     * @Author lipu
     * @Date 2020/11/1 19:17
     * @Description 添加商品信息到ES索引库
     */
    @PostMapping("/saveAll")
    @ApiOperation("添加商品信息到ES索引库")
    public CommonResult saveAll(@RequestBody List<SkuModel> skuModelList){
        Boolean result = skuESService.saveAll(skuModelList);
        if (result){
            return new CommonResult(CommonStatus.SUCCESS,CommonStatus.SUCCESS.getStatus());
        }else {
            return new CommonResult(CommonStatus.ERROR,CommonStatus.ERROR.getStatus());
        }
    }

    /**
     * @Author lipu
     * @Date 2020/11/3 10:33
     * @Description 移除ES索引库的商品信息
     */
    @DeleteMapping("/deleteBatch")
    @ApiOperation("添加商品信息到ES索引库")
    public CommonResult deleteBatch(@RequestBody List<SkuModel> skuModelList){
        Boolean result = skuESService.deleteBatch(skuModelList);
        if (result){
            return new CommonResult(CommonStatus.SUCCESS,CommonStatus.SUCCESS.getStatus());
        }else {
            return new CommonResult(CommonStatus.ERROR,CommonStatus.ERROR.getStatus());
        }
    }
}
