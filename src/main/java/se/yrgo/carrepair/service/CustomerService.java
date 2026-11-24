package se.yrgo.carrepair.service;

import org.springframework.stereotype.Service;
import se.yrgo.carrepair.dto.CustomerDTO;
import se.yrgo.carrepair.dto.VehicleDTO;
import se.yrgo.carrepair.entity.Customer;
import se.yrgo.carrepair.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomerByName(String name) {
        return customerRepository.findByName(name);
    }

    public List<CustomerDTO> getAllCustomersWithVehicles() {

        return customerRepository.findAllCustomersWithVehicles()
                .stream()
                .map(customer -> new CustomerDTO(
                        customer.getId(),
                        customer.getName(),
                        customer.getPhoneNumber(),
                        customer.getVehicles().stream()
                                .map(vehicle -> new VehicleDTO(
                                        vehicle.getId(),
                                        vehicle.getRegistrationNumber(),
                                        vehicle.getBrand(),
                                        vehicle.getModel(),
                                        vehicle.getProductionYear()
                                ))
                                .toList()
                ))
                .toList();
    }

    public Long getCustomerIdByName(String name) {

        return customerRepository.findByName(name)
                .map(Customer::getId)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));
    }

    public CustomerDTO createCustomer(String name, String phoneNumber) {

        Customer customer = new Customer(name, phoneNumber);

        Customer savedCustomer = customerRepository.save(customer);

        return new CustomerDTO(
                savedCustomer.getId(),
                savedCustomer.getName(),
                savedCustomer.getPhoneNumber(),
                List.of()
        );
    }
}

