package com.kafka.distributor;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@OpenAPIDefinition
public class DistributorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DistributorApplication.class, args);
	}


}
