package com.hotel.back.service.impl;

import com.hotel.back.mapper.BillMapper;
import com.hotel.back.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillMapper billMapper;

    @Override
    public Integer getBillIdByRoomIdAndBeforeDate(int roomId, Date date) {
        return billMapper.getBillIdByRoomIdAndBeforeDate(roomId, date);
    }
}
