package com.optimus.testebackend.domain.dto;


import com.optimus.testebackend.domain.entity.Paciente;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CirurgiaDTO {

    private Integer id;

    private Date data;

    private Paciente paciente;

    @Size(max = 180)
    private String descricao;

    private Integer medicoPrincipalId;

    private List<MedicoDTO> medicos;

    private List<InstrumentoDTO> instrumentos;
}

