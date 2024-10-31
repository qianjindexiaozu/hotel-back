package com.hotel.back.service;

import com.hotel.back.entity.User;

public interface UserService {
    User getUserByPhone(String phone);
    String sendVerifyCode(String phone) throws Exception;
}
