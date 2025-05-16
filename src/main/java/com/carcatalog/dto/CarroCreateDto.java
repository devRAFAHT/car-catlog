package com.carcatalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarroCreateDto {

    @NotBlank(message = "O campo nome é obrigatório")
    private String nome;
    @NotBlank(message = "O campo marca é obrigatório")
    private String marca;
    @NotBlank(message = "O campo tipo é obrigatório")
    private String tipo;
    @NotNull(message = "O campo anoFabricacao é obrigatório")
    private Integer anoFabricacao;
    @NotNull(message = "O campo preco é obrigatório")
    private BigDecimal preco;
    @NotBlank(message = "O campo cor é obrigatório")
    private String cor;

}
