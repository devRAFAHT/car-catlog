package com.carcatalog.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CarroCreateDto {

    @NotBlank(message = "O campo modelo é obrigatório")
    private String modelo;
    @NotBlank(message = "O campo marca é obrigatório")
    private String marca;
    @NotNull(message = "O campo anoFabricacao é obrigatório")
    private Integer anoFabricacao;
    @NotNull(message = "O campo preco é obrigatório")
    private BigDecimal preco;
    @NotBlank(message = "O campo cor é obrigatório")
    private String cor;
    @NotNull(message = "O campo quilometragem é obrigatório")
    private BigDecimal quilometragem;
    @NotBlank(message = "O campo statusDisponibilidade é obrigatório")
    private String statusDisponibilidade;
    @NotBlank(message = "O campo urlImg é obrigatório")
    private String urlImg;

}
