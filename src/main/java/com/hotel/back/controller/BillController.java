package com.hotel.back.controller;

import com.hotel.back.constant.enums.*;
import com.hotel.back.entity.Bill;
import com.hotel.back.entity.Reservation;
import com.hotel.back.entity.Result;
import com.hotel.back.entity.Room;
import com.hotel.back.repository.BillInfo;
import com.hotel.back.repository.FeedbackInfo;
import com.hotel.back.service.BillService;
import com.hotel.back.service.ReservationService;
import com.hotel.back.service.RoomService;
import com.hotel.back.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@ResponseBody
@RequestMapping("/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @PostMapping("/new")
    public Result<String> newBill(@RequestParam String token,
                          @RequestParam int reservationId,
                          @RequestParam int roomId,
                          @RequestParam int userId,
                          @RequestParam String name2,
                          @RequestParam String idNumber2){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Staff")){
            billService.newBill(reservationId, roomId, userId, name2, idNumber2);
            Bill b = billService.getBillByReservationId(reservationId);
            if(b == null){
                return Result.error("入住失败");
            }
            else{
                return Result.success();
            }
        }
        else {
            return Result.error("需要职员账号");
        }
    }

    @GetMapping("/info")
    public Result<ArrayList<BillInfo>> getBillInfo(@RequestParam String token){
        String phone = JwtUtil.getPhoneFromToken(token);
        if(phone == null){
            return Result.error("token已过期，请重新登陆");
        }
        else{
            ArrayList<BillInfo> billInfos = billService.getBillInfo(phone);
            return Result.success(billInfos);
        }
    }

    @PutMapping("/pay")
    public Result<String> confirmPayment(@RequestParam String token,
                                         @RequestParam int billId){
        String phone = JwtUtil.getPhoneFromToken(token);
        if(phone == null){
            return Result.error("token已过期，请重新登陆");
        }
        else{
            billService.confirmPayment(phone, billId);
            Bill b = billService.getBillById(billId);
            if(b.getPaymentStatus().equals(PaymentStatus.Paid)){
                return Result.success();
            }
            else{
                return Result.error("支付时出错");
            }
        }
    }

    @GetMapping("/checkOutInfo")
    public Result<ArrayList<BillInfo>> getCheckOutInfo(@RequestParam String token){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Staff")){
            ArrayList<BillInfo> billInfos = billService.getCheckOutInfo();
            return Result.success(billInfos);
        }
        else {
            return Result.error("需要职员账号");
        }
    }

    @PutMapping("/leave")
    public Result<String> leave(@RequestParam String token,
                                @RequestParam int billId){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Staff")){
            billService.leave(billId);
            Bill b = billService.getBillById(billId);
            Reservation res = reservationService.getReservationById(b.getReservationId());
            Room room = roomService.getRoomById(b.getRoomId());
            if(res.getReservationStatus().equals(ReservationStatus.Completed)
                    && room.getStatus().equals(RoomStatus.Available)){
                return Result.success();
            }
            else{
                return Result.error("出现了一些问题");
            }
        }
        else {
            return Result.error("需要职员账号");
        }
    }

    @PostMapping("/setFeedback")
    public Result<String> setFeedback(@RequestParam String token,
                                      @RequestParam int billId,
                                      @RequestParam float rating,
                                      @RequestParam String feedbackText){
        String phone = JwtUtil.getPhoneFromToken(token);
        if(phone == null){
            return Result.error("token已过期，请重新登陆");
        }
        else{
            billService.setFeedback(phone, billId, rating, feedbackText);
            Bill b = billService.getBillById(billId);
            if(b.getFeedbackStatus().equals(FeedbackStatus.Finished)){
                return Result.success();
            }
            else{
                return Result.error("评价出错");
            }
        }
    }

    @GetMapping("/getFeedback")
    public Result<ArrayList<FeedbackInfo>> getFeedbackInfo(@RequestParam String token){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            ArrayList<FeedbackInfo> feedbackInfos = billService.getFeedback();
            return Result.success(feedbackInfos);
        }
        else{
            return Result.error("需要管理员账号");
        }
    }

    @GetMapping("/getThisMonthInfo")
    public Result<ArrayList<BillInfo>> getThisMonthInfo(@RequestParam String token){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            ArrayList<BillInfo> billInfos = billService.getThisMonthInfo();
//            System.out.println(billInfos);
            return Result.success(billInfos);
        }
        else{
            return Result.error("需要管理员账号");
        }
    }

    @GetMapping("/getFeedbackByBill")
    public Result<FeedbackInfo> getFeedbackByBill(@RequestParam String token,
                                                  @RequestParam int billId){
        String phone = JwtUtil.getPhoneFromToken(token);
        if(phone == null){
            return Result.error("token已过期，请重新登陆");
        }
        else{
            FeedbackInfo feedbackInfo = billService.getFeedbackByBill(phone, billId);
            if(feedbackInfo == null){
                return Result.error("查询失败");
            }
            else{
                return Result.success(feedbackInfo);
            }
        }
    }
}
