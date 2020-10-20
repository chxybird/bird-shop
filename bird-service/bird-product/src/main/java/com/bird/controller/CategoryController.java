package com.bird.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.PageVo;
import com.bird.entity.product.Category;
import com.bird.service.ICategoryService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/9/30 17:41
 * @Description
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类模块接口")
@Slf4j
@Validated
public class CategoryController {
    @Resource
    private ICategoryService categoryService;
    @Value("${server.port}")
    private Integer port;

    /**
     * @Author lipu
     * @Date 2020/10/14 9:45
     * @Description Feign和Ribbon的测试接口
     */
    @GetMapping("/find")
    @ApiOperation("远程调用测试接口")
    public CommonResult find() {
        return new CommonResult<Integer>(CommonStatus.SUCCESS, port);
    }

    /**
     * @Author lipu
     * @Date 2020/9/30 17:42
     * @Description 根据父id查询分类信息
     */
    @ApiOperation("根据父id查询分类信息")
    @GetMapping("/findByParentId")
    @SentinelResource(
            value = "categoryFindByParentId",
            defaultFallback = "defaultFallback"
    )
    public CommonResult findByParentId(
            @RequestParam("parentId")
            @NotNull(message = "id不能为空") Long parentId) {
        List<Category> categoryList = categoryService.findByParentId(parentId);
        return new CommonResult<List<Category>>(CommonStatus.SUCCESS, categoryList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 13:39
     * @Description 根据id查询分类信息
     */
    @ApiOperation("根据主键查询分类信息")
    @GetMapping("/findById")
    @SentinelResource(
            value = "categoryFindById",
            defaultFallback = "defaultFallback"
    )
    public CommonResult findById(
            @RequestParam("id")
            @NotNull(message = "id不能为空") Long id) {
        Category category = categoryService.findById(id);
        return new CommonResult<Category>(CommonStatus.SUCCESS, category);
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 14:54
     * @Description 添加一条分类
     */
    @ApiOperation("添加一条分类")
    @PostMapping("/add")
    @SentinelResource(
            value = "categoryAdd",
            defaultFallback = "defaultFallback"
    )
    public CommonResult add(@RequestBody Category category) {
        Integer result = categoryService.add(category);
        if (result != 0) {
            return new CommonResult<String>(CommonStatus.SUCCESS, "分类添加成功");
        } else {
            log.info("分类添加失败");
            return new CommonResult<String>(CommonStatus.ERROR, "分类添加失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 17:26
     * @Description 更新分类信息
     */
    @ApiOperation("更新分类信息")
    @PostMapping("/update")
    @SentinelResource(
            value = "categoryUpdate",
            defaultFallback = "defaultFallback"
    )
    public CommonResult update(@RequestBody Category category) {
        Integer result = categoryService.update(category);
        if (result != 0) {
            return new CommonResult<String>(CommonStatus.SUCCESS, "分类更新成功");
        } else {
            log.info("分类更新失败");
            return new CommonResult<String>(CommonStatus.ERROR, "分类更新失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 17:26
     * @Description 删除分类信息
     */
    @ApiOperation("删除分类信息")
    @DeleteMapping("/deleteById")
    @SentinelResource(
            value = "categoryDeleteById",
            defaultFallback = "defaultFallback"
    )
    public CommonResult deleteById(
            @RequestParam("id")
            @NotNull(message = "id不能为空") Long id) {
        Integer result = categoryService.deleteById(id);
        if (result != 0) {
            return new CommonResult<String>(CommonStatus.SUCCESS, "分类删除成功");
        } else {
            log.info("分类删除失败");
            return new CommonResult<String>(CommonStatus.ERROR, "分类删除失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/8 20:08
     * @Description 批量删除分类信息
     */
    @ApiOperation("批量删除分类信息")
    @DeleteMapping("/deleteBatch")
    @SentinelResource(
            value = "categoryDeleteBatch",
            defaultFallback = "defaultFallback"
    )
    public CommonResult deleteBatch(@RequestBody List<Category> categoryList) {
        categoryService.deleteBatch(categoryList);
        return new CommonResult<String>(CommonStatus.SUCCESS, "分类批量删除成功");
    }

    /**
     * @Author lipu
     * @Date 2020/10/20 20:14
     * @Description 根据模板id查询关联的分类信息
     */
    @ApiOperation("根据模板id查询分类信息")
    @GetMapping("/findByTemplateId")
    public CommonResult findByTemplateId(Long templateId,@Valid PageVo pageVo){
        List<Category> categoryList = categoryService.findByTemplateId(templateId, pageVo);
        return new CommonResult<List<Category>>(CommonStatus.SUCCESS,categoryList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/20 20:41
     * @Description 根据模板id查询该模板没有关联的分类信息
     */
    @ApiOperation("根据模板id查询分类信息")
    @GetMapping("/findByTemplateIdWithout")
    public CommonResult findByTemplateIdWithout(@RequestParam("templateId") Long templateId,@RequestParam("parentId") Long parentId){
        List<Category> categoryList = categoryService.findByTemplateIdWithout(templateId, parentId);
        return new CommonResult<List<Category>>(CommonStatus.SUCCESS,categoryList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/9 20:25
     * @Description 通用降级方法
     */
    public CommonResult defaultFallback(Throwable throwable) {
        log.warn("商品微服务分类模块已被降级处理");
        return new CommonResult<String>(CommonStatus.AMPLE_FLOW, "服务器正忙");
    }

}
