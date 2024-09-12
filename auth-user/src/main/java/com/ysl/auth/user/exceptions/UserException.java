package com.ysl.auth.user.exceptions;

import com.ysl.auth.common.exceptions.CommonException;

/**
 * @author Loong
 */
public class UserException extends CommonException {
    public UserException(String code, String message) {
        super(code, message);
    }


}
