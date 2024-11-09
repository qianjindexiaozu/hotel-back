package com.hotel.back.service.impl;

import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;
import com.hotel.back.entity.Result;
import com.hotel.back.entity.Room;
import com.hotel.back.mapper.RoomMapper;
import com.hotel.back.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public Room getRoomByRoomNumber(String roomNumber) {
        return roomMapper.getRoomByRoomNumber(roomNumber);
    }

    @Override
    public ArrayList<Price> getPrice() {
        return roomMapper.getPrice();
    }

    @Override
    public BigDecimal getPriceByType(RoomType roomType) {
        return roomMapper.getPriceByType(roomType);
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
    public void deleteRoom(int roomId) {
        roomMapper.deleteRoom(roomId);
    }

    @Override
    public void newRoom(String roomNumber, RoomType roomType, RoomStatus status) {
        roomMapper.newRoom(roomNumber, roomType, status);
    }

    @Override
    public boolean questRoom(String inDate, String outDate, RoomType roomType) {
        Date checkInDate = Date.valueOf(inDate);
        Date checkOutDate = Date.valueOf(outDate);
        ReservationStatus confirmedStatus = ReservationStatus.Confirmed;
        RoomStatus roomStatus = RoomStatus.Maintenance;
        int confirmedNumber = roomMapper.countNumber(checkInDate, checkOutDate, roomType, confirmedStatus);
        int availableNumber = roomMapper.countAvailable(roomType, roomStatus);
        System.out.println(confirmedNumber);
        System.out.println(availableNumber);
        return availableNumber > confirmedNumber;
    }

    @Override
    public ArrayList<Room> getAvailableRoom(RoomType roomType) {
        RoomStatus roomStatus = RoomStatus.Available;
        return roomMapper.getAvailableRoom(roomStatus, roomType);
    }

    @Override
    public void setRoomStatus(int roomId, RoomStatus roomStatus) {
        roomMapper.setRoomStatus(roomId, roomStatus);
    }

    @Override
    public Room getRoomById(int roomId) {
        return roomMapper.getRoomById(roomId);
    }
}
