package com.hotel.back.repository;

import com.hotel.back.constant.enums.FeedbackStatus;
import com.hotel.back.constant.enums.PaymentStatus;
import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomType;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class BillInfo {
    private int billId;
    private int reservationId;
    private int userId;
    private int roomId;
    private String roomNumber;
    private String name1;
    private String idNumber1;
    private String phone;
    private String name2;
    private String idNumber2;
    private RoomType roomType;
    private Date checkInDate;
    private Date checkOutDate;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
    private Date issuedDate;
    private FeedbackStatus feedbackStatus;
    private ReservationStatus reservationStatus;
}
