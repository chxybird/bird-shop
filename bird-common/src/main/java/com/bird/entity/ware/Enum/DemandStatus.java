package com.bird.entity.ware.Enum;

/**
 * @Author lipu
 * @Date 2020/10/31 14:02
 * @Description 采购需求状态枚举类
 */
public enum DemandStatus {
    NOT_TO_FINISH(1,"未采购"),
    FINISHING(2,"正在采购"),
    FINISHED(3,"采购完成"),
    FINISH_FAIL(4,"采购失败")
    ;
    private Integer status;
    private String msg;

    public Integer getStatus() {
        return status;
    }

    DemandStatus(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
