package com.bird.entity.ware;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/26 15:26
 * @Description
 */
@Data
@TableName("t_demand")
@ApiModel(value="采购需求实体类")
public class Demand {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "状态 1未采购 2正在采购 3采购完成 4采购失败")
    private Integer status;
    @ApiModelProperty(value = "采购数量")
    private Integer num;
    @ApiModelProperty(value = "外键 采购单id")
    private Long purchaseId;
    @ApiModelProperty(value = "外键 商品id")
    private Long skuId;
    @ApiModelProperty(value = "外键 仓库id")
    private Long wareId;

    @TableField(exist = false)
    private String statusStr;

    public String getStatusStr() {
        if (status==1){
            return "未采购";
        }else if (status==2){
            return "正在采购";
        }else if (status==3){
            return "采购完成";
        }else if (status==4){
            return "采购失败";
        }
        return null;
    }
}
