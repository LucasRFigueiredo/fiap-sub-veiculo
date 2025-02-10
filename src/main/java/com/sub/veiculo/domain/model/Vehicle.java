package com.sub.veiculo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private Long id;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private double preco;
}
