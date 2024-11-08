package com.hotel.back.service.impl;

import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.User;
import com.hotel.back.mapper.ReservationMapper;
import com.hotel.back.service.ReservationService;
import com.hotel.back.service.RoomService;
import com.hotel.back.service.UserService;
import com.hotel.back.utils.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Override
    public void newReservation(String phone, String inDate, String outDate, RoomType roomType) throws Exception {
        User u = userService.getUserByPhone(phone);
        ReservationStatus reservationStatus = ReservationStatus.Confirmed;

        Date checkInDate = Date.valueOf(inDate);
        Date checkOutDate = Date.valueOf(outDate);

        // 将 java.sql.Date 转换为 java.time.LocalDate
        LocalDate checkInLocalDate = checkInDate.toLocalDate();
        LocalDate checkOutLocalDate = checkOutDate.toLocalDate();

        // 计算两个日期之间的天数差
        long daysBetween = ChronoUnit.DAYS.between(checkInLocalDate, checkOutLocalDate);
        BigDecimal between = BigDecimal.valueOf(daysBetween);
        BigDecimal account = roomService.getPriceByType(roomType).multiply(between)
                                        .setScale(2, RoundingMode.HALF_UP);  // 保留2位小数，四舍五入;


        reservationMapper.newReservation(u.getUserId(), roomType, checkInDate, checkOutDate, reservationStatus, account);
        SMS sms = new SMS();
        sms.sendInfo(phone, "confirmed");
    }
}
