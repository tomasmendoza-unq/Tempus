package edu.ar.tempus.service;

import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Usuario;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario guardarUsuario(Usuario usuario);

    Optional<Usuario> recuperarUsuarioPorEmail(String email);


}
