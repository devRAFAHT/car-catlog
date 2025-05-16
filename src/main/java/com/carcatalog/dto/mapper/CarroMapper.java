package com.carcatalog.dto.mapper;

import com.carcatalog.dto.CarroCreateDto;
import com.carcatalog.dto.CarroResponseDto;
import com.carcatalog.entity.Carro;
import org.modelmapper.ModelMapper;

public class CarroMapper {

    private static final ModelMapper mapper = new ModelMapper();

    public static Carro toEntity(CarroCreateDto createDto) {
        return mapper.map(createDto, Carro.class);
    }

    public static CarroResponseDto toDto(Carro carro) {
        return mapper.map(carro, CarroResponseDto.class);
    }

}
