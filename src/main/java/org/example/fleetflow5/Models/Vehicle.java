package org.example.fleetflow5.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fleetflow5.Enums.StatusVehicle;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String plate;
    private int capacity;
    @Enumerated(EnumType.STRING)
    private StatusVehicle status;
}
