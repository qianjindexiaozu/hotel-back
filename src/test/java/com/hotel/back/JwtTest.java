package com.hotel.back;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGen(){
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");

        // 生成jwt的代码
        String token = JWT.create()
                .withClaim("username", claims)  // 添加负载
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))   //添加过期时间
                .sign(Algorithm.HMAC256("qianjindexiaozu"));
        System.out.println(token);
    }

    @Test
    public void testParse() {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6eyJpZCI6MSwidXNlcm5hbWUiOiLlvKDkuIkifSwiZXhwIjoxNzMwMDA5OTM3fQ.mtB-ACK5Fi9zPQizw-16-Hwge2cbW-Ct8R0bEONTj7c";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("qianjindexiaozu")).build();

        try{
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            Map<String, Claim> claims = decodedJWT.getClaims();
            System.out.println(claims.get("username"));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        // 篡改头部和载荷等数据，验证会失败
        // 密钥不正确，验证会失败
        // token过期，验证会失败
    }
}
