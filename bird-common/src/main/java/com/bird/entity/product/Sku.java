package com.bird.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author lipu
 * @Date 2020/10/14 20:22
 * @Description
 */
@Data
@TableName("t_sku")
@ApiModel(value="商品实体类")
public class Sku {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "商品名")
    private String name;
    @ApiModelProperty(value = "描述")
    private String description;
    @ApiModelProperty(value = "图片地址")
    private String img;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "售卖数量")
    private Integer saleCount;
    @ApiModelProperty(value = "所属抽象商品类id")
    private Long spuId;
}
