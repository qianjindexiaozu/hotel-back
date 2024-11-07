package com.hotel.back.service.impl;

import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;
import com.hotel.back.entity.Result;
import com.hotel.back.entity.Room;
import com.hotel.back.mapper.RoomMapper;
import com.hotel.back.service.BillService;
import com.hotel.back.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private BillService billService;

    @Override
    public Room getRoomByRoomNumber(String roomNumber) {
        return roomMapper.getRoomByRoomNumber(roomNumber);
    }

    @Override
    public ArrayList<Price> getPrice() {
        return roomMapper.getPrice();
    }

    @Override
    public void setPrice(RoomType roomType, BigDecimal bd) {
        roomMapper.setPrice(roomType, bd);
    }

    @Override
    public ArrayList<Room> getRoom() {
        return roomMapper.getRoom();
    }

    @Override
    public void setRoom(int roomId, String roomNumber, RoomType roomType, RoomStatus status) {
        roomMapper.setRoom(roomId, roomNumber, roomType, status);
    }

    @Override
    public Result<String> deleteRoom(int roomId) {
        LocalDate localDate = LocalDate.now();
        Date date = Date.valueOf(localDate);
        Integer billId = billService.getBillIdByRoomIdAndBeforeDate(roomId, date);
        if(billId == null){
            roomMapper.deleteRoom(roomId);
            return Result.success();
        }
        else{
            return Result.error("该房间有订单正在使用");
        }
    }

    @Override
    public void newRoom(String roomNumber, RoomType roomType, RoomStatus status) {
            roomMapper.newRoom(roomNumber, roomType, status);
    }
}
