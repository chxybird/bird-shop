package com.bird.controller;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import com.bird.entity.PageVo;
import com.bird.entity.product.Attr;
import com.bird.service.IAttrService;
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
            @Valid PageVo pageVo)
    {
        List<Attr> attrList = attrService.findByTemplateId(templateId, pageVo);
        return new CommonResult<List<Attr>>(CommonStatus.SUCCESS,attrList);
    }

    /**
     * @Author lipu
     * @Date 2020/10/21 21:35
     * @Description 根据模板id查询属性信息
     */
    @GetMapping("/findByTemplateIdWithout")
    @ApiOperation("根据模板id查询属信息(without)")
    public CommonResult findByTemplateIdWithout(@RequestParam("templateId") Long templateId){
        List<Attr> attrList = attrService.findByTemplateIdWithout(templateId);
        return new CommonResult<List<Attr>>(CommonStatus.SUCCESS,attrList);
    }
}
