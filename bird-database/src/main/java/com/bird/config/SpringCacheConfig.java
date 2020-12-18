package com.bird.config;

import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @Author lipu
 * @Date 2020/11/7 16:25
 * @Description
 */
@Configuration
public class SpringCacheConfig {

    /**
     * @Author lipu
     * @Date 2020/11/7 17:47
     * @Description RedisCache序列化配置和过期时间配置
     */
    @Bean
    RedisCacheConfiguration redisCacheConfiguration(CacheProperties cacheProperties){
        RedisCacheConfiguration config=RedisCacheConfiguration.defaultCacheConfig();
        //key的序列化方式
        config=config.serializeKeysWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new StringRedisSerializer()));
        //value的序列化方式
        config=config.serializeValuesWith(RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer()));
        //设置过期时间
        config=config.entryTtl(Duration.ofMinutes(5));
        return config;
    }
}
