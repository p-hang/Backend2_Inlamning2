package se.yrgo.carrepair.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import se.yrgo.carrepair.dto.VehicleBrandDTO;
import se.yrgo.carrepair.dto.VehicleDTO;
import se.yrgo.carrepair.entity.Customer;
import se.yrgo.carrepair.entity.Vehicle;
import se.yrgo.carrepair.repository.CustomerRepository;
import se.yrgo.carrepair.repository.VehicleRepository;

import java.util.List;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final CustomerRepository customerRepository;

    public VehicleService(VehicleRepository vehicleRepository,  CustomerRepository customerRepository) {
        this.vehicleRepository = vehicleRepository;
        this.customerRepository = customerRepository;
    }

    public Vehicle saveVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> findVehiclesByBrand(String brand) {
        return vehicleRepository.findByBrand(brand);
    }

    public List<Vehicle> findVehiclesByCustomerId(Long customerId) {
        return vehicleRepository.findVehiclesByCustomerId(customerId);
    }

    @Transactional
    public int updateVehicleBrand(Long vehicleId, String brand) {
        return vehicleRepository.updateVehicleBrand(vehicleId, brand);
    }

    public VehicleDTO createVehicle(
            String registrationNumber,
            String brand,
            String model,
            int productionYear) {

        Vehicle vehicle = new Vehicle(
                registrationNumber,
                brand,
                model,
                productionYear
        );

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return new VehicleDTO(
                savedVehicle.getId(),
                savedVehicle.getRegistrationNumber(),
                savedVehicle.getBrand(),
                savedVehicle.getModel(),
                savedVehicle.getProductionYear()
        );
    }

    public List<VehicleDTO> getAllVehicles() {

        return vehicleRepository.findAll()
                .stream()
                .map(vehicle -> new VehicleDTO(
                        vehicle.getId(),
                        vehicle.getRegistrationNumber(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getProductionYear()
                ))
                .toList();
    }

    public List<VehicleDTO> getVehiclesByBrand(String brand) {

        return vehicleRepository.findByBrand(brand)
                .stream()
                .map(vehicle -> new VehicleDTO(
                        vehicle.getId(),
                        vehicle.getRegistrationNumber(),
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getProductionYear()
                ))
                .toList();
    }

    @Transactional
    public VehicleDTO addVehicleToCustomer(Long customerId, Long vehicleId) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setCustomer(customer);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return new VehicleDTO(
                savedVehicle.getId(),
                savedVehicle.getRegistrationNumber(),
                savedVehicle.getBrand(),
                savedVehicle.getModel(),
                savedVehicle.getProductionYear()
        );
    }

    public List<VehicleBrandDTO> getAllVehicleBrands() {

        return vehicleRepository.findAllVehicleBrands()
                .stream()
                .map(projection -> new VehicleBrandDTO(
                        projection.getRegistrationNumber(),
                        projection.getBrand()
                ))
                .toList();
    }
}
