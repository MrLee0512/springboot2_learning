package com.liz.springboot2_learning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Springboot2LearningApplication {

	public static void main(String[] args) {
		SpringApplication.run(Springboot2LearningApplication.class, args);
	}
}
