package com.Tempus.Models;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaCorrelativaTest {


    @InjectMocks
    MateriaCorrelativa materiaCorrelativa;

    @Mock
    MateriaSimple materiaSimple1;
    @Mock
    MateriaSimple materiaSimple2;
    @Mock
    MateriaCorrelativaDTO materiaCorrelativaDTO;
    @Mock
    MateriaSimpleDTO materiaSimpleDTO1;
    @Mock
    MateriaSimpleDTO materiaSimpleDTO2;

    @BeforeEach
    public void setup(){
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
