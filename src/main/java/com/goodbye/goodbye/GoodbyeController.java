package com.goodbye.goodbye;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodbyeController {
  @Autowired
  private RestTemplate restTemplate;

  @GetMapping("/goodbye")
  public String goodbye() {
    String response = restTemplate.getForObject("http://say-hello/hello", String.class);
    return "Goodbye. " + response;
  }
}
