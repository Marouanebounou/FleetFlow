package org.example.fleetflow5.Repositories;

import org.example.fleetflow5.Models.Driver;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	List<Driver> findByIsAvailableTrue(Pageable pageable);
}
