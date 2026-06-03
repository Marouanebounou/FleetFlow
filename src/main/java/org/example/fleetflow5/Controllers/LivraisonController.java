package org.example.fleetflow5.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.LivraisonDto;
import org.example.fleetflow5.Enums.StatutLivraison;
import org.example.fleetflow5.Services.LivraisonServiceImp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/livraisons")
@RequiredArgsConstructor
public class LivraisonController {

    private final LivraisonServiceImp livraisonService;

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PostMapping
    public ResponseEntity<LivraisonDto> creerLivraison(@Valid @RequestBody LivraisonDto livraisonDTO) {
        LivraisonDto savedLivraison = livraisonService.createLivraison(livraisonDTO);
        return new ResponseEntity<>(savedLivraison, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @PutMapping("/{id}/assigner")
    public ResponseEntity<LivraisonDto> assignerChauffeurEtVehicule(
            @Valid
            @PathVariable int id,
            @RequestParam int chauffeurId,
            @RequestParam Long vehiculeId) {

        LivraisonDto livraison = livraisonService.assignDriverAndVehicle(id, chauffeurId, vehiculeId);
        return ResponseEntity.ok(livraison);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CHAUFFEUR')")
    @PatchMapping("/{id}/statut")
    public ResponseEntity<LivraisonDto> modifierStatut(
            @Valid
            @PathVariable int id,
            @RequestParam StatutLivraison statut) {

        LivraisonDto livraison = livraisonService.updateLivraisonStatus(id, String.valueOf(statut));
        return ResponseEntity.ok(livraison);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER', 'CHAUFFEUR')")
    @GetMapping
    public ResponseEntity<List<LivraisonDto>> listerLivraisons(@PageableDefault(page = 0 , size = 5) Pageable pageable) {
        List<LivraisonDto> livraisons = new ArrayList<>(livraisonService.listAllLivraisons(pageable));
        return ResponseEntity.ok(livraisons);
    }
}
