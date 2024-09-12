package com.ysl.auth.common.dto;

import com.ysl.auth.common.entity.ManualEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 序列管理表，用于记录不同业务类型的序列号及其相关信息
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseSequenceManagementDto extends ManualEntity {


    /**
     * 业务类型，唯一约束
     */
    private String businessType;

    /**
     * 当前值
     */
    private Long currentValue;

    /**
     * 前缀
     */
    private String prefix;

    /**
     * 步长，默认为1
     */
    private Integer step;

    /**
     * 序列长度
     */
    private Integer length;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;
}
