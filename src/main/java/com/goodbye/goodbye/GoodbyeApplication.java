package com.goodbye.goodbye;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class GoodbyeApplication {
	public static void main(String[] args) {
		SpringApplication.run(GoodbyeApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	@Qualifier("NoBalancing")
	public RestTemplate restTemplateNoBalancing() {
		return new RestTemplate();
	}
}
