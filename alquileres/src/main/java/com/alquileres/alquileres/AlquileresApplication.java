package com.alquileres.alquileres;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.alquileres.alquileres.model", "com.estaciones.estaciones.model"})
public class AlquileresApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlquileresApplication.class, args);
	}

}
