package com.Tempus.DTO;

import lombok.Data;

@Data
public class MateriaResumenDTO {
    private Long id;
    private String nombre;

    public MateriaResumenDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}