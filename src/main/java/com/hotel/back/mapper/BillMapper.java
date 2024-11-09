package com.hotel.back.mapper;

import com.hotel.back.constant.enums.FeedbackStatus;
import com.hotel.back.constant.enums.PaymentStatus;
import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.entity.Bill;
import com.hotel.back.repository.BillInfo;
import com.hotel.back.repository.FeedbackInfo;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

@Mapper
public interface BillMapper {

    @Insert("insert into bills (reservation_id, room_id, name1, id_number1, name2, id_number2, amount, payment_status, feedback_status) " +
            "values (#{reservationId}, #{roomId}, #{name1}, #{idNumber1}, #{name2}, #{idNumber2}, #{amount}, #{paymentStatus}, #{feedbackStatus})")
    void newBill(@Param("reservationId") int reservationId,
                 @Param("roomId") int roomId,
                 @Param("name1") String name1,
                 @Param("idNumber1") String idNumber1,
                 @Param("name2") String name2,
                 @Param("idNumber2") String idNumber2,
                 @Param("amount")BigDecimal amount,
                 @Param("paymentStatus")PaymentStatus paymentStatus,
                 @Param("feedbackStatus")FeedbackStatus feedbackStatus);

    @Select("select * from bills where reservation_id=#{reservationId}")
    Bill getBillByReservationId(@Param("reservationId") int reservationId);

    @Select("select * from bill_info where phone=#{phone}")
    ArrayList<BillInfo> getBillInfo(@Param("phone") String phone);

    @Update("update bill_info set payment_status=#{paymentStatus}, issued_date=#{issuedDate} where phone=#{phone} and bill_id=#{billId}")
    void confirmPayment(@Param("phone") String phone,
                        @Param("billId") int billId,
                        @Param("paymentStatus") PaymentStatus paymentStatus,
                        @Param("issuedDate")Date today);

    @Select("select * from bills where bill_id=#{billId}")
    Bill getBillById(@Param("billId") int billId);

    @Select("select * from bill_info where reservation_status=#{reservationStatus}")
    ArrayList<BillInfo> getCheckOutInfo(@Param("reservationStatus")ReservationStatus reservationStatus);

    @Select("select * from bill_info where bill_id=#{billId}")
    BillInfo getBillInfoById(@Param("billId") int billId);

    @Insert("insert into feedback (user_id, room_id, bill_id, rating, comments, submitted_date)" +
            "values (#{userId}, #{roomId}, #{billId}, #{rating}, #{commits}, #{submittedDate})")
    void setFeedback(@Param("userId")int userId,
                     @Param("roomId")int roomId,
                     @Param("billId")int billId,
                     @Param("rating")float rating,
                     @Param("commits")String commits,
                     @Param("submittedDate")Date submittedDate);

    @Update("update bill_info set feedback_status=#{feedbackStatus} where bill_id=#{billId} and phone=#{phone}")
    void setFeedbackStatus(@Param("phone") String phone,
                           @Param("billId") int billId,
                           @Param("feedbackStatus") FeedbackStatus feedbackStatus);

    @Select("select * from feedback_info")
    ArrayList<FeedbackInfo> getFeedback();

    @Select("select * from bill_info where payment_status=#{paymentStatus} and issued_date>=#{firstDay} and issued_date<=#{lastDay}")
    ArrayList<BillInfo> getThisMonthInfo(@Param("paymentStatus") PaymentStatus paymentStatus,
                                         @Param("firstDay") Date firstDay,
                                         @Param("lastDay") Date lastDay);
}
