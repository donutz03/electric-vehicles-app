package com.electricvehicles.electric_vehicles_app.service;

import com.electricvehicles.electric_vehicles_app.model.ElectricVehicle;
import com.opencsv.CSVReader;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
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
                    continue; // Skip header
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
}
