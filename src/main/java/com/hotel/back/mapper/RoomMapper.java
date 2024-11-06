package com.hotel.back.mapper;

import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;
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
}
