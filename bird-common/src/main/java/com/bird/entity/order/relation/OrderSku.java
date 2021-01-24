package com.bird.entity.order.relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author lipu
 * @Date 2021/1/23 21:52
 * @Description
 */
@Data
@TableName("t_order_sku")
@ApiModel(value="订单商品关联实体类")
public class OrderSku {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "数量")
    private Integer count;
    @ApiModelProperty(value = "外键 订单id")
    private Long orderId;
    @ApiModelProperty(value = "外键 商品id")
    private Long skuId;
    @ApiModelProperty(value = "总价")
    private BigDecimal totalPrice;
    @ApiModelProperty(value = "单价")
    private BigDecimal price;

}
