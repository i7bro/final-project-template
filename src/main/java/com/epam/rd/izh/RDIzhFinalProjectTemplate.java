package com.epam.rd.izh;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;
import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class RDIzhFinalProjectTemplate {
	
	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(RDIzhFinalProjectTemplate.class, args);
	}
}
