package com.optimus.testebackend.controller;


import com.optimus.testebackend.domain.dto.CirurgiaDTO;
import com.optimus.testebackend.service.CirurgiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cirurgias")
@CrossOrigin(origins = "*")
public class CirurgiaController {

    private final CirurgiaService cirurgiaService;

    public CirurgiaController(CirurgiaService cirurgiaService) {
        this.cirurgiaService = cirurgiaService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CirurgiaDTO>> getAllCirurgias() {
        return ResponseEntity.status(HttpStatus.OK).body(cirurgiaService.getAllCirurgias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CirurgiaDTO> getCirurgiaById(@PathVariable Integer id) {
        Optional<CirurgiaDTO> cirurgiaDTO = cirurgiaService.getCirurgiaById(id);
        return cirurgiaDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/")
    public ResponseEntity<CirurgiaDTO> saveCirurgia(@RequestBody @Valid CirurgiaDTO cirurgia) {
        Optional<CirurgiaDTO> cirurgiaDTO = cirurgiaService.saveCirurgia(cirurgia);
        return cirurgiaDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CirurgiaDTO> deleteCirurgia(@PathVariable Integer id) {
        cirurgiaService.deleteCirurgia(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CirurgiaDTO> updateCirurgia(@PathVariable Integer id, @RequestBody @Valid CirurgiaDTO cirurgia) {
        Optional<CirurgiaDTO> cirurgiaDTO = cirurgiaService.updateCirurgia(id, cirurgia);
        return cirurgiaDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
