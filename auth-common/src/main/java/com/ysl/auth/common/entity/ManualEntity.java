package com.ysl.auth.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author long
 */
@Data
public class ManualEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer deleteStatus = 0;
}
