package com.goodbye.goodbye;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
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

@Configuration
public class HazelcastConfig {

  @Bean
  public ClientConfig clientConfig() {
    ClientConfig clientConfig = new ClientConfig();
    clientConfig.getNetworkConfig().getEurekaConfig()
      .setEnabled(true)
      .setProperty("self-registration", "true")
      .setProperty("namespace", "hazelcast");

//    clientConfig.getGroupConfig().setName("HAZELCAST-SAY"); // reemplaza "hazelcast-say" con el nombre de tu grupo Hazelcast si es diferente
    return clientConfig;
  }


  @Bean
  public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
    HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
    return client;
  }
}
