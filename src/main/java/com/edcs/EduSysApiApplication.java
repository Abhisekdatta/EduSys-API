package com.edcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@IntegrationComponentScan
public class EduSysApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EduSysApiApplication.class, args);
	}
}
