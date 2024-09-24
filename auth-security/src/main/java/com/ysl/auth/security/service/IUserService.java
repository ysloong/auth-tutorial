package com.ysl.auth.security.service;

import com.ysl.auth.security.dto.LoginRequest;
import com.ysl.auth.security.dto.QueryUserRequest;
import com.ysl.auth.security.dto.RegistrationRequest;
import com.ysl.auth.security.entity.AuthUser;

public interface IUserService {


    void register(RegistrationRequest request);
    String login(LoginRequest request);
    AuthUser queryUser(QueryUserRequest request);
}
