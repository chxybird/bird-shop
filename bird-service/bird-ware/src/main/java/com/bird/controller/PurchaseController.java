package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.PageVo;
import com.bird.entity.ware.Purchase;
import com.bird.service.IPurchaseService;
import com.bird.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/26 15:51
 * @Description
 */
@RestController
@RequestMapping("/purchase")
@Api(tags = "采购单模块接口")
@Slf4j
@Validated
public class PurchaseController {

    @Resource
    private IPurchaseService purchaseService;

    /**
     * @Author lipu
     * @Date 2020/10/26 15:53
     * @Description 新建采购单
     */
    @PostMapping("/add")
    @ApiOperation("新建采购单")
    public CommonResult add(@RequestBody Purchase purchase){
        Integer result = purchaseService.add(purchase);
        return Utils.checkResult(result);
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 16:02
     * @Description 查询所有采购单信息
     */
    @GetMapping("/findAll")
    @ApiOperation("查询所有采购单信息")
    public CommonResult findAll(@Valid PageVo pageVo){
        List<Purchase> purchaseList = purchaseService.findAll(pageVo);
        return new CommonResult(CommonStatus.SUCCESS,purchaseList);
    }

    @GetMapping("/selectList")
    @ApiOperation("获取采购单下拉列表")
    public CommonResult selectList(){
        List<Purchase> purchaseList = purchaseService.selectList();
        return new CommonResult(CommonStatus.SUCCESS,purchaseList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 20:40
     * @Description 合并采购需求
     */
    @PostMapping("/mergePurchase")
    @ApiOperation("合并采购需求")
    public CommonResult mergePurchase(@RequestBody Purchase purchase){
        purchaseService.mergePurchase(purchase);
        return new CommonResult(CommonStatus.SUCCESS,"合并成功");
    }

}
