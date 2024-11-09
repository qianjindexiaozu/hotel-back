package com.hotel.back.service.impl;

import com.hotel.back.constant.enums.FeedbackStatus;
import com.hotel.back.constant.enums.PaymentStatus;
import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.entity.Reservation;
import com.hotel.back.entity.User;
import com.hotel.back.mapper.BillMapper;
import com.hotel.back.service.BillService;
import com.hotel.back.service.ReservationService;
import com.hotel.back.service.RoomService;
import com.hotel.back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

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
        Reservation res = reservationService.getReservationById(reservationId);
        PaymentStatus paymentStatus = PaymentStatus.Unpaid;
        FeedbackStatus feedbackStatus = FeedbackStatus.Unfinished;
        billMapper.newBill(reservationId, roomId, u.getName(), u.getIdNumber(), name2,
                            idNumber2, res.getAmount(), paymentStatus, feedbackStatus);
        RoomStatus roomStatus = RoomStatus.Occupied;
        roomService.setRoomStatus(roomId, roomStatus);
    }
}
