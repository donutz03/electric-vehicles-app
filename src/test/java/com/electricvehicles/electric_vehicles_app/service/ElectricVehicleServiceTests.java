package com.electricvehicles.electric_vehicles_app.service;

import com.electricvehicles.electric_vehicles_app.model.ElectricVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ElectricVehicleServiceTests {

    private ElectricVehicleService electricVehicleService;

    @BeforeEach
    public void setUp() {
        electricVehicleService = new ElectricVehicleService();
        electricVehicleService.loadData();
    }

    @Test
    public void testLoadData() {
        List<ElectricVehicle> vehicles = electricVehicleService.getFirst20ElectricVehicles();
        assertNotNull(vehicles, "The vehicle list should not be null");
        assertFalse(vehicles.isEmpty(), "The vehicle list should not be empty");
    }

    @Test
    public void testGetFirst20ElectricVehicles() {
        List<ElectricVehicle> vehicles = electricVehicleService.getFirst20ElectricVehicles();
        assertTrue(vehicles.size() <= 20, "The list should contain up to 20 vehicles");
    }
}
