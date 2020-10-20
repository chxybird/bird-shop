package com.bird.entity.product.relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/20 9:24
 * @Description
 */
@Data
@TableName("t_category_template")
@ApiModel(value="分类模板关联实体类")
public class CategoryTemplate {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "外键 分类id")
    private Long categoryId;
    @ApiModelProperty(value = "外键 模板id")
    private Long templateId;
}
