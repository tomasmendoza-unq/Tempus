package edu.ar.tempus.service.impl;


import edu.ar.tempus.exceptions.business.AlumnoAnotadoAOtraComisionException;
import edu.ar.tempus.exceptions.business.EmailYaExisteException;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.exceptions.business.MateriaYaAprobadaException;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.persistence.repository.MateriaRepository;
import edu.ar.tempus.persistence.sql.CarreraDAOSQL;
import edu.ar.tempus.persistence.sql.UsuarioDAOSQL;
import edu.ar.tempus.service.CarreraService;
import edu.ar.tempus.service.ComisionService;
import edu.ar.tempus.service.MateriaService;
import edu.ar.tempus.service.UsuarioService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDAOSQL usuarioDAOSQL;
    private final ComisionService comisionService;
    private final MateriaRepository materiaRepository;
    private final CarreraDAOSQL carreraDAOSQL;

    public UsuarioServiceImpl(UsuarioDAOSQL usuarioDAOSQL, ComisionService comisionService, MateriaRepository materiaRepository, CarreraDAOSQL carreraDAOSQL) {
        this.usuarioDAOSQL = usuarioDAOSQL;
        this.comisionService = comisionService;
        this.materiaRepository = materiaRepository;
        this.carreraDAOSQL = carreraDAOSQL;
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

    @Override
    public void anotarseAComision(List<Long> comisionIds, Long alumnoId) {
        Usuario alumno = recuperarUsuarioPorId(alumnoId);
        
        validarQueNoEstaInscriptoANingunaComision(comisionIds, alumnoId);
        List<Long> comisionesAnotadas = usuarioDAOSQL.recuperarComisionesIds(alumnoId);
        //agregar la validacion de que tiene las materias necesarias para anotarse
        //VALIDAR QUE PUEDE ANOTAR CORRELATIVA
        comisionService.validarSuperPosicion(comisionIds, comisionesAnotadas);
        List<Comision> comisiones = comisionService.recuperarPorIds(comisionIds);

        alumno.anotarseAComisiones(comisiones);

        usuarioDAOSQL.save(alumno);
    }

    @Override
    public void aprobarMaterias(List<Long> comisionIds, Long alumnoId) {
        Usuario alumno = recuperarUsuarioPorId(alumnoId);
        //refactor aca para comprobar directamente que no se aprobo dos veces la misma materia


        if(usuarioDAOSQL.yaAproboAlgunaDeLasMaterias(alumnoId, comisionIds))
            throw new MateriaYaAprobadaException("El alumno ya aprobó una de las materias");

        List<Materia> materiasAprobadas = comisionService.recuperarMateriasPorComision(comisionIds);
        List<Comision> comisionesAprobadas = comisionService.recuperarPorIds(comisionIds);

        alumno.aprobarMaterias(materiasAprobadas);
        alumno.desanotarseDeComisiones(comisionesAprobadas);

        usuarioDAOSQL.save(alumno);
    }

    @Override
    public List<Long> recuperarMateriasAprobadasPorAlumno(Long alumnoId) {
        return usuarioDAOSQL.findMateriasAprobadasById(alumnoId);
    }

    @Override
    public void desaprobarMateria(Long materiaId, Long alumnoId) {
        Usuario alumno = recuperarUsuarioPorId(alumnoId);
        Materia materia = materiaRepository.getById(materiaId);


        alumno.desaprobarMateria(materia);
        usuarioDAOSQL.save(alumno);

    }

    @Override
    public void suscribirseACarrera(Long carreraId, Long alumnoId) {
        Carrera carrera = carreraDAOSQL.findById(carreraId).orElseThrow(() -> new EntityNotFoundException(Carrera.class.getName(), carreraId));;

        Usuario alumno = recuperarUsuarioPorId(alumnoId);

        alumno.suscribirseACarrera(carrera);

        usuarioDAOSQL.save(alumno);
    }

    private void validarQueNoEstaInscriptoANingunaComision(List<Long> comisionIds, Long alumnoId) {
        if(usuarioDAOSQL.estaInscriptoAComisionDeMismaMateria(alumnoId, comisionIds)) throw new AlumnoAnotadoAOtraComisionException("El alumno ya se encuentra inscripto en una de las comisiones");
        if(comisionService.hayComisionesDeMismaMateriaEnNuevas(comisionIds)) throw new AlumnoAnotadoAOtraComisionException("El alumno ya se encuentra inscripto en una de las comisiones");
    }


}
