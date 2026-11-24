package se.yrgo.carrepair.dto;

public class VehicleDTO {

    private final Long id;
    private final String registrationNumber;
    private final String brand;
    private final String model;
    private final int productionYear;

    public VehicleDTO(Long id, String registrationNumber, String brand, String model, int productionYear) {
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public Long getId() {
        return id;
    }
}
