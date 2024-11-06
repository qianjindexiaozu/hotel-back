package com.hotel.back.service;

import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;

import java.math.BigDecimal;
import java.util.ArrayList;

public interface RoomService {
    ArrayList<Price> getPrice();
    void setPrice(RoomType roomType, BigDecimal bd);
}
