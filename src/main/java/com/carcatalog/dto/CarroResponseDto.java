package com.carcatalog.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarroResponseDto {

    private Integer id;
    private String nome;
    private String marca;
    private String tipo;
    private Integer anoFabricacao;
    private BigDecimal preco;
    private String cor;

}
