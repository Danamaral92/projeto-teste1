package com.optimus.testebackend.domain.mapper;

import com.optimus.testebackend.domain.dto.PacienteDTO;
import com.optimus.testebackend.domain.entity.Paciente;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PacienteMapper {

    PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);

    Paciente toEntity(PacienteDTO dto);

    PacienteDTO toDTO(Paciente entity);
}
