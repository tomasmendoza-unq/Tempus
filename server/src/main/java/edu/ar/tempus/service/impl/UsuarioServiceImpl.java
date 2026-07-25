package edu.ar.tempus.service.impl;


import edu.ar.tempus.exceptions.business.*;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.persistence.repository.MateriaRepository;
import edu.ar.tempus.persistence.sql.CarreraDAOSQL;
import edu.ar.tempus.persistence.sql.UsuarioDAOSQL;
import edu.ar.tempus.service.ComisionService;
import edu.ar.tempus.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAOSQL usuarioDAOSQL;

    public UsuarioServiceImpl(UsuarioDAOSQL usuarioDAOSQL) {
        this.usuarioDAOSQL = usuarioDAOSQL;
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioDAOSQL.save(usuario);
    }

    @Override
    public Optional<Usuario> recuperarUsuarioPorEmail(String email) {
        return usuarioDAOSQL.findByEmailEqualsIgnoreCase(email);
    }



}
