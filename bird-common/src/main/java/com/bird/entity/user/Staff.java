package com.bird.entity.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/10/26 16:17
 * @Description
 */
@Data
@TableName("t_user")
@ApiModel(value="用户实体类")
public class Staff {

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
    @ApiModelProperty(value = "用户名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "年龄")
    private Integer age;
    @ApiModelProperty(value = "性别")
    private Integer sex;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "状态 0禁用 1开启")
    private Integer status;

    @TableField(exist = false)
    private String statusStr;



    public String getStatusStr() {
        if (status==0){
            return "禁用";
        }else {
            return "开启";
        }
    }
}
