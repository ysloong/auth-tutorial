package com.ysl.auth.security.exceptions;

import com.ysl.auth.common.exceptions.SystemConstants;
import com.ysl.auth.common.message.BaseResMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author long
 */
@Slf4j
@ControllerAdvice
public class GlobalValidException {
    @ExceptionHandler(value = SecurityException.class)
    @ResponseBody
    public BaseResMessage<Object> baseErrorHandler(SecurityException exception) {
        return BaseResMessage.fail(exception.getCode(), exception.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResMessage<Object> errorHandler(Exception exception) {
        log.error("系统异常 code:{},message:{}", SystemConstants.EXCEPTION_RES_CODE,exception.getMessage());
        return BaseResMessage.fail(SystemConstants.EXCEPTION_RES_CODE, SystemConstants.EXCEPTION_RES_MESSAGE);
    }

}
