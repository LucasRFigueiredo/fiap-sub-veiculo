package com.sub.veiculo.application.port.output;

import com.sub.veiculo.domain.model.Vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleRepositoryPort {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle> findById(Long id);
    List<Vehicle> findAll();
    List<Vehicle> findAllByIds(List<Long> ids);
    void deleteById(Long id);
}