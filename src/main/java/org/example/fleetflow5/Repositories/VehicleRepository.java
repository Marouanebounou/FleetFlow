package org.example.fleetflow5.Repositories;

import org.example.fleetflow5.Enums.StatusVehicle;
import org.example.fleetflow5.Models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
     Vehicle findByPlate(String licensePlate);
     @Query("select v from Vehicle v where v.status = :status")
     List<Vehicle> findAllByStatus(@Param("status") StatusVehicle status);
}
