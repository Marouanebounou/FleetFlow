package org.example.fleetflow5.Services;

import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.LivraisonDto;
import org.example.fleetflow5.Enums.StatutLivraison;
import org.example.fleetflow5.Mappers.LivraisonMapper;
import org.example.fleetflow5.Models.Client;
import org.example.fleetflow5.Models.Driver;
import org.example.fleetflow5.Models.Livraison;
import org.example.fleetflow5.Models.Vehicle;
import org.example.fleetflow5.Repositories.ClientRepository;
import org.example.fleetflow5.Repositories.DriverRepository;
import org.example.fleetflow5.Repositories.LivraisonRepository;
import org.example.fleetflow5.Repositories.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LivraisonServiceImp implements LivraisonService {

    private final LivraisonRepository livraisonRepository;
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;
    private final LivraisonMapper livraisonMapper;

    @Override
    public LivraisonDto createLivraison(LivraisonDto livraisonDto) {
        Livraison livraison = new Livraison();
        livraison.setDateLivraison(livraisonDto.getDateLivraison());
        livraison.setStartAdresse(livraisonDto.getStartAdresse());
        livraison.setFinishAdresse(livraisonDto.getFinishAdresse());
        livraison.setStatut(livraisonDto.getStatut() == null ? StatutLivraison.IN_PROGRESS : livraisonDto.getStatut());

        Client client = clientRepository.findById(livraisonDto.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        livraison.setClient(client);

        Livraison saved = livraisonRepository.save(livraison);
        return livraisonMapper.toDto(saved);
    }

    @Override
    @Transactional
    public LivraisonDto assignDriverAndVehicle(long livraisonId, long driverId, long vehicleId) {
        Livraison livraison = livraisonRepository.findById(livraisonId)
                .orElseThrow(() -> new RuntimeException("Livraison not found"));

        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        if (!Boolean.TRUE.equals(driver.getIsAvailable())) {
            throw new RuntimeException("Driver is not available");
        }

        livraison.setDriver(driver);
        livraison.setVehicle(vehicle);
        livraison.setStatut(StatutLivraison.IN_PROGRESS);

        driver.setIsAvailable(false);
        driverRepository.save(driver);

        Livraison updated = livraisonRepository.save(livraison);
        return livraisonMapper.toDto(updated);
    }

    @Override
    @Transactional
    public LivraisonDto updateLivraisonStatus(long livraisonId, String status) {
        Livraison livraison = livraisonRepository.findById(livraisonId)
                .orElseThrow(() -> new RuntimeException("Livraison not found"));

        StatutLivraison newStatus = StatutLivraison.valueOf(status.toUpperCase());
        livraison.setStatut(newStatus);

        if (newStatus == StatutLivraison.DELIVERED && livraison.getDriver() != null) {
            Driver driver = livraison.getDriver();
            driver.setIsAvailable(true);
            driverRepository.save(driver);
        }

        Livraison updated = livraisonRepository.save(livraison);
        return livraisonMapper.toDto(updated);
    }

    @Override
    public List<LivraisonDto> listAllLivraisons() {
        return livraisonRepository.findAll().stream()
                .map(livraisonMapper::toDto)
                .toList();
    }
}

