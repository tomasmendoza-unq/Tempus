package edu.ar.tempus.persistence.repository.impls;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.neo4J.MateriaNeo4JDAO;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import edu.ar.tempus.persistence.repository.MateriaRepository;
import edu.ar.tempus.persistence.repository.mapper.MateriaMapper;
import edu.ar.tempus.persistence.sql.MateriaSQLDAO;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
public class MateriaRepositoryImpl implements MateriaRepository {

    private final MateriaSQLDAO materiaSQLDAO;
    private final MateriaNeo4JDAO materiaNeo4JDAO;
    private final MateriaMapper materiaMapper;

    public MateriaRepositoryImpl(MateriaSQLDAO materiaSQLDAO,
                                 MateriaNeo4JDAO materiaNeo4JDAO,
                                 MateriaMapper materiaMapper) {
        this.materiaSQLDAO = materiaSQLDAO;
        this.materiaNeo4JDAO = materiaNeo4JDAO;
        this.materiaMapper = materiaMapper;
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
    public List<Materia> recuperarMateriasDisponibles(List<Long> idsAprobadas) {
        Set<Long> idsDisponibles =
                materiaNeo4JDAO.recuperarMateriasDisponibles(idsAprobadas);

        if (idsDisponibles.isEmpty()) {
            return List.of();
        }

        return materiaSQLDAO.findAllByIds(idsDisponibles);
    }
}