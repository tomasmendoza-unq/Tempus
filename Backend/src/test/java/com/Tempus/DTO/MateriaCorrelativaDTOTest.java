package com.Tempus.DTO;

import com.Tempus.Factory.IMateriaFactory;
import com.Tempus.Models.Materia;
import com.Tempus.Models.MateriaCorrelativa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MateriaCorrelativaDTOTest {

    MateriaSimpleDTO materiaSimpleDTO1;
    IMateriaFactory materiaFactory;
    MateriaCorrelativaDTO materiaCorrelativaDTO;
    MateriaCorrelativa materiaCorrelativa;
    List<MateriaDTO> materias;

    @BeforeEach
    public void setup(){
        materiaFactory = mock(IMateriaFactory.class);
        materiaSimpleDTO1 = mock(MateriaSimpleDTO.class);
        materias = List.of(materiaSimpleDTO1);
        materiaCorrelativa = mock(MateriaCorrelativa.class);

        when(materiaCorrelativa.getNombre()).thenReturn("Matematica");
        when(materiaCorrelativa.getId()).thenReturn(1L);

        materiaCorrelativaDTO = new MateriaCorrelativaDTO(1L, "Matematica", materias);

    }

    @Test
    public void toEntity(){
        when(materiaFactory.createCorrelativa(materiaCorrelativaDTO)).thenReturn(materiaCorrelativa);

        Materia resultado = materiaCorrelativaDTO.toEntity(materiaFactory);

        assertEquals(materiaCorrelativaDTO.getNombre(), resultado.getNombre());
        assertEquals(materiaCorrelativaDTO.getId(), resultado.getId());

        verify(materiaFactory).createCorrelativa(materiaCorrelativaDTO);

    }
}
