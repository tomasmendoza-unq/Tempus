package edu.ar.tempus.persistence.neo4J;

import edu.ar.tempus.model.Materia;
import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface MateriaNeo4JDAO extends Neo4jRepository<MateriaNeo4J, Long> {

    @Query("""
    MATCH (origen:Materia {id: $materiaOrigenId})
    MATCH (destino:Materia {id: $materiaDestinoId})
        MERGE (destino)-[:CORRELATIVA]->(origen)
    RETURN r
    """)
    void crearRelacionCorrelativa(Long materiaOrigenId, Long materiaDestinoId);


    @Query("""
    MATCH (m:Materia)
    WHERE NOT m.id IN $idsAprobadas
      AND NOT EXISTS {
        MATCH (previa:Materia)-[:CORRELATIVA]->(m)
        WHERE NOT previa.id IN $idsAprobadas
      }
    RETURN m.id
    """)
    Set<Long> recuperarMateriasDisponibles(@Param("idsAprobadas") List<Long> materiasAprobadas);
}
