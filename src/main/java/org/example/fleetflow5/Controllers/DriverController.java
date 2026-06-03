package org.example.fleetflow5.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.DriverDto;
import org.example.fleetflow5.Mappers.DriverMapper;
import org.example.fleetflow5.Models.Driver;
import org.example.fleetflow5.Services.DriverServiceImp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chauffeurs")
@RequiredArgsConstructor
public class DriverController {

    private final DriverServiceImp driverServiceImp;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DriverDto> ajouterChauffeur(@Valid @RequestBody DriverDto driverDto) {
        DriverDto saved = driverServiceImp.addDriver(driverDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DriverDto> modifierChauffeur(@Valid @PathVariable int id, @RequestBody DriverDto driverDto) {
        DriverDto saved = driverServiceImp.updateDriver(id, driverDto);
        return ResponseEntity.ok(saved);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerChauffeur(@Valid @PathVariable int id) {
        driverServiceImp.deleteDriver(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/disponibles")
    public ResponseEntity<List<DriverDto>> listerChauffeursDisponibles(@PageableDefault(page = 0 , size = 5) Pageable pageable) {
        List<DriverDto> disposables = new ArrayList<>(driverServiceImp.listAvailableDrivers(pageable));
        return ResponseEntity.ok(disposables);
    }
}