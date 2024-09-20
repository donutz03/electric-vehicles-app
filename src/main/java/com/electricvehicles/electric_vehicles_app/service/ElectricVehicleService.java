package com.electricvehicles.electric_vehicles_app.service;

import com.electricvehicles.electric_vehicles_app.model.ElectricVehicle;
import com.electricvehicles.electric_vehicles_app.repository.ElectricVehicleRepository;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ElectricVehicleService {
    final private List<ElectricVehicle> electricVehicles = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(ElectricVehicleService.class);

    @Autowired
    private ElectricVehicleRepository electricVehicleRepository;

    public ElectricVehicle saveVehicle(ElectricVehicle electricVehicle) {
        return electricVehicleRepository.save(electricVehicle);
    }

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
                ev.setDOLVehicleId(line[13]);
                ev.setVehicleLocation(line[14]);
                ev.setElectricUtility(line[15]);
                ev.setCensusTract(line[16]);
                electricVehicles.add(ev);
            }
        } catch (Exception e) {
            logger.error("An error occurred while processing: {}", e.getMessage(), e);
        }
    }

    public ElectricVehicle getVehicleById(String id) {
        return electricVehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found for this id :: " + id));
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

    // 1. Electric Utility: Vehicle Count per Utility
    public Map<String, Long> getVehicleCountByElectricUtility() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(ElectricVehicle::getElectricUtility, Collectors.counting()));
    }

    // 2. Electric Range Statistics per Electric Utility
    public Map<String, Map<String, Double>> getElectricRangeStatsByUtility() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(
                        ElectricVehicle::getElectricUtility,
                        Collectors.collectingAndThen(
                                Collectors.mapping(ev -> {
                                    try {
                                        return Double.parseDouble(ev.getElectricRange());
                                    } catch (NumberFormatException e) {
                                        return 0.0;
                                    }
                                }, Collectors.toList()),
                                this::calculateStatistics
                        )
                ));
    }

    // Helper method to calculate statistics (average, median, min, max, mode)
    private Map<String, Double> calculateStatistics(List<Double> ranges) {
        Map<String, Double> stats = new HashMap<>();
        if (ranges.isEmpty()) {
            stats.put("average", 0.0);
            stats.put("median", 0.0);
            stats.put("min", 0.0);
            stats.put("max", 0.0);
            stats.put("mode", 0.0);
            return stats;
        }

        Collections.sort(ranges);
        double sum = ranges.stream().mapToDouble(Double::doubleValue).sum();
        double average = sum / ranges.size();
        double median = ranges.size() % 2 == 0 ?
                (ranges.get(ranges.size() / 2) + ranges.get(ranges.size() / 2 - 1)) / 2 :
                ranges.get(ranges.size() / 2);
        double min = ranges.get(0);
        double max = ranges.get(ranges.size() - 1);
        double mode = ranges.stream()
                .collect(Collectors.groupingBy(Double::doubleValue, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        stats.put("average", average);
        stats.put("median", median);
        stats.put("min", min);
        stats.put("max", max);
        stats.put("mode", mode);
        return stats;
    }

    // 3. Electric Range Statistics (Overall)
    public Map<String, Double> getOverallElectricRangeStats() {
        List<Double> ranges = electricVehicles.stream()
                .map(ev -> {
                    try {
                        return Double.parseDouble(ev.getElectricRange());
                    } catch (NumberFormatException e) {
                        return 0.0;
                    }
                })
                .collect(Collectors.toList());
        return calculateStatistics(ranges);
    }

    // 4. Electric Range Statistics by Car Make
    public Map<String, Map<String, Double>> getElectricRangeStatsByMake() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(
                        ElectricVehicle::getMake,
                        Collectors.collectingAndThen(
                                Collectors.mapping(ev -> {
                                    try {
                                        return Double.parseDouble(ev.getElectricRange());
                                    } catch (NumberFormatException e) {
                                        return 0.0;
                                    }
                                }, Collectors.toList()),
                                this::calculateStatistics
                        )
                ));
    }

    // 5. Electric Range Statistics by City
    public Map<String, Map<String, Double>> getElectricRangeStatsByCity() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(
                        ElectricVehicle::getCity,
                        Collectors.collectingAndThen(
                                Collectors.mapping(ev -> {
                                    try {
                                        return Double.parseDouble(ev.getElectricRange());
                                    } catch (NumberFormatException e) {
                                        return 0.0;
                                    }
                                }, Collectors.toList()),
                                this::calculateStatistics
                        )
                ));
    }

    // 6. Number of Vehicles per Model Year
    public Map<Integer, Long> getVehicleCountByModelYear() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(
                        ev -> Integer.parseInt(ev.getModelYear()),
                        Collectors.counting()
                ));
    }

    // 7. Number of Unique Makes (Brands)
    public long getUniqueMakesCount() {
        return electricVehicles.stream()
                .map(ElectricVehicle::getMake)
                .distinct()
                .count();
    }

    // 8. Number of Cars Present in Each City
    public Map<String, Long> getVehicleCountByCity() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(ElectricVehicle::getCity, Collectors.counting()));
    }

    // 9. Number of Cars Present in Each County
    public Map<String, Long> getVehicleCountByCounty() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(ElectricVehicle::getCounty, Collectors.counting()));
    }

    // 10. Number of Make Cars Present in Each County
    public Map<String, Map<String, Long>> getMakeCountByCounty() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(
                        ElectricVehicle::getCounty,
                        Collectors.groupingBy(ElectricVehicle::getMake, Collectors.counting())
                ));
    }

    // 11. Number of Unique Models
    public Map<String, Long> getUniqueModelsCount() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(ElectricVehicle::getModel, Collectors.counting()));
    }

    // 12. Yearly Electric Range Average per Make
    public Map<String, Map<Integer, Double>> getYearlyElectricRangeAveragePerMake() {
        return electricVehicles.stream()
                .collect(Collectors.groupingBy(
                        ElectricVehicle::getMake,
                        Collectors.groupingBy(
                                ev -> Integer.parseInt(ev.getModelYear()),
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

    public ElectricVehicle updateVehicle(String id, ElectricVehicle electricVehicleDetails) {
        ElectricVehicle existingVehicle = electricVehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found for this id :: " + id));

        existingVehicle.setVin(electricVehicleDetails.getVin());
        existingVehicle.setCounty(electricVehicleDetails.getCounty());
        existingVehicle.setCity(electricVehicleDetails.getCity());
        existingVehicle.setState(electricVehicleDetails.getState());
        existingVehicle.setPostalCode(electricVehicleDetails.getPostalCode());
        existingVehicle.setModelYear(electricVehicleDetails.getModelYear());
        existingVehicle.setMake(electricVehicleDetails.getMake());
        existingVehicle.setModel(electricVehicleDetails.getModel());
        existingVehicle.setElectricVehicleType(electricVehicleDetails.getElectricVehicleType());
        existingVehicle.setCafvEligibility(electricVehicleDetails.getCafvEligibility());
        existingVehicle.setElectricRange(electricVehicleDetails.getElectricRange());
        existingVehicle.setBaseMsrp(electricVehicleDetails.getBaseMsrp());
        existingVehicle.setLegislativeDistrict(electricVehicleDetails.getLegislativeDistrict());
        existingVehicle.setDOLVehicleId(electricVehicleDetails.getDOLVehicleId());
        existingVehicle.setVehicleLocation(electricVehicleDetails.getVehicleLocation());
        existingVehicle.setElectricUtility(electricVehicleDetails.getElectricUtility());
        existingVehicle.setCensusTract(electricVehicleDetails.getCensusTract());

        return electricVehicleRepository.save(existingVehicle);
    }


    public ElectricVehicle partialUpdateVehicle(String id, Map<String, Object> updates) {
        ElectricVehicle existingVehicle = electricVehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found for this id :: " + id));

        updates.forEach((key, value) -> {
            switch (key) {
                case "vin": existingVehicle.setVin((String) value); break;
                case "county": existingVehicle.setCounty((String) value); break;
                case "city": existingVehicle.setCity((String) value); break;
                case "state": existingVehicle.setState((String) value); break;
                case "postalCode": existingVehicle.setPostalCode((String) value); break;
                case "modelYear": existingVehicle.setModelYear((String) value); break;
                case "make": existingVehicle.setMake((String) value); break;
                case "model": existingVehicle.setModel((String) value); break;
                case "electricVehicleType": existingVehicle.setElectricVehicleType((String) value); break;
                case "cafvEligibility": existingVehicle.setCafvEligibility((String) value); break;
                case "electricRange": existingVehicle.setElectricRange((String) value); break;
                case "baseMsrp": existingVehicle.setBaseMsrp((String) value); break;
                case "legislativeDistrict": existingVehicle.setLegislativeDistrict((String) value); break;
                case "DOLVehicleId": existingVehicle.setDOLVehicleId((String) value); break;
                case "vehicleLocation": existingVehicle.setVehicleLocation((String) value); break;
                case "electricUtility": existingVehicle.setElectricUtility((String) value); break;
                case "censusTract": existingVehicle.setCensusTract((String) value); break;
            }
        });

        return electricVehicleRepository.save(existingVehicle);
    }

    public void deleteVehicle(String id) {
        ElectricVehicle existingVehicle = electricVehicleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found for this id :: " + id));
        electricVehicleRepository.delete(existingVehicle);
    }


}
