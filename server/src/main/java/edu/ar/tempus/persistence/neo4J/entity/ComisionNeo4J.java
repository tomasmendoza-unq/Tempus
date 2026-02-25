package edu.ar.tempus.persistence.neo4J.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

@Data
@Builder
@Node("Comision")
public class ComisionNeo4J {

    @Id
    @GeneratedValue
    private Long id;

    private Long comisionId;
    private int inicio;
    private int fin;
    private String dia;
    private String franja;

    @Relationship(type = "PERTENECE_A", direction = Relationship.Direction.OUTGOING)
    private MateriaNeo4J materia;

    @Relationship(type = "COMPATIBLE_CON", direction = Relationship.Direction.OUTGOING)
    private Set<ComisionNeo4J> comisionesCompatibles;
}