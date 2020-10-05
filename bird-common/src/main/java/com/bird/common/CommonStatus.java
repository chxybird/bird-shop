package com.bird.common;

import lombok.Data;

/**
 * @Author lipu
 * @Date 2020/9/30 17:45
 * @Description 通用状态码
 */
public enum CommonStatus {
    SUCCESS(200,"成功"),
    ERROR(500,"失败"),
    VALIDATE_ERROR(400,"非法参数")
    ;

    private Integer status;
    private String msg;

    CommonStatus(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
