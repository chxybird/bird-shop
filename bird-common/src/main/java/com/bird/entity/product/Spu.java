package com.bird.entity.product;

import com.baomidou.mybatisplus.annotation.*;
import com.bird.entity.product.relation.SpuAttrValue;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/14 20:22
 * @Description
 */
@Data
@TableName("t_spu")
@ApiModel(value = "抽象商品实体类")
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    @ApiModelProperty(value = "发布状态 0:未发布(未上架) 1:发布(上架)")
    private Integer status;
    @ApiModelProperty(value = "所属品牌id")
    private Long brandId;
    @ApiModelProperty(value = "所属分类id")
    private Long categoryId;
    @ApiModelProperty(value = "重量")
    private Double weight;
    @ApiModelProperty(value = "积分")
    private Integer integral;


    @TableField(exist = false)
    private String statusStr;
    @TableField(exist = false)
    private String brandName;
    @TableField(exist = false)
    private String categoryName;

    @TableField(exist = false)
    private Brand brand;
    @TableField(exist = false)
    private Category category;

    @TableField(exist = false)
    private List<SpuAttrValue> spuAttrValueList;
    @TableField(exist = false)
    private List<Attr> attrList;
    @TableField(exist = false)
    private List<Sku> skuList;

    public String getStatusStr() {
        if (status == 0) {
            return "下架";
        }else {
            return "上架";
        }
    }
}
