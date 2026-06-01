package org.example.fleetflow5.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    @NotBlank(message = "Plate is Obligatory")
    private String plate;
    @NotBlank(message = "Capacity is Obligatory")
    @Positive(message = "Capacity must be a positive number")
    private int capacity;
}
