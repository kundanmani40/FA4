package com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//Main method to run spring boot application
@SpringBootApplication
@PropertySource(value = {"classpath:messages.properties"})
public class ProductMsTestApplication {

	public static void main(String[] args)
	{
		SpringApplication.run(ProductMsTestApplication.class, args);
	}
	
}
