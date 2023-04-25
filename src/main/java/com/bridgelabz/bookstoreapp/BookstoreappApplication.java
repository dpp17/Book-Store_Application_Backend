package com.bridgelabz.bookstoreapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@Slf4j
public class BookstoreappApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BookstoreappApplication.class, args);
		log.info("Address Book Application Started SuccessFully.....!!!");
		log.info("Application is started in {} environment", context.getEnvironment().getProperty("environment"));
		log.info("MySQL Database UserName is ::  {}", context.getEnvironment().getProperty("spring.datasource.username"));
		log.info("MySQL Database Password is ::  {}", context.getEnvironment().getProperty("spring.datasource.password"));
	}

}
