package com.ysl.auth.common.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Loong
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CommonException extends RuntimeException{

    private String code;
    private String message;

    public CommonException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
