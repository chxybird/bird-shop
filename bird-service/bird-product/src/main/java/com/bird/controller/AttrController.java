package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.PageVo;
import com.bird.entity.product.Attr;
import com.bird.entity.product.relation.TemplateAttr;
import com.bird.service.IAttrService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/21 20:12
 * @Description
 */
@RestController
@RequestMapping("/attr")
@Api(tags = "属性模块接口")
@Slf4j
@Validated
public class AttrController {
    @Resource
    private IAttrService attrService;

    /**
     * @Author lipu
     * @Date 2020/10/21 20:13
     * @Description 根据模板id查询属信息
     */
    @GetMapping("/findByTemplateId")
    @ApiOperation("根据模板id查询属信息")
    public CommonResult findByTemplateId(
            @RequestParam("templateId") Long templateId,
            @Valid PageVo pageVo) {
        List<Attr> attrList = attrService.findByTemplateId(templateId, pageVo);
        return new CommonResult<List<Attr>>(CommonStatus.SUCCESS, attrList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 21:35
     * @Description 根据模板id查询属性信息
     */
    @GetMapping("/findByTemplateIdWithout")
    @ApiOperation("根据模板id查询属信息(without)")
    public CommonResult findByTemplateIdWithout(@RequestParam("templateId") Long templateId) {
        List<Attr> attrList = attrService.findByTemplateIdWithout(templateId);
        return new CommonResult<List<Attr>>(CommonStatus.SUCCESS, attrList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 17:05
     * @Description 查询所有属性信息 type 1基本属性 2销售属性 0查询所有
     */
    @GetMapping("/findAll/{type}")
    @ApiOperation("查询所有属性信息 type 1基本属性 2销售属性 0查询所有")
    public CommonResult findAll(@Valid PageVo pageVo, @PathVariable("type") Integer type) {
        List<Attr> attrList = attrService.findAll(pageVo, type);
        return new CommonResult<List<Attr>>(CommonStatus.SUCCESS, attrList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 17:39
     * @Description 添加属性
     */
    @PostMapping("/add")
    @ApiOperation("添加属性信息")
    public CommonResult add(@RequestBody Attr attr, @RequestParam("idList") List<Long> idList) {
        attrService.add(attr, idList);
        return new CommonResult(CommonStatus.SUCCESS, "添加属性成功");
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 19:54
     * @Description 删除属性信息
     */
    @DeleteMapping("/delete")
    @ApiOperation("删除属性信息")
    public CommonResult deleteById(@RequestParam("id") Long id) {
        Integer result = attrService.deleteById(id);
        if (result > 0) {
            return new CommonResult(CommonStatus.SUCCESS, "删除属性成功");
        } else {
            return new CommonResult(CommonStatus.ERROR, "删除属性失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:37
     * @Description 更新属性信息
     */
    @PostMapping("/update")
    @ApiOperation("更新属性信息")
    public CommonResult update(@RequestBody Attr attr){
        Integer result = attrService.update(attr);
        if (result > 0) {
            return new CommonResult(CommonStatus.SUCCESS, "更新属性成功");
        } else {
            return new CommonResult(CommonStatus.ERROR, "更新属性失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:45
     * @Description 添加属性模板关联
     */
    @PostMapping("/addToTemplate")
    @ApiOperation("添加属性模板关联")
    public CommonResult addToTemplate(@RequestBody TemplateAttr templateAttr){
        Integer result = attrService.addTemplate(templateAttr);
        if (result > 0) {
            return new CommonResult(CommonStatus.SUCCESS, "添加属性模板关联成功");
        } else {
            return new CommonResult(CommonStatus.ERROR, "添加属性模板关联失败");
        }
    }

    /**
     * @Author lipu
     * @Date 2020/10/22 20:46
     * @Description 删除属性模板关联
     */
    @DeleteMapping("/removeTemplate")
    @ApiOperation("删除属性模板关联")
    public CommonResult removeTemplate(TemplateAttr templateAttr){
        Integer result = attrService.removeTemplate(templateAttr);
        if (result > 0) {
            return new CommonResult(CommonStatus.SUCCESS, "删除属性模板关联成功");
        } else {
            return new CommonResult(CommonStatus.ERROR, "删除属性模板关联失败");
        }
    }
}
