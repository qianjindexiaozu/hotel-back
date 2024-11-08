package com.hotel.back.mapper;

import com.hotel.back.constant.enums.FeedbackStatus;
import com.hotel.back.constant.enums.PaymentStatus;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.sql.Date;

@Mapper
public interface BillMapper {

    @Insert("insert into bills (reservation_id, room_id, name1, id_number1, name2, id_number2, amount, payment_status, feedback_status) " +
            "values (#{reservationId}, #{roomId}, #{name1}, #{idNumber1}, #{name2}, #{idNumber2}, #{amount}, #{paymentStatus}, #{feedbackStatus})")
    void newBill(@Param("reservationId") int reservationId,
                 @Param("roomId") int roomId,
                 @Param("name1") String name1,
                 @Param("idNumber") String idNumber1,
                 @Param("name2") String name2,
                 @Param("idNumber2") String idNumber2,
                 @Param("amount")BigDecimal amount,
                 @Param("paymentStatus")PaymentStatus paymentStatus,
                 @Param("feedbackStatus")FeedbackStatus feedbackStatus);
}
