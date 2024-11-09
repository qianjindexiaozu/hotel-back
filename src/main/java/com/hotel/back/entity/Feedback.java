package com.hotel.back.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Feedback {
    private Integer feedbackId;
    private Integer userId;
    private Integer roomId;
    private Float rating;
    private String text;
    private Date submittedDate;
}
