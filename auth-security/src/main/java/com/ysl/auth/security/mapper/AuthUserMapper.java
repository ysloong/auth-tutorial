package com.ysl.auth.security.mapper;

import com.ysl.auth.security.entity.AuthUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author loong
 * @since 2024-09-24
 */
@Mapper
public interface AuthUserMapper extends BaseMapper<AuthUser> {

}
