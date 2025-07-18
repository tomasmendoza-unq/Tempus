package com.Tempus.Models;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,               // usa el nombre del tipo
        property = "tipo"                         // el nombre del campo que se agrega
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MateriaSimple.class, name = "simple"),
        @JsonSubTypes.Type(value = MateriaCorrelativa.class, name = "correlativa")
})
@Data
public abstract class Materia {

    @Id
    @GeneratedValue
    protected Long id;

    protected String nombre;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @OneToMany(mappedBy = "materia")
    private List<Comision> comision;

    public abstract MateriaDTO toDTO();


}
