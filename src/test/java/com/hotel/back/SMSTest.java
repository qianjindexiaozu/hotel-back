package com.hotel.back;

import com.hotel.back.utils.SMS;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class SMSTest {
//    @Test
//    public void SendCode() throws Exception {
//        SMS sms = new SMS();
//        String result = sms.sendCode("18856052309");
//        System.out.println(result);
//
//    }

    @Test
    public void stringAppend() {
        Random random = new Random();
        StringBuilder captcha = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            captcha.append(random.nextInt(10)); // 生成0-9的随机数
        }
        String code = captcha.toString();
        String codeTemp = "{\"code\":\"" + code + "\"}";
        System.out.println(codeTemp);
    }
}
