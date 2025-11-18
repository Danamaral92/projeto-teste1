package com.optimus.testebackend.domain.dto;

import com.optimus.testebackend.domain.entity.Cirurgia;
import com.optimus.testebackend.domain.entity.Medico;
import com.optimus.testebackend.domain.mapper.MedicoMapper;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoDTO {
    private Integer id;

    private Integer matricula;

    @Size(max = 90)
    private String nome;

    @Size(max = 10)
    private String crm;

    private EspecialidadeDTO especialidade;
}
