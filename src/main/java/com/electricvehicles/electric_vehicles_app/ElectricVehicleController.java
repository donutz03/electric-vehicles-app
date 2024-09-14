package com.electricvehicles.electric_vehicles_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ElectricVehicleController {

    @Autowired
    private ElectricVehicleService electricVehicleService;

    @GetMapping("/")
    public String home(Model model) {
        try {
            model.addAttribute("vehicles", electricVehicleService.getFirst20ElectricVehicles());
            return "index";
        } catch (Exception e) {
            return "redirect:/error";
        }
    }
}
