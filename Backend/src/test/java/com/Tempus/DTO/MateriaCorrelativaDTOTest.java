package com.Tempus.DTO;

import com.Tempus.Factory.IMateriaFactory;
import com.Tempus.Models.Materia;
import com.Tempus.Models.MateriaCorrelativa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaCorrelativaDTOTest {

    @Mock
    MateriaSimpleDTO materiaSimpleDTO1;

    @Mock
    IMateriaFactory materiaFactory;

    @InjectMocks
    MateriaCorrelativaDTO materiaCorrelativaDTO;

    @Mock
    MateriaCorrelativa materiaCorrelativa;

    List<MateriaDTO> materias;


    @BeforeEach
    public void setup(){
        materias = List.of(materiaSimpleDTO1);

        when(materiaCorrelativa.getNombre()).thenReturn("Matematica");
        when(materiaCorrelativa.getId()).thenReturn(1L);

        materiaCorrelativaDTO = new MateriaCorrelativaDTO(1L, "Matematica", materias);

    }

    @Test
    public void toEntityTest(){
        when(materiaFactory.createCorrelativa(materiaCorrelativaDTO)).thenReturn(materiaCorrelativa);

        Materia resultado = materiaCorrelativaDTO.toEntity(materiaFactory);

        assertEquals(materiaCorrelativaDTO.getNombre(), resultado.getNombre());
        assertEquals(materiaCorrelativaDTO.getId(), resultado.getId());

        verify(materiaFactory).createCorrelativa(materiaCorrelativaDTO);

    }
}
