package com.optimus.testebackend.service;


import com.optimus.testebackend.domain.dto.PacienteDTO;
import com.optimus.testebackend.domain.entity.Paciente;
import com.optimus.testebackend.domain.mapper.PacienteMapper;
import com.optimus.testebackend.domain.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    private final PacienteMapper pacienteMapper;


    public List<PacienteDTO> getAllPacientes() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::toDTO)
                .toList();
    }

    public Optional<PacienteDTO> getPacienteById(Integer id) {

        return pacienteRepository.findById(id).map(pacienteMapper::toDTO);
    }

    @Transactional
    public Optional<PacienteDTO> savePaciente(PacienteDTO pacienteDTO) {
        return this.getPacienteById(pacienteRepository.save(pacienteMapper.toEntity(pacienteDTO)).getId());
    }

    @Transactional
    public void deletePaciente(Integer id){
        pacienteRepository.deleteById(id);
    }

    @Transactional
    public Optional<PacienteDTO> updatePaciente(Integer id, PacienteDTO pacienteDTO) {

        if(pacienteRepository.findById(id).isEmpty()){
            return null;
        }

        pacienteDTO.setId(id);
        return this.getPacienteById(pacienteRepository.save(pacienteMapper.toEntity(pacienteDTO)).getId());
    }
}
