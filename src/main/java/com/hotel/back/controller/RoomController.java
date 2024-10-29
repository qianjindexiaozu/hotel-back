package com.hotel.back.controller;

import com.hotel.back.entity.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
public class RoomController {
    @GetMapping("/list")
    public Result<String> list(){
        return Result.success("房间数据");
    }
}
