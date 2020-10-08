package com.bird;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @Author lipu
 * @Date 2020/10/8 11:07
 * @Description
 */
@SpringBootApplication
@MapperScan("com.bird.dao")
@EnableCaching
public class SurveyApp {
    public static void main(String[] args) {
        SpringApplication.run(SurveyApp.class);
    }
}
