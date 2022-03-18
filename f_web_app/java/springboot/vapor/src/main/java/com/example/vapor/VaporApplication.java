package com.example.vapor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class VaporApplication {

	public static void main(String[] args) {

		SpringApplication.run(VaporApplication.class, args);

//		SpringApplicationBuilder builder = new SpringApplicationBuilder(VaporApplication.class);
//		builder.headless(false);
//		ConfigurableApplicationContext context = builder.run(args);
//		System.out.println("Here");
	}


}
