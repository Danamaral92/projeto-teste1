package com.optimus.testebackend.domain.mapper;


import com.optimus.testebackend.domain.dto.InstrumentoDTO;
import com.optimus.testebackend.domain.entity.Instrumento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InstrumentoMapper {
    Instrumento toEntity(InstrumentoDTO dto);

    InstrumentoDTO toDTO(Instrumento entity);
}
