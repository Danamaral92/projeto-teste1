package com.optimus.testebackend.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEDICO_CIRURGIA")
@Builder
public class MedicoCirurgia {

    @EmbeddedId
    private MedicoCirurgiaId id;

    @ManyToOne
    @MapsId("medicoId")
    @JoinColumn(name = "MECI_ID_MEDICO")
    private Medico medico;

    @ManyToOne
    @MapsId("cirurgiaId")
    @JoinColumn(name = "MECI_ID_CIRURGIA")
    private Cirurgia cirurgia;

    @Column(name = "MECI_NR_FLAG_PRINCIPAL")
    private Boolean principal;



    @Embeddable
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MedicoCirurgiaId {

        @Column(name = "MECI_ID_MEDICO")
        public Integer medicoId;

        @Column(name = "MECI_ID_CIRURGIA")
        public Integer cirurgiaId;
    }
}
