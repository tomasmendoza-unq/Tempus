package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAOSQL extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailEqualsIgnoreCase(String email);

    @Query("""
        SELECT COUNT(u) > 0 
        FROM Usuario u, IN(u.comisiones) AS c
        WHERE u.id = :alumnoId
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
        FROM Usuario u, IN (u.comisiones) as c
        WHERE u.id = :alumnoId
    """)
    List<Long> recuperarComisionesIds(@Param("alumnoId") Long alumnoId);
}
