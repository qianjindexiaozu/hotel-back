package com.hotel.back.entity;

import com.hotel.back.constant.enums.Rating;
import lombok.Data;

import java.util.Date;

@Data
public class Feedback {
    private Integer feedbackId;
    private Integer userId;
    private Integer roomId;
    private Rating rating;
    private String text;
    private Date submittedDate;
}
