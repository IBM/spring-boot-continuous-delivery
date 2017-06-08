package com.ibm.account.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}
	

	@Bean
	public Database account(CloudantClient cloudant) {
		return cloudant.database("account", true);
	}
}
