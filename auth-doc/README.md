# auth-tutorial

## 需求
1 docker 部署微服务
2 jenkins 自动构建
3 gitlab 代码管理

### 技术架构
* jdk21
* spring boot 3.3.2
* spring cloud 2023.0.2
* maven <font color=red>待定</font>

## 服务端口

| 服务名        | 端口 | 说明                           |
| ------------- | ---- | ------------------------------ |
| auth-gateway  | 6001 | API网关                        |
| auth-security | 6002 | 鉴权、认证                     |
| auth-user     | 6003 | 用户                           |
| auth-base     | 6004 | 基础                           |
| auth-common   | -    | 全局实体类、异常、响应、工具类 |
| auth-parent   | -    | 版本管理                       |
| auth-doc      | -    | 文档                           |
|               |      |                                |



## 数据库配置



| 服务名    | 数据库        | schema    | IP        | PORT | user  | pw    |      |
| --------- | ------------- | --------- | --------- | ---- | ----- | ----- | ---- |
| auth-user | auth-tutorial | auth-user | localhost | 5432 | loong | loong |      |
| auth-base | auth-tutorial | auth-base | localhost | 5432 | loong | loong |      |
|           |               |           |           |      |       |       |      |


## 问题
### 版本问题



* jdk版本选择

~~~text
Spring Boot 2.5.x: 推荐使用 JDK 8、JDK 11
Spring Boot 2.6.x: 推荐使用 JDK 8、JDK 11、JDK 17
Spring Boot 2.7.x: 推荐使用 JDK 8、JDK 11、JDK 17
Spring Boot 3.x: 从 Spring Boot 3 开始，推荐使用 JDK 17 或更高版本

生产上如果使用jdk8，则不要使用3.x


~~~



* 高版本

~~~xml
https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E

spring boot 2.7.10
spring-cloud 2021.0.5
spring-cloud-alibaba 2023.0.0.0-RC1
jdk21

~~~



* 选择版本

  ~~~xml
  spring boot 3.3.2
  spring-cloud 2023.0.2
  spring-cloud-alibaba 2023.0.0.0-RC1
  ~~~

  





* nacos 2.x 和nacos1.x 的引用不一样





~~~xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
    <exclusions>
        <exclusion>
            <groupId>com.alibaba.nacos</groupId>
            <artifactId>nacos-client</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
    <exclusions>
        <exclusion>
            <groupId>com.alibaba.nacos</groupId>
            <artifactId>nacos-client</artifactId>
        </exclusion>
    </exclusions>
</dependency>

<dependency>
    <groupId>com.alibaba.nacos</groupId>
    <artifactId>nacos-client</artifactId>
    <version>1.4.7</version>
</dependency>
~~~



* 使用openfeign 高版本spring-cloud-alibaba 需要额外引入loadbalancer

~~~xml
<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
        </dependency>


<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
    <version>3.1.0</version>
</dependency>
~~~



* 调查是否需要@EnableFeignClients(basePackages = "com.ysl.auth.user.feign")



* 调查是否需要引入nacos-client





* 调查是否需要开放9849、9849端口



* Nacos版本问题

  ~~~text
  引入 gRPC 支持
  Spring Cloud Alibaba 2023.0.0.0-RC1 版本引入了对 gRPC 的支持，这是为了提高服务发现和配置管理的性能和效率。
  在这个版本中，Nacos 客户端默认使用 gRPC 进行通信，而不是之前的 HTTP 协议。
  依赖项的变化
  以前的版本（如 2021.x.x）中，只需要引入 spring-cloud-starter-alibaba-nacos-discovery 和 spring-cloud-starter-alibaba-nacos-config 就可以使用 Nacos 的服务发现和配置管理功能。
  但是，在 2023.0.0.0-RC1 版本中，由于引入了 gRPC 支持，需要额外的依赖项来支持 gRPC 通信。
  
  Nacos 默认的服务端口是 8848，而 gRPC 端口通常是 9848 和 9849
  
  建议使用2021.x.x版本
  
  使用Spring Cloud Alibaba 2023.0.0.0-RC1 需要使用的nacos以来为：
  
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-openfeign</artifactId>
  </dependency>
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
      <exclusions>
          <exclusion>
              <groupId>com.alibaba.nacos</groupId>
              <artifactId>nacos-client</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  <dependency>
      <groupId>com.alibaba.cloud</groupId>
      <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
      <exclusions>
          <exclusion>
              <groupId>com.alibaba.nacos</groupId>
              <artifactId>nacos-client</artifactId>
          </exclusion>
      </exclusions>
  </dependency>
  
  <dependency>
      <groupId>com.alibaba.nacos</groupId>
      <artifactId>nacos-client</artifactId>
      <version>1.4.7</version>
  </dependency>
  
  如果想使用grpc，则增加依赖：
  <!-- 新增的 gRPC 依赖 -->
  <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-netty-shaded</artifactId>
      <version>1.47.0</version>
  </dependency>
  <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-protobuf</artifactId>
      <version>1.47.0</version>
  </dependency>
  <dependency>
      <groupId>io.grpc</groupId>
      <artifactId>grpc-stub</artifactId>
      <version>1.47.0</version>
  </dependency>
  
  
  ~~~

  

