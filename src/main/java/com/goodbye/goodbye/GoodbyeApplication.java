package com.goodbye.goodbye;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.DiscoveryStrategyConfig;
import com.hazelcast.eureka.one.EurekaOneDiscoveryStrategyFactory;
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
		clientConfig.setProperty("hazelcast.discovery.enabled", "true");

		EurekaOneDiscoveryStrategyFactory discoveryStrategyFactory = new EurekaOneDiscoveryStrategyFactory();
		Map<String, Comparable> properties = new HashMap<>();
		properties.put("self-registration", "true");
		properties.put("namespace", "hazelcast");
		DiscoveryStrategyConfig discoveryStrategyConfig = new DiscoveryStrategyConfig(discoveryStrategyFactory, properties);

		clientConfig.getNetworkConfig().getDiscoveryConfig()
			.addDiscoveryStrategyConfig(discoveryStrategyConfig);

		return clientConfig;

	}
}
