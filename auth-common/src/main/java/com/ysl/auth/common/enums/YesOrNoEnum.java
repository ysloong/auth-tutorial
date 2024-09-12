package com.ysl.auth.common.enums;

/**
 * 0 否；1 是
 */
public enum YesOrNoEnum {
    /**
     * 1 是
     */
    YES("1", "是"),
    /**
     * 0 否
     */
    NO("0", "否")
    ;
    private final String code;
    private final String value;

    YesOrNoEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
