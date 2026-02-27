package edu.ar.tempus.persistence.sql;

import edu.ar.tempus.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDAOSQL extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmailEqualsIgnoreCase(String email);
}
