package com.ysl.auth.security.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import com.ysl.auth.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户凭证表
 * </p>
 *
 * @author loong
 * @since 2024-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("auth_user_credential")
public class AuthUserCredential extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 外键, 关联到auth_user表的username
     */
    @TableField("username")
    private String username;

    /**
     * 凭证类型，例如EMAIL, PHONE, WECHAT, GOOGLE，字符串类型
     */
    @TableField("credential_type")
    private String credentialType;

    /**
     * 凭证标识符，例如邮箱地址、手机号、第三方平台的唯一标识符
     */
    @TableField("identifier")
    private String identifier;

    /**
     * 凭证，对于邮箱和手机号可能是密码的哈希值，对于第三方登录则为空或存储access token等信息
     */
    @TableField("credential")
    private String credential;

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
