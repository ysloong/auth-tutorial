package com.ysl.auth.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysl.auth.common.message.BaseResMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    public static final String ACCESS_DENIED_CODE = "403";

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        BaseResMessage<Object> res = BaseResMessage.fail(ACCESS_DENIED_CODE, "无权限访问");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(new ObjectMapper().writeValueAsString(res));
    }
}
