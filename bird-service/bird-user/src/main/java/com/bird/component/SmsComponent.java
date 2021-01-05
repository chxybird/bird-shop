package com.bird.component;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * @Author lipu
 * @Date 2020/12/22 14:54
 * @Description 短信工具类
 */
@Component
@Data
@ConfigurationProperties(prefix = "sms")
public class SmsComponent {
    public static final String BIRD_PREFIX = "BIRD:";
    public static final String APP_CODE = "APPCODE ";
    public static final String SMS_SIGN = "sign";
    public static final String SMS_SKIN = "skin";
    public static final String SMS_PHONE = "phone";
    public static final String SMS_PARAM = "param";
    public static final String SYMBOL_EQUAL = "=";
    public static final String SYMBOL_QUESTION = "?";
    public static final String SYMBOL_AND = "&";

    public static final int[] NUMBER_LIST = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    public static final int SIZE = 6;

    @Resource
    private RestTemplate restTemplate;
    @Resource
    public RedisTemplate<String, Object> redisTemplate;
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
     * @Author lipu
     * @Date 2020/12/22 17:26
     * @Description 验证码发送
     */
    public String sendMessage(String phone) {
        try {
            //校验验证码是否已经存在
            String value = (String) redisTemplate.opsForValue().get(BIRD_PREFIX + phone);
            if (!StringUtils.isEmpty(value)) {
                return "验证码已存在,请稍候再试!!!";
            }
            StringBuilder stringBuilder = new StringBuilder();
            //随机生成验证码
            for (int i = 0; i < SIZE; i++) {
                Random random = new Random();
                int index = random.nextInt(NUMBER_LIST.length);
                stringBuilder.append(NUMBER_LIST[index]);
            }
            String param = stringBuilder.toString();

            //参数拼接
            String queryParam = SYMBOL_QUESTION + SMS_SIGN + SYMBOL_EQUAL + sign +
                    SYMBOL_AND + SMS_SKIN + SYMBOL_EQUAL + skin +
                    SYMBOL_AND + SMS_PARAM + SYMBOL_EQUAL + param +
                    SYMBOL_AND + SMS_PHONE + SYMBOL_EQUAL + phone;
            String url = path + queryParam;
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", APP_CODE + appCode);
            //构建请求对象(头、url、方法类型)
            RequestEntity<String> requestEntity = new RequestEntity<String>(headers, HttpMethod.GET, URI.create(url));
            //发送请求
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
            //将验证码写入redis key:手机号 value:验证码
            redisTemplate.opsForValue().set(BIRD_PREFIX + phone, param, 20, TimeUnit.MINUTES);
            return "验证码发送成功!!!";
        } catch (Exception e) {
            return "验证码发送失败!!!";
        }
    }

    /**
     * @Author lipu
     * @Date 2020/12/22 17:57
     * @Description 验证验证码
     */
    public Boolean checkMessage(String param, String phone) {
        String value = (String) redisTemplate.opsForValue().get(BIRD_PREFIX + phone);
        //验证码过期
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        if (value.equals(param)) {
            return true;
        }
        return false;
    }
}
