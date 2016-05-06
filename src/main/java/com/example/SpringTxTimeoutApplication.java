package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = { "com.example.domain.repository" })
//@ComponentScan(basePackages = { "com.example.config" })
@SpringBootApplication
public class SpringTxTimeoutApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringTxTimeoutApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}