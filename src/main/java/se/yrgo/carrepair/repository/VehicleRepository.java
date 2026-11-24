package se.yrgo.carrepair.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import se.yrgo.carrepair.entity.Vehicle;
import se.yrgo.carrepair.projection.VehicleBrandProjection;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    public List<Vehicle> findByBrand(String brand);

    @Query("SELECT v FROM Vehicle v WHERE v.customer.id = :customerId")
    List<Vehicle> findVehiclesByCustomerId(@Param("customerId") Long customerId);

    @Modifying
    @Query("UPDATE Vehicle v SET v.brand = :brand WHERE v.id = :vehicleId")
    int updateVehicleBrand(
            @Param("vehicleId") Long vehicleId,
            @Param("brand") String brand
    );

    @Query("SELECT v.registrationNumber AS registrationNumber, " +
            "v.brand AS brand FROM Vehicle v")
    List<VehicleBrandProjection> findAllVehicleBrands();
}
