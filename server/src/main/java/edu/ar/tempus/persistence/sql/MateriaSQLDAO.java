package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MateriaSQLDAO extends JpaRepository<Materia,Long> {
    @Query("""
        FROM Materia m
        WHERE m.materiaId IN :idsMaterias
    """)
    List<Materia> findAllByIds(@Param("idsMaterias") Set<Long> idsMaterias);

    List<Materia> findAllByMateriaNombreContainsIgnoreCase(String nombreMateria);
}
