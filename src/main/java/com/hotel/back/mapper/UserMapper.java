package com.hotel.back.mapper;

import com.hotel.back.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from users where phone=#{phone}")
    User getUserByPhone(String phone);
}
