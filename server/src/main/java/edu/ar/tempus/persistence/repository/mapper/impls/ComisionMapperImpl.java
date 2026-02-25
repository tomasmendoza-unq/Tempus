package edu.ar.tempus.persistence.repository.mapper.impls;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import edu.ar.tempus.persistence.repository.mapper.ComisionMapper;
import org.springframework.stereotype.Component;

@Component
public class ComisionMapperImpl implements ComisionMapper {
    @Override
    public ComisionNeo4J toNeo4J(Comision comisionGuardada, MateriaNeo4J materiaNeo) {
        return ComisionNeo4J.builder()
                .comisionId(comisionGuardada.getComisionId())
                .inicio(comisionGuardada.getHorarioInicio().getHour() * 100 + comisionGuardada.getHorarioInicio().getMinute())
                .fin(comisionGuardada.getHorarioFin().getHour() * 100 + comisionGuardada.getHorarioFin().getMinute())
                .build();
    }

    @Override
    public Comision toModel(Comision comision, ComisionNeo4J neo) {
        return null;
    }
}
