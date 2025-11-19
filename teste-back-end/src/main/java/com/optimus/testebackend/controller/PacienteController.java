package com.optimus.testebackend.controller;


import com.optimus.testebackend.domain.dto.PacienteDTO;
import com.optimus.testebackend.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
@CrossOrigin(origins = "*")
public class PacienteController {


    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/")
    public ResponseEntity<List<PacienteDTO>> getAllPacientes() {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.getAllPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getPacienteById(@PathVariable Integer id) {
        Optional<PacienteDTO> pacienteDTO = pacienteService.getPacienteById(id);
        return pacienteDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping("/")
    public ResponseEntity<PacienteDTO> savePaciente(@RequestBody PacienteDTO paciente) {
        Optional<PacienteDTO> pacienteDTO = pacienteService.savePaciente(paciente);
        return pacienteDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PacienteDTO> deletePaciente(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> updatePaciente(@PathVariable Integer id, PacienteDTO paciente) {
        Optional<PacienteDTO> pacienteDTO = pacienteService.updatePaciente(id, paciente);
        return pacienteDTO.map(dto ->
                ResponseEntity.status(HttpStatus.OK).body(dto)).orElseGet(() ->
                ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
