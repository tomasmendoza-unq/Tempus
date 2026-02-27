package edu.ar.tempus.service.impl;


import edu.ar.tempus.exceptions.business.EmailYaExisteException;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.persistence.sql.UsuarioDAOSQL;
import edu.ar.tempus.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAOSQL usuarioDAOSQL;

    public UsuarioServiceImpl(UsuarioDAOSQL usuarioDAOSQL) {
        this.usuarioDAOSQL = usuarioDAOSQL;
    }

    @Override
    public Usuario recuperarUsuarioPorId(Long idUsuario) {
        return usuarioDAOSQL.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException(Usuario.class.getName(), idUsuario));
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        if (this.recuperarUsuarioPorEmail(usuario.getEmail()).isPresent()) {
            throw new EmailYaExisteException(
                    "El email ya está registrado"
            );
        }
        return usuarioDAOSQL.save(usuario);
    }

    @Override
    public Optional<Usuario> recuperarUsuarioPorEmail(String email) {
        return usuarioDAOSQL.findByEmailEqualsIgnoreCase(email);
    }


}
