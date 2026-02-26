package edu.ar.tempus.persistence.repository.mapper.impls;

import edu.ar.tempus.model.Comision;
import edu.ar.tempus.persistence.neo4J.entity.ClaseHorarioNeo4J;
import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import edu.ar.tempus.persistence.repository.mapper.ComisionMapper;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ComisionMapperImpl implements ComisionMapper {
    @Override
    public ComisionNeo4J toNeo4J(Comision comision, MateriaNeo4J materiaNeo) {
        Set<ClaseHorarioNeo4J> horariosNeo = comision.getClases().stream()
                .map(clase -> ClaseHorarioNeo4J.builder()
                        .dia(clase.getDia().name())
                        .inicio(clase.getInicio().getHour() * 100 + clase.getInicio().getMinute())
                        .fin(clase.getFin().getHour() * 100 + clase.getFin().getMinute())
                        .build())
                .collect(Collectors.toSet());

        return ComisionNeo4J.builder()
                .id(comision.getComisionId())
                .horarios(horariosNeo)
                .materia(materiaNeo)
                .build();
    }

}
