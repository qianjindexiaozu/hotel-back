package com.hotel.back.controller;

import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Result;
import com.hotel.back.service.ReservationService;
import com.hotel.back.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@ResponseBody
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @PostMapping("/new")
    public Result<String> newReservation(@RequestParam String token,
                                         @RequestParam String checkInDate,
                                         @RequestParam String checkOutDate,
                                         @RequestParam RoomType roomType) throws Exception {
        String phone = JwtUtil.getPhoneFromToken(token);
        if(phone == null){
            return Result.error("token已过期，请重新登陆！");
        }
        reservationService.newReservation(phone, checkInDate, checkOutDate, roomType);
        return Result.success();
    }
}
