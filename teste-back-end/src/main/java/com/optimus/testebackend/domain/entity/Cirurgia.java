package com.optimus.testebackend.domain.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "CIRURGIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cirurgia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CIRU_ID_CIRURGIA")
    private Integer id;

    @Column(name = "CIRU_DT_CIRURGIA")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "PACI_ID_PACIENTE")
    private Paciente paciente;

    @Column(name = "CIRU_TX_DESCRICAO")
    @Size(max = 180)
    private String descricao;

    @OneToMany(mappedBy = "cirurgia")
    private List<MedicoCirurgia> medicosCirurgia;

    @ManyToMany
    @JoinTable(
            name = "INSTRUMENTO_CIRURGIA",
            joinColumns = @JoinColumn(name = "INCI_ID_CIRURGIA"),
            inverseJoinColumns = @JoinColumn(name = "INCI_ID_INSTRUMENTO")
    )
    private List<Instrumento> instrumentos;

    @Nullable
    public Integer getMedicoPrincipalId() {
        return medicosCirurgia.stream().filter(m -> m.getPrincipal()).findFirst().orElse(null).getMedico().getId();
    }
}
