package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
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
        AND c.materia IN (
            SELECT c2.materia FROM Comision c2
            WHERE c2.comisionId IN :comisionesIds
            AND c2.comisionId <> c.comisionId
        )
        """)
    boolean hayComisionesDeMismaMateriaEnNuevas(@Param("comisionesIds") List<Long> comisionesIds);

    @Query("""
        SELECT c.materia
        FROM Comision c
        WHERE c.comisionId IN :comisionIds
    """)
    List<Materia> findAllMateriasByIds(@Param("comisionIds") List<Long> comisionIds);
}
