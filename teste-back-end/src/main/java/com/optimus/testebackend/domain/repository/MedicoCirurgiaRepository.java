package com.optimus.testebackend.domain.repository;

import com.optimus.testebackend.domain.entity.MedicoCirurgia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoCirurgiaRepository extends JpaRepository<MedicoCirurgia, MedicoCirurgia.MedicoCirurgiaId> {

    @Query(value = "SELECT * FROM MEDICO_CIRURGIA WHERE MECI_ID_CIRURGIA = :cirurgiaId", nativeQuery = true)
    List<MedicoCirurgia> findByCirurgiaId(@Param("cirurgiaId") Integer cirurgiaId);

}
