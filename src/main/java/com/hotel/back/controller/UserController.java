package com.hotel.back.controller;

import com.auth0.jwt.interfaces.Claim;
import com.hotel.back.constant.enums.Gender;
import com.hotel.back.entity.Result;
import com.hotel.back.entity.User;
import com.hotel.back.service.UserService;
import com.hotel.back.utils.JwtUtil;
import com.hotel.back.utils.RedisUtil;
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
            System.out.println(u);
            if(u.getPassword().equals(password)){
                Map<String, Object> claims = new HashMap<>();
                claims.put("phone", u.getPhone());
                claims.put("name", u.getName());
                claims.put("idNumber", u.getIdNumber());
                claims.put("gender", u.getGender().toString());
                claims.put("role", u.getRole().toString());
                claims.put("pic", u.getUserPic());
                String token = JwtUtil.genToken(claims);
                RedisUtil redisUtil = new RedisUtil();
                redisUtil.setValue(phone + "_token", 60 * 60 * 6, token);
                return Result.success(token);
            }
            else {
                return Result.error("密码错误");
            }
        }
    }

    @PostMapping("/register")
    public Result<String> register(@RequestParam String name, @RequestParam Gender gender, @RequestParam String idNumber, @RequestParam String phone, @RequestParam String password, @RequestParam String verifyCode){
        User u = userService.getUserByIdNumber(idNumber);
        if(u != null){
            return Result.error("此身份证号码已注册");
        }
        RedisUtil redisUtil = new RedisUtil();
        if(verifyCode.equals(redisUtil.getValue(phone))){
            userService.register(name, gender, idNumber, phone, password);
            u = userService.getUserByPhone(phone);
            if(u != null){
                // 注册成功
                return Result.success("注册成功");
            }
            else{
                return Result.error("注册失败");
            }
        }
        else{
            return Result.error("验证码错误！请重新获取验证码注册！");
        }
    }

    @PostMapping("/register_sms")
    public Result<String> register_SMS(@RequestParam String phone) throws Exception {
        //查询用户
        User u = userService.getUserByPhone(phone);
        if(u != null){
            // 用户已注册
            return Result.error("号码已注册");
        }
        else{
            String result = userService.sendVerifyCode(phone, "register");
            if(result.equals("OK")){
                return Result.success();
            }
            else{
                return Result.error(result);
            }
        }
    }

    @PostMapping("/forget")
    public Result<String> forget(@RequestParam String phone, @RequestParam String verifyCode, @RequestParam String password) {
        RedisUtil redisUtil = new RedisUtil();
        if(verifyCode.equals(redisUtil.getValue(phone))){
            userService.forget(phone, password);
            return Result.success();
        }
        else{
            return Result.error("验证码错误！请重新获取验证码注册！");
        }
    }

    @PostMapping("/forget_sms")
    public Result<String> forget_sms(@RequestParam String phone) throws Exception {
        User u = userService.getUserByPhone(phone);
        if(u == null){
            return Result.error("该号码还未注册");
        }
        else{
            String result = userService.sendVerifyCode(phone, "forget");
            if(result.equals("OK")){
                return Result.success();
            }
            else{
                return Result.error(result);
            }
        }
    }

    @PostMapping("/change_pic")
    public Result<String> changePic(@RequestParam String token, @RequestParam String pic){
        Map<String, Object> map = JwtUtil.parseToken(token);
        String phone = map.get("phone").toString();
        userService.changePic(phone, pic);
        return Result.success();
    }

}
