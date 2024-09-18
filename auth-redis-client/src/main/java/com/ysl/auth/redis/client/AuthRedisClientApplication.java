//// src/main/java/com/ysl/auth/redis/client/AuthRedisClientApplication.java
//package com.ysl.auth.redis.client;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.core.env.Environment;
//
//import java.util.Arrays;
//
///**
// * @author long
// */
//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
//@EnableDiscoveryClient
//public class AuthRedisClientApplication {
//    public static void main(String[] args) {
//        ConfigurableApplicationContext context = SpringApplication.run(AuthRedisClientApplication.class, args);
//        Environment env = context.getEnvironment();
//        System.out.println("Active Profiles: " + Arrays.toString(env.getActiveProfiles()));
//        System.out.println("Property Sources: " + context.getEnvironment().getPropertySources());
//        System.out.println("Redis Host: " + env.getProperty("spring.redis.host"));
//        System.out.println("Redis Port: " + env.getProperty("spring.redis.port"));
//        System.out.println("Redis Password: " + env.getProperty("spring.redis.password"));
//    }
//
//
//}