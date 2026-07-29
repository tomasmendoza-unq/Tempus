package edu.ar.tempus.feature.alumno.service.impl;

import edu.ar.tempus.exceptions.business.*;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.feature.alumno.model.Alumno;
import edu.ar.tempus.feature.alumno.persistence.sql.AlumnoDAOSQL;
import edu.ar.tempus.feature.alumno.service.AlumnoService;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.repository.MateriaRepository;
import edu.ar.tempus.persistence.sql.CarreraDAOSQL;
import edu.ar.tempus.service.CarreraService;
import edu.ar.tempus.service.ComisionService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService {

    private final CarreraService carreraService;

    private final ComisionService comisionService;

    private final MateriaRepository materiaRepository;

    private final CarreraDAOSQL carreraDAOSQL;

    private final AlumnoDAOSQL alumnoDAOSQL;


    public AlumnoServiceImpl(CarreraService carreraService, ComisionService comisionService, MateriaRepository materiaRepository, CarreraDAOSQL carreraDAOSQL, AlumnoDAOSQL alumnoDAOSQL) {
        this.carreraService = carreraService;
        this.comisionService = comisionService;
        this.materiaRepository = materiaRepository;
        this.carreraDAOSQL = carreraDAOSQL;
        this.alumnoDAOSQL = alumnoDAOSQL;
    }

    @Override
    public List<Carrera> getCarrerasDisponibles(Long idAlumno) {
        return carreraService.recuperarCarrerasPorAlumno(idAlumno);
    }

    @Override
    public Carrera desuscribirseACarrera(Long idCarrera, Long idAlumno) {
        Carrera carrera = carreraService.recuperar(idCarrera);

        Alumno alumno = getAlumnoById(idAlumno);

        alumno.desuscribirseACarrera(carrera);

        this.update(alumno);

        return carrera;
    }

    @Override
    public Carrera suscribirseACarrera(Long carreraId, Long alumnoId) {
        Carrera carrera = carreraService.recuperar(carreraId);

        Alumno alumno = getAlumnoById(alumnoId);

        alumno.suscribirseACarrera(carrera);

        this.update(alumno);

        return carrera;
    }

    @Override
    public Alumno guardar(Alumno alumno, Long idCarrera) {
        Carrera carrera = carreraService.recuperar(idCarrera);

        alumno.suscribirseACarrera(carrera);

        try {
            return alumnoDAOSQL.save(alumno);
        } catch (DataIntegrityViolationException e) {
            throw new EmailYaExisteException("No se pudo completar el registro con los datos proporcionados.");
        }
    }

    @Override
    public Alumno getAlumnoById(Long idAlumno) {
        return alumnoDAOSQL.findById(idAlumno).orElseThrow(() -> new EntityNotFoundException(Alumno.class.getName(), idAlumno));
    }

    @Override
    public Page<Comision> recuperarComisionesByAlumnoId(int page, Long alumnoId) {
        Alumno alumno = getAlumnoById(alumnoId);
        return comisionService.recuperarComisiones(page, alumno);
    }

    @Override
    public Materia aprobarComision(Long idComision, Long idAlumno) {
        Alumno alumno = getAlumnoById(idAlumno);

        Comision comision = comisionService.recuperar(idComision);

        alumno.aprobarMateria(comision);

        alumnoDAOSQL.save(alumno);

        return comision.getMateria();
    }

    @Override
    public List<Materia> recuperarMateriasDisponibles(Long alumnoId) {
        Alumno alumno = this.getAlumnoById(alumnoId);
        List<Long> materiasAprobadas =  this.recuperarMateriasAprobadasPorAlumno(alumnoId);

        return materiaRepository.recuperarMateriasDisponibles(
                materiasAprobadas,
                alumno.getCarreraActiva().getId()
        );
    }

    @Override
    public void anotarseAComision(List<Long> comisionIds, Long alumnoId) {
        Alumno alumno = getAlumnoById(alumnoId);
        //validarQueTieneLasMaterias(alumno, comisionIds);
        validarQueNoEstaInscriptoANingunaComision(comisionIds, alumnoId);
        List<Long> comisionesAnotadas = alumnoDAOSQL.recuperarComisionesIds(alumnoId);
        comisionService.validarSuperPosicion(comisionIds, comisionesAnotadas);
        List<Comision> comisiones = comisionService.recuperarPorIds(comisionIds);

        alumno.anotarseAComisiones(comisiones);

        alumnoDAOSQL.save(alumno);
    }

    private void validarQueTieneLasMaterias(Alumno alumno, List<Long> comisionIds) {
        if (materiaRepository.validarSiCuentaConLasCorrelativas(alumno, comisionIds)) throw new AlumnoNoCuentaConLasCorrelativasException("El alumno no cuenta con las correlativas para anotarse");
    }

    @Override
    public void aprobarMaterias(List<Long> comisionIds, Long alumnoId) {
        Alumno alumno = getAlumnoById(alumnoId);
        //refactor aca para comprobar directamente que no se aprobo dos veces la misma materia


        if(alumnoDAOSQL.yaAproboAlgunaDeLasMaterias(alumnoId, comisionIds))
            throw new MateriaYaAprobadaException("El alumno ya aprobó una de las materias");

        List<Materia> materiasAprobadas = comisionService.recuperarMateriasPorComision(comisionIds);
        List<Comision> comisionesAprobadas = comisionService.recuperarPorIds(comisionIds);

        alumno.aprobarMaterias(materiasAprobadas);
        alumno.desanotarseDeComisiones(comisionesAprobadas);

        alumnoDAOSQL.save(alumno);
    }

    @Override
    public List<Long> recuperarMateriasAprobadasPorAlumno(Long alumnoId) {
        return alumnoDAOSQL.findMateriasAprobadasById(alumnoId);
    }

    @Override
    public Materia desaprobarMateria(Long materiaId, Long alumnoId) {
        Alumno alumno = getAlumnoById(alumnoId);

        Materia materia = materiaRepository.getById(materiaId);

        alumno.desaprobarMateria(materia);

        alumnoDAOSQL.save(alumno);

        return materia;
    }

    @Override
    public void seleccionarCarreraActiva(Long carreraId, Long alumnoId) {
        Carrera carrera = carreraDAOSQL.findById(carreraId).orElseThrow(() -> new EntityNotFoundException(Carrera.class.getName(), carreraId));

        Alumno alumno = getAlumnoById(alumnoId);

        alumno.seleccionarCarreraActiva(carrera);

        alumnoDAOSQL.save(alumno);
    }

    @Override
    public Alumno update(Alumno alumno) {
        return alumnoDAOSQL.save(alumno);
    }


    private void validarQueNoEstaInscriptoANingunaComision(List<Long> comisionIds, Long alumnoId) {
        if(alumnoDAOSQL.estaInscriptoAComisionDeMismaMateria(alumnoId, comisionIds)) throw new AlumnoAnotadoAOtraComisionException("El alumno ya se encuentra inscripto en una de las comisiones");
        if(comisionService.hayComisionesDeMismaMateriaEnNuevas(comisionIds)) throw new AlumnoAnotadoAOtraComisionException("El alumno ya se encuentra inscripto en una de las comisiones");
    }

}
