package com.ysl.auth.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author long
 */
@EnableDiscoveryClient
@SpringBootApplication

public class AuthBaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthBaseApplication.class, args);
    }

}
