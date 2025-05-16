package com.carcatalog.repository;

import com.carcatalog.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {
    Optional<Carro> findById(Long id);

    Optional<Carro> findByNomeAndMarcaAndTipoAndAnoFabricacaoAndPrecoAndCor(
            String nome,
            String marca,
            String tipo,
            Integer anoFabricacao,
            BigDecimal preco,
            String cor
    );
}
