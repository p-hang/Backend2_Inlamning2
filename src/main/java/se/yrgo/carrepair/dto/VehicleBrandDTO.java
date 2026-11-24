package se.yrgo.carrepair.dto;

public class VehicleBrandDTO {

    private final String registrationNumber;
    private final String brand;

    public VehicleBrandDTO(String registrationNumber, String brand) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }
}
