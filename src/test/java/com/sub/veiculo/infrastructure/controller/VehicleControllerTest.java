package com.sub.veiculo.infrastructure.controller;

import com.sub.veiculo.application.port.input.VehicleUseCase;
import com.sub.veiculo.domain.model.Vehicle;
import com.sub.veiculo.infrastructure.controller.dto.VehicleRequestDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VehicleControllerTest {

    @Mock
    private VehicleUseCase vehicleUseCase;

    @InjectMocks
    private VehicleController vehicleController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
    }

    @Test
    void testCreateVehicle() throws Exception {
        Vehicle vehicle = new Vehicle(1L, "Toyota", "Corolla", 2022, "Branco", 90000.0);
        when(vehicleUseCase.createVehicle(any(Vehicle.class))).thenReturn(vehicle);

        VehicleRequestDTO request = new VehicleRequestDTO();
        request.setMarca("Toyota");
        request.setModelo("Corolla");
        request.setAno(2022);
        request.setCor("Branco");
        request.setPreco(90000.0);
        String jsonRequest = """
                {
                  "marca": "Toyota",
                  "modelo": "Corolla",
                  "ano": 2022,
                  "cor": "Branco",
                  "preco": 90000.0
                }
                """;

        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("Toyota"))
                .andExpect(jsonPath("$.modelo").value("Corolla"))
                .andExpect(jsonPath("$.ano").value(2022))
                .andExpect(jsonPath("$.preco").value(90000.0));

        verify(vehicleUseCase, times(1)).createVehicle(any(Vehicle.class));
    }

    @Test
    void testGetVehicle() throws Exception {
        Vehicle vehicle = new Vehicle(1L, "Toyota", "Corolla", 2022, "Branco", 90000.0);
        when(vehicleUseCase.getVehicle(1L)).thenReturn(vehicle);

        mockMvc.perform(get("/vehicles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.marca").value("Toyota"))
                .andExpect(jsonPath("$.modelo").value("Corolla"))
                .andExpect(jsonPath("$.ano").value(2022))
                .andExpect(jsonPath("$.preco").value(90000.0));

        verify(vehicleUseCase, times(1)).getVehicle(1L);
    }

    @Test
    void testListVehicles() throws Exception {
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle(1L, "Toyota", "Corolla", 2022, "Branco", 90000.0),
                new Vehicle(2L, "Honda", "Civic", 2021, "Preto", 85000.0)
        );
        when(vehicleUseCase.listVehicles()).thenReturn(vehicles);

        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].marca").value("Toyota"))
                .andExpect(jsonPath("$[1].marca").value("Honda"));

        verify(vehicleUseCase, times(1)).listVehicles();
    }

    @Test
    void testGetVehiclesByIds() throws Exception {
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle(1L, "Toyota", "Corolla", 2022, "Branco", 90000.0),
                new Vehicle(2L, "Honda", "Civic", 2021, "Preto", 85000.0)
        );
        when(vehicleUseCase.listVehiclesByIds(anyList())).thenReturn(vehicles);

        mockMvc.perform(post("/vehicles/batch")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1, 2]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].marca").value("Toyota"))
                .andExpect(jsonPath("$[1].marca").value("Honda"));

        verify(vehicleUseCase, times(1)).listVehiclesByIds(anyList());
    }

    @Test
    void testListOrderedByPrice() throws Exception {
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle(2L, "Honda", "Civic", 2021, "Preto", 85000.0),
                new Vehicle(1L, "Toyota", "Corolla", 2022, "Branco", 90000.0)
        );
        when(vehicleUseCase.listVehicles()).thenReturn(vehicles);

        mockMvc.perform(get("/vehicles/ordenados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].preco").value(85000.0))
                .andExpect(jsonPath("$[1].preco").value(90000.0));

        verify(vehicleUseCase, times(1)).listVehicles();
    }

    @Test
    void testDeleteVehicle() throws Exception {
        mockMvc.perform(delete("/vehicles/1"))
                .andExpect(status().isOk());

        verify(vehicleUseCase, times(1)).removeVehicle(1L);
    }
}
