// src/main/java/com/ysl/auth/redis/client/AuthRedisClientApplication.java
package com.ysl.auth.redis.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthRedisClientApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AuthRedisClientApplication.class, args);
        Environment env = context.getEnvironment();
        if (env instanceof ConfigurableEnvironment) {
            ConfigurableEnvironment configurableEnv = (ConfigurableEnvironment) env;
            System.out.println("Active Profiles: " + Arrays.toString(configurableEnv.getActiveProfiles()));
            System.out.println("Property Sources: ");
            configurableEnv.getPropertySources().forEach(ps -> {
                if (ps instanceof org.springframework.core.env.MapPropertySource) {
                    ((org.springframework.core.env.MapPropertySource) ps).getSource().forEach((k, v) -> {
                        System.out.println(k + ": " + v);
                    });
                }
            });
        } else {
            System.out.println("Environment is not configurable.");
        }

        System.out.println("Redis Host: " + env.getProperty("spring.redis.host"));
        System.out.println("Redis Port: " + env.getProperty("spring.redis.port"));
        System.out.println("Redis Password: " + env.getProperty("spring.redis.password"));
        System.out.println("Redis Database: " + env.getProperty("spring.redis.database"));
    }
}