package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.PageVo;
import com.bird.entity.ware.Ware;
import com.bird.service.IWareService;
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
 * @Date 2020/10/26 11:18
 * @Description
 */
@RestController
@RequestMapping("/ware")
@Api(tags = "仓库模块接口")
@Slf4j
@Validated
public class WareController {
    @Resource
    private IWareService wareService;

    /**
     * @Author lipu
     * @Date 2020/10/26 11:21
     * @Description 添加仓库
     */
    @PostMapping("/add")
    @ApiOperation("添加仓库")
    public CommonResult add(@RequestBody Ware ware) {
        Integer result = wareService.add(ware);
        return Utils.checkResult(result);
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 14:09
     * @Description 查询仓库信息
     */
    @GetMapping("/findAll")
    @ApiOperation("查询仓库信息")
    public CommonResult findAll(@Valid PageVo pageVo) {
        List<Ware> wareList = wareService.findAll(pageVo);
        return new CommonResult(CommonStatus.SUCCESS, wareList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 14:19
     * @Description 获取仓库下拉列表
     */
    @GetMapping("/selectList")
    @ApiOperation("获取仓库下拉列表")
    public CommonResult selectList() {
        List<Ware> wareList = wareService.selectList();
        return new CommonResult(CommonStatus.SUCCESS, wareList);
    }

    /**
     * @Author lipu
     * @Date 2021/1/24 20:19
     * @Description 批量查询商品库存信息
     */
    @GetMapping("/findSkuWare")
    @ApiOperation("批量查询库存信息")
    public CommonResult findSkuWare(List<Long> idList){
        return null;
    }


}
