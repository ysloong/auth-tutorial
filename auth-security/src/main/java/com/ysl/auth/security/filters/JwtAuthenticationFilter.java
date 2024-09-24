package com.ysl.auth.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ysl.auth.common.message.BaseResMessage;
import com.ysl.auth.security.dto.LoginRequest;
import com.ysl.auth.security.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录过滤器
 * @author long
 */
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService; // 你的JWT服务

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        setFilterProcessesUrl("/api/login"); // 设置登录的URL
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {


        try {
            // 使用 ObjectMapper 解析 JSON 请求体
            ObjectMapper objectMapper = new ObjectMapper();
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);

            String username = loginRequest.getUsername();
            String password = loginRequest.getPassword();

            logger.info("JwtAuthenticationFilter triggered, username: " + username);

            // 创建 UsernamePasswordAuthenticationToken 并进行认证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 登录成功，生成JWT
        Map<String, Object> payload = new HashMap<>();
        payload.put("username", authResult.getName());
        String token = jwtService.createToken(payload);
        response.setHeader("Authorization", "Bearer " + token);



        BaseResMessage<Object> res = BaseResMessage.success();
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write(new ObjectMapper().writeValueAsString(res));
    }

//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
//        // 登录成功，生成JWT
//        Map<String, Object> payload = new HashMap<>();
//        payload.put("username", authResult.getName());
//        String token = jwtService.createToken(payload);
//
//        // 将 token 返回在响应体中
//        Map<String, String> responseBody = new HashMap<>();
//        responseBody.put("token", token);
//        response.setContentType("application/json");
//        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
//    }

}
