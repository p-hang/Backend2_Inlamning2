package se.yrgo.carrepair.rest;

import org.springframework.web.bind.annotation.*;
import se.yrgo.carrepair.dto.CustomerDTO;
import se.yrgo.carrepair.dto.VehicleBrandDTO;
import se.yrgo.carrepair.dto.VehicleDTO;
import se.yrgo.carrepair.service.CustomerService;
import se.yrgo.carrepair.service.VehicleService;

import java.util.List;

@RestController
public class CarRepairRestController {

    private final CustomerService customerService;
    private final VehicleService vehicleService;

    public CarRepairRestController(
            CustomerService customerService,
            VehicleService vehicleService) {
        this.customerService = customerService;
        this.vehicleService = vehicleService;
    }

    @PostMapping("/customers")
    public CustomerDTO createCustomer(
            @RequestParam String name,
            @RequestParam String phone) {

        return customerService.createCustomer(name, phone);
    }

    @PostMapping("/vehicles")
    public VehicleDTO createVehicle(
            @RequestParam String registrationNumber,
            @RequestParam String brand,
            @RequestParam String model,
            @RequestParam int productionYear) {

        return vehicleService.createVehicle(
                registrationNumber,
                brand,
                model,
                productionYear
        );
    }

    @PostMapping("/addvehicletocustomer")
    public VehicleDTO addVehicleToCustomer(
            @RequestParam Long customerId,
            @RequestParam Long vehicleId) {

        return vehicleService.addVehicleToCustomer(customerId, vehicleId);
    }

    @GetMapping("/customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomersWithVehicles();
    }

    @GetMapping("/vehicles")
    public List<VehicleDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/vehicles-by-brand")
    public List<VehicleDTO> getVehiclesByBrand(
            @RequestParam String brand) {

        return vehicleService.getVehiclesByBrand(brand);
    }

    @GetMapping("/customer-id")
    public Long getCustomerIdByName(@RequestParam String name) {
        return customerService.getCustomerIdByName(name);
    }

    @PutMapping("/vehicles/{vehicleId}/brand")
    public String updateVehicleBrand(
            @PathVariable Long vehicleId,
            @RequestParam String brand) {

        int updatedRows = vehicleService.updateVehicleBrand(vehicleId, brand);

        if (updatedRows == 0) {
            throw new RuntimeException("Vehicle not found");
        }

        return "Vehicle brand updated successfully";
    }

    @GetMapping("/vehicle-brand-info")
    public List<VehicleBrandDTO> getAllVehicleBrands() {
        return vehicleService.getAllVehicleBrands();
    }
}