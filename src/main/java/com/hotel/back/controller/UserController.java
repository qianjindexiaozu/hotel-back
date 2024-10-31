package com.hotel.back.controller;

import com.hotel.back.entity.Result;
import com.hotel.back.entity.User;
import com.hotel.back.service.UserService;
import com.hotel.back.utils.JwtUtil;
import com.hotel.back.utils.RedisUtil;
import com.hotel.back.utils.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;    // 注入一个service层对象


    @PostMapping("/login")
    public Result<String> login(@RequestParam String phone, @RequestParam String password){
        //查询用户
        User u = userService.getUserByPhone(phone);
        if(u == null){
            // 用户未注册
            return Result.error("号码未注册");
        }
        else{
            // 登录
            if(u.getPassword().equals(password)){
                Map<String, Object> claims = new HashMap<>();
                claims.put("name", u.getName());
                claims.put("idNumber", u.getIdNumber());
                claims.put("gender", u.getGender().toString());
                claims.put("role", u.getRole().toString());
                claims.put("pic", u.getUserPic());
                String token = JwtUtil.genToken(claims);
                return Result.success(token);
            }
            else {
                return Result.error("密码错误");
            }
        }
    }

    @PostMapping("/register")
    public Result<String> register(){
        return null;
    }

    @PostMapping("/register_sms")
    public Result<String> SMS(@RequestParam String phone) throws Exception {
        //查询用户
        User u = userService.getUserByPhone(phone);
        if(u != null){
            // 用户已注册
            return Result.error("号码已注册");
        }
        else{
            String result = userService.sendVerifyCode(phone);
            if(result.equals("OK")){
                return Result.success();
            }
            else{
                return Result.error(result);
            }
        }
    }

}
