package com.example.iteminfoservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ItemInfoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemInfoServiceApplication.class, args);
	}

}
