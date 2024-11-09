package com.hotel.back.controller;

import com.hotel.back.constant.enums.PaymentStatus;
import com.hotel.back.entity.Bill;
import com.hotel.back.entity.Result;
import com.hotel.back.repository.BillInfo;
import com.hotel.back.repository.CheckInInfo;
import com.hotel.back.service.BillService;
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

    @PostMapping("/new")
    public Result<String> newBill(@RequestParam String token,
                          @RequestParam int reservationId,
                          @RequestParam int roomId,
                          @RequestParam int userId,
                          @RequestParam String name2,
                          @RequestParam String idNumber2){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Staff")){
            billService.newBill(reservationId, userId, roomId, name2, idNumber2);
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

}
