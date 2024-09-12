package com.ysl.auth.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author long
 */
@Data
public class RegisterInput implements Serializable {

    private String registerType;
    private String email;
    private String phoneNumber;
    private String passwordHash;

}
