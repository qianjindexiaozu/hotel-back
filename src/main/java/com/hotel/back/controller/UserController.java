package com.hotel.back.controller;

import com.hotel.back.entity.Result;
import com.hotel.back.entity.User;
import com.hotel.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;    // 注入一个service层对象


    @PostMapping("/login")
    public Result<String> login(@RequestParam String phone, @RequestParam String password){
        //查询用户
//        System.out.println(phone);
        User u = userService.getUserByPhone(phone);
        if(u == null){
            // 用户未注册
            return Result.error("号码未注册");
        }
        else{
            // 登录
            if(u.getPassword().equals(password)){
                return Result.success();
            }
            else {
                return Result.error("密码错误");
            }
        }
    }
}
