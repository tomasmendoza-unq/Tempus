package edu.ar.tempus.feature.alumno.service;

import edu.ar.tempus.feature.alumno.model.Alumno;
import edu.ar.tempus.model.Carrera;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AlumnoService {
    List<Carrera> getCarrerasDisponibles(Long idAlumno);

    Carrera desuscribirseACarrera(Long idCarrera, Long idAlumno);

    Carrera suscribirseACarrera(Long carreraId, Long alumnoId);

    void anotarseAComision(List<Long> comisionId, Long alumnoId);

    void aprobarMaterias(List<Long> comisionIds, Long alumnoId);

    List<Long> recuperarMateriasAprobadasPorAlumno(Long alumnoId);

    List<Materia> recuperarMateriasDisponibles(Long alumnoId);

    public void desaprobarMateria(Long materiaId, Long alumnoId); //SE PUEDE MEJORAR, HACIENDO QUE VUELVA LA MATERIA A LA COMISION QUE ESTABA ANOTADO

    void seleccionarCarreraActiva(Long carreraId, Long alumnoId);

    Alumno update(Alumno alumno);

    Alumno guardar(Alumno alumno, Long idCarrera);

    Alumno getAlumnoById(Long idAlumno);

    Page<Comision> recuperarComisionesByAlumnoId(int page, Long alumnoId);
}
