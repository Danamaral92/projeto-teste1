package com.optimus.testebackend.domain.dto;

import com.optimus.testebackend.domain.entity.Paciente;
import com.optimus.testebackend.domain.mapper.PacienteMapper;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PacienteDTO {

    private Integer id;

    private Integer matricula;

    @Size(max = 90)
    private String nome;

    private Date dataInternamento;

    @Size(max = 10)
    private String cid;


}
