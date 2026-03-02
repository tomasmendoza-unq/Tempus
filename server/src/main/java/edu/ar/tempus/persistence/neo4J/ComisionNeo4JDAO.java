package edu.ar.tempus.persistence.neo4J;

import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComisionNeo4JDAO extends Neo4jRepository<ComisionNeo4J, Long> {
    @Query("""
            MATCH (c1:Comision {id: $id}), (c2:Comision)
            WHERE c1.id <> c2.id
              AND NOT EXISTS {
                MATCH (c1)-[:SE_DICTA_EL]->(h1:ClaseHorario),
                      (c2)-[:SE_DICTA_EL]->(h2:ClaseHorario)
                WHERE h1.dia = h2.dia
                  AND h1.inicio < h2.fin
                  AND h1.fin > h2.inicio
              }
              AND EXISTS { MATCH (c1)-[:SE_DICTA_EL]->() }
              AND EXISTS { MATCH (c2)-[:SE_DICTA_EL]->() }
            MERGE (c1)-[:COMPATIBLE_CON]->(c2)
            """)
    void vincularCompatibilidadesPorId(@Param("id") Long id);

    @Query("MATCH (c:Comision {id: $idA})-[:COMPATIBLE_CON]-(target:Comision {id: $idB}) RETURN count(target) > 0")
    boolean verificarCompatibilidad(Long idA, Long idB);
}
