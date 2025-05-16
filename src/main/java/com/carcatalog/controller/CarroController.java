package com.carcatalog.controller;

import com.carcatalog.dto.CarroCreateDto;
import com.carcatalog.dto.CarroResponseDto;
import com.carcatalog.entity.Carro;
import com.carcatalog.service.CarroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.carcatalog.dto.mapper.CarroMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CarroController {

    private final CarroService carroService;

    @PostMapping(value = "create")
    public ResponseEntity<CarroResponseDto> create(@RequestBody @Valid CarroCreateDto dto) {
        Carro criado = carroService.create(CarroMapper.toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CarroMapper.toDto(criado));
    }

    @GetMapping(value = "find-all")
    public ResponseEntity<List<CarroResponseDto>> findAll() {
        List<CarroResponseDto> lista = carroService.findAll()
                .stream()
                .map(CarroMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @GetMapping(value = "find-by-id", params = "id")
    public ResponseEntity<CarroResponseDto> findById(@RequestParam("id") Long id) {
        Carro carro = carroService.findById(id);
        return ResponseEntity.ok(CarroMapper.toDto(carro));
    }

    @PutMapping(value = "update", params = "id")
    public ResponseEntity<CarroResponseDto> update(@RequestParam("id") Long id, @RequestBody @Valid CarroCreateDto dto) {
        Carro atualizado = carroService.update(id, CarroMapper.toEntity(dto));
        return ResponseEntity.ok(CarroMapper.toDto(atualizado));
    }

    @DeleteMapping(value = "delete", params = "id")
    public ResponseEntity<String> delete(@RequestParam("id") Long id) {
        carroService.delete(id);
        return ResponseEntity.ok("Carro deletado com sucesso");
    }
}
