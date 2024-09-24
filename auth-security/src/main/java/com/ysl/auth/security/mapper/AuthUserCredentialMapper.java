package com.ysl.auth.security.mapper;

import com.ysl.auth.security.entity.AuthUserCredential;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户凭证表 Mapper 接口
 * </p>
 *
 * @author loong
 * @since 2024-09-24
 */
@Mapper
public interface AuthUserCredentialMapper extends BaseMapper<AuthUserCredential> {

}
