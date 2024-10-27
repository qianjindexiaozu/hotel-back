package com.hotel.back.entity;

import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.constant.enums.RoomType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Room {
    private Integer roomId;
    private String roomNumber;
    private RoomType roomType;
    private RoomStatus roomStatus;
    private BigDecimal roomPrice;
}
