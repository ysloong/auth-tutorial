package com.ysl.auth.user.service;

import com.ysl.auth.common.dto.UserBaseInfoDto;
import com.ysl.auth.user.dto.RegisterInput;

/**
 * @author long
 */
public interface IUserService {


    UserBaseInfoDto register(RegisterInput registerInput);
}
