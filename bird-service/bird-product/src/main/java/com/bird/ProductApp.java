package com.bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author lipu
 * @Date 2020/9/13 16:37
 * @Description 商品微服务启动类
 */
@SpringBootApplication
@MapperScan("com.bird.dao")
@EnableCaching
public class ProductApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductApp.class);
    }
}