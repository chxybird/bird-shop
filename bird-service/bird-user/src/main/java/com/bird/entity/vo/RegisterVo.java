package com.bird.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/12/23 15:36
 * @Description 用户注册vo类
 */
@Data
public class RegisterVo {
    @ApiModelProperty("短信验证码")
    private String code;
    @ApiModelProperty("用户姓名")
    private String username;
    @ApiModelProperty("密码")
    private String password;
    @ApiModelProperty("年龄")
    private Integer age;
    @ApiModelProperty("性别 0女 1男")
    private Integer sex;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("邮箱")
    private String email;
}
