package com.hotel.back.service;

import com.hotel.back.constant.enums.Gender;
import com.hotel.back.entity.User;

public interface UserService {
    User getUserByPhone(String phone);
    User getUserByIdNumber(String IdNumber);
    String sendVerifyCode(String phone, String condition) throws Exception;
    void register(String name, Gender gender, String idNumber, String phone, String password);
    void forget(String phone, String password);
    void changePic(String phone, String pic);
}
