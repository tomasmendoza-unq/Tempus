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

    @Query("""
    SELECT m
        FROM Materia m
        JOIN m.carreras c
        WHERE m.materiaId IN :idsMaterias
          AND c.id = :idCarrera
    """)
    List<Materia> recuperarMateriasPorCarrera(@Param("idsMaterias") Set<Long> materiasAprobadas,@Param("idCarrera") Long idCarrera);
}
