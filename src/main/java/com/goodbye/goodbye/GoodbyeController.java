package com.goodbye.goodbye;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GoodbyeController {

  private final RestTemplate restTemplate;
  private final DiscoveryClient discoveryClient;

  @Autowired
  public GoodbyeController(RestTemplate restTemplate, DiscoveryClient discoveryClient) {
    this.restTemplate = restTemplate;
    this.discoveryClient = discoveryClient;
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
