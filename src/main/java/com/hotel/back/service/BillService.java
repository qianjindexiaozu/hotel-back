package com.hotel.back.service;

import com.hotel.back.entity.Bill;
import com.hotel.back.repository.BillInfo;

import java.util.ArrayList;

public interface BillService {
    void newBill(int reservationId, int roomId, int userId, String name2, String idNumber2);
    Bill getBillByReservationId(int reservationId);
    ArrayList<BillInfo> getBillInfo(String phone);
    void confirmPayment(String phone, int billId);
    Bill getBillById(int billId);
    ArrayList<BillInfo> getCheckOutInfo();
    void leave(int billId);
}
