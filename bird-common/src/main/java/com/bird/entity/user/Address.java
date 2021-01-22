package com.bird.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2021/1/22 13:33
 * @Description
 */
@Data
@TableName("t_address")
@ApiModel(value="收获地址实体类")
public class Address {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "收货地址")
    private String address;
    @ApiModelProperty(value = "外键 用户id")
    private Long staffId;
}
