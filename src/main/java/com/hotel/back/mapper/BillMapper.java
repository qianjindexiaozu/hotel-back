package com.hotel.back.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Date;

@Mapper
public interface BillMapper {
    @Select("select bill_id from bill_info where room_id=#{roomId} and check_out_date > #{date}")
    Integer getBillIdByRoomIdAndBeforeDate(@Param("roomId") int roomId,
                                                        @Param("date") Date date);


}
