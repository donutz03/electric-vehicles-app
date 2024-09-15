package com.electricvehicles.electric_vehicles_app.controller;

import com.electricvehicles.electric_vehicles_app.model.ElectricVehicle;
import com.electricvehicles.electric_vehicles_app.service.ElectricVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/map")
    public String showMap() {
        return "map";
    }

    @GetMapping("/api/vehicles")
    @ResponseBody
    public List<ElectricVehicle> getVehicles(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "100") int size) {
        return electricVehicleService.getVehicles(page, size);
    }

    @GetMapping("/api/statistics/vehicle-count-by-location")
    @ResponseBody
    public Map<String, Map<String, Long>> getVehicleCountByCountyAndState() {
        return electricVehicleService.getVehicleCountByCountyAndState();
    }

    @GetMapping("/api/statistics/average-range-by-make-model")
    @ResponseBody
    public Map<String, Map<String, Double>> getAverageRangeByMakeAndModel() {
        return electricVehicleService.getAverageRangeByMakeAndModel();
    }

    @GetMapping("/api/statistics/registration-trends")
    @ResponseBody
    public Map<Integer, Long> getRegistrationTrendsByYear() {
        return electricVehicleService.getRegistrationTrendsByYear();
    }

    @GetMapping("/api/statistics/cafv-eligibility-summary")
    @ResponseBody
    public Map<String, Long> getCAFVEligibilitySummary() {
        return electricVehicleService.getCAFVEligibilitySummary();
    }

    @GetMapping("/api/statistics/vehicle-distribution-heatmap")
    @ResponseBody
    public List<Map<String, Object>> getVehicleDistributionHeatmapData() {
        return electricVehicleService.getVehicleDistributionHeatmapData();
    }

    @GetMapping("/api/vehicles/search")
    @ResponseBody
    public List<ElectricVehicle> searchVehiclesByYearAndMake(@RequestParam int year, @RequestParam String make) {
        return electricVehicleService.searchVehiclesByYearAndMake(year, make);
    }

    @GetMapping("/api/statistics/manufacturer-stats")
    @ResponseBody
    public Map<String, Map<String, Object>> getManufacturerStatistics() {
        return electricVehicleService.getManufacturerStatistics();
    }

}
