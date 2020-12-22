package com.bird.Utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;


/**
 * @Author lipu
 * @Date 2020/12/22 14:54
 * @Description 短信工具类
 */
@Component
@Data
@ConfigurationProperties(prefix = "sms")
public class SmsUtils {
    public static final String SMS_SIGN="sign";
    public static final String SMS_SKIN="skin";
    public static final String SMS_PHONE="phone";
    public static final String SMS_PARAM="param";
    public static final String SYMBOL_EQUAL="?";
    public static final String SYMBOL_QUESTION="?";
    public static final String SYMBOL_AND="&";

    @Resource
    private RestTemplate restTemplate;
    /**
     * 请求路径
     */
    private String path;
    /**
     * 买家AppCode
     */
    private String appCode;
    /**
     * 签名编号 测试号1
     */
    private String sign;
    /**
     * 短信模板编号 测试号1-21
     */
    private String skin;
    /**
     * 验证码
     */
    private String param;
    /**
     * 手机号
     */
    private String phone;

    public String sendMessage(){
        //参数拼接
        String queryParam = SYMBOL_QUESTION + SMS_SIGN + SYMBOL_EQUAL + sign +
                SYMBOL_AND + SMS_SKIN + SYMBOL_EQUAL + skin +
                SYMBOL_AND + SMS_PARAM + SYMBOL_EQUAL + param +
                SYMBOL_AND + SMS_PHONE + SYMBOL_EQUAL + phone;
        String url=path+queryParam;
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization","APPCODE "+appCode);
        //构建请求对象(头、url、方法类型)
        RequestEntity<String> requestEntity=new RequestEntity<String>(headers,HttpMethod.GET,URI.create(url));
        //发送请求
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity,String.class);
        return responseEntity.getBody();
    }
}
