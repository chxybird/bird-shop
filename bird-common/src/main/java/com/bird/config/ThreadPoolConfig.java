package com.bird.config;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @Author lipu
 * @Date 2020/12/18 15:01
 * @Description
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor=new ThreadPoolExecutor(
                50,
                50,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(100)
        );
        return threadPoolExecutor;
    }
}
