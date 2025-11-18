package com.optimus.testebackend.service;



import com.optimus.testebackend.domain.dto.MedicoDTO;
import com.optimus.testebackend.domain.entity.Medico;
import com.optimus.testebackend.domain.mapper.MedicoMapper;
import com.optimus.testebackend.domain.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;

    private final MedicoMapper medicoMapper;


    public List<MedicoDTO> getAllMedicos() {
        return medicoRepository.findAll().stream()
                .map(medicoMapper::toDTO)
                .toList();
    }

    public Optional<MedicoDTO> getMedicoById(Integer id) {
        return medicoRepository.findById(id).map(medicoMapper::toDTO);
    }

    @Transactional
    public Optional<MedicoDTO> saveMedico(MedicoDTO medicoDTO) {
        return this.getMedicoById(medicoRepository.save(medicoMapper.toEntity(medicoDTO)).getId());
    }


    @Transactional
    public void deleteMedico(Integer id) {
        medicoRepository.deleteById(id);
    }


    @Transactional
    public Optional<MedicoDTO> updateMedico(Integer id, MedicoDTO medicoDTO) {

        if(medicoRepository.findById(id).isEmpty()){
            return null;
        }
        medicoDTO.setId(id);

        return this.getMedicoById(medicoRepository.save(medicoMapper.toEntity(medicoDTO)).getId());
    }



}
