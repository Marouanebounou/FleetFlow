package org.example.fleetflow5.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.*;
import org.example.fleetflow5.Enums.DriverLicenseType;

@Entity
@Table(name = "drivers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends User {
    @Enumerated(EnumType.STRING)
    private DriverLicenseType driverLicense;
    private Boolean isAvailable;
}
