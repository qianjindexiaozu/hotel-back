package com.hotel.back.utils;

import redis.clients.jedis.Jedis;


public class RedisUtil {
    private static final Jedis jedis = new Jedis("192.168.228.133", 6379);
    private static final String password = "12345678";
    public RedisUtil(){
        jedis.auth(password);
    }

    public void setValue(String key, Integer time, String value){
        jedis.setex(key, time, value);   // 将验证码存入redis并设置过期时间五分钟
    }

    public String getValue(String key){
        String value = jedis.get(key);
        if (value != null){
            jedis.del(key);
            return value;
        }
        else {
            return null;
        }
    }

    public String getValueWithoutDel(String key){
        return jedis.get(key);
    }

}
