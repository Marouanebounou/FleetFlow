package org.example.fleetflow5.Mappers;

import org.example.fleetflow5.Dtos.VehicleDto;
import org.example.fleetflow5.Models.Vehicle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    VehicleDto  toDto(Vehicle vehicle);
    Vehicle toEntity(VehicleDto vehicleDto);
}
