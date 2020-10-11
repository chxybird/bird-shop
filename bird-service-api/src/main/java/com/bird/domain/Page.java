package com.bird.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author lipu
 * @Date 2020/10/8 19:38
 * @Description 分页类
 */
@Data
public class Page implements Serializable {
    @ApiModelProperty(value = "当前页码")
    @Min(value = 1,message = "页码不能小于0")
    @NotNull(message = "当前页参数不能为空")
    private Integer page;

    @ApiModelProperty(value = "每页大小")
    @Min(value = 5,message = "每页数据最少不能低于5条")
    @NotNull(message = "每页大小参数不能为空")
    private Integer size;

    @ApiModelProperty(value = "搜索条件")
    private String key;

}
