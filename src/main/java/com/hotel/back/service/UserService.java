package com.hotel.back.service;

import com.hotel.back.entity.Result;
import com.hotel.back.entity.User;

public interface UserService {
    User getUserByPhone(String phone);
}
