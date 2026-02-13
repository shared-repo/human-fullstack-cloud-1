package com.myweb.bootmywebref;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@EntityScan(basePackages = { "com.myweb.bootmywebref.entity" })
@SpringBootApplication
public class BootmywebrefApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootmywebrefApplication.class, args);
	}

}
