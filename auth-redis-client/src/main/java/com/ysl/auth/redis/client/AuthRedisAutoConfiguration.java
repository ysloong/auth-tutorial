package com.ysl.auth.redis.client;

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

//    @Value("${spring.redis.host}")
//    private String host;
//
//    @Value("${spring.redis.port}")
//    private int port;
//
//    @Value("${spring.redis.password}")
//    private String password;
//
//    @Value("${spring.redis.database}")
//    private int database;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
//        config.setHostName(host);
//        config.setPort(port);
//        config.setPassword(password);
//        config.setDatabase(database);
//        return new LettuceConnectionFactory(config);
//    }
//
//    @Bean
//    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
//        return new StringRedisTemplate(factory);
//    }
//
//    @Bean
//    public RedisService redisService(StringRedisTemplate stringRedisTemplate) {
//        return new RedisServiceImpl(stringRedisTemplate);
//    }
}