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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class VehicleControllerTest {

    @Mock
    private VehicleUseCase service;

    @InjectMocks
    private VehicleController controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testCreate() throws Exception {
        VehicleRequestDTO dto = new VehicleRequestDTO();
        dto.setMarca("Ford");
        dto.setModelo("Focus");
        dto.setAno(2022);
        dto.setCor("Preto");
        dto.setPreco(75000.0);

        Vehicle createdVehicle = new Vehicle(1L, "Ford", "Focus", 2022, "Preto", 75000.0);

        when(service.createVehicle(any(Vehicle.class))).thenReturn(createdVehicle);

        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "marca": "Ford",
                          "modelo": "Focus",
                          "ano": 2022,
                          "cor": "Preto",
                          "preco": 75000.0
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.marca").value("Ford"))
                .andExpect(jsonPath("$.modelo").value("Focus"))
                .andExpect(jsonPath("$.ano").value(2022))
                .andExpect(jsonPath("$.cor").value("Preto"))
                .andExpect(jsonPath("$.preco").value(75000.0));

        verify(service, times(1)).createVehicle(any(Vehicle.class));
    }

    @Test
    void testGet() throws Exception {
        Vehicle vehicle = new Vehicle(1L, "Ford", "Focus", 2022, "Preto", 75000.0);
        when(service.getVehicle(1L)).thenReturn(vehicle);

        mockMvc.perform(get("/vehicles/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.marca").value("Ford"))
                .andExpect(jsonPath("$.modelo").value("Focus"))
                .andExpect(jsonPath("$.ano").value(2022))
                .andExpect(jsonPath("$.cor").value("Preto"))
                .andExpect(jsonPath("$.preco").value(75000.0));

        verify(service, times(1)).getVehicle(1L);
    }

    @Test
    void testList() throws Exception {
        List<Vehicle> vehicles = Arrays.asList(
                new Vehicle(1L, "Ford", "Focus", 2022, "Preto", 75000.0),
                new Vehicle(2L, "Chevrolet", "Cruze", 2021, "Branco", 80000.0)
        );
        when(service.listVehicles()).thenReturn(vehicles);

        mockMvc.perform(get("/vehicles")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].marca").value("Ford"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].marca").value("Chevrolet"));

        verify(service, times(1)).listVehicles();
    }

    @Test
    void testDelete() throws Exception {
        Long vehicleId = 1L;

        mockMvc.perform(delete("/vehicles/{id}", vehicleId))
                .andExpect(status().isOk());

        verify(service, times(1)).removeVehicle(vehicleId);
    }
}
