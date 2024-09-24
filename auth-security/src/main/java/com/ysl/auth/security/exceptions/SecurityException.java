package com.ysl.auth.security.exceptions;

import com.ysl.auth.common.exceptions.CommonException;

/**
 * @author Loong
 */
public class SecurityException extends CommonException {
    public SecurityException(String code, String message) {
        super(code, message);
    }


}
