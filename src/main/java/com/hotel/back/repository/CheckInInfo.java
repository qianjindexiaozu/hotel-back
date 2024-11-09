package com.hotel.back.repository;

import com.hotel.back.constant.enums.RoomType;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class CheckInInfo {
    private int userId;
    private int reservationId;
    private String name;
    private String idNumber;
    private String phone;
    private RoomType roomType;
    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal amount;
}
