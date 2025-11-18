package com.optimus.testebackend.controller;


import com.optimus.testebackend.domain.dto.InstrumentoDTO;
import com.optimus.testebackend.service.InstrumentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/instrumentos")
public class InstrumentoController {

    private final InstrumentoService instrumentoService;

    public InstrumentoController(InstrumentoService instrumentoService) {
        this.instrumentoService = instrumentoService;
    }

    @GetMapping("/")
    public ResponseEntity<List<InstrumentoDTO>> getAllInstrumentos() {
        return ResponseEntity.status(HttpStatus.OK).body(instrumentoService.getAllInstrumentos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> getInstrumentoById(@PathVariable Integer id) {
        Optional<InstrumentoDTO> instrumentoDTO = instrumentoService.getInstrumentoById(id);
        return instrumentoDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/")
    public ResponseEntity<InstrumentoDTO> saveInstrumento(@RequestBody @Valid InstrumentoDTO instrumento) {
        Optional<InstrumentoDTO> instrumentoDTO = instrumentoService.saveInstrumento(instrumento);
        return instrumentoDTO.map( dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet( () ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> deleteInstrumento(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InstrumentoDTO> updateInstrumento(@PathVariable Integer id, @RequestBody @Valid InstrumentoDTO instrumento) {
        Optional<InstrumentoDTO> instrumentoDTO = instrumentoService.updateInstrumento(id, instrumento);
        return instrumentoDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
