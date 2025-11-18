package com.optimus.testebackend.domain.entity;

import com.optimus.testebackend.domain.dto.MedicoDTO;
import com.optimus.testebackend.domain.mapper.MedicoMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "MEDICO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEDI_ID_MEDICO")
    private Integer id;

    @Column(name = "MEDI_NR_MATRICULA", nullable = false, unique = true)
    private Integer matricula;

    @Column(name = "MEDI_TX_NOME")
    @Size(max = 90)
    private String nome;

    @Column(name = "MEDI_TX_CRM")
    @Size(max = 10)
    private String crm;

    @ManyToOne
    @JoinColumn(name = "ESPE_ID_ESPECIALIDADE")
    private Especialidade especialidade;

//    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
//    private List<MedicoCirurgia> cirurgias;
}
