package com.hotel.back.mapper;

import com.hotel.back.constant.enums.FeedbackStatus;
import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomType;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.sql.Date;

@Mapper
public interface ReservationMapper {

    @Insert("insert into reservations (user_id, room_type, check_in_date, check_out_date, reservation_status, account) " +
            "values (#{userId}, #{roomType}, #{checkInDate}, #{checkOutDate}, #{reservationStatus}, #{account})")
    void newReservation(@Param("userId") int userId,
                        @Param("roomType") RoomType roomType,
                        @Param("checkInDate") Date checkInDate,
                        @Param("checkOutDate") Date checkOutDate,
                        @Param("reservationStatus")ReservationStatus reservationStatus,
                        @Param("account")BigDecimal account);
}
