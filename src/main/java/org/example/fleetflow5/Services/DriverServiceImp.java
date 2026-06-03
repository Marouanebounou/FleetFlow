package org.example.fleetflow5.Services;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.DriverDto;
import org.example.fleetflow5.Mappers.DriverMapper;
import org.example.fleetflow5.Models.Driver;
import org.example.fleetflow5.Repositories.DriverRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImp implements DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Override
    public DriverDto addDriver(DriverDto driverDto) {
        Driver driver = driverMapper.toEntity(driverDto);
        driver.setIsAvailable(true);
        Driver saved = driverRepository.save(driver);
        return driverMapper.toDto(saved);
    }

    @Override
    public DriverDto updateDriver(long id, DriverDto driverDto) {
        Driver existingDriver = driverRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        existingDriver.setName(driverDto.getName());
        existingDriver.setEmail(driverDto.getEmail());
        existingDriver.setPassword(driverDto.getPassword());
        existingDriver.setRole(driverDto.getRole());
        existingDriver.setDriverLicense(driverDto.getDriverLicenseType());
        existingDriver.setIsAvailable(driverDto.getIsAvailable());

        Driver updated = driverRepository.save(existingDriver);
        return driverMapper.toDto(updated);
    }

    @Override
    public void deleteDriver(long id) {
        driverRepository.deleteById(id);
    }

    @Override
    public List<DriverDto> listAvailableDrivers(Pageable pageable) {
        return driverRepository.findByIsAvailableTrue(pageable).stream()
                .map(driverMapper::toDto)
                .toList();
    }
}

