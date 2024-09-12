package com.ysl.auth.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ysl.auth.common.entity.BaseEntity;
import com.ysl.auth.common.entity.ManualEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserBaseInfoDto extends ManualEntity {


    /**
     * 用户编号，外键关联 user_login_info
     */
    private String userCode;

    private String email;

    private String phoneNumber;

    /**
     * 名字
     */
    private String firstName;

    /**
     * 姓氏
     */
    private String lastName;

    /**
     * 生日
     */
    private LocalDate birthdate;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    private LocalDateTime updatedAt;


}
