package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.PageVo;
import com.bird.entity.product.Template;
import com.bird.service.ITemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public CommonResult update(Template template){
        Integer result = templateService.update(template);
        if (result>0){
            return new CommonResult<String>(CommonStatus.SUCCESS,"更新模板成功");
        }else {
            return new CommonResult<String>(CommonStatus.ERROR,"更新模板失败");
        }
    }

}
