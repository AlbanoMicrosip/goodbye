package com.goodbye.goodbye;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GoodbyeController {

  private final RestTemplate restTemplate;
  private final LoadBalancerClient loadBalancerClient;

  @Autowired
  public GoodbyeController(RestTemplate restTemplate, LoadBalancerClient loadBalancerClient) {
    this.restTemplate = restTemplate;
    this.loadBalancerClient = loadBalancerClient;
  }

  @GetMapping("/call-say-instance")
  public String callSayInstance() {
    ServiceInstance instance = loadBalancerClient.choose("SAY-INSTANCE");
    String url = instance.getUri().toString();
    return restTemplate.getForObject(url, String.class);
  }
  @GetMapping("/call-say-instance-fix")
  public String callSayInstanceFix() {
    System.out.println("Viendo si soy llamdo");
    return restTemplate.getForObject("http://say-hello1:8080/", String.class);
  }

  @GetMapping("/hola")
  public String hola(){
    return "Hola";
  }
}
