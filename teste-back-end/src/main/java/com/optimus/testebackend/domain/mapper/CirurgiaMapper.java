package com.optimus.testebackend.domain.mapper;


import com.optimus.testebackend.domain.dto.CirurgiaDTO;
import com.optimus.testebackend.domain.entity.Cirurgia;
import com.optimus.testebackend.domain.entity.MedicoCirurgia;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {InstrumentoMapper.class, MedicoMapper.class, PacienteMapper.class})
public interface CirurgiaMapper {

    Cirurgia toEntity(CirurgiaDTO dto);
    CirurgiaDTO toDTO(Cirurgia entity);

    @AfterMapping
    default void afterMap(@MappingTarget CirurgiaDTO target, Cirurgia source) {
        target.setMedicoPrincipalId(source.getMedicoPrincipalId());
    }

//    @AfterMapping
//    default void afterMap(@MappingTarget Cirurgia target, CirurgiaDTO source) {
//        target.setMedicos(source.getMedicos().stream().map(medicoDTO ->
//                target.
//                ).toList());
//        };
}
