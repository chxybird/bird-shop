package com.bird.common;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author lipu
 * @Date 2020/10/9 17:39
 * @Description 通用降级处理类
 */
@Slf4j
public class CommonFallBackHandler {
    public static CommonResult flowFallBackHandler(BlockException blockException){
        log.warn("系统流量较大,已降级处理");
        return new CommonResult<String>(CommonStatus.AMPLE_FLOW,"服务正忙,请稍候再试。。。");
    }

    public static CommonResult commonFallbackHandler(Throwable throwable){
        log.warn("服务器正忙，请稍候再试。。。");
        return new CommonResult<String>(CommonStatus.AMPLE_FLOW,"服务器正忙,请稍候再试。。。");
    }

}
