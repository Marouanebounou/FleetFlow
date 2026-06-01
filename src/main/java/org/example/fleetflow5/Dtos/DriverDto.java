package org.example.fleetflow5.Dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fleetflow5.Enums.DriverLicenseType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDto extends UserDto{
    @NotBlank(message = "Driver license is Obligatory")
    private DriverLicenseType driverLicenseType;
    private Boolean isAvailable;
}
