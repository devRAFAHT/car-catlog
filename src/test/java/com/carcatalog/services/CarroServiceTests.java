package com.carcatalog.services;

import com.carcatalog.entity.Carro;
import com.carcatalog.exception.DatabaseException;
import com.carcatalog.exception.ResourceNotFoundException;
import com.carcatalog.factory.CarroFactory;
import com.carcatalog.repository.CarroRepository;
import com.carcatalog.service.CarroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CarroServiceTests {

    @Mock
    private CarroRepository repository;

    @InjectMocks
    private CarroService service;

    Carro carro;
    Carro carroPersistido;
    List<Carro> carrosPersistidos;
    Carro carroAtualizado;

    @BeforeEach
    void setUp(){
        carro = CarroFactory.carroValido();
        carroPersistido = CarroFactory.carroPersistido();
        carrosPersistidos = CarroFactory.carrosPersistidos();
        carroAtualizado = CarroFactory.carroAtualizado();
    }

    @Test
    void createDeveSalvarERetornarOObjetoPersistido(){
        when(repository.save(carro)).thenReturn(carroPersistido);

        Carro carroSalvo = service.create(carro);

        assertNotNull(carroSalvo);
        assertEquals(carroPersistido, carroSalvo);
    }

    @Test
    void updateDeveAtualizarOCarroQuandoExistir() {
        Long id = 1L;
        Carro novoCarro = CarroFactory.carroAtualizar();

        when(repository.findById(id)).thenReturn(Optional.of(carroPersistido));
        when(repository.save(novoCarro)).thenReturn(carroPersistido);

        Carro atualizado = service.update(id, novoCarro);

        assertNotEquals(CarroFactory.carroPersistido(), atualizado);
        assertEquals(carroAtualizado, atualizado);
    }

    @Test
    void updateDeveLancarNotFoundExceptionQuandoNaoEncontrarCarro() {
        Long id = 99L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.update(id, carro));
    }

    @Test
    void deleteDeveExcluirCarroQuandoExistir() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(carroPersistido));
        doNothing().when(repository).delete(carroPersistido);

        assertDoesNotThrow(() -> service.delete(id));
        verify(repository, times(1)).delete(carroPersistido);
    }

    @Test
    void deleteDeveLancarDatabaseExceptionQuandoCapturarDataIntegrityViolationException() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(carroPersistido));
        doThrow(DataIntegrityViolationException.class).when(repository).delete(carroPersistido);

        DatabaseException ex = assertThrows(DatabaseException.class, () -> service.delete(id));
        assertEquals("Erro de integridade ao excluir carro.", ex.getMessage());
    }

    @Test
    void deleteDeveLancarNotFoundExceptionQuandoNaoEncontrarCarro() {
        Long id = 42L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.delete(id));
    }

    @Test
    void findByIdDeveRetornarCarroQuandoExistir() {
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.of(carroPersistido));

        Carro carroEncontrado = service.findById(id);

        assertNotNull(carroEncontrado);
        assertEquals(carroPersistido, carroEncontrado);
    }

    @Test
    void findByIdDeveLancarNotFoundExceptionQuandoNaoEncontrar() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.findById(99L));
    }

    @Test
    void findAllDeveRetornarListaDeCarros() {
        when(repository.findAll()).thenReturn(carrosPersistidos);

        List<Carro> resultado = service.findAll();

        assertNotNull(resultado);
        assertEquals(3, resultado.size());
        assertEquals(resultado, carrosPersistidos);
        verify(repository, times(1)).findAll();
    }
}
