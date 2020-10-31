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
    public CommonResult add(@RequestBody Purchase purchase) {
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
    public CommonResult findAll(@Valid PageVo pageVo) {
        List<Purchase> purchaseList = purchaseService.findAll(pageVo);
        return new CommonResult(CommonStatus.SUCCESS, purchaseList);
    }

    @GetMapping("/selectList")
    @ApiOperation("获取采购单下拉列表")
    public CommonResult selectList() {
        List<Purchase> purchaseList = purchaseService.selectList();
        return new CommonResult(CommonStatus.SUCCESS, purchaseList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/27 10:32
     * @Description 分配采购单给采购人员
     */
    @PutMapping("/distribute")
    @ApiOperation("分配采购单给采购人员")
    public CommonResult distribute(
            @RequestParam("staffId") Long staffId,
            @RequestParam("id") Long id) {
        Integer result = purchaseService.distribute(staffId, id);
        return Utils.checkResult(result);
    }

    /**
     * @Author lipu
     * @Date 2020/10/27 10:47
     * @Description 根据采购人员id查询采购单信息
     */
    @GetMapping("/findByStaffId")
    @ApiOperation("根据采购人员id查询采购单信息")
    public CommonResult findByStaffId(@RequestParam("staffId") Long staffId){
        List<Purchase> purchaseList = purchaseService.findByStaffId(staffId);
        return new CommonResult(CommonStatus.SUCCESS,purchaseList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/31 13:23
     * @Description 领取采购单
     */
    @PutMapping("/accept")
    @ApiOperation("领取采购单")
    public CommonResult accept(@RequestParam("id") Long id){
        purchaseService.accept(id);
        return new CommonResult(CommonStatus.SUCCESS,"领取成功");
    }

    /**
     * @Author lipu
     * @Date 2020/10/31 13:38
     * @Description 完成采购
     */
    @PostMapping("/accept")
    @ApiOperation("完成采购")
    public CommonResult finish(@RequestBody Purchase purchase){
        purchaseService.finish(purchase);
        return new CommonResult(CommonStatus.SUCCESS,"操作成功");
    }


}
