package com.bird.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/26 17:01
 * @Description
 */
@Data
@TableName("t_role")
@ApiModel(value="角色实体类")
public class Role {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "角色名")
    private String roleName;
    @ApiModelProperty(value = "描述")
    private String description;
}
