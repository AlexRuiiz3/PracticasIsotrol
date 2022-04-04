package com.isotrol.BootsrapJQuery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.isotrol.controller")
@SpringBootApplication
public class BootsrapJQueryApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootsrapJQueryApplication.class, args);
	}

}
