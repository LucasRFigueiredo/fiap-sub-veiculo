package com.sub.veiculo.infrastructure.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDTO {
    private Long id;
    private String marca;
    private String modelo;
    private Integer ano;
    private String cor;
    private Double preco;
}