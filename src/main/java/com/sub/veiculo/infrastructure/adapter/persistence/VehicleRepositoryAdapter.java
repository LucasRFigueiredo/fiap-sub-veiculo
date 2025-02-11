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
        return toModel(savedEntity);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return jpaRepository.findById(id).map(this::toModel);
    }

    @Override
    public List<Vehicle> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<Vehicle> findAllByIds(List<Long> ids) {
        return jpaRepository.findAllById(ids).stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    private Vehicle toModel(VehicleEntity entity) {
        return new Vehicle(
                entity.getId(),
                entity.getMarca(),
                entity.getModelo(),
                entity.getAno(),
                entity.getCor(),
                entity.getPreco()
        );
    }
}
