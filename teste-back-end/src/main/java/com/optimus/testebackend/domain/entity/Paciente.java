package com.optimus.testebackend.domain.entity;

import com.optimus.testebackend.domain.dto.PacienteDTO;
import com.optimus.testebackend.domain.mapper.PacienteMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "PACIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PACI_ID_PACIENTE")
    private Integer id;

    @Column(name = "PACI_NR_MATRICULA", nullable = false, unique = true)
    private Integer matricula;

    @Column(name = "PACI_TX_NOME")
    @Size(max = 90)
    private String nome;

    @Column(name = "PACI_DT_INTERNAMENTO")
    private Date dataInternamento;

    @Column(name = "PACI_TX_CID")
    @Size(max = 10)
    private String cid;
}
