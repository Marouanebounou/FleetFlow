package org.example.fleetflow5.Dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fleetflow5.Enums.StatusVehicle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    @NotBlank(message = "Plate is Obligatory")
    private String plate;
    @Positive(message = "Capacity must be a positive number")
    private int capacity;
    @Enumerated(EnumType.STRING)
    private StatusVehicle status;
}
