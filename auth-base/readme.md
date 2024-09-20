# auth-base 服务说明文档



## 生产业务编码

### 方案1 使用Java业务代码方式实现序列管理
### 方案2 使用数据库函数方式实现序列管理（废弃，可移植性差，数据库依赖）
~~~sql
-- 创建序列管理表
CREATE OR REPLACE FUNCTION generate_business_code(p_business_type VARCHAR)
    RETURNS VARCHAR AS $$
DECLARE
    v_current_value BIGINT;
    v_prefix VARCHAR;
    v_length INTEGER;
    v_code VARCHAR;
BEGIN
    -- 更新当前值并获取必要信息
    UPDATE base_sequence_management
    SET current_value = current_value + step,
        updated_at = CURRENT_TIMESTAMP
    WHERE business_type = p_business_type
    RETURNING current_value, prefix, length
        INTO v_current_value, v_prefix, v_length;

    -- 如果没有找到匹配的业务类型，抛出异常
    IF v_current_value IS NULL THEN
        RAISE EXCEPTION 'Business type % not found', p_business_type;
    END IF;

    -- 生成编码
    v_code := v_prefix || LPAD(v_current_value::TEXT, v_length - LENGTH(v_prefix), '0');

    RETURN v_code;
END;
$$ LANGUAGE plpgsql;
~~~

### 接口1
### 接口2

## 模块2


是的，您的理解基本正确。`auth-security` 和 `auth-gateway` 的关系可以这样理解：

1. **`auth-security` 项目**：
    - 负责用户的认证（Authentication），即验证用户的身份。
    - 提供 OAuth2 授权服务器的功能，生成和管理访问令牌（Access Token）。
    - 提供用户信息和权限管理。

2. **`auth-gateway` 项目**：
    - 负责路由和鉴权（Authorization），即验证用户是否有权限访问特定的资源。
    - 作为系统的入口点，所有外部请求首先经过网关。
    - 通过 OAuth2 令牌来验证用户身份和权限。

### 具体流程

1. **用户请求**：
    - 用户通过浏览器或其他客户端发送请求到 `auth-gateway`。

2. **认证**：
    - 如果用户未登录或没有有效的访问令牌，`auth-gateway` 会重定向用户到 `auth-security` 的登录页面。
    - 用户在 `auth-security` 的登录页面输入凭据（如用户名和密码）进行认证。
    - `auth-security` 验证用户凭据，如果成功，则生成访问令牌并返回给用户。

3. **鉴权**：
    - 用户获取到访问令牌后，再次发送请求到 `auth-gateway`，并在请求头中携带访问令牌。
    - `auth-gateway` 拦截请求，解析访问令牌，验证其有效性和权限。
    - 如果令牌有效且用户有权限访问请求的资源，`auth-gateway` 将请求路由到相应的后端服务。
    - 如果令牌无效或用户没有权限，`auth-gateway` 返回相应的错误信息。

### 示例代码

#### `auth-security` 项目

##### 1.1 添加依赖

在 `pom.xml` 文件中添加必要的依赖：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.security.oauth.boot</groupId>
        <artifactId>spring-security-oauth2-autoconfigure</artifactId>
        <version>2.5.2</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```

##### 1.2 配置 `application.yml`

在 `application.yml` 文件中配置数据源和安全设置：

```yaml
spring:
  datasource:
    url: jdbc:h2:mem:testdb
    driverClassName: org.h2.Driver
    username: sa
    password:
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
  security:
    oauth2:
      client:
        registration:
          auth-service:
            client-id: client-id
            client-secret: client-secret
            authorization-grant-type: authorization_code
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
            scope: read,write
        provider:
          auth-service:
            authorization-uri: http://localhost:8081/oauth2/authorize
            token-uri: http://localhost:8081/oauth2/token
            user-info-uri: http://localhost:8081/user
            user-name-attribute: sub

server:
  port: 8081
```

##### 1.3 配置安全

创建一个配置类来配置认证服务的安全设置：

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/oauth2/authorize", "/oauth2/token").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("{noop}password").roles("USER").build());
        return manager;
    }
}

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
            .tokenStore(new InMemoryTokenStore());
    }
}

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/user").authenticated();
    }
}
```

#### `auth-gateway` 项目

##### 2.1 添加依赖

在 `pom.xml` 文件中添加必要的依赖：

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-client</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
</dependencies>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2021.0.3</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

##### 2.2 配置 `application.yml`

在 `application.yml` 文件中配置网关路由和安全设置：

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: auth-service
          uri: lb://auth-service
          predicates:
            - Path=/auth/**
        - id: file-service
          uri: lb://file-service
          predicates:
            - Path=/file/**

  security:
    oauth2:
      client:
        registration:
          auth-service:
            client-id: client-id
            client-secret: client-secret
            authorization-grant-type: authorization_code
            redirect-uri: "{baseUrl}/login/oauth2/code/{registrationId}"
            scope: read,write
        provider:
          auth-service:
            authorization-uri: http://localhost:8081/oauth2/authorize
            token-uri: http://localhost:8081/oauth2/token
            user-info-uri: http://localhost:8081/user
            user-name-attribute: sub

server:
  port: 8080
```

##### 2.3 配置安全

创建一个配置类来配置网关的安全设置：

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf().disable()
            .authorizeExchange(exchanges -> exchanges
                .pathMatchers("/auth/**").permitAll()
                .anyExchange().authenticated()
            )
            .oauth2Login();

        return http.build();
    }
}
```

### 测试

1. **启动认证服务**：
    - 运行 `auth-security` 项目的主类，确保服务在 `http://localhost:8081` 上运行。

2. **启动网关服务**：
    - 运行 `auth-gateway` 项目的主类，确保服务在 `http://localhost:8080` 上运行。

3. **测试认证**：
    - 访问 `http://localhost:8080/file/upload` 或其他需要鉴权的接口。
    - 如果用户未登录或没有有效的访问令牌，`auth-gateway` 会重定向用户到 `auth-security` 的登录页面。
    - 用户在 `auth-security` 的登录页面输入凭据进行认证。
    - 认证成功后，`auth-security` 生成访问令牌并返回给用户。
    - 用户再次发送请求到 `auth-gateway`，并在请求头中携带访问令牌。
    - `auth-gateway` 拦截请求，验证访问令牌的有效性和权限。
    - 如果验证通过，请求被路由到相应的后端服务；否则，返回相应的错误信息。

通过以上步骤，您可以实现一个独立的认证服务 `auth-security` 和一个网关服务 `auth-gateway`，确保用户请求首先经过认证和鉴权，然后根据结果路由到相应的后端服务。希望这些信息对您有所帮助！如果有任何进一步的问题，请随时提问。

