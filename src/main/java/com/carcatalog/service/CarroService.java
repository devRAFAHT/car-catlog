package com.carcatalog.service;

import com.carcatalog.entity.Carro;
import com.carcatalog.exception.CarAlreadyExistsException;
import com.carcatalog.exception.DatabaseException;
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

        carro = repository.save(carro);
        log.info("Carro salvo com sucesso: {}", carro);
        return carro;
    }

    @Transactional
    public Carro update(Long id, Carro novoCarro) {
        log.info("Atualizando carro com ID: {}", id);
        Carro carro = findById(id);

        carro.setModelo(novoCarro.getModelo());
        carro.setMarca(novoCarro.getMarca());
        carro.setAnoFabricacao(novoCarro.getAnoFabricacao());
        carro.setPreco(novoCarro.getPreco());
        carro.setCor(novoCarro.getCor());
        carro.setQuilometragem(novoCarro.getQuilometragem());
        carro.setStatusDisponibilidade(novoCarro.getStatusDisponibilidade());
        carro.setUrlImg(novoCarro.getUrlImg());


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
            throw new DatabaseException("Erro de integridade ao excluir carro.");
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
