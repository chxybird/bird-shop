package com.bird.entity.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/12/24 11:26
 * @Description
 */
@Data
@TableName("t_cart")
@ApiModel(value="购物车实体类")
public class Cart {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "外键 用户id")
    private Long staffId;

    @TableField(exist = false)
    private List<CartItem> cartItemList;

}
