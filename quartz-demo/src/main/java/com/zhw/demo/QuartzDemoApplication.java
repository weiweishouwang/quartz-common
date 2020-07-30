package com.zhw.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zhw.quartz", "com.zhw.demo"})
public class QuartzDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuartzDemoApplication.class, args);
	}

}
