package edu.ar.tempus.feature.alumno.persistence.sql;

import edu.ar.tempus.feature.alumno.model.Alumno;
import edu.ar.tempus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoDAOSQL extends JpaRepository<Alumno, Long> {

    @Query("""
        SELECT COUNT(a) > 0 
        FROM Alumno a, IN(a.comisiones) AS c
        WHERE a.id = :alumnoId
        AND c.materia IN (
            SELECT c2.materia FROM Comision c2, IN(c2.materia.comisiones) AS cm
            WHERE cm.id IN :comisionesIds
        )
        """)
    boolean estaInscriptoAComisionDeMismaMateria(
            @Param("alumnoId") Long alumnoId,
            @Param("comisionesIds") List<Long> comisionesIds
    );

    @Query("""
        SELECT id(c)
        FROM Alumno a, IN (a.comisiones) as c
        WHERE a.id = :alumnoId
    """)
    List<Long> recuperarComisionesIds(@Param("alumnoId") Long alumnoId);

    @Query("""
    SELECT COUNT(a) > 0
    FROM Usuario a, IN(a.materiasAprobadas) AS m
    WHERE a.id = :alumnoId
    AND m.materiaId IN (
        SELECT c.materia.materiaId FROM Comision c WHERE c.comisionId IN :comisionIds
    )
    """)
    boolean yaAproboAlgunaDeLasMaterias(
            @Param("alumnoId") Long alumnoId,
            @Param("comisionIds") List<Long> comisionIds
    );


    @Query("""
       SELECT id(a.materiasAprobadas)
       FROM Alumno a
       WHERE a.id = :alumnoId
    """)
    List<Long> findMateriasAprobadasById(@Param("alumnoId") Long alumnoId);
}
