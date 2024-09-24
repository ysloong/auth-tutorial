package com.ysl.auth.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ysl.auth.security.dto.LoginRequest;
import com.ysl.auth.security.dto.QueryUserRequest;
import com.ysl.auth.security.dto.RegistrationRequest;
import com.ysl.auth.security.entity.AuthUser;
import com.ysl.auth.security.entity.AuthUserCredential;
import com.ysl.auth.security.service.IAuthUserCredentialService;
import com.ysl.auth.security.service.IAuthUserService;
import com.ysl.auth.security.service.IUserService;
import com.ysl.auth.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author long
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private IAuthUserService authUserService;

    @Autowired
    private JwtService jwtService;


    @Autowired
    private IAuthUserCredentialService authUserCredentialService;

    @Override
    public void register(RegistrationRequest request) {

        AuthUser authUser = new AuthUser();
        authUser.setUsername("20000000001");
        authUser.setNickname(request.getUsername());
        authUser.setAvatarUrl("");
        authUser.setCreatedAt(LocalDateTime.now());
        authUser.setUpdatedAt(LocalDateTime.now());
        authUserService.save(authUser);
        AuthUserCredential authUserCredential = new AuthUserCredential();
        authUserCredential.setUsername(authUser.getUsername());
        authUserCredential.setCredentialType("EMAIL");
        authUserCredential.setIdentifier(request.getUsername());
        authUserCredential.setCredential(passwordEncoder.encode(request.getPassword()));
        authUserCredential.setCreatedAt(LocalDateTime.now());
        authUserCredential.setUpdatedAt(LocalDateTime.now());
        authUserCredentialService.save(authUserCredential);
    }

    @Override
    public String login(LoginRequest request) {
        QueryWrapper<AuthUserCredential> wrapper = new QueryWrapper<>();
        wrapper.eq("credential_type", request.getCredentialType());
        wrapper.eq("identifier", request.getUsername());
        AuthUserCredential userCredential = authUserCredentialService.getOne(wrapper);
        if (userCredential != null && passwordEncoder.matches(request.getPassword(), userCredential.getCredential())) {
            Map<String, Object> payload = new HashMap<>();
            payload.put("username", userCredential.getUsername());
            return jwtService.createToken(payload);
        }
        return "";
    }

    @Override
    public AuthUser queryUser(QueryUserRequest request) {

        QueryWrapper<AuthUserCredential> wrapper = new QueryWrapper<>();
        wrapper.eq("credential_type", request.getCredentialType());
        wrapper.eq("identifier", request.getUsername());
        AuthUserCredential userCredential = authUserCredentialService.getOne(wrapper);

        if (userCredential != null) {

            QueryWrapper<AuthUser> authUserWrapper = new QueryWrapper<>();
            wrapper.eq("username", userCredential.getUsername());
            return authUserService.getOne(authUserWrapper);
        }
        return null;
    }
}
