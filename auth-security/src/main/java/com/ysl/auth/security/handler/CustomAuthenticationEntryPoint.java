package com.ysl.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysl.auth.common.message.BaseResMessage;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public static final String AUTHENTICATION_FAILURE_CODE = "401";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        BaseResMessage<Object> res = BaseResMessage.fail(AUTHENTICATION_FAILURE_CODE, "用户未认证");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(new ObjectMapper().writeValueAsString(res));
    }
}
