package com.hotel.back.service;

import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Reservation;

import java.util.ArrayList;

public interface ReservationService {
    void newReservation(String phone, String inDate, String outDate, RoomType roomType) throws Exception;
    void cancelReservation(String phone, int ReservationId) throws Exception;
    ArrayList<Reservation> getReservations(int userId);
}
