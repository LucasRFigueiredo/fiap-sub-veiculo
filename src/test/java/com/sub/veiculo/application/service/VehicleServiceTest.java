package com.sub.veiculo.application.service;

import com.sub.veiculo.application.port.output.VehicleRepositoryPort;
import com.sub.veiculo.domain.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    @Mock
    private VehicleRepositoryPort repository;

    @InjectMocks
    private VehicleService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVehicle() {
        Vehicle vehicle = new Vehicle();
        when(repository.save(vehicle)).thenReturn(vehicle);

        Vehicle createdVehicle = service.createVehicle(vehicle);

        assertNotNull(createdVehicle);
        verify(repository, times(1)).save(vehicle);
    }

    @Test
    void testGetVehicle_Found() {
        Long vehicleId = 1L;
        Vehicle vehicle = new Vehicle();
        when(repository.findById(vehicleId)).thenReturn(Optional.of(vehicle));

        Vehicle foundVehicle = service.getVehicle(vehicleId);

        assertNotNull(foundVehicle);
        verify(repository, times(1)).findById(vehicleId);
    }

    @Test
    void testGetVehicle_NotFound() {
        Long vehicleId = 1L;
        when(repository.findById(vehicleId)).thenReturn(Optional.empty());

        Vehicle foundVehicle = service.getVehicle(vehicleId);

        assertNull(foundVehicle);
        verify(repository, times(1)).findById(vehicleId);
    }

    @Test
    void testListVehicles() {
        List<Vehicle> vehicles = Arrays.asList(new Vehicle(), new Vehicle());
        when(repository.findAll()).thenReturn(vehicles);

        List<Vehicle> result = service.listVehicles();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repository, times(1)).findAll();
    }

    @Test
    void testRemoveVehicle() {
        Long vehicleId = 1L;

        service.removeVehicle(vehicleId);

        verify(repository, times(1)).deleteById(vehicleId);
    }

    @Test
    void testUpdateVehicle_Found() {
        Long vehicleId = 1L;
        Vehicle updatedVehicle = new Vehicle();
        when(repository.findById(vehicleId)).thenReturn(Optional.of(new Vehicle()));
        when(repository.save(updatedVehicle)).thenReturn(updatedVehicle);

        Vehicle result = service.updateVehicle(vehicleId, updatedVehicle);

        assertNotNull(result);
        verify(repository, times(1)).findById(vehicleId);
        verify(repository, times(1)).save(updatedVehicle);
    }

    @Test
    void testUpdateVehicle_NotFound() {
        Long vehicleId = 1L;
        Vehicle updatedVehicle = new Vehicle();
        when(repository.findById(vehicleId)).thenReturn(Optional.empty());

        Vehicle result = service.updateVehicle(vehicleId, updatedVehicle);

        assertNull(result);
        verify(repository, times(1)).findById(vehicleId);
        verify(repository, times(0)).save(any(Vehicle.class));
    }
}
