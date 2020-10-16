package com.bird.entity.product.relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/15 14:33
 * @Description
 */
@Data
@TableName("t_brand_category")
@ApiModel(value="品牌分类关联实体类")
public class BrandCategory {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "外键BrandId")
    private Long brandId;
    @ApiModelProperty(value = "外键categoryId")
    private Long categoryId;
}
