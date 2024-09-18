// auth-common/src/main/java/com/example/auth/common/redis/RedisServiceImpl.java
package com.ysl.auth.common.redis.impl;

import com.ysl.auth.common.redis.RedisService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * reids 实现操作
 * @author long
 */
@Component
public class RedisServiceImpl implements RedisService {

    private final StringRedisTemplate stringRedisTemplate;

    public RedisServiceImpl(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public Long delete(String key) {
        Boolean deleted = stringRedisTemplate.delete(key);
        if (deleted == null || !deleted) {
            return 0L;
        }
        return 1L;
    }
    @Override
    public boolean exists(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
    }

    @Override
    public Set<String> keys(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }
}