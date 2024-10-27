package com.hotel.back.entity;

import com.hotel.back.constant.enums.Gender;
import com.hotel.back.constant.enums.Role;
import lombok.Data;

import java.util.Date;


//使用lombok方法在编译阶段自动生成getter等方法
@Data
public class User {
    private Integer userId;
    private String name;
    private Gender gender;
    private String idNumber;
    private String phone;
    private String password;
    private Role role;
    private Date regTime;
    private String userPic;
}

