package com.ysl.auth.security.controller;


import com.ysl.auth.common.message.BaseResMessage;
import com.ysl.auth.security.dto.LoginRequest;
import com.ysl.auth.security.dto.RegistrationRequest;
import com.ysl.auth.security.service.IUserService;
import com.ysl.auth.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class UserAuthController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;


    @PostMapping(value = "/api/register")
    public BaseResMessage<Object> register(@RequestBody RegistrationRequest request) {
        userService.register(request);
        return BaseResMessage.success();
    }

    @PostMapping(value = "/api/login")
    public BaseResMessage<Object> login(@RequestBody LoginRequest request) {
        return BaseResMessage.success(userService.login(request));
    }

    @PostMapping(value = "/api/test")
    public BaseResMessage<Object> test(@RequestParam String token) {
        return BaseResMessage.success(jwtService.getPayloadFromToken(token));
    }

}
