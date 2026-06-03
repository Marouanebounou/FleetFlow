package org.example.fleetflow5.Dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.fleetflow5.Enums.StatutLivraison;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivraisonDto {
    private Long id;

    @NotNull(message = "Delivery date is obligatory")
    private LocalDateTime dateLivraison;

    @NotBlank(message = "Start address is obligatory")
    private String startAdresse;

    @NotBlank(message = "Finish address is obligatory")
    private String finishAdresse;

    @NotNull(message = "Delivery status is obligatory")
    @Enumerated(EnumType.STRING)
    private StatutLivraison statut;

    private Long driverId;

    private Long vehicleId;

    @NotNull(message = "Client is obligatory")
    private Long clientId;
}

