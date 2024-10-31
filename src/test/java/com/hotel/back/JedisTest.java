package com.hotel.back;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {
    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("192.168.228.133", 6379);
        jedis.auth("12345678");

        jedis.set("username", "Andy");

        String value = jedis.get("username");
        jedis.del("username");
        System.out.println(value);

        jedis.hset("VerifyCode", "aliyun", "232323");

        value = jedis.hget("VerifyCode", "aliyun");
        System.out.println(value);

        jedis.close();
    }
}
