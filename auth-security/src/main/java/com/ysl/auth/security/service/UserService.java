package com.ysl.auth.security.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author long
 */
@Component
public class UserService {
    public Map<String, Object> getUserInfo(String username) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", "123456");
        map.put("username", "username");
        return map;
    }
}
