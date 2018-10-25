package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
@SpringBootApplication
public class DeptApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeptApplication.class, args);
	}
}
