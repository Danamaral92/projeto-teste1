package com.optimus.testebackend.domain.repository;

import com.optimus.testebackend.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
}
