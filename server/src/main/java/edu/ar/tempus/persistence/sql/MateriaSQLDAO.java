package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;
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
        SELECT id(m)
        FROM Materia m
        WHERE m.materiaId IN :idsMaterias
    """)
    List<Long> recuperarMateriasPorCarrera(@Param("idsMaterias") List<Long> materiasAprobadas,@Param("idCarrera") Long idCarrera);
}
