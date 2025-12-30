package edu.ar.tempus.persistence.neo4J;

import edu.ar.tempus.persistence.neo4J.entity.MateriaNeo4J;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaNeo4JDAO extends Neo4jRepository<MateriaNeo4J, Long> {
}
