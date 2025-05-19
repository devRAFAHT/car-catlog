package com.carcatalog.factory;

import com.carcatalog.entity.Carro;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CarroFactory {

    public static Carro carroValido(){
        return new Carro(null, "Civic", "Honda", 2022, new BigDecimal("95000.00"), "Prata", new BigDecimal("15000.00"), "Disponível", "https://exemplo.com/imagem-civic.jpg");
    }

    public static Carro carroPersistido(){
        return new Carro(1L, "Civic", "Honda", 2022, new BigDecimal("95000.00"), "Prata", new BigDecimal("15000.00"), "Disponível", "https://exemplo.com/imagem-civic.jpg");
    }

    public static Carro carroAtualizado(){
        return new Carro(1L, "Fusion", "Ford", 2023, new BigDecimal("80000"), "Cinza", new BigDecimal("10000"), "Disponível", "https://img.com/fusion.jpg");
    }

    public static Carro carroAtualizar(){
        return new Carro(null, "Fusion", "Ford", 2023, new BigDecimal("80000"), "Cinza", new BigDecimal("10000"), "Disponível", "https://img.com/fusion.jpg");
    }

    public static List<Carro> carrosPersistidos(){
        List<Carro> carros = new ArrayList<>();
        carros.add(new Carro(1L, "Civic", "Honda", 2022, new BigDecimal("95000.00"), "Prata", new BigDecimal("15000.00"), "Disponível", "https://exemplo.com/imagem-civic.jpg"));
        carros.add(new Carro(2L, "Corolla", "Toyota", 2021, new BigDecimal("92000.00"), "Preto", new BigDecimal("20000.00"), "Vendido", "https://exemplo.com/imagem-corolla.jpg"));
        carros.add(new Carro(3L, "Onix", "Chevrolet", 2020, new BigDecimal("65000.00"), "Branco", new BigDecimal("30000.00"), "Disponível", "https://exemplo.com/imagem-onix.jpg"));
        return carros;
    }
}
