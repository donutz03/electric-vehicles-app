package com.electricvehicles.electric_vehicles_app.controller;

import com.electricvehicles.electric_vehicles_app.service.ElectricVehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ElectricVehicleController.class)
public class ElectricVehicleControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ElectricVehicleService electricVehicleService;

    @Test
    public void testHomePage() throws Exception {
        when(electricVehicleService.getFirst20ElectricVehicles()).thenReturn(List.of()); // Mock the service call

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("vehicles"));

        verify(electricVehicleService, times(1)).getFirst20ElectricVehicles();
    }
}
