package com.bird.entity.product.relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/20 10:01
 * @Description
 */
@Data
@TableName("t_sku_attr_value")
@ApiModel(value="商品销售属性值实体类")
public class SkuAttrValue {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "属性名")
    private String attrName;
    @ApiModelProperty(value = "属性值")
    private String attrValue;
    @ApiModelProperty(value = "外键 商品id")
    private Long skuId;
    @ApiModelProperty(value = "外键 属性id")
    private Long attrId;
}
