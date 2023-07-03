//package com.goodbye.goodbye;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;
//
//@RestController
//public class GoodbyeController {
//  private final WebClient.Builder webClientBuilder;
//
//  public GoodbyeController(WebClient.Builder webClientBuilder) {
//    this.webClientBuilder = webClientBuilder;
//  }
//
//  @GetMapping("/goodbye")
//  public Mono<String> goodbye() {
//    return webClientBuilder.build()
//      .get()
//      .uri("http://say-hello/hello")
//      .retrieve()
//      .bodyToMono(String.class)
//      .map(response -> "Goodbye. " + response);
//  }
//}
