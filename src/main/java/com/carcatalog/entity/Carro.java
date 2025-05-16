package com.carcatalog.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String marca;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
    private Integer anoFabricacao;
    @Column(nullable = false)
    private BigDecimal preco;
    @Column(nullable = false)
    private String cor;

}
