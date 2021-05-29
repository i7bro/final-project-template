package com.epam.rd.izh.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.*;

import javax.sql.DataSource;

/**
 * Данный класс можно использовать для создание бинов приложения, например бин ObjectMapper для десериализации.
 * Этот класс не является обязательным, но является стандартным там, где используется настройка бинов.
 */

@Configuration
@PropertySource("classpath:application.properties")
public class WebConfig implements WebMvcConfigurer {

    @Value("${db.mysql.url}")
    private String URL;
    @Value("${db.mysql.user}")
    private String USER;
    @Value("${db.mysql.password}")
    private String PASSWORD;
    @Value("${db.mysql.driver}")
    private String DRIVER;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(DRIVER);
        dataSource.setUrl(URL);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }
}
