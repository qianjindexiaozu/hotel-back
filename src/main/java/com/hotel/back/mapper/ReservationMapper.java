package com.hotel.back.mapper;

import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Reservation;
import com.hotel.back.utils.BigDecimalTypeHandler;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

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

    @Update("update reservations set reservation_status=#{reservationStatus} where reservation_id=#{reservationId}")
    void cancelReservation(@Param("reservationId") int reservationId,
                           @Param("reservationStatus") ReservationStatus reservationStatus);

    @Select("select * from reservations where user_id=#{userId}")
    @Results({
            @Result(property = "account", column = "account",
                    javaType = BigDecimal.class,
                    typeHandler = BigDecimalTypeHandler.class)
    })
    ArrayList<Reservation> getReservations(@Param("userId") int userId);
}
