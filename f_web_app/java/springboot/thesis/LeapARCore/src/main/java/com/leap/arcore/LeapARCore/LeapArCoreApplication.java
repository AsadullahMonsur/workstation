package com.leap.arcore.LeapARCore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.crypto.Data;

@SpringBootApplication
public class LeapArCoreApplication {

	public static void main(String[] args) {

		SpringApplication.run(LeapArCoreApplication.class, args);

		Database database = new Database();
		database.initialization();
	}

}
