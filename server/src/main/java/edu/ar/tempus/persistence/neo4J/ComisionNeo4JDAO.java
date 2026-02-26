package edu.ar.tempus.persistence.neo4J;

import edu.ar.tempus.persistence.neo4J.entity.ComisionNeo4J;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
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
*/

    @Query("MATCH (m:Materia) WHERE m.id IN $materiasIds " +
            "WITH collect(m) AS materiasRequeridas, count(m) AS totalMaterias " +
            "MATCH (c:Comision)-[:PERTENECE_A]->(m:Materia) " +
            "WHERE m.id IN $materiasIds " +
            "WITH c, m, totalMaterias " +
            "MATCH path = (c)-[:COMPATIBLE_CON*]-(cn:Comision) " +
            "WHERE size(nodes(path)) = totalMaterias " +
            "  AND all(n IN nodes(path) WHERE n:Comision) " +
            "RETURN [n IN nodes(path) | n.id] LIMIT 1")
    List<Long> encontrarIdsUnaCombinacionCompatible(@Param("materiasIds") List<Long> materiasIds);

    @Query("MATCH (c1:Comision {id: $id}), (c2:Comision) " +
            "WHERE c1.id <> c2.id " +
            "OPTIONAL MATCH (c1)-[:SE_DICTA_EL]->(h1:ClaseHorario), " +
            "               (c2)-[:SE_DICTA_EL]->(h2:ClaseHorario) " +
            "WHERE h1.dia = h2.dia " +
            "  AND h1.inicio < h2.fin " +
            "  AND h1.fin > h2.inicio " +
            "WITH c1, c2, COUNT(h1) as superposiciones " +
            "WHERE superposiciones = 0 " +
            "MERGE (c1)-[:COMPATIBLE_CON]-(c2)")
    void vincularCompatibilidadesPorId(@Param("id") Long id);

    @Query("MATCH (c:Comision {id: $idA})-[:COMPATIBLE_CON]-(target:Comision {id: $idB}) RETURN count(target) > 0")
    boolean verificarCompatibilidad(Long idA, Long idB);
}
