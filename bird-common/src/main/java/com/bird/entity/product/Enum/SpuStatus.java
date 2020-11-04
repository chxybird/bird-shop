package com.bird.entity.product.Enum;

/**
 * @Author lipu
 * @Date 2020/11/3 10:13
 * @Description
 */
public enum SpuStatus {
    PUBLISH(1,"已发布(上架)"),
    NOT_PUBLISH(0,"未发布(下架)")
    ;

    private Integer status;
    private String msg;

    SpuStatus(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }
}
