package com.electricvehicles.electric_vehicles_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.electricvehicles.electric_vehicles_app.model")
public class ElectricVehiclesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElectricVehiclesAppApplication.class, args);
	}

}
