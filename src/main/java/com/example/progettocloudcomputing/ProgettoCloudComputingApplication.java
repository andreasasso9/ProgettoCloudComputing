package com.example.progettocloudcomputing;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCosmosRepositories(basePackages = "com.example.progettocloudcomputing.repository")
@ComponentScan("com.example.progettocloudcomputing.control")
public class ProgettoCloudComputingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoCloudComputingApplication.class, args);
	}

}
