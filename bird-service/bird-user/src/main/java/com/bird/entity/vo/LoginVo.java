package com.bird.entity.vo;

import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/12/23 16:08
 * @Description
 */
@Data
public class LoginVo {
    private String phone;
    private String username;
    private String password;
    private String email;
}
