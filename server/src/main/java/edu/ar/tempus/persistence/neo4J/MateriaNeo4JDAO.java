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
    MATCH (m:Materia)
    WHERE NOT m.id IN $idsAprobadas
      AND NOT EXISTS {
        MATCH (previa:Materia)-[:CORRELATIVA]->(m)
        WHERE NOT previa.id IN $idsAprobadas
      }
    RETURN m.id
    """)
    Set<Long> recuperarMateriasDisponibles(@Param("idsAprobadas") List<Long> materiasAprobadas);

    @Query("""
    MATCH (materia:Materia {id: $materiaDestinoId})
    MATCH (correlativa:Materia  {id: $materiaOrigenId})
        MERGE (correlativa)-[:CORRELATIVA]->(materia)
    """)
    void crearRelacionCorrelativa(Long materiaOrigenId, Long materiaDestinoId);

    @Query("""
    MATCH (materia:Materia {id: $materiaId}), (correlativa:Materia)
    WHERE correlativa.id IN $correlativaIds
        MERGE (correlativa)-[:CORRELATIVA]->(materia)
    """)
    void crearRelacionesCorrelativas(@Param("materiaId") Long materiaId,
                                     @Param("correlativaIds") List<Long> correlativaIds);
    @Query("""
    UNWIND $materiaDestinoIds AS destinoId
        MATCH (correlativa:Materia {id: $materiaOrigenId})-[:CORRELATIVA]->(materia:Materia {id: destinoId})
    RETURN collect(destinoId)
    """)
    List<Long> existeRelacionCorrelativa(@Param("materiaOrigenId") Long materiaOrigenId,
                                         @Param("materiaDestinoIds") List<Long> materiaDestinoIds);

    @Query("""
    UNWIND $materiaDestinoIds AS destinoId
    MATCH path = (destino:Materia {id: destinoId})-[:CORRELATIVA*]->(origen:Materia {id: $materiaOrigenId})
    RETURN collect(DISTINCT destinoId)
    """)
    List<Long> existeDependenciaCircular(@Param("materiaOrigenId") Long materiaOrigenId,
                                         @Param("materiaDestinoIds") List<Long> materiaDestinoIds);
}
