package com.optimus.testebackend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicoCirurgia {

//    @JoinColumn(name = "MECI_ID_MEDICO")
    private Integer medicoId;

//    @JoinColumn(name = "MECI_ID_CIRURGIA")
    private Integer cirurgiaId;

//    @Column(name = "MECI_NR_FLAG_PRINCIPAL")
    private Boolean principal;


}
