package com.hotel.back.service.impl;

import com.hotel.back.constant.enums.Gender;
import com.hotel.back.constant.enums.Role;
import com.hotel.back.entity.User;
import com.hotel.back.mapper.UserMapper;
import com.hotel.back.service.UserService;
import com.hotel.back.utils.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service    // 将当前对象注册到Service层
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }

    @Override
    public User getUserByIdNumber(String IdNumber) {
        return userMapper.getUserByIdNumber(IdNumber);
    }

    @Override
    public String sendVerifyCode(String phone, String condition) throws Exception {
        SMS sms = new SMS();
        return sms.sendCode(phone, condition);
    }

    @Override
    public void register(String name, Gender gender, String idNumber, String phone, String password) {
        Role role = Role.Customer;
        LocalDate now = LocalDate.now();
        Date reg_time = Date.valueOf(now);
        String user_pic = "default";
        userMapper.register(name, gender, idNumber, phone, password, role, reg_time, user_pic);
    }

    @Override
    public void forget(String phone, String password) {
        userMapper.forget(phone, password);
    }

    @Override
    public void changePic(String phone, String pic) {
        userMapper.changePic(phone, pic);
    }


}
