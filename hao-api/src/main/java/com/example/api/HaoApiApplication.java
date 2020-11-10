package com.example.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.*")
@MapperScan("com.example.*.mapper")
public class HaoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HaoApiApplication.class, args);
	}

}
