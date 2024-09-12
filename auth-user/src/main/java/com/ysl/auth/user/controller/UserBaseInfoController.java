package com.ysl.auth.user.controller;


import com.ysl.auth.common.message.BaseResMessage;
import com.ysl.auth.user.dto.RegisterInput;
import com.ysl.auth.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author loong
 * @since 2024-09-12
 */
@RestController
public class UserBaseInfoController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/userBaseInfo/register")
    public BaseResMessage<Object> register(@RequestBody RegisterInput registerInput) {
        return BaseResMessage.success(userService.register(registerInput));
    }

}
