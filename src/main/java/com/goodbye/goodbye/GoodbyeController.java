package com.goodbye.goodbye;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodbyeController {
  @PostMapping("/hola")
  public String hola(@RequestBody String cadena){
    System.out.println("MetodoHola llmado POST");
    return "Hola: "+cadena;
  }

  @GetMapping("/hola")
  public String metodoHola(){
    System.out.println("MetodoHola llmado GET");
    return "Soy un metodo hola";
  }
  @GetMapping("/aux")
  public String metodoAux(){
    System.out.println("MetodoAux llamado");
    return "Soy un metodo aux";
  }
}
