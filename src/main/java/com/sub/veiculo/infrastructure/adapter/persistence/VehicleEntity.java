package com.sub.veiculo.infrastructure.adapter.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "veiculos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private double preco;
}
