package com.hotel.back.service.impl;

import com.hotel.back.constant.enums.Gender;
import com.hotel.back.constant.enums.Role;
import com.hotel.back.entity.User;
import com.hotel.back.mapper.UserMapper;
import com.hotel.back.service.UserService;
import com.hotel.back.utils.Md5Util;
import com.hotel.back.utils.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

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

    @Override
    public void changeInfo(String name, Gender gender, String idNumber, String phone, String originalPhone) {
        userMapper.changeInfo(name, gender, idNumber, phone, originalPhone);
    }

    @Override
    public void changePassword(String phone, String password) {
        userMapper.changePassword(phone, password);
    }

    @Override
    public ArrayList<User> getStaff() {
        return userMapper.getStaff();
    }

    @Override
    public void setStaff(int userId, String name, Gender gender, String idNumber, String phone){
        userMapper.setStaff(userId, name, gender, idNumber, phone);
    }

    @Override
    public void deleteStaff(int userId) {
        userMapper.deleteStaff(userId);
    }

    @Override
    public void newStaff(String name, Gender gender, String idNumber, String phone) {
        Md5Util md5Util = new Md5Util();
        String password = md5Util.getMD5("abc123");
        Role role = Role.Staff;
        String userPic = "default";
        userMapper.newStaff(name, gender, idNumber, phone, password, role, userPic);
    }
}
