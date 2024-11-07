package com.hotel.back.mapper;

import com.hotel.back.constant.enums.RoomStatus;
import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;
import com.hotel.back.entity.Room;
import com.hotel.back.utils.BigDecimalTypeHandler;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
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
}
