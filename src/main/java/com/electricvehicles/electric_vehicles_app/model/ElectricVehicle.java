
package com.electricvehicles.electric_vehicles_app.model;

import lombok.Data;

@Data
public class ElectricVehicle {
    private String vin;
    private String county;
    private String city;
    private String state;
    private String postalCode;
    private String modelYear;
    private String make;
    private String model;
    private String electricVehicleType;
    private String cafvEligibility;
    private String electricRange;
    private String baseMsrp;
    private String legislativeDistrict;
    private String vehicleLocation;
    private String electricUtility;
    private String censusTract;

    @Override
    public String toString() {
        return "ElectricVehicle{" +
                "vin='" + vin + '\'' +
                ", county='" + county + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", modelYear=" + modelYear +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", electricVehicleType='" + electricVehicleType + '\'' +
                ", cafvEligibility='" + cafvEligibility + '\'' +
                ", electricRange=" + electricRange +
                ", baseMsrp=" + baseMsrp +
                ", legislativeDistrict=" + legislativeDistrict +
                ", vehicleLocation='" + vehicleLocation + '\'' +
                ", electricUtility='" + electricUtility + '\'' +
                ", censusTract='" + censusTract + '\'' +
                '}';
    }

}

