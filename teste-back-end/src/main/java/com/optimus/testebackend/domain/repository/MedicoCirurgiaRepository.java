package com.optimus.testebackend.domain.repository;



import com.optimus.testebackend.domain.entity.MedicoCirurgia;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class MedicoCirurgiaRepository{
    private final JdbcTemplate jdbcTemplate;

    public MedicoCirurgiaRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MedicoCirurgia> getAllMedicoCirurgia(){
        String sql = "SELECT * FROM medico_cirurgia";

        return jdbcTemplate.query(sql, ((rs, rowNum) ->
                new MedicoCirurgia(
                        rs.getInt("MECI_ID_MEDICO"),
                        rs.getInt("MECI_ID_CIRURGIA"),
                        rs.getBoolean("MECI_NR_FLAG_PRINCIPAL")
                )
                ));
    }


    public List<MedicoCirurgia> getByCirurgiaId(Integer id){
        String sql = "SELECT * FROM medico_cirurgia WHERE MECI_ID_CIRURGIA= ?";

        return jdbcTemplate.query(sql, ps -> ps.setInt(1, id), ((rs, rowNum) ->
                new MedicoCirurgia(
                        rs.getInt("MECI_ID_MEDICO"),
                        rs.getInt("MECI_ID_CIRURGIA"),
                        rs.getBoolean("MECI_NR_FLAG_PRINCIPAL")
                )
        ));
    }


    public int inserirMedicoCirurgia(Integer medicoId, Integer cirurgiaId, boolean isPrincipal) {
        String sql = "INSERT INTO medico_cirurgia(MECI_ID_MEDICO, MECI_ID_CIRURGIA, MECI_NR_FLAG_PRINCIPAL) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, medicoId, cirurgiaId);
    }



}
