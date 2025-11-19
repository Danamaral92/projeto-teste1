package com.optimus.testebackend.controller;


import com.optimus.testebackend.domain.dto.MedicoDTO;
import com.optimus.testebackend.service.MedicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medicos")
@CrossOrigin(origins = "*")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService) {
        this.medicoService = medicoService;
    }


    @GetMapping("/")
    public ResponseEntity<List<MedicoDTO>> getAllMedicos() {
        return ResponseEntity.status(HttpStatus.OK).body(medicoService.getAllMedicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoDTO> getMedicoById(@PathVariable Integer id) {
        Optional<MedicoDTO> medicoDTO = medicoService.getMedicoById(id);
        return medicoDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/")
    public ResponseEntity<MedicoDTO> saveMedico(@RequestBody @Valid MedicoDTO medico) {
        Optional<MedicoDTO> medicoDTO = medicoService.saveMedico(medico);
        return medicoDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MedicoDTO> deleteMedico(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoDTO> updateMedico(@PathVariable Integer id, @RequestBody @Valid MedicoDTO medico) {
        Optional<MedicoDTO> medicoDTO = medicoService.updateMedico(id, medico);
        return medicoDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
