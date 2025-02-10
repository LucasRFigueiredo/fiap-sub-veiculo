package com.sub.veiculo.infrastructure.adapter.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaVehicleRepository extends JpaRepository<VehicleEntity, Long> {}