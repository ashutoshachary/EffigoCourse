package com.ashutosh.connect2db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class Connect2dbApplication {

	public static void main(String[] args) {
		SpringApplication.run(Connect2dbApplication.class, args);
	}

}
