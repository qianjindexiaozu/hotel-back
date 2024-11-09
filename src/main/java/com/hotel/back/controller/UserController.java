package com.hotel.back.controller;

import com.hotel.back.constant.enums.Gender;
import com.hotel.back.entity.Price;
import com.hotel.back.entity.Result;
import com.hotel.back.entity.User;
import com.hotel.back.service.UserService;
import com.hotel.back.utils.JwtUtil;
import com.hotel.back.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@RestController
@ResponseBody
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
                String token = JwtUtil.genTokenByUser(u);
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
    public Result<String> register(@RequestParam String name,
                                   @RequestParam Gender gender,
                                   @RequestParam String idNumber,
                                   @RequestParam String phone,
                                   @RequestParam String password,
                                   @RequestParam String verifyCode){
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
    public Result<String> forget(@RequestParam String phone,
                                 @RequestParam String verifyCode,
                                 @RequestParam String password) {
        RedisUtil redisUtil = new RedisUtil();
        if(verifyCode.equals(redisUtil.getValue(phone))){
            userService.forget(phone, password);
            User u = userService.getUserByPhone(phone);
            if(u.getPassword().equals(password)){
                return Result.success();
            }
            else{
                return Result.error("密码修改失败");
            }
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

    private static final String UPLOAD_DIR = "E:/HFUT/projects/Hotel-Management-System/back/src/main/resources/static/assets/avatars/";

    @PostMapping("/change_pic")
    public Result<String> handleAvatarUpload(@RequestHeader("Authorization") String token,
                                             @RequestParam("file") MultipartFile file) {
        String phone = JwtUtil.getPhoneFromToken(token);
        if (phone == null) {
            return Result.error("登录超时，请重新登录");
        }

        // 检查文件是否为空
        if (file.isEmpty()) {
            return Result.error("文件为空");
        }

        // 检查文件类型
        String fileType = file.getContentType();
        if (!"image/jpeg".equals(fileType) && !"image/png".equals(fileType)) {
            return Result.error("头像必须为 JPG/PNG 格式");
        }

        // 检查文件大小
        if (file.getSize() > 2 * 1024 * 1024) { // 2MB
            return Result.error("头像文件大小不能超过 2MB");
        }

        // 确保目标目录存在
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        // 获取文件名并构造目标文件路径
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path targetPath = Paths.get(uploadDir.getAbsolutePath(), filename);

        // 保存文件
        try {
            Files.copy(file.getInputStream(), targetPath);
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("头像上传失败");
        }

        userService.changePic(phone, filename);
        User u = userService.getUserByPhone(phone);

        if(u.getUserPic().equals(filename)){
            token = JwtUtil.genTokenByUser(u);
            // 返回文件名或其他信息
            return Result.success(token);
        }
        else{
            return Result.error("头像修改失败");
        }

    }


    @PostMapping("/changeInfo_sms")
    public Result<String> changeInfoSMS(@RequestParam String phone) throws Exception {
        String result = userService.sendVerifyCode(phone, "changeInfo");
        if(result.equals("OK")){
            return Result.success();
        }
        else{
            return Result.error(result);
        }
    }

    @PutMapping("/changeInfo")
    public Result<String> changeInfo(@RequestParam String token,
                                     @RequestParam String name,
                                     @RequestParam Gender gender,
                                     @RequestParam String idNumber,
                                     @RequestParam String phone,
                                     @RequestParam String verifyCode){
        String originalPhone = JwtUtil.getPhoneFromToken(token);
        if(originalPhone == null){
            return Result.error("登录超时，请重新登陆");
        }
        RedisUtil redisUtil = new RedisUtil();
        if(verifyCode.equals(redisUtil.getValue(phone))){
            userService.changeInfo(name, gender, idNumber, phone, originalPhone);
            User u = userService.getUserByPhone(phone);
            if(u == null){
                return Result.error("修改信息出错，请重新登陆");
            }
            if(u.getName().equals(name) && u.getGender().equals(gender)
            && u.getIdNumber().equals(idNumber)){
                token = JwtUtil.genTokenByUser(u);
                return Result.success(token);
            }
            else {
                return Result.error("修改信息出错，请重新登陆");
            }

        }
        else{
            return Result.error("验证码错误！请重新获取验证码并验证！");
        }
    }

    @PutMapping("/changePassword")
    public Result<String> changePassword(@RequestParam String token, @RequestParam String password){
        String phone = JwtUtil.getPhoneFromToken(token);
        if(phone == null){
            return Result.error("token验证有误，请重新登陆");
        }
        userService.changePassword(phone, password);
        User u = userService.getUserByPhone(phone);
        if(u.getPassword().equals(password)){
            return Result.success();
        }
        else{
            return Result.error("密码修改失败");
        }
    }

    @GetMapping("/get_staff")
    public Result<ArrayList<User>> getStaff(@RequestParam String token){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            ArrayList<User> staff = userService.getStaff();
            return Result.success(staff);
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

    @PutMapping("/set_staff")
    public  Result<String> setStaff(@RequestParam String token,
                                    @RequestParam int userId,
                                    @RequestParam String name,
                                    @RequestParam Gender gender,
                                    @RequestParam String idNumber,
                                    @RequestParam String phone){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            userService.setStaff(userId, name, gender, idNumber, phone);
            User u = userService.getUserById(userId);
            if(u.getName().equals(name) && u.getGender().equals(gender) &&
            u.getIdNumber().equals(idNumber) && u.getPhone().equals(phone)){
                return Result.success();
            }
            else{
                return Result.error("信息修改出错");
            }
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

    @DeleteMapping("/delete_staff")
    public Result<String> deleteStaff(@RequestParam String token, @RequestParam int userId){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            userService.deleteStaff(userId);
            User u = userService.getUserById(userId);
            if(u == null){
                return Result.success();
            }
            else{
                return Result.error("删除失败");
            }
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

    @PostMapping("/new_staff")
    public Result<String> newStaff(@RequestParam String token,
                                   @RequestParam String name,
                                   @RequestParam Gender gender,
                                   @RequestParam String idNumber,
                                   @RequestParam String phone){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            userService.newStaff(name, gender, idNumber, phone);
            User u = userService.getUserByPhone(phone);
            System.out.println(u);
            if(u == null){
                return Result.error("新建职员失败");
            }
            else{
                return Result.success();
            }
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

}
