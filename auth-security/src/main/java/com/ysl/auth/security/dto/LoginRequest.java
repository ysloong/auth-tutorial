package com.ysl.auth.security.dto;

import lombok.Data;

/**
 * @author long
 */
@Data
public class LoginRequest {

    private String credentialType;
    private String username;
    private String password;
}
