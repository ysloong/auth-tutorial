// auth-common/src/main/java/com/example/auth/common/redis/RedisService.java
package com.ysl.auth.common.redis;

import java.util.Set;

/**
 * redis 操作接口
 * @author long
 */
public interface RedisService {

    void set(String key, String value);

    String get(String key);

    Long delete(String key);
    boolean exists(String key);

    Set<String> keys(String pattern);
}