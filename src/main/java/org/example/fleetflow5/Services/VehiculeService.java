package org.example.fleetflow5.Services;

import jakarta.validation.Valid;
import org.example.fleetflow5.Dtos.VehicleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehiculeService {
    VehicleDto save(VehicleDto vehicleDto);
    List<VehicleDto> findByStatut(String statut);
    VehicleDto update(long id, VehicleDto vehicule);

    List<VehicleDto> getAll(Pageable pageable);

    void delete(long id);

    List<VehicleDto> findByCapaciteGreaterThan(int capacite , Pageable pageable);
}
