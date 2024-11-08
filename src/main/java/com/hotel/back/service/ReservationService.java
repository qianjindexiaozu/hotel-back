package com.hotel.back.service;

import com.hotel.back.constant.enums.RoomType;

public interface ReservationService {
    void newReservation(String phone, String inDate, String outDate, RoomType roomType) throws Exception;
}
