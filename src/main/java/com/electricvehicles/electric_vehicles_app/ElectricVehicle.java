
package com.electricvehicles.electric_vehicles_app;

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

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getElectricVehicleType() {
        return electricVehicleType;
    }

    public void setElectricVehicleType(String electricVehicleType) {
        this.electricVehicleType = electricVehicleType;
    }

    public String getCafvEligibility() {
        return cafvEligibility;
    }

    public void setCafvEligibility(String cafvEligibility) {
        this.cafvEligibility = cafvEligibility;
    }

    public String getElectricRange() {
        return electricRange;
    }

    public void setElectricRange(String electricRange) {
        this.electricRange = electricRange;
    }

    public String getBaseMsrp() {
        return baseMsrp;
    }

    public void setBaseMsrp(String baseMsrp) {
        this.baseMsrp = baseMsrp;
    }

    public String getLegislativeDistrict() {
        return legislativeDistrict;
    }

    public void setLegislativeDistrict(String legislativeDistrict) {
        this.legislativeDistrict = legislativeDistrict;
    }

    public String getVehicleLocation() {
        return vehicleLocation;
    }

    public void setVehicleLocation(String vehicleLocation) {
        this.vehicleLocation = vehicleLocation;
    }

    public String getElectricUtility() {
        return electricUtility;
    }

    public void setElectricUtility(String electricUtility) {
        this.electricUtility = electricUtility;
    }

    public String getCensusTract() {
        return censusTract;
    }

    public void setCensusTract(String censusTract) {
        this.censusTract = censusTract;
    }
}

