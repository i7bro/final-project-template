package com.epam.rd.izh.util;


import com.epam.rd.izh.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    private PropertiesUtil() {}

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }

    static {
        loadUtil();
    }

    private static void loadUtil() {
        try (InputStream resourceAsStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(resourceAsStream);
        } catch (IOException e) {
            throw new DaoException(e);
        }
    }
}
