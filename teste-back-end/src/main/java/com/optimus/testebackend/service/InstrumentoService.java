package com.optimus.testebackend.service;


import com.optimus.testebackend.domain.dto.InstrumentoDTO;
import com.optimus.testebackend.domain.entity.Instrumento;
import com.optimus.testebackend.domain.mapper.InstrumentoMapper;
import com.optimus.testebackend.domain.repository.InstrumentoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InstrumentoService {

    private final InstrumentoRepository instrumentoRepository;
    private final InstrumentoMapper instrumentoMapper;

    public List<InstrumentoDTO> getAllInstrumentos() {
        List<Instrumento> listInstrumentos = instrumentoRepository.findAll();
        return listInstrumentos.stream().map(instrumentoMapper::toDTO).toList();
    }


    public Optional<InstrumentoDTO> getInstrumentoById(Integer id) {
        return instrumentoRepository.findById(id).map(instrumentoMapper::toDTO);
    }

    @Transactional
    public Optional<InstrumentoDTO> saveInstrumento(InstrumentoDTO instrumentoDTO) {
        return this.getInstrumentoById(instrumentoRepository.save(instrumentoMapper.toEntity(instrumentoDTO)).getId());
    }

    @Transactional
    public void deleteInstrumento(Integer id){
        instrumentoRepository.deleteById(id);
    }

    @Transactional
    public Optional<InstrumentoDTO> updateInstrumento(Integer id, InstrumentoDTO instrumentoDTO) {

        if(instrumentoRepository.findById(id).isEmpty()){
            return null;
        }
        instrumentoDTO.setId(id);

        return this.getInstrumentoById(instrumentoRepository.save(instrumentoMapper.toEntity(instrumentoDTO)).getId());
    }
}
