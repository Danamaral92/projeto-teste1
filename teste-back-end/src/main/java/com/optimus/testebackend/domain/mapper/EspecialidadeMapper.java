package com.optimus.testebackend.domain.mapper;

import com.optimus.testebackend.domain.dto.EspecialidadeDTO;
import com.optimus.testebackend.domain.entity.Especialidade;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface EspecialidadeMapper {
    EspecialidadeDTO toDTO(Especialidade especialidade);
    Especialidade toEntity(EspecialidadeDTO especialidadeDTO);
}
