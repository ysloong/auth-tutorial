package com.ysl.auth.security.dto;

import lombok.Data;

/**
 * @author long
 */
@Data
public class QueryUserRequest {

    private String credentialType;
    private String username;
}
