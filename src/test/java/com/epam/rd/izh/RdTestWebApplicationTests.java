package com.epam.rd.izh;

import com.epam.rd.izh.dao.TourRepository;
import com.epam.rd.izh.initializer.Mysql;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(initializers = {
		Mysql.Initializer.class
})
@Transactional
@ActiveProfiles("test")
public class RdTestWebApplicationTests {

	@Autowired
	TourRepository repository;

	@BeforeAll
	public static void start() {
		Mysql.CONTAINER.start();
	}

	@Test
	public void contextLoads() {
		assertEquals(3, repository.findAll().size());
		System.out.println(repository.findAll());
	}

}
