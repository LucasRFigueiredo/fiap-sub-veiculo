package com.sub.veiculo.infrastructure.adapter.persistence;

import com.sub.veiculo.application.port.output.VehicleRepositoryPort;
import com.sub.veiculo.domain.model.Vehicle;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VehicleRepositoryAdapter implements VehicleRepositoryPort {
    private final JpaVehicleRepository jpaRepository;

    public VehicleRepositoryAdapter(JpaVehicleRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        VehicleEntity entity = new VehicleEntity();
        BeanUtils.copyProperties(vehicle, entity);
        VehicleEntity savedEntity = jpaRepository.save(entity);
        Vehicle savedVehicle = new Vehicle(
                savedEntity.getId(), savedEntity.getMarca(), savedEntity.getModelo(),
                savedEntity.getAno(), savedEntity.getCor(), savedEntity.getPreco()
        );
        return savedVehicle;
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return jpaRepository.findById(id)
                .map(entity -> new Vehicle(
                        entity.getId(), entity.getMarca(), entity.getModelo(),
                        entity.getAno(), entity.getCor(), entity.getPreco()));
    }

    @Override
    public List<Vehicle> findAll() {
        return jpaRepository.findAll().stream()
                .map(entity -> new Vehicle(
                        entity.getId(), entity.getMarca(), entity.getModelo(),
                        entity.getAno(), entity.getCor(), entity.getPreco()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
