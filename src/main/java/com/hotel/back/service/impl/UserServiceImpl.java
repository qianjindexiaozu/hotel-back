package com.hotel.back.service.impl;

import com.hotel.back.entity.Result;
import com.hotel.back.entity.User;
import com.hotel.back.mapper.UserMapper;
import com.hotel.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service    // 将当前对象注册到Service层
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }
}
