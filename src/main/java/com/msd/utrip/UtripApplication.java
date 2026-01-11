package com.msd.utrip;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UtripApplication {

	public static void main(String[] args) {
		SpringApplication.run(UtripApplication.class, args);
	}

}
