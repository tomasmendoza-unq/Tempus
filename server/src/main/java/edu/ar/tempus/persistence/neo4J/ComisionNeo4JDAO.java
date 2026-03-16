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
    MATCH (c1:Comision {id: $idA})
    MATCH (c2:Comision {id: $idB})
    RETURN NOT EXISTS {
        MATCH (c1)-[:SE_DICTA_EL]->(h1:ClaseHorario),
              (c2)-[:SE_DICTA_EL]->(h2:ClaseHorario)
        WHERE h1.dia = h2.dia
          AND h1.inicio < h2.fin
          AND h1.fin > h2.inicio
    }
    """)
    boolean verificarCompatibilidad(Long idA, Long idB);

    @Query("""
    MATCH (c1:Comision) WHERE c1.id IN $nuevas
    WITH collect(c1) AS nuevas
    
    MATCH (c2:Comision) WHERE c2.id IN ($nuevas + $anotadas)
    WITH nuevas, collect(c2) AS todas
    
    UNWIND nuevas AS c1
    UNWIND todas AS c2
    
    WITH c1, c2
    WHERE c1.id <> c2.id
    AND EXISTS {
        MATCH (c1)-[:SE_DICTA_EL]->(h1:ClaseHorario),
              (c2)-[:SE_DICTA_EL]->(h2:ClaseHorario)
        WHERE h1.dia = h2.dia
          AND h1.inicio < h2.fin
          AND h1.fin > h2.inicio
    }
    
    RETURN COUNT(*) > 0
    """)
    boolean haySuperposicionHoraria(
            @Param("nuevas") List<Long> nuevas,
            @Param("anotadas") List<Long> anotadas
    );
}
