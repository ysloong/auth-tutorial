package com.ysl.auth.common.message;

import com.ysl.auth.common.exceptions.SystemConstants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 响应信息基础类
 *
 * @author loong
 */
@NoArgsConstructor
@Data
public class BaseResMessage<T extends Object> implements Serializable {
    private String code;
    private String message;
    private T data;

    public BaseResMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static BaseResMessage<Object> success() {
        BaseResMessage<Object> result = new BaseResMessage<>();
        result.setCode(SystemConstants.RES_SUCCESS_CODE);
        result.setMessage(SystemConstants.RES_SUCCESS_MESSAGE);
        return result;
    }

    public static <T> BaseResMessage<T> success(T data) {
        BaseResMessage<T> result = new BaseResMessage<>();
        result.setCode(SystemConstants.RES_SUCCESS_CODE);
        result.setMessage(SystemConstants.RES_SUCCESS_MESSAGE);
        result.setData(data);
        return result;
    }

    public static BaseResMessage<Object> sysError(String message) {
        BaseResMessage<Object> result = new BaseResMessage<>();
        result.setCode(SystemConstants.EXCEPTION_RES_CODE);
        if (!StringUtils.hasText(message)) {
            result.setMessage(SystemConstants.EXCEPTION_RES_MESSAGE);
        } else {
            result.setMessage(message);
        }
        return result;
    }

    public static BaseResMessage<Object> fail(String code, String message) {
        BaseResMessage<Object> result = new BaseResMessage<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static BaseResMessage<Object> remoteError() {
        BaseResMessage<Object> result = new BaseResMessage<>();
        result.setCode(SystemConstants.HYSTRIX_RES_CODE);
        result.setMessage(SystemConstants.HYSTRIX_RES_MESSAGE);
        return result;
    }
}