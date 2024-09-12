package com.ysl.auth.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.ysl.auth.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_base_info")
public class UserBaseInfo extends BaseEntity {

    /**
     * 用户编号，外键关联 user_login_info
     */
    @TableField("user_code")
    private String userCode;

    @TableField("email")
    private String email;

    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 名字
     */
    @TableField("first_name")
    private String firstName;

    /**
     * 姓氏
     */
    @TableField("last_name")
    private String lastName;

    /**
     * 生日
     */
    @TableField("birthdate")
    private LocalDate birthdate;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

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
