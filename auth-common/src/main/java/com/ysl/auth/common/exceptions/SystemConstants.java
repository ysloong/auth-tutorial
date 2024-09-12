package com.ysl.auth.common.exceptions;


/**
 *
 * @author long
 */
public class SystemConstants {
    public static final String RES_SUCCESS_CODE = "00";
    public static final String RES_SUCCESS_MESSAGE = "操作成功";

    public static final String HYSTRIX_RES_CODE = "01";
    public static final String HYSTRIX_RES_MESSAGE = "服务器通讯异常!";

    public static final String EXCEPTION_RES_CODE = "02";
    public static final String EXCEPTION_RES_MESSAGE = "系统异常!";

    public static final String EXCEPTION_RES_CODE_DB = "03";
    public static final String EXCEPTION_RES_MESSAGE_DB = "数据库异常!";

    public static final String EXCEPTION_RES_CODE_NULL = "04";
    public static final String EXCEPTION_RES_MESSAGE_NULL = "空数据异常!";

}