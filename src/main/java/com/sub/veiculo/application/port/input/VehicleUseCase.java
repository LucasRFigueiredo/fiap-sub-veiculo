package com.sub.veiculo.application.port.input;

import com.sub.veiculo.domain.model.Vehicle;
import java.util.List;

public interface VehicleUseCase {
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle getVehicle(Long id);
    List<Vehicle> listVehicles();
    void removeVehicle(Long id);
    Vehicle updateVehicle(Long id, Vehicle vehicle);
}
