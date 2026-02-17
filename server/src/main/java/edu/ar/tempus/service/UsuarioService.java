package edu.ar.tempus.service;

import edu.ar.tempus.model.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Usuario recuperarUsuarioPorId(Long id);

    Usuario guardarUsuario(Usuario usuario);

    Optional<Usuario> recuperarUsuarioPorEmail(String email);

}
