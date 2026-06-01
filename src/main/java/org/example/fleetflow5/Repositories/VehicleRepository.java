package org.example.fleetflow5.Repositories;

import org.example.fleetflow5.Models.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
     Vehicle findByLicensePlate(String licensePlate);
}
