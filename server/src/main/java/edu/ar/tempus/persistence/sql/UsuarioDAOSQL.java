package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UsuarioDAOSQL extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailEqualsIgnoreCase(String email);

}
