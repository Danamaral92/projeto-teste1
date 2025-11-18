package com.optimus.testebackend.domain.repository;

import com.optimus.testebackend.domain.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Integer>{
}
