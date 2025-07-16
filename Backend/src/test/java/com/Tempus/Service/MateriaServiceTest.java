package com.Tempus.Service;

import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Exceptions.MateriaNotFound;
import com.Tempus.Factory.impls.MateriaFactory;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.impls.MateriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class MateriaServiceTest {

    private IMateriaRepository materiaRepository;

    private MateriaFactory materiaFactory;

    private Materia materia;

    private MateriaDTO materiaDTO;

    private MateriaService materiaService;

    @BeforeEach
    public void setup(){
        materiaRepository = mock(IMateriaRepository.class);
        materiaFactory = mock(MateriaFactory.class);
        materia = mock(Materia.class);
        materiaDTO = mock(MateriaDTO.class);
        materiaService = new MateriaService(materiaRepository,materiaFactory);

        when(materiaDTO.getNombre()).thenReturn("Matematiica");
        when(materiaDTO.getId()).thenReturn(1L);
        when(materia.toDTO()).thenReturn(materiaDTO);
        when(materia.getNombre()).thenReturn("Matematica");
        when(materia.getId()).thenReturn(1L);
    }

    @Test
    public void  testGetCorrelativasOk(){
        when(materiaRepository.findById(1L)).thenReturn(Optional.of(materia));

        MateriaDTO resultado = materiaService.findCorrelativasById(1L);

        assertEquals(materiaDTO.getNombre(), resultado.getNombre());
        assertEquals(materiaDTO.getId(), resultado.getId());

        verify(materiaRepository).findById(1L);
    }

    @Test
    public void testCreatedMateria(){
        when(materiaFactory.factoryMethod(materiaDTO)).thenReturn(materia);
        when(materiaRepository.save(materia)).thenReturn(materia);
        when(materia.toDTO()).thenReturn(materiaDTO);

        MateriaDTO resultado = materiaService.createdMateria(materiaDTO);

        assertEquals(materiaDTO.getNombre(), resultado.getNombre());
        assertEquals(materiaDTO.getId(), resultado.getId());

        verify(materiaRepository).save(materia);
    }

    @Test
    public void testGetCorrelativasThrowsExceptionWhenNotFound() {
        when(materiaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(MateriaNotFound.class, () -> materiaService.findCorrelativasById(99L));
    }
}
