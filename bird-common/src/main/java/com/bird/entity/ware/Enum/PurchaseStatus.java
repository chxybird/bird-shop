package com.bird.entity.ware.Enum;

/**
 * @Author lipu
 * @Date 2020/10/31 14:10
 * @Description
 */
public enum PurchaseStatus {
    NOT_DISTRIBUTE(1,"未分配"),
    DISTRIBUTE(2,"已分配"),
    GET(3,"已领取"),
    FINISHED(4,"已完成"),
    FINISH_FAIL(5,"异常(完成失败)")
    ;
    private Integer status;
    private String msg;

    PurchaseStatus(Integer status, String msg) {
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
