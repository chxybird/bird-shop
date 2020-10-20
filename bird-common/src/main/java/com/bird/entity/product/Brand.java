package com.bird.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @Author lipu
 * @Date 2020/10/10 14:04
 * @Description 品牌实体类
 */
@Data
@TableName("t_brand")
@ApiModel(value="品牌实体类")
public class Brand implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "品牌名")
    private String name;
    @ApiModelProperty(value = "品牌图标")
    private String icon;
    @ApiModelProperty(value = "品牌首字母")
    private String letter;
    @ApiModelProperty(value = "排序值")
    private Integer sort;
}
