package org.example.fleetflow5.Services;

import org.example.fleetflow5.Dtos.DriverDto;

import java.util.List;

public interface DriverService {
    DriverDto addDriver(DriverDto driverDto);
    DriverDto updateDriver(long id, DriverDto driverDto);
    void deleteDriver(long id);
    List<DriverDto> listAvailableDrivers();
}

