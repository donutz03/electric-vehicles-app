package com.electricvehicles.electric_vehicles_app.repository;

import com.electricvehicles.electric_vehicles_app.model.ElectricVehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricVehicleRepository extends JpaRepository<ElectricVehicle, String> {
}
