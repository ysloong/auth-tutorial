package com.ysl.auth.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author long
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class AuthBaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthBaseApplication.class, args);
    }

}
