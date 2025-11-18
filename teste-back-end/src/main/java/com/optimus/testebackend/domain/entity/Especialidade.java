package com.optimus.testebackend.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ESPECIALIDADE")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ESPE_ID_ESPECIALIDADE")
    private Integer id;

    @Column(name = "ESPE_TX_DESCRICAO")
    @Size(max = 45)
    private String descricao;
}
