package se.yrgo.carrepair.dto;

import se.yrgo.carrepair.entity.Customer;

import java.util.List;

public class CustomerDTO {

    private final Long id;
    private final String name;
    private final String phoneNumber;
    private final List<VehicleDTO> vehicles;

    public CustomerDTO(Long id, String name, String phoneNumber, List<VehicleDTO> vehicles) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.vehicles = vehicles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<VehicleDTO> getVehicles() {
        return vehicles;
    }
}
