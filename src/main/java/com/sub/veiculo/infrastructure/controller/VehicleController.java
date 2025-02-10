package com.sub.veiculo.infrastructure.controller;

import com.sub.veiculo.application.port.input.VehicleUseCase;
import com.sub.veiculo.domain.model.Vehicle;
import com.sub.veiculo.infrastructure.controller.dto.VehicleRequestDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    private final VehicleUseCase service;

    public VehicleController(VehicleUseCase service) {
        this.service = service;
    }

    @PostMapping
    public Vehicle create(@Valid @RequestBody VehicleRequestDTO dto) {
        Vehicle vehicle = new Vehicle(
                null,  // O ID será gerado automaticamente
                dto.getMarca(),
                dto.getModelo(),
                dto.getAno(),
                dto.getCor(),
                dto.getPreco()
        );
        return service.createVehicle(vehicle);
    }

    @GetMapping("/{id}")
    public Vehicle get(@PathVariable Long id) {
        return service.getVehicle(id);
    }

    @GetMapping
    public List<Vehicle> list() {
        return service.listVehicles();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeVehicle(id);
    }
}