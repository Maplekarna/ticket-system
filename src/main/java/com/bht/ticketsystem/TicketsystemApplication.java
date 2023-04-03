package com.bht.ticketsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class TicketsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketsystemApplication.class, args);
	}

}
