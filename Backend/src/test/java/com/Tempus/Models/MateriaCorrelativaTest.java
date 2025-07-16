package com.Tempus.Models;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

public class MateriaCorrelativaTest {

    MateriaCorrelativa materiaCorrelativa;
    MateriaSimple materiaSimple1;
    MateriaSimple materiaSimple2;
    MateriaCorrelativaDTO materiaCorrelativaDTO;
    MateriaSimpleDTO materiaSimpleDTO1;
    MateriaSimpleDTO materiaSimpleDTO2;

    @BeforeEach
    public void setup(){
        materiaSimple1 = mock(MateriaSimple.class);
        materiaSimple2 = mock(MateriaSimple.class);

        when(materiaSimple1.toDTO()).thenReturn(materiaSimpleDTO1);
        when(materiaSimple2.toDTO()).thenReturn(materiaSimpleDTO2);


        materiaCorrelativa = new MateriaCorrelativa();
        materiaCorrelativa.addCorrelativas(List.of(materiaSimple1,materiaSimple2));
    }


    @Test
    public void toDTOMateriaCorrelativa(){
        materiaCorrelativa.toDTO();

        verify(materiaSimple1).toDTO();
        verify(materiaSimple2).toDTO();
    }
}
