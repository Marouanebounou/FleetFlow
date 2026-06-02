package org.example.fleetflow5.Services;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.VehicleDto;
import org.example.fleetflow5.Enums.StatutLivraison;
import org.example.fleetflow5.Mappers.VehicleMapper;
import org.example.fleetflow5.Models.Vehicle;
import org.example.fleetflow5.Repositories.LivraisonRepository;
import org.example.fleetflow5.Repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImp implements VehiculeService{

    private final VehicleRepository vehiculeRepository;
    private final VehicleMapper vehilculeMapper;
    private final LivraisonRepository livraisonRepository;

    @Override
    public VehicleDto save(VehicleDto vehicleDto) {
        Vehicle vehicle = vehiculeRepository.save(vehilculeMapper.toEntity(vehicleDto));
        return vehilculeMapper.toDto(vehicle);
    }

    @Override
    public List<VehicleDto> findByStatut(String statut) {
        return vehiculeRepository.findAllByStatus(statut).stream().map(vehilculeMapper::toDto).toList();
    }

    @Override
    public VehicleDto update(long id, VehicleDto vehicle) {
        Vehicle existingVehicle = vehiculeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vehicle not found with id: " + id));

        existingVehicle.setPlate(vehicle.getPlate());
        existingVehicle.setCapacity(vehicle.getCapacity());

        Vehicle updatedVehicle = vehiculeRepository.save(existingVehicle);
        return vehilculeMapper.toDto(updatedVehicle);
    }

}
