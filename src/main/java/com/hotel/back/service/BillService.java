package com.hotel.back.service;

public interface BillService {
    void newBill(int reservationId, int roomId, int userId, String name2, String idNumber2);
}
