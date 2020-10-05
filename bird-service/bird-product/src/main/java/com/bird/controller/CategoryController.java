package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.domain.Category;
import com.bird.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/9/30 17:41
 * @Description
 */
@RestController
@RequestMapping("/category")
@Api(tags = "分类管理")
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
    public CommonResult<List<Category>> findByParentId(Long parentId){
        List<Category> categoryList = categoryService.findByParentId(parentId);
        return new CommonResult<List<Category>>(CommonStatus.SUCCESS,categoryList);
    }
}
