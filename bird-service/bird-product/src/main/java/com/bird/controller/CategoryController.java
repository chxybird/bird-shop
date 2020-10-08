package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.domain.Category;
import com.bird.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/9/30 17:41
 * @Description
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类管理")
@Slf4j
@Validated
public class CategoryController {
    @Resource
    private ICategoryService categoryService;

    /**
     * @Author lipu
     * @Date 2020/9/30 17:42
     * @Description 根据父id查询分类信息
     */
    @ApiOperation("根据父id查询分类信息")
    @GetMapping("/findByParentId")
    public CommonResult<List<Category>> findByParentId(
            @RequestParam("parentId")
            @NotBlank(message = "id不能为空")Long parentId) {
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
    public CommonResult<Category> findById(
            @RequestParam("id")
            @NotBlank(message = "id不能为空")Long id) {
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
    public CommonResult<String> add(@RequestBody Category category) {
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
    public CommonResult<String> update(@RequestBody Category category) {
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
    public CommonResult<String> deleteById(
            @RequestParam("id")
            @NotNull(message = "id不能为空")Long id){
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
    public CommonResult<String> deleteBatch(@RequestBody List<Category> categoryList){
        categoryService.deleteBatch(categoryList);
        return new CommonResult<String>(CommonStatus.SUCCESS,"分类批量删除成功");
    }

}
