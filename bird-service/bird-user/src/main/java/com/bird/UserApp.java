package com.bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author lipu
 * @Date 2020/9/13 16:35
 * @Description 用户微服务启动类
 */
@MapperScan("com.bird.dao")
@EnableCaching
@EnableFeignClients
@SpringCloudApplication
public class UserApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class);
    }
}
