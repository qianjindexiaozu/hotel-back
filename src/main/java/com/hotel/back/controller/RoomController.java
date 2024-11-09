package com.hotel.back.controller;

import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;
import com.hotel.back.entity.Result;
import com.hotel.back.entity.Room;
import com.hotel.back.service.RoomService;
import com.hotel.back.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/price")
    public Result<ArrayList<Price>> getPrice(@RequestParam String token){
        Map<String, Object> mp = JwtUtil.parseToken(token);
        if(!mp.get("Error").toString().equals("null")){
            return Result.error(mp.get("Error").toString());
        }
        ArrayList<Price> prices = roomService.getPrice();
        return Result.success(prices);
    }

    @PutMapping("/set_price")
    public  Result<String> setPrice(@RequestParam String token, @RequestParam RoomType roomType, @RequestParam double price){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            BigDecimal bd = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
            roomService.setPrice(roomType, bd);
            if(roomService.getPriceByType(roomType).equals(bd)){
                return Result.success();
            }
            else{
                return  Result.error("修改时出错");
            }
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

    @GetMapping("/list")
    public Result<ArrayList<Room>> getRoom(@RequestParam String token){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            ArrayList<Room> rooms = roomService.getRoom();
            return Result.success(rooms);
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

    @PutMapping("/set_room")
    public Result<String> setRoom(@RequestParam String token,
                                  @RequestParam int roomId,
                                  @RequestParam String roomNumber,
                                  @RequestParam RoomType roomType,
                                  @RequestParam RoomStatus status){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            roomService.setRoom(roomId, roomNumber, roomType, status);
            Room room = roomService.getRoomById(roomId);
            if(room.getRoomNumber().equals(roomNumber) && room.getRoomType().equals(roomType)
            && room.getStatus().equals(status)){
                return Result.success();
            }
            else{
                return Result.error("修改时出错");
            }
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

    @DeleteMapping("/delete")
    public Result<String> deleteRoom(@RequestParam String token,
                                     @RequestParam int roomId){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            roomService.deleteRoom(roomId);
            Room r = roomService.getRoomById(roomId);
            if(r == null){
                return Result.success();
            }
            else {
                return Result.error("删除失败");
            }
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

    @PostMapping("/new")
    public Result<String> newRoom(@RequestParam String token,
                                  @RequestParam String roomNumber,
                                  @RequestParam RoomType roomType,
                                  @RequestParam RoomStatus status){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            Room r = roomService.getRoomByRoomNumber(roomNumber);
            if (r == null){
                roomService.newRoom(roomNumber, roomType, status);
                r = roomService.getRoomByRoomNumber(roomNumber);
                if(r == null){
                    return Result.error("新建房间失败");
                }
                else{
                    return Result.success();
                }
            }
            else {
                return Result.error("房间代号重复");
            }
        }
        else {
            return Result.error("需要管理员账号");
        }
    }

    @GetMapping("/quest")
    public Result<String> questRoom(@RequestParam String token,
                                    @RequestParam String checkInDate,
                                    @RequestParam String checkOutDate,
                                    @RequestParam RoomType roomType){
        Map<String, Object> mp = JwtUtil.parseToken(token);
        if(!mp.get("Error").toString().equals("null")){
            return Result.error(mp.get("Error").toString());
        }
        boolean isAvailable = roomService.questRoom(checkInDate, checkOutDate, roomType);
        if(isAvailable){
            return Result.success();
        }
        else{
            return Result.error("所选日期范围房间不足");
        }
    }

    @GetMapping("/getAvailableRoom")
    public Result<ArrayList<Room>> getAvailableRoom(@RequestParam String token,
                                                    @RequestParam RoomType roomType){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Staff")){
            ArrayList<Room> rooms = roomService.getAvailableRoom(roomType);
            return Result.success(rooms);
        }
        else {
            return Result.error("需要职员账号");
        }
    }

    @GetMapping("/getSumRoomNumber")
    public Result<Integer> getSumRoomNumber(@RequestParam String token){
        String role = JwtUtil.getRoleFromToken(token);
        if(role.equals("Admin")){
            int sumRoomNumber = roomService.getSumRoomNumber();
            return Result.success(sumRoomNumber);
        }
        else {
            return Result.error("需要管理员账号");
        }
    }
}
