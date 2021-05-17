package com.epam.rd.izh;

import com.epam.rd.izh.entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Класс с main методом.
 * <p>Аннотация @SpringBootApplication является мета-аннотацией, т.е. по сути, является алиасом для нескольких аннотаций:
 * <br>- {@link SpringBootConfiguration SpringBootConfiguration}
 * <br>- {@link EnableAutoConfiguration EnableAutoConfiguration}
 * <br>- {@link ComponentScan ComponentScan}
 * <p>Т.е. наличие @SpringBootApplication включает сканирование компонентов, автоконфигурацию и показывает разным компонентам Spring
 * (например, интеграционным тестам), что это Spring Boot приложение.
 */
@SpringBootApplication
public class RDIzhFinalProjectTemplate {

	public static void main(String[] args) { {}
		SpringApplication.run(RDIzhFinalProjectTemplate.class, args);
	}
}
