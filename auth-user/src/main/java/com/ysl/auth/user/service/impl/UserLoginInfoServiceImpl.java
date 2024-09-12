package com.ysl.auth.user.service.impl;

import com.ysl.auth.user.entity.UserLoginInfo;
import com.ysl.auth.user.mapper.UserLoginInfoMapper;
import com.ysl.auth.user.service.IUserLoginInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
@Service
public class UserLoginInfoServiceImpl extends ServiceImpl<UserLoginInfoMapper, UserLoginInfo> implements IUserLoginInfoService {

}
