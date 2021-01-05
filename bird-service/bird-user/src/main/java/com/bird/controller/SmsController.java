package com.bird.controller;

import com.bird.component.SmsComponent;
import com.bird.common.CommonResult;
import com.bird.common.CommonStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author lipu
 * @Date 2020/12/22 17:14
 * @Description
 */
@Api(tags = "阿里云短信接口")
@RestController
@RequestMapping("/sms")
public class SmsController {
    @Resource
    private SmsComponent smsComponent;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;


    /**
     * @Author lipu
     * @Date 2020/12/22 17:45
     * @Description 发送验证码
     */
    @PostMapping("/sendMessage")
    @ApiOperation("发送验证码")
    public CommonResult sendMessage(@RequestParam("phone") String phone){
        String message = smsComponent.sendMessage(phone);
        return new CommonResult<String>(CommonStatus.SUCCESS,message);
    }
}
