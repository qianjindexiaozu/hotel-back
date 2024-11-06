package com.hotel.back;

import com.hotel.back.utils.Md5Util;
import org.junit.jupiter.api.Test;

public class Md5Test {
    @Test
    public void Test(){
        Md5Util md5Util = new Md5Util();
        System.out.println(md5Util.getMD5("abc123"));
    }
}
