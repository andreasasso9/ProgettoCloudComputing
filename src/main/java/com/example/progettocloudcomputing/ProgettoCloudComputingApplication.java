package com.example.progettocloudcomputing;

import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableCosmosRepositories(basePackages = "com.example.progettocloudcomputing.repository")
public class ProgettoCloudComputingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgettoCloudComputingApplication.class, args);
	}

}
