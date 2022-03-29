package com.isotrol.CRUDHibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"com.isotrol.controller","com.isotrol.service"})
@EntityScan("com.isotrol.entity")
@EnableJpaRepositories("com.isotrol.repository")
@SpringBootApplication
public class CrudHibernateApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudHibernateApplication.class, args);
	}

}
