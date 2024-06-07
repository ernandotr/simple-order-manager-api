package com.ernandorezende.simple_order_manager_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class SimpleOrderManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleOrderManagerApiApplication.class, args);
	}

}
