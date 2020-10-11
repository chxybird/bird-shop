package com.bird;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @Author lipu
 * @Date 2020/10/11 10:57
 * @Description 文件微服务
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class FileApp {
    public static void main(String[] args) {
        SpringApplication.run(FileApp.class);
    }
}
