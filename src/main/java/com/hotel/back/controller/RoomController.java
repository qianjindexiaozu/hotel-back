package com.hotel.back.controller;

import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;
import com.hotel.back.entity.Result;
import com.hotel.back.service.RoomService;
import com.hotel.back.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@RestController
@ResponseBody
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/list")
    public Result<String> list(){
        return Result.success("房间数据");
    }

    @GetMapping("/price")
    public Result<ArrayList<Price>> getPrice(@RequestParam String token){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            ArrayList<Price> prices = roomService.getPrice();
            return Result.success(prices);
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

    @PutMapping("/set_price")
    public  Result<String> setPrice(@RequestParam String token, @RequestParam RoomType roomType, @RequestParam double price){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
            roomService.setPrice(roomType, bd);
            return Result.success();
        }
        else {
            return Result.error("需要管理员账号");
        }
    }
}
