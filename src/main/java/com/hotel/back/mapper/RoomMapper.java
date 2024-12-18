package com.hotel.back.mapper;

import com.hotel.back.constant.enums.ReservationStatus;
import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;
import com.hotel.back.entity.Room;
import com.hotel.back.utils.BigDecimalTypeHandler;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;

@Mapper
public interface RoomMapper {
    @Select("select * from price")
    @Results({
            @Result(property = "roomPrice", column = "roomPrice",
                    javaType = BigDecimal.class,
                    typeHandler = BigDecimalTypeHandler.class)
    })
    ArrayList<Price> getPrice();

    @Select("select room_price from price where room_type=#{roomType}")
    BigDecimal getPriceByType(@Param("roomType") RoomType roomType);

    @Update("update price set room_price=#{bd} where room_type=#{roomType}")
    void setPrice(@Param("roomType")RoomType roomType,
                  @Param("bd")BigDecimal bd);

    @Select("select * from rooms")
    ArrayList<Room> getRoom();

    @Select("select * from rooms where room_number=#{roomNumber}")
    Room getRoomByRoomNumber(@Param("roomNumber") String roomNumber);

    @Update("update rooms set room_number=#{roomNumber}, room_type=#{roomType}, status=#{status} where room_id=#{roomId}")
    void setRoom(@Param("roomId") int roomId,
                 @Param("roomNumber") String roomNumber,
                 @Param("roomType") RoomType roomType,
                 @Param("status") RoomStatus status);

    @Delete("delete from rooms where room_id=#{roomId}")
    void deleteRoom(@Param("roomId") int roomId);

    @Insert("insert into rooms (room_number, room_type, status) values (#{roomNumber}, #{roomType}, #{status})")
    void newRoom(@Param("roomNumber") String roomNumber,
                 @Param("roomType") RoomType roomType,
                 @Param("status") RoomStatus status);

    @Select("select count(*) from reservations where room_type=#{roomType} and reservation_status=#{reservationStatus} " +
            "and check_out_date > #{checkInDate} and check_in_date < #{checkOutDate}")
    int countNumber(@Param("checkInDate") Date checkInDate,
                      @Param("checkOutDate") Date checkOutDate,
                      @Param("roomType") RoomType roomType,
                      @Param("reservationStatus")ReservationStatus confirmedStatus);

    @Select("select count(*) from rooms where room_type=#{roomType} and status!=#{status}")
    int countAvailable(@Param("roomType") RoomType roomType,
                       @Param("status") RoomStatus roomStatus);

    @Select("select * from rooms where room_type=#{roomType} and status=#{status}")
    ArrayList<Room> getAvailableRoom(@Param("status") RoomStatus status,
                                     @Param("roomType") RoomType roomType);

    @Update("update rooms set status=#{status} where room_id=#{roomId}")
    void setRoomStatus(@Param("roomId") int roomId,
                       @Param("status") RoomStatus roomStatus);

    @Select("select * from rooms where room_id=#{roomId}")
    Room getRoomById(@Param("roomId") int roomId);

    @Select("select count(*) from rooms where status!=#{status}")
    Integer getSumRoomNumber(@Param("status") RoomStatus roomStatus);
}
