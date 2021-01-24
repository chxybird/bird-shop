package com.bird.entity.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author lipu
 * @Date 2020/10/14 9:42
 * @Description
 */
@Data
@TableName("t_order")
@ApiModel(value="订单实体类")
public class Order {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "订单编号")
    private String orderNumber;
    @ApiModelProperty(value = "收获人姓名")
    private String username;
    @ApiModelProperty(value = "收货地址")
    private String address;
    @ApiModelProperty(value = "收货人手机号")
    private String phone;
    @ApiModelProperty(value = "订单备注")
    private String description;
    @ApiModelProperty(value = "订单状态 0未支付 1已支付")
    private Integer status;
    @ApiModelProperty(value = "订单总价")
    private BigDecimal totalPrice;
    @ApiModelProperty(value = "外键 物流id")
    private Long logisticsId;
    @ApiModelProperty(value = "外键 收货人id")
    private Long staffId;

}
