package com.bird.entity.product;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author lipu
 * @Date 2020/10/14 20:22
 * @Description
 */
@Data
@TableName("t_spu")
@ApiModel(value="抽象商品实体类")
public class Spu {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "抽象商品名")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "图片地址")
    private String img;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
    @ApiModelProperty(value = "发布状态 0:未发布 1:发布")
    private Integer status;
    @ApiModelProperty(value = "所属品牌id")
    private Long brandId;
    @ApiModelProperty(value = "所属分类id")
    private Long categoryId;
}
