package com.hotel.back.service;

import java.sql.Date;

public interface BillService {
    Integer getBillIdByRoomIdAndBeforeDate(int roomId, Date date);
}
