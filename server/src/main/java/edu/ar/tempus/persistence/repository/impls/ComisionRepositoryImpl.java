package edu.ar.tempus.persistence.repository.impls;

import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.model.Comision;
import edu.ar.tempus.persistence.neo4J.ComisionNeo4JDAO;
import edu.ar.tempus.persistence.neo4J.MateriaNeo4JDAO;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import edu.ar.tempus.persistence.repository.ComisionRepository;
import edu.ar.tempus.persistence.repository.mapper.ComisionMapper;
import edu.ar.tempus.persistence.sql.ComisionDAOSQL;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ComisionRepositoryImpl implements ComisionRepository {
    
    private final ComisionMapper comisionMapper;
    private final ComisionNeo4JDAO comisionNeo4JDAO;
    private final ComisionDAOSQL comisionDAOSQL;
    private final MateriaNeo4JDAO materiaNeo4JDAO;

    public ComisionRepositoryImpl(ComisionMapper comisionMapper, ComisionNeo4JDAO comisionNeo4JDAO, ComisionDAOSQL comisionDAOSQL, MateriaNeo4JDAO materiaNeo4JDAO) {
        this.comisionMapper = comisionMapper;
        this.comisionNeo4JDAO = comisionNeo4JDAO;
        this.comisionDAOSQL = comisionDAOSQL;
        this.materiaNeo4JDAO = materiaNeo4JDAO;
    }

    @Override
    public Comision guardar(Comision comision) {
        Comision comisionGuardada = comisionDAOSQL.save(comision);

        Long materiaId = comision.getMateria().getMateriaId();

        MateriaNeo4J materiaNeo = materiaNeo4JDAO.findById(materiaId)
                .orElseThrow(() -> new EntityNotFoundException(MateriaNeo4J.class.getName(), materiaId));

        ComisionNeo4J neo = comisionMapper.toNeo4J(comisionGuardada, materiaNeo);
        neo.setMateria(materiaNeo);

        ComisionNeo4J neoGuardado = comisionNeo4JDAO.save(neo);


        comisionNeo4JDAO.vincularCompatibilidadesPorId(neoGuardado.getId());

        return comisionGuardada;

    }

    @Override
    public Comision recuperar(Long comisionId) {

        return comisionDAOSQL.findById(comisionId)
                .orElseThrow(() -> new EntityNotFoundException(Comision.class.getName(), comisionId));
    }

    @Override
    public List<Comision> encontrarIdsUnaCombinacionCompatible(List<Long> materiasIds) {
        List<Long> idsComisionesCompatibles = comisionNeo4JDAO.encontrarIdsUnaCombinacionCompatible(materiasIds);

        return comisionDAOSQL.findAllById(idsComisionesCompatibles);
    }
}
