package com.hotel.back.service;

import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;
import com.hotel.back.entity.Result;
import com.hotel.back.entity.Room;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

public interface RoomService {
    Room getRoomByRoomNumber(String roomNumber);
    ArrayList<Price> getPrice();
    BigDecimal getPriceByType(RoomType roomType);
    void setPrice(RoomType roomType, BigDecimal bd);
    ArrayList<Room> getRoom();
    void setRoom(int roomId, String roomNumber, RoomType roomType, RoomStatus status);
    Result<String> deleteRoom(int roomId);
    void newRoom(String roomNumber, RoomType roomType, RoomStatus status);
    boolean questRoom(String checkInDate, String checkOutDate, RoomType roomType);
    ArrayList<Room> getAvailableRoom(RoomType roomType);
}
