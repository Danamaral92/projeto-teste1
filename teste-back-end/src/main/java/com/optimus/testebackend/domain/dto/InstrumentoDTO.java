package com.optimus.testebackend.domain.dto;


import com.optimus.testebackend.domain.entity.Instrumento;
import com.optimus.testebackend.domain.mapper.InstrumentoMapper;
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
public class InstrumentoDTO {
    private Integer id;

    @Size(max = 45)
    private String nome;
}
