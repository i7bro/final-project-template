package com.epam.rd.izh.config;

import com.epam.rd.izh.util.PropertiesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.*;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;

/**
 * Данный класс можно использовать для создание бинов приложения, например бин ObjectMapper для десериализации.
 * Этот класс не является обязательным, но является стандартным там, где используется настройка бинов.
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String URL_KEY = "db.mysql.url";
    private static final String USER_KEY = "db.mysql.user";
    private static final String PASSWORD_KEY = "db.mysql.password";
    private static final String DRIVER_KEY = "db.mysql.driver";

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(PropertiesUtil.getProperty(DRIVER_KEY));
        dataSource.setUrl(PropertiesUtil.getProperty(URL_KEY));
        dataSource.setUsername(PropertiesUtil.getProperty(USER_KEY));
        dataSource.setPassword(PropertiesUtil.getProperty(PASSWORD_KEY));

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
