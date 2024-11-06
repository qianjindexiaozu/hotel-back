package com.hotel.back.service.impl;

import com.hotel.back.constant.enums.RoomType;
import com.hotel.back.entity.Price;
import com.hotel.back.mapper.RoomMapper;
import com.hotel.back.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;

    @Override
    public ArrayList<Price> getPrice() {
        return roomMapper.getPrice();
    }

    @Override
    public void setPrice(RoomType roomType, BigDecimal bd) {
        roomMapper.setPrice(roomType, bd);
    }
}
