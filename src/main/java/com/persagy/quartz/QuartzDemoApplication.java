package com.persagy.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class QuartzDemoApplication {

	public static void main(String[] args) {
		// 设置热启动是否开启
		System.setProperty("spring.devtools.restart.enabled", "true");
		SpringApplication.run(QuartzDemoApplication.class, args);
	}
}
