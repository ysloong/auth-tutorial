package com.ysl.auth.security.controller;


import com.ysl.auth.common.message.BaseResMessage;
import com.ysl.auth.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
@RestController
public class UserBaseInfoController {
    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "/api/login")
    public BaseResMessage<Object> login(@RequestParam String username, @RequestParam String password) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", 11);
        payload.put("username", username);
        String token = jwtService.createToken(payload);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return BaseResMessage.success(response);
    }

    @PostMapping(value = "/api/test")
    public BaseResMessage<Object> test(@RequestParam String token) {
        return BaseResMessage.success(jwtService.getPayloadFromToken(token));
    }

}
