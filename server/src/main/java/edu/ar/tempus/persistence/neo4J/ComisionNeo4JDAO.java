package edu.ar.tempus.persistence.neo4J;

import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComisionNeo4JDAO extends Neo4jRepository<ComisionNeo4J, Long> {
/*
    @Query("""
        MATCH (m:Materia) 
        WHERE m.materiaId IN $materiasIds
        WITH collect(m) AS seleccionadas, count(m) AS totalMaterias
        
        MATCH (c:Comision)-[:PERTENECE_A]->(m)
        WHERE m IN seleccionadas
        
        WITH totalMaterias, collect(c) AS potenciales

        MATCH p = (c1:Comision)-[:COMPATIBLE_CON*]-(cN:Comision)
        WHERE all(com in nodes(p) WHERE com IN potenciales)
          AND size(nodes(p)) = totalMaterias
          
        RETURN nodes(p) LIMIT 1
        """)
    List<ComisionNeo4J> encontrarHorario(List<Long> materiasIds);

    @Query("""
        MATCH (a:Comision), (b:Comision)
        WHERE id(a) < id(b)
          AND NOT (a)-[:PERTENECE_A]->()<-[:PERTENECE_A]-(b)
          AND (a.dia <> b.dia OR (a.fin <= b.inicio OR b.fin <= a.inicio))
        MERGE (a)-[:COMPATIBLE_CON]-(b)
        """)
    void vincularCompatibilidadesTodo();

    @Query("""
        MATCH (c:Comision) WHERE id(c) = $id
        MATCH (otra:Comision)
        WHERE id(c) <> id(otra)
          AND NOT (c)-[:PERTENECE_A]->()<-[:PERTENECE_A]-(otra)
          AND (c.dia <> otra.dia OR (c.fin <= otra.inicio OR otra.fin <= c.inicio))
        MERGE (c)-[:COMPATIBLE_CON]-(otra)
        """)
    void vincularCompatibilidadesPorId(Long id);*/
}
