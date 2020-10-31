package com.bird.entity.ware;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author lipu
 * @Date 2020/10/26 15:17
 * @Description
 */
@Data
@TableName("t_purchase")
@ApiModel(value="采购单实体类")
public class Purchase {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "采购单状态 1未分配 2已分配 3已领取 4已完成 5异常")
    private Integer status;
    @ApiModelProperty(value = "采购价格")
    private BigDecimal price;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "更新时间")
    private Date gmtModified;
    @ApiModelProperty(value = "外键 采购人id")
    private Long staffId;


    @TableField(exist = false)
    @ApiModelProperty(value = "采购员姓名",hidden = true)
    private String staffName;
    @TableField(exist = false)
    @ApiModelProperty(value = "状态名",hidden = true)
    private String statusStr;

    @TableField(exist = false)
    @ApiModelProperty(hidden = true)
    private List<Demand> demandList;

    public String getStatusStr() {
        if (status==1){
            return "未分配";
        }else if (status==2){
            return "已分配";
        }else if (status==3){
            return "已领取";
        }else if (status==4){
            return "已完成";
        }else {
            return "异常";
        }
    }
}
