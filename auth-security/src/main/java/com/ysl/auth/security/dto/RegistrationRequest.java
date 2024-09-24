package com.ysl.auth.security.dto;

import lombok.Data;

/**
 * @author long
 */
@Data
public class RegistrationRequest {
    private String username;
    private String password;
}
