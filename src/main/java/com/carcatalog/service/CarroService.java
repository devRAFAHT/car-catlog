package com.carcatalog.service;

import com.carcatalog.entity.Carro;
import com.carcatalog.exception.CarAlreadyExistsException;
import com.carcatalog.exception.ResourceNotFoundException;
import com.carcatalog.repository.CarroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarroService {

    private final CarroRepository repository;

    @Transactional
    public Carro create(Carro carro) {
        log.info("Criando novo carro: {}", carro);

        boolean exists = repository.findByNomeAndMarcaAndTipoAndAnoFabricacaoAndPrecoAndCor(carro.getNome(), carro.getMarca(), carro.getTipo(), carro.getAnoFabricacao(), carro.getPreco(), carro.getCor()).isPresent();

        if (exists) {
            log.error("Já existe um carro com os dados fornecidos: {}", carro);
            throw new CarAlreadyExistsException("Já existe um carro com esses dados.");
        }

        carro = repository.save(carro);
        log.info("Carro salvo com sucesso: {}", carro);
        return carro;
    }

    @Transactional
    public Carro update(Long id, Carro novoCarro) {
        log.info("Atualizando carro com ID: {}", id);
        Carro carro = findById(id);

        boolean exists = repository.findByNomeAndMarcaAndTipoAndAnoFabricacaoAndPrecoAndCor(novoCarro.getNome(), novoCarro.getMarca(), novoCarro.getTipo(), novoCarro.getAnoFabricacao(), novoCarro.getPreco(), novoCarro.getCor()).filter(c -> !c.getId().equals(id)).isPresent();

        if (exists) {
            log.error("Já existe um carro com os dados fornecidos: {}", novoCarro);
            throw new CarAlreadyExistsException("Já existe um carro com esses dados.");
        }

        carro.setNome(novoCarro.getNome());
        carro.setPreco(novoCarro.getPreco());
        carro.setAnoFabricacao(novoCarro.getAnoFabricacao());
        carro.setTipo(novoCarro.getTipo());
        carro.setMarca(novoCarro.getMarca());
        carro.setCor(novoCarro.getCor());

        carro = repository.save(carro);
        log.info("Carro atualizado: {}", carro);
        return carro;
    }

    @Transactional
    public void delete(Long id) {
        log.info("Excluindo carro com ID: {}", id);
        Carro carro = findById(id);
        try {
            repository.delete(carro);
            log.info("Carro excluído com sucesso.");
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao excluir carro: {}", e.getMessage());
            throw new RuntimeException("Erro de integridade ao excluir carro.");
        }
    }

    @Transactional(readOnly = true)
    public Carro findById(Long id) {
        log.info("Buscando carro por ID: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Carro com id " + id + " não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<Carro> findAll() {
        log.info("Buscando todos os carros");
        return repository.findAll();
    }

}
