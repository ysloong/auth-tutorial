package com.ysl.auth.redis.client.starter;

import com.ysl.auth.common.redis.RedisService;
import com.ysl.auth.common.redis.impl.RedisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author long
 */
@Configuration
public class AuthRedisAutoConfiguration {

    @Bean
    public RedisService redisService(StringRedisTemplate stringRedisTemplate) {
        return new RedisServiceImpl(stringRedisTemplate);
    }
}