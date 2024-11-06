package com.hotel.back.entity;

import com.hotel.back.constant.enums.RoomType;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class Price {
    private RoomType roomType;
    private BigDecimal roomPrice;
    // 构造函数
    public Price(RoomType roomType, BigDecimal roomPrice) {
        this.roomType = roomType;
        // 保证每次传入的 roomPrice 保留两位小数
        this.roomPrice = roomPrice.setScale(2, RoundingMode.HALF_UP);
    }
}
