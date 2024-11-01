package com.hotel.back.service;

import com.hotel.back.constant.enums.Gender;
import com.hotel.back.entity.User;

public interface UserService {
    User getUserByPhone(String phone);
    String sendVerifyCode(String phone) throws Exception;
    void register(String name, Gender gender, String idNumber, String phone, String password);
}
