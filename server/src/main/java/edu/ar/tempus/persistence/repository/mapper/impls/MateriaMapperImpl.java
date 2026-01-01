package edu.ar.tempus.persistence.repository.mapper.impls;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import edu.ar.tempus.persistence.repository.mapper.MateriaMapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MateriaMapperImpl implements MateriaMapper {

    public MateriaNeo4J toNeo4J(Materia materia) {
        if (materia == null) {
            return null;
        }

        // Map correlativas recursively
        Set<MateriaNeo4J> correlativasNeo4j = new HashSet<>();
        if (materia.getCorrelativas() != null) {
            for (Materia correlativa : materia.getCorrelativas()) {
                // Only map ID and nombre to avoid infinite recursion
                MateriaNeo4J corrNeo4j = MateriaNeo4J.builder()
                        .id(correlativa.getMateriaId())  // Use SQL ID
                        .nombre(correlativa.getMateriaNombre())
                        .build();
                correlativasNeo4j.add(corrNeo4j);
            }
        }

        return MateriaNeo4J.builder()
                .id(materia.getMateriaId())  // Use the SQL ID as Neo4j ID
                .nombre(materia.getMateriaNombre())
                .correlativas(correlativasNeo4j)
                .build();
    }

    public Materia toModel(Materia sqlMateria, MateriaNeo4J neo4jMateria) {
        // Start with SQL data
        if (sqlMateria == null) {
            return null;
        }

        // Add correlativas from Neo4j
        if (neo4jMateria != null && neo4jMateria.getCorrelativas() != null) {
            Set<Materia> correlativas = new HashSet<>();
            for (MateriaNeo4J corrNeo4j : neo4jMateria.getCorrelativas()) {
                Materia corr = new Materia();
                corr.setMateriaId(corrNeo4j.getId());
                corr.setMateriaNombre(corrNeo4j.getNombre());
                correlativas.add(corr);
            }
            sqlMateria.setCorrelativas(correlativas);
        }

        return sqlMateria;
    }
}