package com.Tempus.DTO;

import com.Tempus.Factory.impls.MateriaFactory;
import com.Tempus.Models.Materia;
import com.Tempus.Models.MateriaSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MateriaSimpleDTOTest {


    MateriaSimpleDTO materiaSimpleDTO;
    MateriaSimple materiaSimple;
    MateriaFactory materiaFactory;

    @BeforeEach
    public void setup(){
        materiaSimple = mock(MateriaSimple.class);
        when(materiaSimple.getNombre()).thenReturn("Matematica");
        when(materiaSimple.getId()).thenReturn(1L);

        materiaSimpleDTO = new MateriaSimpleDTO(1L, "Matematica");
        materiaFactory = mock(MateriaFactory.class);
    }

    @Test
    public void toEntityMateriaSimple(){
        when(materiaFactory.createSimple(materiaSimpleDTO)).thenReturn(materiaSimple);

        Materia resultado = materiaSimpleDTO.toEntity(materiaFactory);

        assertEquals(materiaSimpleDTO.getNombre(), resultado.getNombre());
        assertEquals(materiaSimpleDTO.getId(), resultado.getId());

        verify(materiaFactory).createSimple(materiaSimpleDTO);
    }


}
