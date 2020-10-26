package com.bird.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @Author lipu
 * @Date 2020/10/8 19:38
 * @Description 分页类
 */
@Data
public class PageVo implements Serializable {
    @ApiModelProperty(value = "当前页码",required = true)
    @Min(value = 1,message = "页码不能小于0")
    @NotNull(message = "当前页参数不能为空")
    private Integer page;

    @ApiModelProperty(value = "每页大小",required = true)
    @Min(value = 5,message = "每页数据最少不能低于5条")
    @Max(value = 100,message = "每页数据最大不能超过100条")
    @NotNull(message = "每页大小参数不能为空")
    private Integer size;

    @ApiModelProperty(value = "搜索条件")
    @Length(max = 16,message = "搜索字符不能超过16")
    private String key;

    @ApiModelProperty(value = "排序规则 asc或者desc")
    @Pattern(regexp = "^asc|desc$",message = "只能输入asc或者desc")
    private String order;

    @ApiModelProperty(value = "排序字段")
    private String sort;

}
