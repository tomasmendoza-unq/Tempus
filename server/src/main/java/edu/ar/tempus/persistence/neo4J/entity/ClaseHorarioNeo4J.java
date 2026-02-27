package edu.ar.tempus.persistence.neo4J.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Data
@Builder
@Node("ClaseHorario")
public class ClaseHorarioNeo4J {
    @Id
    @GeneratedValue
    private Long id;

    private String dia;
    private int inicio; // 1830
    private int fin;    // 2100
}