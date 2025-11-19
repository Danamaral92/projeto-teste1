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
public class CirurgiaCreateDTO {

    private Integer id;

    private Date data;

    private Integer pacienteId;

    @Size(max = 180)
    private String descricao;

    private Integer medicoPrincipalId;

    private List<Integer> medicosId;

    private List<Integer> instrumentosId;
}

