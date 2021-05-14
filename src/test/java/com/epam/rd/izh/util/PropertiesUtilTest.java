package com.epam.rd.izh.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PropertiesUtilTest {

    private static final String URL = "jdbc:mysql://localhost:3306/tour_shop";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String URL_KEY = "db.mysql.url";
    private static final String USER_KEY = "db.mysql.user";
    private static final String PASSWORD_KEY = "db.mysql.password";
    private static final String DRIVER_KEY = "db.mysql.driver";

    @Test
    void getProperty_expected_User_key() {
        assertEquals(USER, PropertiesUtil.getProperty(USER_KEY));
        assertEquals(URL, PropertiesUtil.getProperty(URL_KEY));
        assertEquals(PASSWORD, PropertiesUtil.getProperty(PASSWORD_KEY));
        assertEquals(DRIVER, PropertiesUtil.getProperty(DRIVER_KEY));
    }
}