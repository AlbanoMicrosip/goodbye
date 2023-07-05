package com.goodbye.goodbye;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GoodbyeController {

  private final RestTemplate restTemplate;

  @Qualifier("NoBalancing")
  private final RestTemplate restTemplateNoBalancin;
  private final DiscoveryClient discoveryClient;

  @Autowired
  public GoodbyeController(RestTemplate restTemplate, DiscoveryClient discoveryClient, RestTemplate restTemplateNoBalancin) {
    this.restTemplate = restTemplate;
    this.discoveryClient = discoveryClient;
    this.restTemplateNoBalancin =restTemplateNoBalancin;
  }

  @GetMapping("/call-say-instance")
  public String callSayInstance() {
    System.out.println("Viendo si soy llamdo");
    return restTemplate.getForObject("http://" + "SAY-INSTANCE" + "/", String.class);
  }
  @GetMapping("/call-say-instance-fix")
  public String callSayInstanceFix() {
    System.out.println("Viendo si soy llamdo");
    return restTemplateNoBalancin.getForObject("http://say-hello1:8080/", String.class);
  }

  @GetMapping("/hola")
  public String hola(){
    return "Hola";
  }
}
