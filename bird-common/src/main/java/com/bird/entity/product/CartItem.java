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
 * @Date 2020/12/24 11:51
 * @Description
 */
@Data
@TableName("t_cart_item")
@ApiModel(value="购物项实体类")
public class CartItem {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "外键 商品id")
    private Long skuId;
    @ApiModelProperty(value = "价格")
    private BigDecimal price;
    @ApiModelProperty(value = "总价格")
    private BigDecimal totalPrice;
    @ApiModelProperty(value = "数量")
    private Integer count;
    @ApiModelProperty(value = "外键 购物车id")
    private Long cartId;
}
