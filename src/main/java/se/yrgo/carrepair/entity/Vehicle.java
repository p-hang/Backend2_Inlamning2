package se.yrgo.carrepair.entity;

import jakarta.persistence.*;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String registrationNumber;
    private String brand;
    private String model;
    private int productionYear;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Vehicle() {
    }
    public Vehicle(String registrationNumber, String brand, String model, int productionYear) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    public Long getId() {
        return id;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
