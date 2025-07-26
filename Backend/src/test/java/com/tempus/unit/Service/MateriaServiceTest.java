package com.tempus.unit.Service;

import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Exceptions.ResourceNotFound;
import com.Tempus.Factory.impls.MateriaFactory;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.IMateriaRepository;
import com.Tempus.Services.impls.MateriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaServiceTest {

    @Mock
    private IMateriaRepository materiaRepository;

    @Mock
    private MateriaFactory materiaFactory;

    @Mock
    private Materia materia;

    @Mock
    private MateriaDTO materiaDTO;

    private MateriaService materiaService;

    @BeforeEach
    public void setup(){
        materiaService = new MateriaService(materiaRepository,materiaFactory);
    }

    @Test
    public void  testGetCorrelativasOk(){
        when(materiaRepository.findById(1L)).thenReturn(Optional.of(materia));

        materiaService.findByIdMateriaDTO(1L);

        verify(materiaRepository).findById(1L);
    }

    @Test
    public void testCreatedMateria(){
        when(materiaFactory.factoryMethod(materiaDTO)).thenReturn(materia);
        when(materiaRepository.save(materia)).thenReturn(materia);
        when(materia.toDTO()).thenReturn(materiaDTO);

        materiaService.createdMateria(materiaDTO);

        verify(materiaRepository).save(materia);
    }

    @Test
    public void testGetCorrelativasThrowsExceptionWhenNotFound() {
        when(materiaRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> materiaService.findByIdMateriaDTO(99L));
    }
}
