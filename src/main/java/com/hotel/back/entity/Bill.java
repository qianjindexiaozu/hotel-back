package com.hotel.back.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Bill {
    private Integer billId;
    private Integer reservationId;
    private Integer roomId;
    private BigDecimal amount;
    private Date issuedDate;
}
