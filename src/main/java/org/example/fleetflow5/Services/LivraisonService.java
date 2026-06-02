package org.example.fleetflow5.Services;

import org.example.fleetflow5.Dtos.LivraisonDto;

import java.util.List;

public interface LivraisonService {
    LivraisonDto createLivraison(LivraisonDto livraisonDto);
    LivraisonDto assignDriverAndVehicle(long livraisonId, long driverId, long vehicleId);
    LivraisonDto updateLivraisonStatus(long livraisonId, String status);
    List<LivraisonDto> listAllLivraisons();
}

