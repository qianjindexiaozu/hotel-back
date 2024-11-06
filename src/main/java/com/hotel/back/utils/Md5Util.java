package com.hotel.back.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    public String getMD5(String input) {
        try {
            // 创建MessageDigest对象，并指定MD5算法
            MessageDigest md = MessageDigest.getInstance("MD5");

            // 更新数据
            md.update(input.getBytes());

            // 获取最终的MD5哈希值
            byte[] bytes = md.digest();

            // 转换为十六进制字符串
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
