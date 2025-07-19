package com.tempus.unit.Models;

import com.Tempus.DTO.MateriaSimpleDTO;
import com.Tempus.Models.Carrera;
import com.Tempus.Models.MateriaSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MateriaSimpleTest {

    @Mock
    private Carrera carrera;

    private MateriaSimple materiaSimple;

    @BeforeEach
    public void setup() {
        materiaSimple = new MateriaSimple();
        materiaSimple.setNombre("Matematica");
        materiaSimple.setId(1L);
        materiaSimple.setCarrera(carrera);
    }

    @Test
    public void toDTOMateriaSimple() {
        when(carrera.getId_carrera()).thenReturn(1L);
        MateriaSimpleDTO dto = (MateriaSimpleDTO) materiaSimple.toDTO();

        assertNotNull(dto);
        assertEquals("Matematica", dto.getNombre());
        assertEquals(1L, dto.getId());
        assertEquals(1L, dto.getId_carrera());
    }
}
