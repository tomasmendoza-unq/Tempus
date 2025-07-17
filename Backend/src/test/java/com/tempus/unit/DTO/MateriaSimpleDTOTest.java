package com.tempus.unit.DTO;

import com.Tempus.DTO.MateriaSimpleDTO;
import com.Tempus.Factory.impls.MateriaFactory;
import com.Tempus.Models.Materia;
import com.Tempus.Models.MateriaSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaSimpleDTOTest {



    MateriaSimpleDTO materiaSimpleDTO;

    @Mock
    MateriaSimple materiaSimple;

    @Mock
    MateriaFactory materiaFactory;

    @BeforeEach
    public void setup(){
        when(materiaSimple.getNombre()).thenReturn("Matematica");
        when(materiaSimple.getId()).thenReturn(1L);

        materiaSimpleDTO = new MateriaSimpleDTO(1L, "Matematica");

    }

    @Test
    public void toEntityMateriaSimpleTest(){
        when(materiaFactory.createSimple(materiaSimpleDTO)).thenReturn(materiaSimple);

        Materia resultado = materiaSimpleDTO.toEntity(materiaFactory);

        assertEquals(materiaSimpleDTO.getNombre(), resultado.getNombre());
        assertEquals(materiaSimpleDTO.getId(), resultado.getId());

        verify(materiaFactory).createSimple(materiaSimpleDTO);
    }


}
