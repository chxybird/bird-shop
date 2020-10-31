package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.ware.Demand;
import com.bird.entity.ware.Purchase;
import com.bird.service.IDemandService;
import com.bird.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2020/10/26 20:38
 * @Description
 */

@RestController
@RequestMapping("/demand")
@Api(tags = "采购需求模块接口")
@Slf4j
@Validated
public class DemandController {
    @Resource
    private IDemandService demandService;

    /**
     * @Author lipu
     * @Date 2020/10/26 20:40
     * @Description 合并采购需求
     */
    @PostMapping("/mergeToPurchase")
    @ApiOperation("合并采购需求")
    public CommonResult mergeToPurchase(@RequestBody Purchase purchase){
        demandService.mergeToPurchase(purchase);
        return new CommonResult(CommonStatus.SUCCESS,"合并成功");
    }

    /**
     * @Author lipu
     * @Date 2020/10/31 13:08
     * @Description 创建采购需求
     */
    @PostMapping("/add")
    @ApiOperation("创建采购需求")
    public CommonResult add(@RequestBody Demand demand){
        Integer result = demandService.add(demand);
        return Utils.checkResult(result);
    }

}
