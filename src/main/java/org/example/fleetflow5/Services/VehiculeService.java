package org.example.fleetflow5.Services;

import org.example.fleetflow5.Dtos.VehicleDto;

import java.util.List;

public interface VehiculeService {
    VehicleDto save(VehicleDto vehicleDto);
    List<VehicleDto> findByStatut(String statut);
    VehicleDto update(long id, VehicleDto vehicule);
}
