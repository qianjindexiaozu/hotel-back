package com.hotel.back.mapper;

import com.hotel.back.constant.enums.Gender;
import com.hotel.back.constant.enums.Role;
import com.hotel.back.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;

@Mapper
public interface UserMapper {
    @Select("select * from users where phone=#{phone}")
    User getUserByPhone(String phone);
    @Select("select * from users where id_number=#{idNumber}")
    User getUserByIdNumber(String idNumber);

    @Insert("INSERT INTO users (name, gender, id_number, phone, password, role, reg_time, user_pic) " +
            "VALUES (#{name}, #{gender}, #{idNumber}, #{phone}, #{password}, #{role}, #{regTime}, #{userPic})")
    void register(@Param("name") String name,
                  @Param("gender") Gender gender,
                  @Param("idNumber") String idNumber,
                  @Param("phone") String phone,
                  @Param("password") String password,
                  @Param("role") Role role,
                  @Param("regTime") Date reg_time,
                  @Param("userPic") String user_pic);

    @Update("UPDATE users set password=#{password} where phone=#{phone}")
    void forget(@Param("phone") String phone,
                @Param("password") String password);

    @Update("UPDATE  users set user_pic=#{pic} where phone=#{phone}")
    void changePic(@Param("phone") String phone,
                   @Param("pic") String pic);
}
