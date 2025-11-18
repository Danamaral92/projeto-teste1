package com.optimus.testebackend.domain.mapper;

import com.optimus.testebackend.domain.dto.MedicoDTO;
import com.optimus.testebackend.domain.entity.Medico;
import com.optimus.testebackend.domain.entity.MedicoCirurgia;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = EspecialidadeMapper.class)
public interface MedicoMapper {
    Medico toEntity(MedicoDTO dto);

    MedicoDTO toDTO(Medico entity);

    default MedicoDTO toDTO(MedicoCirurgia entity){
        return toDTO(entity.getMedico());
    }

    default MedicoCirurgia toMedicoCirurgiaEntity(MedicoDTO dto){
        return MedicoCirurgia.builder()
                .medico(toEntity(dto))
                .build();
    }
}
