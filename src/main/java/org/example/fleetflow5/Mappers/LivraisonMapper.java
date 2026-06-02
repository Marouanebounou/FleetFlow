package org.example.fleetflow5.Mappers;

import org.example.fleetflow5.Dtos.LivraisonDto;
import org.example.fleetflow5.Models.Livraison;
import org.springframework.stereotype.Component;

@Component
public class LivraisonMapper {

    public LivraisonDto toDto(Livraison livraison) {
        LivraisonDto dto = new LivraisonDto();
        dto.setId(livraison.getId());
        dto.setDateLivraison(livraison.getDateLivraison());
        dto.setStartAdresse(livraison.getStartAdresse());
        dto.setFinishAdresse(livraison.getFinishAdresse());
        dto.setStatut(livraison.getStatut());
        dto.setDriverId(livraison.getDriver() != null ? livraison.getDriver().getId() : null);
        dto.setVehicleId(livraison.getVehicle() != null ? livraison.getVehicle().getId() : null);
        dto.setClientId(livraison.getClient() != null ? livraison.getClient().getId() : null);
        return dto;
    }

    public Livraison toEntity(LivraisonDto dto) {
        Livraison livraison = new Livraison();
        livraison.setId(dto.getId());
        livraison.setDateLivraison(dto.getDateLivraison());
        livraison.setStartAdresse(dto.getStartAdresse());
        livraison.setFinishAdresse(dto.getFinishAdresse());
        livraison.setStatut(dto.getStatut());
        return livraison;
    }
}
