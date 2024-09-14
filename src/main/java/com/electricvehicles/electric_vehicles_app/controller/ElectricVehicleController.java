package com.electricvehicles.electric_vehicles_app.controller;

import com.electricvehicles.electric_vehicles_app.model.ElectricVehicle;
import com.electricvehicles.electric_vehicles_app.service.ElectricVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/statistics")
    public String statistics() {
        return "statistics";
    }


    @GetMapping("/statistics/total")
    @ResponseBody
    public long getTotalVehicles() {
        return electricVehicleService.getTotalVehicles();
    }

    @GetMapping("/statistics/type-distribution")
    @ResponseBody
    public Map<String, Long> getVehicleTypeDistribution() {
        return electricVehicleService.getVehicleTypeDistribution();
    }

    @GetMapping("/api/type-distribution")
    @ResponseBody
    public Map<String, Long> getVehicleTypeDistributionData() {
        return electricVehicleService.getVehicleTypeDistribution();
    }

    @GetMapping("/statistics/average-range")
    @ResponseBody
    public double getAverageElectricRange() {
        return electricVehicleService.getAverageElectricRange();
    }

    @GetMapping("/statistics/manufacturer-distribution")
    @ResponseBody
    public Map<String, Long> getVehiclesByManufacturer() {
        return electricVehicleService.getVehiclesByManufacturer();
    }

    @GetMapping("/search")
    @ResponseBody
    public List<ElectricVehicle> searchVehicles(@RequestParam(required = false) String make,
                                                @RequestParam(required = false) String model) {
        return electricVehicleService.searchVehicles(make, model);
    }
}
