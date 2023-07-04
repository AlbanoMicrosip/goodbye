package com.goodbye.goodbye;

import com.hazelcast.client.config.ClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GoodbyeApplication {

	@Value("${hazelcast.port:5701}")
	private int hazelcastPort;

	public static void main(String[] args) {
		SpringApplication.run(GoodbyeApplication.class, args);
	}


	@Bean
	public ClientConfig hazelcastConfig() {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.getNetworkConfig().getEurekaConfig().setEnabled(true)
			.setProperty("namespace", "hazelcast");

		return clientConfig;
	}
}
