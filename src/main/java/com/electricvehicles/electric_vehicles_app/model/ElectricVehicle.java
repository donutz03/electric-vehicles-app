
package com.electricvehicles.electric_vehicles_app.model;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "electric_vehicle")
public class ElectricVehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vin;
    private String county;
    private String city;
    private String state;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "model_year")
    private String modelYear;

    private String make;
    private String model;

    @Column(name = "electric_vehicle_type")
    private String electricVehicleType;

    @Column(name = "cafv_eligibility")
    private String cafvEligibility;

    @Column(name = "electric_range")
    private String electricRange;

    @Column(name = "base_msrp")
    private String baseMsrp;

    @Column(name = "legislative_district")
    private String legislativeDistrict;

    @Column(name = "dolvehicle_id")
    private String DOLVehicleId;

    @Column(name = "vehicle_location")
    private String vehicleLocation;

    @Column(name = "electric_utility")
    private String electricUtility;

    @Column(name = "census_tract")
    private String censusTract;
}

