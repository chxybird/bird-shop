package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.product.Brand;
import com.bird.entity.PageVo;
import com.bird.entity.product.Category;
import com.bird.entity.product.relation.BrandCategory;
import com.bird.service.IBrandService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
 * @Date 2020/10/14 14:49
 * @Description
 */
@RestController
@RequestMapping("/brand")
@Api(tags = "品牌模块接口")
@Slf4j
@Validated
@DefaultProperties(defaultFallback = "hystrixFallback")
public class BrandController {

    @Resource
    private IBrandService brandService;

    /**
     * @Author lipu
     * @Date 2020/10/14 14:51
     * @Description 查询所有品牌信息
     */
    @GetMapping("/findAll")
    @ApiOperation("查询所有品牌信息")
    @HystrixCommand
    public CommonResult findAll(@Valid PageVo pageVo) throws Exception {
        List<Brand> brandList = brandService.findAll(pageVo);
        CommonResult<List<Brand>> commonResult = new CommonResult<>(CommonStatus.SUCCESS, brandList);
        return commonResult;
    }

    /**
     * @Author lipu
     * @Date 2020/10/14 15:47
     * @Description 根据id查询品牌信息
     */
    @GetMapping("/findById")
    @ApiOperation("根据id查询品牌信息")
    @HystrixCommand
    public CommonResult findById(@RequestParam("id") Long id) throws Exception {
        Brand brand = brandService.findById(id);
        return new CommonResult<Brand>(CommonStatus.SUCCESS, brand);
    }

    /**
     * @Author lipu
     * @Date 2020/10/14 16:06
     * @Description 根据id删除品牌信息
     */
    @DeleteMapping("/deleteById")
    @ApiOperation("根据id删除品牌信息")
    @HystrixCommand
    public CommonResult deleteById(Long id) throws Exception {
        Integer result = brandService.deleteById(id);
        if (result > 0) {
            return new CommonResult<String>(CommonStatus.SUCCESS, "品牌删除成功");
        } else {
            return new CommonResult<String>(CommonStatus.ERROR, "品牌删除失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/14 16:06
     * @Description 添加一条品牌信息
     */
    @PostMapping("/add")
    @ApiOperation("添加一条品牌信息")
    @HystrixCommand
    public CommonResult add(@RequestBody @Valid Brand brand) throws Exception {
        Integer result = brandService.add(brand);
        if (result > 0) {
            return new CommonResult<String>(CommonStatus.SUCCESS, "添加品牌成功");
        } else {
            return new CommonResult<String>(CommonStatus.ERROR, "添加品牌失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/14 16:10
     * @Description 更新品牌信息
     */
    @PostMapping("/update")
    @ApiOperation("更新品牌信息")
    @HystrixCommand
    public CommonResult update(@RequestBody Brand brand) throws Exception {
        Integer result = brandService.update(brand);
        if (result > 0) {
            return new CommonResult<String>(CommonStatus.SUCCESS, "更新品牌成功");
        } else {
            return new CommonResult<String>(CommonStatus.ERROR, "更新品牌失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/14 16:11
     * @Description 批量删除品牌信息
     */
    @DeleteMapping("/deleteBatch")
    @ApiOperation("批量删除品牌信息")
    @HystrixCommand
    public CommonResult deleteBatch(List<Brand> brandList) throws Exception {
        brandService.deleteBatch(brandList);
        return new CommonResult<String>(CommonStatus.SUCCESS, "批量删除品牌成功");
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 14:03
     * @Description 查询品牌关联的所有分类信息
     */
    @GetMapping("/findCategory")
    @ApiOperation("根据品牌id查询分类信息")
    @HystrixCommand
    public CommonResult findCategory(@RequestParam("brandId") Long brandId){
        List<Category> categoryList = brandService.findCategory(brandId);
        return new CommonResult<List<Category>>(CommonStatus.SUCCESS,categoryList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/16 9:26
     * @Description 新增品牌分类关联
     */
    @PostMapping("/addToCategory")
    @ApiOperation("新增品牌分类关联")
    @HystrixCommand
    public CommonResult addToCategory(@RequestBody BrandCategory brandCategory){
        Integer result = brandService.addToCategory(brandCategory);
        if (result > 0) {
            return new CommonResult<String>(CommonStatus.SUCCESS, "关联成功");
        } else {
            return new CommonResult<String>(CommonStatus.ERROR, "关联失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/16 9:48
     * @Description 移除品牌分类关联
     */
    @DeleteMapping("/removeCategory")
    @ApiOperation("移除品牌分类关联")
    @HystrixCommand
    public CommonResult removeCategory(BrandCategory brandCategory){
        Integer result = brandService.removeCategory(brandCategory);
        if (result > 0) {
            return new CommonResult<String>(CommonStatus.SUCCESS, "移除成功");
        } else {
            return new CommonResult<String>(CommonStatus.ERROR, "移除失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/23 9:39
     * @Description 根据分类id查询关联的品牌信息
     */
    @GetMapping("/findByCategoryId")
    @ApiOperation("根据分类id查询关联的品牌信息")
    @HystrixCommand
    public CommonResult findByCategoryId(@RequestParam("categoryId") Long categoryId){
        List<Brand> brandList = brandService.findByCategoryId(categoryId);
        return new CommonResult(CommonStatus.SUCCESS,brandList);
    }


    /**
     * @Author lipu
     * @Date 2020/10/14 18:59
     * @Description Hystrix通用降级方法
     */
    public CommonResult<String> hystrixFallback() throws Exception {
        log.warn("商品微服务品牌模块已被降级处理");
        return new CommonResult<String>(CommonStatus.AMPLE_FLOW, "服务器正忙");
    }
}
