package com.goodbye.goodbye;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GoodbyeController {

  private final RestTemplate restTemplate;

  @Autowired
  public GoodbyeController(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @GetMapping("/call-say-instance")
  public String callSayInstance() {
    System.out.println("Viendo si soy llamdo");
    return restTemplate.getForObject("http://" + "SAY-INSTANCE" + "/", String.class);
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
