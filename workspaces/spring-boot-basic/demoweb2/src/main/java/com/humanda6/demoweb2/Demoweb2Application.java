package com.humanda6.demoweb2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = { "com.humanda6.demoweb2.entity" })
@SpringBootApplication
public class Demoweb2Application {

	public static void main(String[] args) { // flask의 __init__.py 역할
		SpringApplication.run(Demoweb2Application.class, args);
	}

}
