package com.bird.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/15 9:24
 * @Description
 */
@Data
@TableName("t_template")
@ApiModel(value="模板实体类")
public class Template {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "模板id")
    private Long id;
    @ApiModelProperty(value = "模板名")
    private String name;
    @ApiModelProperty(value = "图标")
    private String icon;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "排序值")
    private Integer sort;
}
