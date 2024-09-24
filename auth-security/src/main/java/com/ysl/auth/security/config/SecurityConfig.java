package com.ysl.auth.security.config;

import com.ysl.auth.security.entity.AuthUserCredential;
import com.ysl.auth.security.filters.JwtAuthenticationFilter;
import com.ysl.auth.security.filters.JwtRequestFilter;
import com.ysl.auth.security.handler.CustomAccessDeniedHandler;
import com.ysl.auth.security.handler.CustomAuthenticationEntryPoint;
import com.ysl.auth.security.service.IUserService;
import com.ysl.auth.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;

/**
 * @author long
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    @Autowired
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    // 配置 AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())  // 你需要实现的UserDetailsService
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    // 配置用户加载逻辑
    @Bean
    public UserDetailsService userDetailsService() {

        IUserService userService = applicationContext.getBean(IUserService.class);
        return username -> {
            // 从数据库中根据用户名查找用户
            AuthUserCredential user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found with username: " + username);
            }
            // 将数据库中的用户信息转换为UserDetails
            return new org.springframework.security.core.userdetails.User(
                    user.getIdentifier(),
                    user.getCredential(),  // 数据库中加密后的密码
                    new ArrayList<>()    // 权限列表，可以根据你的需求赋值
            );
        };
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(authenticationManager, jwtService);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/login");

        http.csrf().disable()
                .formLogin().disable()
                .logout().disable()
                .authorizeRequests()
                .antMatchers("/api/login", "/api/register").permitAll() // 注册和登录不需要认证
                .anyRequest().authenticated()// 其他需要认证
                .and()
                .addFilter(jwtAuthenticationFilter)// 登录过滤器
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler)
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}