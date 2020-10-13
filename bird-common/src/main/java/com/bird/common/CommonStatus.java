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
    VALIDATE_ERROR(400,"非法参数"),
    AMPLE_FLOW(700,"流控限制"),
    FILE_EMPTY(400,"空文件异常"),
    FILE_NOT_FOUND(404,"文件不存在")
    ;

    private Integer status;
    private String msg;

    CommonStatus(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
