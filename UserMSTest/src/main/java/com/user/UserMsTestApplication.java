package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//Main method to run spring boot application
@SpringBootApplication
@PropertySource(value = {"classpath:messages.properties"})
public class UserMsTestApplication {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(UserMsTestApplication.class, args);

	}

}
