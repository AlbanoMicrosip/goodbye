package com.goodbye.goodbye;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientUserCodeDeploymentConfig;
import com.hazelcast.config.DiscoveryStrategyConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.instance.HazelcastInstanceFactory;
import com.hazelcast.spring.context.SpringManagedContext;
import com.hazelcast.core.ManagedContext;
import com.hazelcast.eureka.one.EurekaOneDiscoveryStrategyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class GoodbyeApplication implements ApplicationContextAware {

	@Bean
	public ManagedContext managedContext() {
		ManagedContext springManagedContext = new SpringManagedContext();
		return springManagedContext;
	}

	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}


	@Value("${hazelcast.port:5701}")
	private int hazelcastPort;

	public static void main(String[] args) {
		SpringApplication.run(GoodbyeApplication.class, args);
	}
	@Bean
	public ClientConfig hazelcastConfig(ManagedContext managedContext) {
		ClientConfig clientConfig = new ClientConfig();
		clientConfig.setProperty("hazelcast.discovery.enabled", "true");

		EurekaOneDiscoveryStrategyFactory discoveryStrategyFactory = new EurekaOneDiscoveryStrategyFactory();
		Map<String, Comparable> properties = new HashMap<>();
		properties.put("namespace", "hazelcast");
		DiscoveryStrategyConfig discoveryStrategyConfig = new DiscoveryStrategyConfig(discoveryStrategyFactory, properties);

		clientConfig.getNetworkConfig().getDiscoveryConfig()
			.addDiscoveryStrategyConfig(discoveryStrategyConfig);

		clientConfig.setManagedContext(managedContext);

		ClientUserCodeDeploymentConfig clientUserCodeDeploymentConfig = new ClientUserCodeDeploymentConfig();
		clientUserCodeDeploymentConfig.setEnabled(true);
		clientConfig.setUserCodeDeploymentConfig(clientUserCodeDeploymentConfig);

		return clientConfig;
	}

	@PreDestroy
	void destroyHazelcast() {
		Hazelcast.shutdownAll();
		HazelcastInstanceFactory.shutdownAll();
	}
}
