package com.goodbye.goodbye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import sun.misc.Signal;

@SpringBootApplication
public class GoodbyeApplication {
	public static void main(String[] args) {

		// Manejo de la señal SIGTERM
		Signal.handle(new Signal("TERM"), signal -> {
			System.out.println("Se recibió SIGTERM. Limpiando y saliendo...");
			// Aquí puedes agregar cualquier lógica de limpieza que necesites
			System.exit(0);
		});


		SpringApplication.run(GoodbyeApplication.class, args);
	}

}
