package com.bird.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lipu
 * @Date 2020/9/13 18:07
 * @Description 通用结果集
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //状态
    private CommonStatus commonStatus;
    //响应数据
    private T data;
}
