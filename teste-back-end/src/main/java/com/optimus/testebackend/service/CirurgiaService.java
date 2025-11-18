package com.optimus.testebackend.service;


import com.optimus.testebackend.domain.dto.CirurgiaDTO;
import com.optimus.testebackend.domain.dto.InstrumentoDTO;
import com.optimus.testebackend.domain.dto.MedicoDTO;
import com.optimus.testebackend.domain.entity.Cirurgia;
import com.optimus.testebackend.domain.entity.Instrumento;
import com.optimus.testebackend.domain.entity.Medico;
import com.optimus.testebackend.domain.entity.MedicoCirurgia;
import com.optimus.testebackend.domain.mapper.CirurgiaMapper;
import com.optimus.testebackend.domain.mapper.MedicoMapper;
import com.optimus.testebackend.domain.repository.CirurgiaRepository;
import com.optimus.testebackend.domain.repository.InstrumentoRepository;
import com.optimus.testebackend.domain.repository.MedicoCirurgiaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CirurgiaService {
    private final CirurgiaRepository cirurgiaRepository;
    private final CirurgiaMapper cirurgiaMapper;
    private final MedicoMapper medicoMapper;
    private final MedicoCirurgiaRepository medicoCirurgiaRepository;


    public List<CirurgiaDTO> getAllCirurgias() {
        return cirurgiaRepository.findAll().stream()
                .map(cirurgiaMapper::toDTO)
                .toList();
    }

    public Optional<CirurgiaDTO> getCirurgiaById(Integer id) {
        return cirurgiaRepository.findById(id).map(cirurgiaMapper::toDTO);
    }


    @Transactional
    public Optional<CirurgiaDTO> saveCirurgia(CirurgiaDTO cirurgiaDTO) {
        Cirurgia cirurgia = cirurgiaMapper.toEntity(cirurgiaDTO);
        Optional<CirurgiaDTO> dto = this.getCirurgiaById(cirurgiaRepository.save(cirurgiaMapper.toEntity(cirurgiaDTO)).getId());
        List<MedicoCirurgia> medicoCirurgia = dto.map( mc-> mc.getMedicos().stream()
           .map(m -> MedicoCirurgia.builder()
                   .medico(medicoMapper.toEntity(m))
                   .cirurgia(cirurgiaMapper.toEntity(mc))
                   .principal(m.getId().equals(mc.getMedicoPrincipalId()))
                   .build())
           .toList()).get();

        medicoCirurgiaRepository.saveAll(medicoCirurgia);

        return this.getCirurgiaById(cirurgiaRepository.save(cirurgiaMapper.toEntity(cirurgiaDTO)).getId());
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
