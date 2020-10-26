package com.bird.entity.user.relation;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/26 17:03
 * @Description
 */
@Data
@TableName("t_user_role")
@ApiModel(value="用户角色关联实体类")
public class StaffRole {
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "外键 用户id")
    private Long userId;
    @ApiModelProperty(value = "外键 角色id")
    private Long roleId;
}
