package com.hotel.back.controller;

import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Reservation;
import com.hotel.back.entity.Result;
import com.hotel.back.entity.User;
import com.hotel.back.repository.CheckInInfo;
import com.hotel.back.service.ReservationService;
import com.hotel.back.service.UserService;
import com.hotel.back.utils.JwtUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


@RestController
@ResponseBody
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

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

    @GetMapping("/list")
    public Result<ArrayList<Reservation>> getReservations(@RequestParam String token){
        String phone = JwtUtil.getPhoneFromToken(token);
        if(phone == null){
            return Result.error("token已过期，请重新登陆！");
        }
        User u = userService.getUserByPhone(phone);
        ArrayList<Reservation> reservations = reservationService.getReservations(u.getUserId());
        return Result.success(reservations);
    }

    @PutMapping("/cancel")
    public Result<String> cancelReservation(@RequestParam String token,
                                            @RequestParam int reservationId) throws Exception {
        String phone = JwtUtil.getPhoneFromToken(token);
        if(phone == null){
            return Result.error("token已过期，请重新登陆！");
        }
        reservationService.cancelReservation(phone, reservationId);
        return Result.success();
    }

    @GetMapping("/getCheckInInfo")
    public Result<ArrayList<CheckInInfo>> getCheckInInfo(@RequestParam String token){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Staff")){
            ArrayList<CheckInInfo> checkInInfos = reservationService.getCheckInInfo();
            return Result.success(checkInInfos);
        }
        else {
            return Result.error("需要职员账号");
        }
    }
}
