package com.tempus.unit.Models;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
import com.Tempus.Models.Carrera;
import com.Tempus.Models.Materia;
import com.Tempus.Models.MateriaCorrelativa;
import com.Tempus.Models.MateriaSimple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaCorrelativaTest {

    MateriaCorrelativa materiaCorrelativa;

    @Mock
    MateriaSimple materiaSimple1;

    @Mock
    MateriaSimple materiaSimple2;

    @Mock
    MateriaSimpleDTO materiaSimpleDTO1;

    @Mock
    MateriaSimpleDTO materiaSimpleDTO2;

    @Mock
    Carrera carrera;


    @BeforeEach
    public void setup(){
        materiaCorrelativa = new MateriaCorrelativa();
        materiaCorrelativa.setCarrera(carrera);
    }


    @Test
    public void toDTOMateriaCorrelativa(){
        materiaCorrelativa.addCorrelativas(List.of(materiaSimple1,materiaSimple2));
        when(carrera.getId_carrera()).thenReturn(1L);
        when(materiaSimple1.toDTO()).thenReturn(materiaSimpleDTO1);
        when(materiaSimple2.toDTO()).thenReturn(materiaSimpleDTO2);

        materiaCorrelativa.toDTO();

        verify(materiaSimple1).toDTO();
        verify(materiaSimple2).toDTO();
    }




}
