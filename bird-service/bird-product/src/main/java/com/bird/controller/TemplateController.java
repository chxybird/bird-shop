package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.PageVo;
import com.bird.entity.product.Category;
import com.bird.entity.product.Template;
import com.bird.entity.product.relation.CategoryTemplate;
import com.bird.entity.product.relation.TemplateAttr;
import com.bird.service.ITemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/15 9:29
 * @Description
 */
@RestController
@RequestMapping("/template")
@Api(tags = "模板模块接口")
@Slf4j
@Validated
public class TemplateController {

    @Resource
    private ITemplateService templateService;
    
    /**
     * @Author lipu
     * @Date 2020/10/22 17:49
     * @Description 查询所有模板信息
     */
    @GetMapping("/findAll")
    @ApiOperation("根据分类查询模板信息")
    public CommonResult findAll(@Valid PageVo pageVo){
        List<Template> templateList = templateService.findAll(pageVo);
        return new CommonResult<List<Template>>(CommonStatus.SUCCESS,templateList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 9:42
     * @Description 根据分类查询模板信息
     */
    @GetMapping("/findByCategoryId")
    @ApiOperation("根据分类查询模板信息")
    public CommonResult findByCategoryId(@RequestParam("categoryId") Long categoryId){
        List<Template> templateList = templateService.findByCategoryId(categoryId);
        return new CommonResult<List<Template>>(CommonStatus.SUCCESS,templateList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 10:14
     * @Description 添加模板
     */
    @PostMapping("/add")
    @ApiOperation("添加模板")
    public CommonResult add(@RequestBody Template template){
        Integer result = templateService.add(template);
        if (result>0){
            return new CommonResult<String>(CommonStatus.SUCCESS,"添加模板成功");
        }else {
            return new CommonResult<String>(CommonStatus.ERROR,"添加模板失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 10:24
     * @Description 根据id删除模板
     */
    @DeleteMapping("/deleteById")
    @ApiOperation("根据id删除模板")
    public CommonResult deleteById(@RequestParam("id") @NotNull(message = "id不能为空") Long id){
        Integer result = templateService.deleteById(id);
        if (result>0){
            return new CommonResult<String>(CommonStatus.SUCCESS,"删除模板成功");
        }else {
            return new CommonResult<String>(CommonStatus.ERROR,"删除模板失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/15 10:27
     * @Description 更新模板
     */
    @PostMapping("/update")
    @ApiOperation("更新模板")
    public CommonResult update(Template template){
        Integer result = templateService.update(template);
        if (result>0){
            return new CommonResult<String>(CommonStatus.SUCCESS,"更新模板成功");
        }else {
            return new CommonResult<String>(CommonStatus.ERROR,"更新模板失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 19:45
     * @Description 添加模板分类关联
     */
    @PostMapping("/addToCategory")
    @ApiOperation("添加模板分类关联")
    public CommonResult addToCategory(@RequestBody CategoryTemplate categoryTemplate){
        Integer result = templateService.addToCategory(categoryTemplate);
        if (result>0){
            return new CommonResult<String>(CommonStatus.SUCCESS,"添加模板分类关联成功");
        }else {
            return new CommonResult<String>(CommonStatus.ERROR,"添加模板分类关联失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 19:48
     * @Description 删除模板分类关联
     */
    @DeleteMapping("/removeCategory")
    @ApiOperation("删除模板分类关联")
    public CommonResult removeCategory(CategoryTemplate categoryTemplate){
        Integer result = templateService.removeCategory(categoryTemplate);
        if (result>0){
            return new CommonResult<String>(CommonStatus.SUCCESS,"移除模板分类关联成功");
        }else {
            return new CommonResult<String>(CommonStatus.ERROR,"移除模板分类关联失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 21:35
     * @Description 添加模板属性关联
     */
    @PostMapping("/addToAttr")
    @ApiOperation("添加模板属性关联")
    public CommonResult addToAttr(@RequestBody TemplateAttr templateAttr){
        Integer result = templateService.addToAttr(templateAttr);
        if (result>0){
            return new CommonResult<String>(CommonStatus.SUCCESS,"添加模板属性关联成功");
        }else {
            return new CommonResult<String>(CommonStatus.ERROR,"添加模板属性关联失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 21:35
     * @Description 删除模板属性关联
     */
    @DeleteMapping("/removeAttr")
    @ApiOperation("删除模板属性关联")
    public CommonResult removeAttr(TemplateAttr templateAttr){
        Integer result = templateService.removeAttr(templateAttr);
        if (result>0){
            return new CommonResult<String>(CommonStatus.SUCCESS,"移除模板属性关联成功");
        }else {
            return new CommonResult<String>(CommonStatus.ERROR,"移除模板属性关联失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:04
     * @Description 根据属性查询已关联的模板信息
     */
    @GetMapping("/findByAttrId")
    @ApiOperation("根据属性id查询模板信息")
    public CommonResult findByAttrId(@RequestParam("attrId") Long attrId,@Valid PageVo pageVo){
        List<Template> templateList = templateService.findByAttrId(attrId,pageVo);
        return new CommonResult<List<Template>>(CommonStatus.SUCCESS,templateList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:12
     * @Description  根据属性查询未关联的模板信息
     */
    @ApiOperation("根据属性id查询模板信息(without)")
    @GetMapping("/findByAttrIdWithout")
    public CommonResult findByAttrIdWithout(@RequestParam("attrId") Long attrId){
        List<Template> templateList = templateService.findByAttrIdWithout(attrId);
        return new CommonResult<List<Template>>(CommonStatus.SUCCESS,templateList);
    }


}
