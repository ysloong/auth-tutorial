package com.ysl.auth.base.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ysl.auth.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("base_sequence_management")
@Data
public class BaseSequenceManagement extends BaseEntity {

    /**
     * 业务类型，唯一约束
     */
    @TableField("business_type")
    private String businessType;

    /**
     * 当前值
     */
    @TableField("current_value")
    private Long currentValue;

    /**
     * 前缀
     */
    @TableField("prefix")
    private String prefix;

    /**
     * 步长，默认为1
     */
    @TableField("step")
    private Integer step;

    /**
     * 序列长度
     */
    @TableField("length")
    private Integer length;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;

}
