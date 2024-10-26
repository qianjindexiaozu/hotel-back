package com.hotel.back;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class BackApplicationTests {
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
    }

    /**
     * 数据库连接池：
     * 1.DBCP
     * 2.C3P0
     * 3.Hikari: 管理数据库连接对象
     * HikariProxyConnection@380556447 wrapping com.mysql.cj.jdbc.ConnectionImpl@66de00f2
     * @throws SQLException
     */
    @Test
    void getConnect() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

}
