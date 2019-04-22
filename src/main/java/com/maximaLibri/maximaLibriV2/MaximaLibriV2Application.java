package com.maximaLibri.maximaLibriV2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = "com.maximaLibri.maximaLibriV2")
public class MaximaLibriV2Application {

	public static void main(String[] args) {
		SpringApplication.run(MaximaLibriV2Application.class, args);
//		AddAdmin addAdmin = new AddAdmin();
//		addAdmin.AddAdmin();
	}

}
