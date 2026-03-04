package edu.ar.tempus.service;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario recuperarUsuarioPorId(Long id);

    Usuario guardarUsuario(Usuario usuario);

    Optional<Usuario> recuperarUsuarioPorEmail(String email);

    void anotarseAComision(List<Long> comisionId, Long alumnoId);

    void aprobarMaterias(List<Long> comisionIds, Long alumnoId);

    List<Long> recuperarMateriasAprobadasPorAlumno(Long alumnoId);
}
