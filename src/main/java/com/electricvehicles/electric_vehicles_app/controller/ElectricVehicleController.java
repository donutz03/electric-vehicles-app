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

    // 1. Electric Utility: Vehicle Count per Utility
    @GetMapping("/api/statistics/utility-count")
    @ResponseBody
    public Map<String, Long> getVehicleCountByElectricUtility() {
        return electricVehicleService.getVehicleCountByElectricUtility();
    }

    // 2. Electric Range Statistics per Electric Utility
    @GetMapping("/api/statistics/range-by-utility")
    @ResponseBody
    public Map<String, Map<String, Double>> getElectricRangeStatsByUtility() {
        return electricVehicleService.getElectricRangeStatsByUtility();
    }

    // 3. Electric Range Statistics (Overall)
    @GetMapping("/api/statistics/overall-range")
    @ResponseBody
    public Map<String, Double> getOverallElectricRangeStats() {
        return electricVehicleService.getOverallElectricRangeStats();
    }

    // 4. Electric Range Statistics by Car Make
    @GetMapping("/api/statistics/range-by-make")
    @ResponseBody
    public Map<String, Map<String, Double>> getElectricRangeStatsByMake() {
        return electricVehicleService.getElectricRangeStatsByMake();
    }

    // 5. Electric Range Statistics by City
    @GetMapping("/api/statistics/range-by-city")
    @ResponseBody
    public Map<String, Map<String, Double>> getElectricRangeStatsByCity() {
        return electricVehicleService.getElectricRangeStatsByCity();
    }

    // 6. Number of Vehicles per Model Year
    @GetMapping("/api/statistics/vehicle-count-by-year")
    @ResponseBody
    public Map<Integer, Long> getVehicleCountByModelYear() {
        return electricVehicleService.getVehicleCountByModelYear();
    }

    // 7. Number of Unique Makes (Brands)
    @GetMapping("/api/statistics/unique-makes")
    @ResponseBody
    public long getUniqueMakesCount() {
        return electricVehicleService.getUniqueMakesCount();
    }

    // 8. Number of Cars Present in Each City
    @GetMapping("/api/statistics/vehicle-count-by-city")
    @ResponseBody
    public Map<String, Long> getVehicleCountByCity() {
        return electricVehicleService.getVehicleCountByCity();
    }

    // 9. Number of Cars Present in Each County
    @GetMapping("/api/statistics/vehicle-count-by-county")
    @ResponseBody
    public Map<String, Long> getVehicleCountByCounty() {
        return electricVehicleService.getVehicleCountByCounty();
    }

    // 10. Number of Make Cars Present in Each County
    @GetMapping("/api/statistics/make-count-by-county")
    @ResponseBody
    public Map<String, Map<String, Long>> getMakeCountByCounty() {
        return electricVehicleService.getMakeCountByCounty();
    }

    // 11. Number of Unique Models
    @GetMapping("/api/statistics/unique-models")
    @ResponseBody
    public Map<String, Long> getUniqueModelsCount() {
        return electricVehicleService.getUniqueModelsCount();
    }

    // 12. Yearly Electric Range Average per Make
    @GetMapping("/api/statistics/yearly-range-average-per-make")
    @ResponseBody
    public Map<String, Map<Integer, Double>> getYearlyElectricRangeAveragePerMake() {
        return electricVehicleService.getYearlyElectricRangeAveragePerMake();
    }


}
