//package com.ysl.auth.base.config;
//
//import org.springframework.boot.autoconfigure.grpc.ClientChannelBuilderCustomizer;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.util.CollectionUtils;
//
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
//import io.grpc.ManagedChannelBuilder;
//import org.springframework.cloud.grpc.client.DefaultGrpcClientBuilderFactory;
//import org.springframework.cloud.grpc.client.GrpcClientProperties;
//import org.springframework.cloud.grpc.client.GrpcClientBuilder;
//import org.springframework.cloud.grpc.client.GrpcClientBuilderCustomizer;
//
//@Configuration
//public class GrpcConfig {
//
//    @Bean
//    public GrpcClientBuilderCustomizer customizer() {
//        return builder -> builder.withOption(ManagedChannelBuilder.ChannelOption.MAX_INBOUND_MESSAGE_SIZE, 4 * 1024 * 1024);
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "grpc.client")
//    public GrpcClientProperties properties(Environment env) {
//        GrpcClientProperties properties = new GrpcClientProperties();
//        Map<String, Object> channelOptions = properties.getChannelOptions();
//        if (!CollectionUtils.isEmpty(channelOptions)) {
//            Long connectTimeoutMillis = (Long) channelOptions.get(ChannelOptions.CONNECT_TIMEOUT_MILLIS);
//            if (connectTimeoutMillis != null) {
//                builder.withOption(ManagedChannelBuilder.ChannelOption.CONNECT_TIMEOUT, connectTimeoutMillis, TimeUnit.MILLISECONDS);
//            }
//        }
//        return properties;
//    }
//}
