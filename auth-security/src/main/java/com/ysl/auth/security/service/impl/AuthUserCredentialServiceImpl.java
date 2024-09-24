package com.ysl.auth.security.service.impl;

import com.ysl.auth.security.entity.AuthUserCredential;
import com.ysl.auth.security.mapper.AuthUserCredentialMapper;
import com.ysl.auth.security.service.IAuthUserCredentialService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户凭证表 服务实现类
 * </p>
 *
 * @author loong
 * @since 2024-09-24
 */
@Service
public class AuthUserCredentialServiceImpl extends ServiceImpl<AuthUserCredentialMapper, AuthUserCredential> implements IAuthUserCredentialService {

}
