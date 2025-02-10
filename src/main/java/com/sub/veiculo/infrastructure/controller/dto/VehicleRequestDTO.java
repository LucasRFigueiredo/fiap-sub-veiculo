package com.sub.veiculo.infrastructure.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VehicleRequestDTO {

    @NotBlank(message = "Marca é obrigatória")
    private String marca;

    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    @NotNull(message = "Ano é obrigatório")
    private Integer ano;

    @NotBlank(message = "Cor é obrigatória")
    private String cor;

    @NotNull(message = "Preço é obrigatório")
    private Double preco;
}