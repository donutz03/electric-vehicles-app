package com.electricvehicles.electric_vehicles_app.service;

import com.electricvehicles.electric_vehicles_app.model.ElectricVehicle;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ElectricVehicleService {
    final private List<ElectricVehicle> electricVehicles = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(ElectricVehicleService.class);

    @PostConstruct
    public void loadData() {
        try (CSVReader reader = new CSVReader(new FileReader("src/main/data/Electric_Vehicle_Population_Data.csv"))) {
            String[] line;
            boolean isFirstLine = true;
            while ((line = reader.readNext()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                ElectricVehicle ev = new ElectricVehicle();
                ev.setVin(line[0]);
                ev.setCounty(line[1]);
                ev.setCity(line[2]);
                ev.setState(line[3]);
                ev.setPostalCode(line[4]);
                ev.setModelYear(line[5]);
                ev.setMake(line[6]);
                ev.setModel(line[7]);
                ev.setElectricVehicleType(line[8]);
                ev.setCafvEligibility(line[9]);
                ev.setElectricRange(line[10]);
                ev.setBaseMsrp(line[11]);
                ev.setLegislativeDistrict(line[12]);
                ev.setVehicleLocation(line[14]);
                ev.setElectricUtility(line[15]);
                ev.setCensusTract(line[16]);
                electricVehicles.add(ev);
            }
        } catch (Exception e) {
            logger.error("An error occurred while processing: {}", e.getMessage(), e);
        }
    }

    public List<ElectricVehicle> getFirst20ElectricVehicles() {
        return electricVehicles.subList(0, Math.min(20, electricVehicles.size()));
    }

    public List<ElectricVehicle> getAllElectricVehicles() {
        return new ArrayList<>(electricVehicles);
    }


    public long getTotalVehicles() {
        return electricVehicles.size();
    }

    public List<ElectricVehicle> getVehicles(int page, int size) {
        int skip = page * size;
        return electricVehicles.stream()
                .skip(skip)
                .limit(size)
                .collect(Collectors.toList());
    }


    public Map<String, Long> getVehicleTypeDistribution() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(ElectricVehicle::getElectricVehicleType, Collectors.counting()));
    }


    public double getAverageElectricRange() {
        return electricVehicles.stream()
                .mapToInt(ev -> {
                    try {
                        return Integer.parseInt(ev.getElectricRange());
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .average()
                .orElse(0.0);
    }


    public Map<String, Long> getVehiclesByManufacturer() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(ElectricVehicle::getMake, Collectors.counting()));
    }


    public List<ElectricVehicle> searchVehicles(String make, String model) {
        return electricVehicles.stream()
                .filter(ev -> (make == null || ev.getMake().equalsIgnoreCase(make)) &&
                        (model == null || ev.getModel().equalsIgnoreCase(model)))
                .collect(Collectors.toList());
    }

    public Map<String, Map<String, Long>> getVehicleCountByCountyAndState() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(
                        ElectricVehicle::getState,
                        Collectors.groupingBy(ElectricVehicle::getCounty, Collectors.counting())
                ));
    }

    public Map<String, Map<String, Double>> getAverageRangeByMakeAndModel() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(
                        ElectricVehicle::getMake,
                        Collectors.groupingBy(
                                ElectricVehicle::getModel,
                                Collectors.averagingDouble(ev -> {
                                    try {
                                        return Double.parseDouble(ev.getElectricRange());
                                    } catch (NumberFormatException e) {
                                        return 0.0;
                                    }
                                })
                        )
                ));
    }

    public Map<Integer, Long> getRegistrationTrendsByYear() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(
                        ev -> Integer.parseInt(ev.getModelYear()),
                        Collectors.counting()
                ));
    }

    public Map<String, Long> getCAFVEligibilitySummary() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(ElectricVehicle::getCafvEligibility, Collectors.counting()));
    }

    public List<Map<String, Object>> getVehicleDistributionHeatmapData() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(ElectricVehicle::getVehicleLocation, Collectors.counting()))
                .entrySet().stream()
                .map(entry -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("location", entry.getKey());
                    map.put("count", entry.getValue());
                    return map;
                })
                .collect(Collectors.toList());
    }


    public List<ElectricVehicle> searchVehiclesByYearAndMake(int year, String make) {
        return electricVehicles.stream()
                .filter(ev -> (ev.getModelYear().equals(String.valueOf(year))) && ev.getMake().equalsIgnoreCase(make))
                .collect(Collectors.toList());
    }

    public Map<String, Map<String, Object>> getManufacturerStatistics() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(ElectricVehicle::getMake, Collectors.collectingAndThen(
                        Collectors.toList(),
                        list -> {
                            Map<String, Object> stats = new HashMap<>();
                            stats.put("number_of_models", list.stream().map(ElectricVehicle::getModel).distinct().count());
                            stats.put("average_msrp", list.stream()
                                    .mapToDouble(ev -> {
                                        try {
                                            return Double.parseDouble(ev.getBaseMsrp());
                                        } catch (NumberFormatException e) {
                                            return 0.0;
                                        }
                                    })
                                    .average()
                                    .orElse(0.0));
                            return stats;
                        }
                )));
    }
}
