package edu.ar.tempus.service;

import edu.ar.tempus.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario recuperarUsuarioPorId(Long id);

    Usuario guardarUsuario(Usuario usuario, Long carreraId);

    Usuario generarAdmin(Usuario usuario);

    Optional<Usuario> recuperarUsuarioPorEmail(String email);

    void anotarseAComision(List<Long> comisionId, Long alumnoId);

    void aprobarMaterias(List<Long> comisionIds, Long alumnoId);

    List<Long> recuperarMateriasAprobadasPorAlumno(Long alumnoId);

    public void desaprobarMateria(Long materiaId, Long alumnoId); //SE PUEDE MEJORAR, HACIENDO QUE VUELVA LA MATERIA A LA COMISION QUE ESTABA ANOTADO

    void suscribirseACarrera(Long carreraId, Long alumnoId);

    void seleccionarCarreraActiva(Long carreraId, Long alumnoId);
}
