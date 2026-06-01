package org.example.fleetflow5.Mappers;

import org.example.fleetflow5.Dtos.DriverDto;
import org.example.fleetflow5.Models.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    DriverDto toDto(Driver driver);
    Driver toEntity(DriverDto driverDto);
}
