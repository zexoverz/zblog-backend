package com.server.zblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class ZblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZblogApplication.class, args);
	}

}
