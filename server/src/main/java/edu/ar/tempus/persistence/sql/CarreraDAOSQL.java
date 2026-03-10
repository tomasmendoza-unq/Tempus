package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarreraDAOSQL extends JpaRepository<Carrera,Long> {
    List<Carrera> findAllByNombreCarreraContainsIgnoreCase(String nombreCarrera);

    @Query("""
        FROM Carrera c 
            WHERE c.id 
                NOT IN (
                    SELECT id(u.carreras) 
                    FROM Usuario u 
                    WHERE u.id = :id
                )
    """)
    List<Carrera> recuperarCarerrasPorAlumno(@Param("id") Long alumnoId);
}
