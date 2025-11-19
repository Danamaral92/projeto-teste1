package com.optimus.testebackend.service;


import com.optimus.testebackend.domain.dto.CirurgiaCreateDTO;
import com.optimus.testebackend.domain.dto.CirurgiaDTO;
import com.optimus.testebackend.domain.dto.InstrumentoDTO;
import com.optimus.testebackend.domain.dto.MedicoDTO;
import com.optimus.testebackend.domain.entity.*;
import com.optimus.testebackend.domain.mapper.CirurgiaMapper;
import com.optimus.testebackend.domain.mapper.MedicoMapper;
import com.optimus.testebackend.domain.repository.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CirurgiaService {
    private final CirurgiaRepository cirurgiaRepository;
    private final CirurgiaMapper cirurgiaMapper;
    private final MedicoMapper medicoMapper;
    private final MedicoCirurgiaRepository medicoCirurgiaRepository;
    private final MedicoRepository medicoRepository;
    private final InstrumentoRepository instrumentoRepository;
    private final PacienteRepository pacienteRepository;


    public List<CirurgiaDTO> getAllCirurgias() {
        return cirurgiaRepository.findAll().stream()
                .map(cirurgiaMapper::toDTO)
                .toList();
    }

    public Optional<CirurgiaDTO> getCirurgiaById(Integer id) {
        return cirurgiaRepository.findById(id).map(cirurgiaMapper::toDTO);
    }


    @Transactional
    public Optional<CirurgiaDTO> saveCirurgia(CirurgiaCreateDTO cirurgiaDTO) {
        List<Medico> medicos = cirurgiaDTO.getMedicosId().stream()
                .map(m -> {
                    Optional<Medico> medico = medicoRepository.findById(m);
                    if (medico.isEmpty()) {
                        throw new RuntimeException("Medico nao encontrado");
                    }
                    return medico.get();
                })
                .toList();

        List<Instrumento> instrumentos = cirurgiaDTO.getInstrumentosId().stream()
                .map(i -> {
                    Optional<Instrumento> instrumento = instrumentoRepository.findById(i);
                    if (instrumento.isEmpty()) {
                        throw new RuntimeException("Instrumento nao encontrado");
                    }
                    return instrumento.get();
                })
                .toList();
        Paciente paciente = Optional.ofNullable(cirurgiaDTO.getPacienteId()).map(p -> {
            Optional<Paciente> paci = pacienteRepository.findById(p);
            if (paci.isEmpty()) {
                throw new RuntimeException("Paciente nao encontrado");
            }
            return paci.get();
        }).orElse(null);

        Cirurgia cirurgia = Cirurgia.builder()
                .data(cirurgiaDTO.getData())
                .paciente(paciente)
                .medicos()
                .descricao(cirurgiaDTO.getDescricao())
                .instrumentos(instrumentos)
                .build();



        return this.getCirurgiaById(cirurgiaRepository.save(cirurgia).getId());
    }


    @Transactional
    public void deleteCirurgia(Integer id) {
        cirurgiaRepository.deleteById(id);
    }


    @Transactional
    public Optional<CirurgiaDTO> updateCirurgia(Integer id, CirurgiaDTO cirurgiaDTO) {

        if(cirurgiaRepository.findById(id).isEmpty()){
            return null;
        }

        cirurgiaDTO.setId(id);

        return this.getCirurgiaById(cirurgiaRepository.save(cirurgiaMapper.toEntity(cirurgiaDTO)).getId());
    }

}
