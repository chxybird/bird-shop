package com.bird.utils;

import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * @Author lipu
 * @Date 2020/10/21 20:50
 * @Description 小鸟程序员的工具类
 */
@Slf4j
public class Utils {

    /**
     * @Author lipu
     * @Date 2020/10/26 9:20
     * @Description 校验数据库操作是否成功
     */
    public static CommonResult checkResult(Integer result){
        if (result>0){
            return new CommonResult(CommonStatus.SUCCESS,"成功");
        }else {
            log.error("操作失败");
            return new CommonResult(CommonStatus.ERROR,"失败");
        }
    }

}
