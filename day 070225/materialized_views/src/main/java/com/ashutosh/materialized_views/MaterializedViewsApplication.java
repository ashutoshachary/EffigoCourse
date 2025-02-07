package com.ashutosh.materialized_views;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MaterializedViewsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaterializedViewsApplication.class, args);
	}

}
