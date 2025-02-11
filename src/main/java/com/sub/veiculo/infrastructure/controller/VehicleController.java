package com.sub.veiculo.infrastructure.controller;

import com.sub.veiculo.application.port.input.VehicleUseCase;
import com.sub.veiculo.domain.model.Vehicle;
import com.sub.veiculo.infrastructure.controller.dto.VehicleRequestDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
                null,
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

    @PostMapping("/batch")
    public List<Vehicle> getVehiclesByIds(@RequestBody List<Long> ids) {
        return service.listVehiclesByIds(ids);
    }

    @GetMapping("/ordenados")
    public List<Vehicle> listOrderedByPrice() {
        return service.listVehicles().stream()
                .sorted((v1, v2) -> Double.compare(v1.getPreco(), v2.getPreco()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.removeVehicle(id);
    }
}
