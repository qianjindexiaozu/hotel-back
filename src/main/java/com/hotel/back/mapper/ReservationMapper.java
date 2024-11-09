package com.hotel.back.mapper;

import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Reservation;
import com.hotel.back.repository.CheckInInfo;
import com.hotel.back.utils.BigDecimalTypeHandler;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

@Mapper
public interface ReservationMapper {

    @Insert("insert into reservations (user_id, room_type, check_in_date, check_out_date, reservation_status, amount) " +
            "values (#{userId}, #{roomType}, #{checkInDate}, #{checkOutDate}, #{reservationStatus}, #{amount})")
    void newReservation(@Param("userId") int userId,
                        @Param("roomType") RoomType roomType,
                        @Param("checkInDate") Date checkInDate,
                        @Param("checkOutDate") Date checkOutDate,
                        @Param("reservationStatus")ReservationStatus reservationStatus,
                        @Param("amount")BigDecimal amount);

    @Update("update reservations set reservation_status=#{reservationStatus} where reservation_id=#{reservationId}")
    void cancelReservation(@Param("reservationId") int reservationId,
                           @Param("reservationStatus") ReservationStatus reservationStatus);

    @Select("select * from checkininfo where user_id=#{userId}")
    @Results({
            @Result(property = "account", column = "account",
                    javaType = BigDecimal.class,
                    typeHandler = BigDecimalTypeHandler.class)
    })
    ArrayList<Reservation> getReservations(@Param("userId") int userId);

    @Select("select * from checkininfo where check_in_date=#{today}")
    ArrayList<CheckInInfo> getCheckInInfo(@Param("today") Date today);

    @Select("select * from reservations where reservation_id=#{reservationId}")
    Reservation getReservationById(@Param("reservationId") int reservationId);

    @Select("select count(*) from reservations where user_id=#{userId} and check_in_date=#{checkInDate} " +
            "and check_out_date=#{checkOutDate} and room_type=#{roomType}")
    int countReservationByDetail(@Param("userId") int userId,
                                 @Param("checkInDate") Date checkInDate,
                                 @Param("checkOutDate") Date checkOutDate,
                                 @Param("roomType") RoomType roomType);

    @Update("update reservations set reservation_status=#{reservationStatus} where reservation_id=#{reservationId}")
    void setReservationStatus(@Param("reservationId") int reservationId,
                              @Param("reservationStatus") ReservationStatus reservationStatus);
}
