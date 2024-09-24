package com.ysl.auth.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author long
 */
@EnableDiscoveryClient
@SpringBootApplication
public class AuthSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthSecurityApplication.class, args);
	}

}
