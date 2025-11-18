package com.optimus.testebackend.domain.entity;

import com.optimus.testebackend.domain.dto.InstrumentoDTO;
import com.optimus.testebackend.domain.mapper.InstrumentoMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "INSTRUMENTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Instrumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INST_ID_INSTRUMENTO")
    private Integer id;

    @Column(name = "INST_TX_NOME")
    @Size(max = 45)
    private String nome;
}
