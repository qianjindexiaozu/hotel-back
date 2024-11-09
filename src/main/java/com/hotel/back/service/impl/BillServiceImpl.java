package com.hotel.back.service.impl;

import com.hotel.back.constant.enums.FeedbackStatus;
import com.hotel.back.constant.enums.PaymentStatus;
import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.entity.Bill;
import com.hotel.back.entity.Reservation;
import com.hotel.back.entity.User;
import com.hotel.back.mapper.BillMapper;
import com.hotel.back.repository.BillInfo;
import com.hotel.back.repository.FeedbackInfo;
import com.hotel.back.service.BillService;
import com.hotel.back.service.ReservationService;
import com.hotel.back.service.RoomService;
import com.hotel.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationService reservationService;

    @Override
    public void newBill(int reservationId, int roomId, int userId, String name2, String idNumber2) {
        User u = userService.getUserById(userId);
        System.out.println(userId);
        Reservation res = reservationService.getReservationById(reservationId);
        PaymentStatus paymentStatus = PaymentStatus.Unpaid;
        FeedbackStatus feedbackStatus = FeedbackStatus.Unfinished;
        System.out.println(u);
        System.out.println(u.getIdNumber());
        System.out.println(res.getAmount());
        billMapper.newBill(reservationId, roomId, u.getName(), u.getIdNumber(), name2,
                            idNumber2, res.getAmount(), paymentStatus, feedbackStatus);
        RoomStatus roomStatus = RoomStatus.Occupied;
        roomService.setRoomStatus(roomId, roomStatus);
    }

    @Override
    public Bill getBillByReservationId(int reservationId) {
        return billMapper.getBillByReservationId(reservationId);
    }

    @Override
    public ArrayList<BillInfo> getBillInfo(String phone) {
        return billMapper.getBillInfo(phone);
    }

    @Override
    public void confirmPayment(String phone, int billId) {
        PaymentStatus paymentStatus = PaymentStatus.Paid;
        LocalDate localDate = LocalDate.now();
        Date today = Date.valueOf(localDate);
        billMapper.confirmPayment(phone, billId, paymentStatus, today);
    }

    @Override
    public Bill getBillById(int billId) {
        return billMapper.getBillById(billId);
    }

    @Override
    public ArrayList<BillInfo> getCheckOutInfo() {
        ReservationStatus reservationStatus = ReservationStatus.Confirmed;
        return billMapper.getCheckOutInfo(reservationStatus);
    }

    @Override
    public void leave(int billId) {
        Bill b = getBillById(billId);
        int reservationId = b.getReservationId();
        int roomId = b.getRoomId();
        ReservationStatus reservationStatus = ReservationStatus.Completed;
        RoomStatus roomStatus = RoomStatus.Available;
        reservationService.setReservationStatus(reservationId, reservationStatus);
        roomService.setRoomStatus(roomId, roomStatus);
    }

    @Override
    public void setFeedback(String phone, int billId, float rating, String comments) {
        FeedbackStatus feedbackStatus = FeedbackStatus.Finished;
        BillInfo billInfo = billMapper.getBillInfoById(billId);
        int userId = billInfo.getUserId();
        int roomId = billInfo.getRoomId();
        LocalDate localDate = LocalDate.now();
        Date today = Date.valueOf(localDate);
        billMapper.setFeedback(userId, roomId, billId, rating, comments, today);
        billMapper.setFeedbackStatus(phone, billId, feedbackStatus);
    }

    @Override
    public ArrayList<FeedbackInfo> getFeedback() {
        return billMapper.getFeedback();
    }

    @Override
    public ArrayList<BillInfo> getThisMonthInfo() {
        PaymentStatus paymentStatus = PaymentStatus.Paid;
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        Date firstDay = Date.valueOf(firstDayOfMonth);
        Date lastDay = Date.valueOf(lastDayOfMonth);
        return billMapper.getThisMonthInfo(paymentStatus, firstDay, lastDay);
    }


}
