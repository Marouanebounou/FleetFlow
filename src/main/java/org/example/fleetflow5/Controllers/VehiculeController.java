package org.example.fleetflow5.Controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow5.Dtos.VehicleDto;
import org.example.fleetflow5.Services.VehiculeServiceImp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicule")
@RequiredArgsConstructor
public class VehiculeController {

    private final VehiculeServiceImp vehilculeService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public VehicleDto save(@Valid @RequestBody VehicleDto vehicule){
        return vehilculeService.save(vehicule);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping
    public List<VehicleDto> getAllVehilcule(@PageableDefault(page = 0 , size = 5 , sort = "plate") Pageable pageable){
        return vehilculeService.getAll(pageable);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public void delete(@Valid @PathVariable long id){
        vehilculeService.delete(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public VehicleDto update(@PathVariable long id, @Valid @RequestBody VehicleDto vehicule){
        return vehilculeService.update(id, vehicule);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/status")
    public List<VehicleDto> status(@RequestParam String status){
        return vehilculeService.findByStatut(status);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    @GetMapping("/capacite")
    public List<VehicleDto> capacite(@RequestParam int capacite , @PageableDefault(page = 0 , size = 5 , sort = "plate") Pageable pageable){
        return vehilculeService.findByCapaciteGreaterThan(capacite , pageable);
    }
}
