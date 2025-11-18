package com.optimus.testebackend.domain.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EspecialidadeDTO {

    private Integer id;

    @Size(max = 45)
    private String descricao;
}
