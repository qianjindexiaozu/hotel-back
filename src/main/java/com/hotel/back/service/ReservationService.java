package com.hotel.back.service;

import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Reservation;
import com.hotel.back.repository.CheckInInfo;

import java.util.ArrayList;

public interface ReservationService {
    void newReservation(String phone, String inDate, String outDate, RoomType roomType) throws Exception;
    void cancelReservation(String phone, int ReservationId) throws Exception;
    ArrayList<Reservation> getReservations(int userId);
    ArrayList<CheckInInfo> getCheckInInfo();
    Reservation getReservationById(int reservationId);
    int countReservationByDetail(int userId, String inDate, String outDate, RoomType roomType);
}
