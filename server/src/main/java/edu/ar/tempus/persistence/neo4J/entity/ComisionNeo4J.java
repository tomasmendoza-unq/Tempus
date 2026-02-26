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
    private Long id;


    @Relationship(type = "SE_DICTA_EL", direction = Relationship.Direction.OUTGOING)
    private Set<ClaseHorarioNeo4J> horarios;

    @Relationship(type = "PERTENECE_A", direction = Relationship.Direction.OUTGOING)
    private MateriaNeo4J materia;

    @Relationship(type = "COMPATIBLE_CON", direction = Relationship.Direction.OUTGOING)
    private Set<ComisionNeo4J> comisionesCompatibles;
}