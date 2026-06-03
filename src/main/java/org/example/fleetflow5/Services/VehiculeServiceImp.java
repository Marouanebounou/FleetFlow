package org.example.fleetflow5.Services;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.VehicleDto;
import org.example.fleetflow5.Enums.StatusVehicle;
import org.example.fleetflow5.Enums.StatutLivraison;
import org.example.fleetflow5.Mappers.VehicleMapper;
import org.example.fleetflow5.Models.Vehicle;
import org.example.fleetflow5.Repositories.LivraisonRepository;
import org.example.fleetflow5.Repositories.VehicleRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeServiceImp implements VehiculeService{

    private final VehicleRepository vehiculeRepository;
    private final VehicleMapper vehilculeMapper;

    @Override
    public VehicleDto save(VehicleDto vehicleDto) {
        Vehicle vehicle = vehiculeRepository.save(vehilculeMapper.toEntity(vehicleDto));
        return vehilculeMapper.toDto(vehicle);
    }

    @Override
    public List<VehicleDto> findByStatut(String statut) {
        return vehiculeRepository.findAllByStatus(StatusVehicle.valueOf(statut)).stream().map(vehilculeMapper::toDto).toList();
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

    @Override
    public List<VehicleDto> getAll(Pageable pageable) {
        return vehiculeRepository.findAll(pageable).stream().map(vehilculeMapper::toDto).toList();
    }

    @Override
    public void delete(long id) {
        if (!vehiculeRepository.existsById(id)){
            throw new IllegalArgumentException("User not found");
        }
        vehiculeRepository.deleteById(id);
    }

    @Override
    public List<VehicleDto> findByCapaciteGreaterThan(int capacite , Pageable pageable) {
        List<Vehicle> av = vehiculeRepository.findAll(pageable).stream().filter(vehicle -> vehicle.getCapacity() > capacite).toList();
        return av.stream().map(vehilculeMapper::toDto).toList();
    }

}
