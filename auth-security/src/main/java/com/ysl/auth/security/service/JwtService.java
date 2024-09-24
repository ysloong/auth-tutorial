package com.ysl.auth.security.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author long
 */
@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration; // 修改为long类型

    /**
     * 创建Token
     * @param payloadMap 包含用户信息的Map
     * @return 生成的Token字符串
     */
    public String createToken(Map<String, Object> payloadMap) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration); // 使用long类型的expiration

        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("payload", payloadMap)
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(algorithm);
    }

    /**
     * 验证Token并返回DecodedJWT对象
     * @param token 要验证的Token字符串
     * @return 如果验证通过，则返回DecodedJWT对象；否则抛出异常
     */
    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (Exception exception) {
            throw new RuntimeException("Invalid token.", exception);
        }
    }

    /**
     * 获取Token中的Payload值
     * @param token 要解析的Token字符串
     * @return 返回一个包含用户信息的Map
     */
    public Map<String, Object> getPayloadFromToken(String token) {
        DecodedJWT jwt = verifyToken(token);
        return jwt.getClaim("payload").asMap();
    }
}