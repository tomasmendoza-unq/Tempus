package com.Tempus.Models;

import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MateriaSimpleTest {

    private MateriaSimple materiaSimple;

    @BeforeEach
    public void setup() {
        materiaSimple = new MateriaSimple();
        materiaSimple.setNombre("Matematica");
        materiaSimple.setId(1L); // Asegurate de que `setId` esté disponible (probablemente en la clase `Materia`)
    }

    @Test
    public void toDTOMateriaSimple() {
        MateriaSimpleDTO dto = (MateriaSimpleDTO) materiaSimple.toDTO();

        assertNotNull(dto);
        assertEquals("Matematica", dto.getNombre());
        assertEquals(1L, dto.getId());
    }
}
