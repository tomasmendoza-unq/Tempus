package edu.ar.tempus.persistence.neo4J.entity;


import lombok.*;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@ToString
@Setter
@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Node("Materia")
public class MateriaNeo4J {

    @Id
    private Long id;

    private String nombre;

    @Builder.Default
    @Relationship(type = "CORRELATIVA")
    private Set<MateriaNeo4J> correlativas = new HashSet<>();




}
