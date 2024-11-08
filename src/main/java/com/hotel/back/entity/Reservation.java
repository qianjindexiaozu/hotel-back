package com.hotel.back.entity;

import com.hotel.back.constant.enums.FeedbackStatus;
import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomType;
import lombok.Data;

import java.util.Date;

@Data
public class Reservation {
    private Integer reservationId;
    private Integer userId;
    private RoomType roomType;
    private Date checkInDate;
    private Date checkOutDate;
    private ReservationStatus reservationStatus;
    private FeedbackStatus feedBackStatus;
}
