package com.luwojtaszek.springbootjsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.luwojtaszek.springbootjsp.repositories"})
public class SpringBootJspApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootJspApplication.class, args);
	}
}
