package com.ysl.auth.base.exceptions;

import com.ysl.auth.common.exceptions.CommonException;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Loong
 */
public class BaseException extends CommonException {
    public BaseException(String code, String message) {
        super(code, message);
    }


}
