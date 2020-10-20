package com.bird.entity.product.relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/20 10:07
 * @Description
 */
@Data
@TableName("t_template_attr")
@ApiModel(value="模板属性关联实体类")
public class TemplateAttr {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "外键 模板id")
    private Long templateId;
    @ApiModelProperty(value = "外键 属性id")
    private Long attrId;

}
