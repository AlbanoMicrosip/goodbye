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

  private static Logger log = LoggerFactory.getLogger(HazelcastConfig.class);

  private ApplicationContext applicationContext;

  @Bean
  public ManagedContext managedContext() {
    ManagedContext springManagedContext = new SpringManagedContext();
    return springManagedContext;
  }
  @Bean
  public ClientConfig clientConfig(ManagedContext managedContext, EurekaClient eurekaClient) {
    if(eurekaClient != null){
      System.out.println("Hola");
    }
    EurekaOneDiscoveryStrategyFactory.setEurekaClient(eurekaClient);

    ClientConfig clientConfig = new ClientConfig();
    clientConfig.getNetworkConfig().getEurekaConfig()
      .setEnabled(true)
      .setProperty("self-registration", "true")
      .setProperty("namespace", "hazelcast")
      .setProperty("use-metadata-for-host-and-port", "true");

    clientConfig.setManagedContext(managedContext);

    return clientConfig;
  }

  @Bean
  public HazelcastInstance hazelcastInstance(ClientConfig clientConfig) {
    HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
    return client;
  }
}
