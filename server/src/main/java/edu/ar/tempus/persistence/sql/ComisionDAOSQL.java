package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Comision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComisionDAOSQL extends JpaRepository<Comision,Long> {
    List<Comision> findAllByMateria_MateriaId(Long materiaMateriaId);

    @Query("""
        SELECT COUNT(c) > 0
        FROM Comision c
        WHERE c.comisionId IN :comisionesIds
        GROUP BY c.materia
        HAVING COUNT(c) > 1
        """)
    boolean hayComisionesDeMismaMateriaEnNuevas(@Param("comisionesIds") List<Long> comisionesIds);
}
