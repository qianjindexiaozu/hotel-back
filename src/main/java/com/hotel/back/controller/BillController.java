package com.hotel.back.controller;

import com.hotel.back.entity.Result;
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
            return Result.success();
        }
        else {
            return Result.error("需要职员账号");
        }
    }
}
