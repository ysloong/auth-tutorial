package com.ysl.auth.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author long
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class AuthGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthGatewayApplication.class, args);
	}

}
