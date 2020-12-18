package com.bird.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/19 21:48
 * @Description
 */
@Data
@TableName("t_attr")
@ApiModel(value="属性实体类")
public class Attr {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "属性名")
    private String name;
    @ApiModelProperty(value = "属性类型 1基本属性 2销售属性")
    private Integer type;
    @ApiModelProperty(value = "是否检索 0否 1是")
    private Integer isSearch;
}
