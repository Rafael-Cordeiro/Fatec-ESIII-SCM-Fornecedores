package com.temperosoft.samplaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.dvsmedeiros", "com.temperosoft" })
@EntityScan({ "com.dvsmedeiros", "com.temperosoft" })
@EnableAutoConfiguration
@SpringBootApplication
public class SampleServerApplication {
	

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run(SampleServerApplication.class, args);
	}
	
}