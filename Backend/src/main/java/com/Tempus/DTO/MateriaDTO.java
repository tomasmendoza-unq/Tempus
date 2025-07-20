package com.Tempus.DTO;

import com.Tempus.Models.Materia;
import com.Tempus.Factory.IMateriaFactory;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MateriaSimpleDTO.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = MateriaCorrelativaDTO.class, name = "CORRELATIVA")
})
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class MateriaDTO {
    protected Long id;
    protected String nombre;
    protected Long id_carrera;

    public MateriaDTO(Long id, String nombre, Long id_carrera) {
        this.id = id;
        this.nombre = nombre;
        this.id_carrera = id_carrera;
    }


    public abstract Materia toEntity(IMateriaFactory materiaFactory);
}
