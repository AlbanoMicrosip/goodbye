package com.goodbye.goodbye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoodbyeApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoodbyeApplication.class, args);
	}

//	@Bean
//	@LoadBalanced
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
//
//	@Bean
//	@Qualifier("NoBalancing")
//	public RestTemplate restTemplateNoBalancing() {
//		return new RestTemplate();
//	}
}
