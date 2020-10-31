package com.bird.entity.ware.relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/31 14:24
 * @Description
 */
@Data
@TableName("t_ware_sku")
@ApiModel(value="商品仓库关联实体类")
public class WareSku {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "库存量")
    private Integer stock;
    @ApiModelProperty(value = "锁定数量")
    private Integer lock;
    @ApiModelProperty(value = "商品名称")
    private String skuName;
    @ApiModelProperty(value = "外键 仓库id")
    private Long wareId;
    @ApiModelProperty(value = "外键 商品id")
    private Long skuId;
}
