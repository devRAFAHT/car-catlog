package com.carcatalog.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarroResponseDto {

    private Long id;
    private String modelo;
    private String marca;
    private Integer anoFabricacao;
    private BigDecimal preco;
    private String cor;
    private BigDecimal quilometragem;
    private String statusDisponibilidade;
    private String urlImg;

}
