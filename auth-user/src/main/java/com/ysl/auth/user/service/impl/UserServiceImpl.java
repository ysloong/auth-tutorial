package com.ysl.auth.user.service.impl;

import com.ysl.auth.common.dto.UserBaseInfoDto;
import com.ysl.auth.common.redis.RedisService;
import com.ysl.auth.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author long
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

//    @Autowired
//    private IServiceBaseFeign serviceBaseFeign;

    @Autowired
    private RedisService redisService;

    @Override
    public UserBaseInfoDto register() {
        UserBaseInfoDto baseInfoDto = new UserBaseInfoDto();

        redisService.set("name", LocalDateTime.now().toString());
//        BaseResMessage<String> result = serviceBaseFeign.generateCode(SequenceManagementEnum.user.getCode());
//        if (result.getCode().equals(SystemConstants.RES_SUCCESS_CODE)) {
//            baseInfoDto.setUserCode(result.getData());
//        }
        baseInfoDto.setUserCode(redisService.get("name"));
        return baseInfoDto;
    }
}
