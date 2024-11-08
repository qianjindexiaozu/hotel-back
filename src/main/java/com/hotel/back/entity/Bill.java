package com.hotel.back.entity;

import com.hotel.back.constant.enums.PaymentStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Bill {
    private Integer billId;
    private Integer reservationId;
    private Integer roomId;
    private String name1;
    private String idNumber1;
    private String name2;
    private String idnUmber2;
    private BigDecimal amount;
    private PaymentStatus paymentStatus;
    private Date issuedDate;
}
