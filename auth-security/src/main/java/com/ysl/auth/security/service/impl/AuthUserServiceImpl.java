package com.ysl.auth.security.service.impl;

import com.ysl.auth.security.entity.AuthUser;
import com.ysl.auth.security.mapper.AuthUserMapper;
import com.ysl.auth.security.service.IAuthUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author loong
 * @since 2024-09-24
 */
@Service
public class AuthUserServiceImpl extends ServiceImpl<AuthUserMapper, AuthUser> implements IAuthUserService {

}
