package org.example.fleetflow5.Dtos;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class DriverDto extends UserRegisterDto {
    @Enumerated(EnumType.STRING)
    private DriverLicenseType driverLicenseType;
    private Boolean isAvailable;
}
