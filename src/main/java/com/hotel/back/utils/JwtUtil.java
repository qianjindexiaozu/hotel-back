package com.hotel.back.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.hotel.back.entity.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private static final String KEY = "qianjindexiaozu";

    // 接收业务数据，生成token并返回
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60))   // 1小时过期
                .sign(Algorithm.HMAC256(KEY));
    }

    public static String genTokenByUser(User u){
        Map<String, Object> claims = new HashMap<>();
        claims.put("phone", u.getPhone());
        claims.put("name", u.getName());
        claims.put("idNumber", u.getIdNumber());
        claims.put("gender", u.getGender().toString());
        claims.put("role", u.getRole().toString());
        claims.put("pic", u.getUserPic());
        return genToken(claims);
    }

    // 接收token，验证token，并返回业务数据
    public static Map<String, Object> parseToken(String token) {
        JWTVerifier jwtVerifier =  JWT.require(Algorithm.HMAC256(KEY)).build();
        try{
            Map<String, Object> mp = jwtVerifier.verify(token).getClaim("claims").asMap();
            mp.put("Error", "null");
            return mp;
        } catch (Exception e){
            Map<String, Object> mp = new HashMap<>();
            mp.put("Error", "token已过期");
            return mp;
        }
    }

    public static String getPhoneFromToken(String token) {
        Map<String, Object> mp = parseToken(token);
        String err = mp.get("Error").toString();
        if(!err.equals("null")){
            return null;
        }
        else{
            return mp.get("phone").toString();
        }
    }
}
