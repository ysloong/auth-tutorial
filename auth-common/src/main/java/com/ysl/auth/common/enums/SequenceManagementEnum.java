package com.ysl.auth.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SequenceManagementEnum {

    /**
     * 1 是
     */
    user("user-type"),
    /**
     * 0 否
     */
    order("order-type")
    ;
    private final String code;

}
