package com.hotel.back.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Inventory {
    private Integer itemId;
    private String itemName;
    private Integer quantity;
    private BigDecimal unitPrice;
}
