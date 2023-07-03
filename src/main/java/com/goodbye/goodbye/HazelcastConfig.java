package com.goodbye.goodbye;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.DiscoveryStrategyConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ManagedContext;
import com.hazelcast.eureka.one.EurekaOneDiscoveryStrategyFactory;
import com.hazelcast.spring.context.SpringManagedContext;
import com.netflix.discovery.EurekaClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class HazelcastConfig {

  @Bean
  public ClientConfig hazelcastClientConfig() {
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


  @Bean
  public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
    HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
    return client;
  }
}
