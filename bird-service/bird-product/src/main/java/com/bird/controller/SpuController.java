package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.PageVo;
import com.bird.entity.product.Spu;
import com.bird.service.ISpuService;
import com.bird.utils.Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/23 14:20
 * @Description
 */
@RestController
@RequestMapping("/spu")
@Api(tags = "抽象商品模块接口")
@Slf4j
@Validated
public class SpuController {
    @Resource
    private ISpuService spuService;

    /**
     * @Author lipu
     * @Date 2020/10/23 14:22
     * @Description 添加商品
     */
    @PostMapping("/add")
    @ApiOperation("添加商品")
    public CommonResult add(@RequestBody Spu spu){
        spuService.add(spu);
        return new CommonResult(CommonStatus.SUCCESS,"添加成功");
    }

    /**
     * @Author lipu
     * @Date 2020/10/25 20:05
     * @Description 查询所有商品信息
     */
    @GetMapping("/findAll")
    @ApiOperation("查询所有商品信息")
    public CommonResult findAll(@Valid PageVo pageVo){
        List<Spu> spuList = spuService.findAll(pageVo);
        return new CommonResult(CommonStatus.SUCCESS,spuList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/25 20:24
     * @Description 商品上架下架
     */
    @PutMapping("/putStatus/{id}")
    @ApiOperation("商品上架下架")
    public CommonResult putStatus(
            @RequestParam("status")
            @Pattern(regexp = "^0|1$",message ="状态只能是0下架和1上架" ) String status,
            @PathVariable Long id)
    {
        int parseIntStatus = Integer.parseInt(status);
        Integer result = spuService.putStatus(parseIntStatus, id);
        return Utils.checkResult(result);
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 9:13
     * @Description 更新商品信息
     */
    @PostMapping("/update")
    @ApiOperation("更新商品信息")
    public CommonResult update(@RequestBody Spu spu){
        Integer result = spuService.update(spu);
        return Utils.checkResult(result);
    }

    /**
     * @Author lipu
     * @Date 2020/10/26 9:13
     * @Description 根据id删除商品信息
     */
    @PostMapping("/deleteById")
    @ApiOperation("根据id删除商品信息")
    public CommonResult deleteById(Long id){
        Integer result = spuService.deleteById(id);
        return Utils.checkResult(result);
    }

}
