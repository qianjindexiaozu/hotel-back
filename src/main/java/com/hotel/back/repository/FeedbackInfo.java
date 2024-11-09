package com.hotel.back.repository;

import com.hotel.back.constant.enums.RoomType;
import lombok.Data;

import java.sql.Date;

@Data
public class FeedbackInfo {
    private int feedbackId;
    private int userId;
    private String name;
    private String phone;
    private int billId;
    private RoomType roomType;
    private int roomId;
    private String roomNumber;
    private float rating;
    private String comments;
    private Date submittedDate;
}
