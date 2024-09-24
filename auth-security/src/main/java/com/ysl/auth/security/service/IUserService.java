package com.ysl.auth.security.service;

import com.ysl.auth.security.dto.LoginRequest;
import com.ysl.auth.security.dto.RegistrationRequest;

public interface IUserService {


    void register(RegistrationRequest request);
    String login(LoginRequest request);
}
