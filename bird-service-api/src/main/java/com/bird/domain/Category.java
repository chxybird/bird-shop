package com.bird.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author lipu
 * @Date 2020/9/30 17:00
 * @Description 分类实体类
 */
@Data
@TableName("t_category")
@ApiModel(value="分类实体类")
public class Category implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "分类id")
    private Long id;
    @ApiModelProperty(value = "分类名")
    private String name;
    @ApiModelProperty(value = "分类图片地址")
    private String icon;
    @ApiModelProperty(value = "产品数量")
    private Integer productCount;
    @ApiModelProperty(value = "父分类id")
    private Integer parentId;
    @ApiModelProperty(value = "计量单位")
    private Integer productUnit;
}
