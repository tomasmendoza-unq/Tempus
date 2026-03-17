package edu.ar.tempus.persistence.repository.impls;

import edu.ar.tempus.exceptions.business.DependenciaCircularException;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.exceptions.business.RelacionCorrelativaYaExisteException;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.model.Usuario;
import edu.ar.tempus.persistence.neo4J.MateriaNeo4JDAO;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import edu.ar.tempus.persistence.repository.ComisionRepository;
import edu.ar.tempus.persistence.repository.MateriaRepository;
import edu.ar.tempus.persistence.repository.mapper.MateriaMapper;
import edu.ar.tempus.persistence.sql.MateriaSQLDAO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MateriaRepositoryImpl implements MateriaRepository {

    private final MateriaSQLDAO materiaSQLDAO;
    private final MateriaNeo4JDAO materiaNeo4JDAO;
    private final MateriaMapper materiaMapper;
    private final ComisionRepository comisionRepository;

    public MateriaRepositoryImpl(MateriaSQLDAO materiaSQLDAO, MateriaNeo4JDAO materiaNeo4JDAO, MateriaMapper materiaMapper, ComisionRepository comisionRepository) {
        this.materiaSQLDAO = materiaSQLDAO;
        this.materiaNeo4JDAO = materiaNeo4JDAO;
        this.materiaMapper = materiaMapper;
        this.comisionRepository = comisionRepository;
    }

    @Override
    public Materia save(Materia materia) {
        Materia materiaGuardada = materiaSQLDAO.save(materia);
        materiaSQLDAO.saveAll(materia.getCorrelativas());
        MateriaNeo4J neo = materiaMapper.toNeo4J(materiaGuardada);
        materiaNeo4JDAO.save(neo);
        return materiaGuardada;
    }

    @Override
    public Materia getById(Long materiaId) {
        Materia materia = materiaSQLDAO.findById(materiaId)
                .orElseThrow(() -> new EntityNotFoundException(Materia.class.getName(), materiaId));

        MateriaNeo4J neo = materiaNeo4JDAO.findById(materiaId)
                .orElseThrow(() -> new EntityNotFoundException(MateriaNeo4J.class.getName(), materiaId));

        return materiaMapper.toModel(materia, neo);
    }

    @Override
    public void crearRelacionCorrelativa(Long materiaOrigenId, Long materiaDestinoId) {
        materiaNeo4JDAO.crearRelacionCorrelativa(materiaOrigenId, materiaDestinoId);
    }

    @Override
    public List<Materia> recuperarTodos() {

        return materiaSQLDAO.findAll();
    }

    @Override
    public List<Materia> recuperarMateriasDisponibles(List<Long> idsAprobadas, Long idCarrera) {
        Set<Long> idsHabilitados = materiaNeo4JDAO.recuperarMateriasDisponibles(idsAprobadas);

        if (idsHabilitados.isEmpty()) {
            return List.of();
        }

        return materiaSQLDAO.recuperarMateriasPorCarrera(idsHabilitados, idCarrera);
    }

    @Override
    public List<Materia> recuperarMateriasPorNombre(String nombreMateria) {
        return materiaSQLDAO.findAllByMateriaNombreContainsIgnoreCase(nombreMateria);
    }

    @Override
    public boolean existeRelacionCorrelativa(Long materiaOrigenId, Long materiaDestinoId) {
        return !materiaNeo4JDAO.existeRelacionCorrelativa(materiaOrigenId, List.of(materiaDestinoId)).isEmpty();
    }

    @Override
    public boolean existeDependenciaCircular(Long materiaOrigenId, Long materiaDestinoId) {
        return !materiaNeo4JDAO.existeDependenciaCircular(materiaOrigenId, List.of(materiaDestinoId)).isEmpty();
    }

    @Override
    public boolean validarSiCuentaConLasCorrelativas(Usuario alumno, List<Long> comisionIds) {
        List<Long> materiasAprobadasIds = alumno.getMateriasAprobadas()
                .stream()
                .map(Materia::getMateriaId)
                .toList();

        return materiaNeo4JDAO.cuentaConLasCorrelativas(materiasAprobadasIds, comisionIds);
    }

    @Override
    public List<Materia> saveAll(List<Materia> materias) {

        materias.forEach(materia -> {
            if (materia.getComisiones() != null) {
                materia.getComisiones().forEach(comision ->
                        comision.setMateria(materia)
                );
            }
        });

        List<Materia> materiasSaved = materiaSQLDAO.saveAll(materias);

        List<MateriaNeo4J> neo4JS = materiasSaved.stream()
                .map(materiaMapper::toNeo4J)
                .toList();

        materiaNeo4JDAO.saveAll(neo4JS);

        return materiasSaved;
    }

    @Override
    public void crearRelacionesCorrelativas(Long materiaId, List<Long> materiaIds) {
        List<Long> duplicadas = materiaNeo4JDAO.existeRelacionCorrelativa(materiaId, materiaIds);
        if (!duplicadas.isEmpty()) {
            throw new RelacionCorrelativaYaExisteException(materiaId, duplicadas);
        }

        List<Long> circulares = materiaNeo4JDAO.existeDependenciaCircular(materiaId, materiaIds);
        if (!circulares.isEmpty()) {
            throw new DependenciaCircularException(materiaId, circulares);
        }

        materiaNeo4JDAO.crearRelacionesCorrelativas(materiaId, materiaIds);
    }


}