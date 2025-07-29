package com.tempus.unit.data;

import com.tempus.data.impls.MateriaFinder;
import com.tempus.exceptions.ResourceNotFound;
import com.tempus.models.Materia;
import com.tempus.repository.IMateriaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MateriaFinderTest {
    
    @Mock
    IMateriaRepository materiaRepository;
    
    @InjectMocks
    MateriaFinder materiaFinder;
    
    @Mock
    Materia materia;
    
    @Test
    public void testFindByIdOK(){
        Long id = 1L;
        when(materiaRepository.findById(id)).thenReturn(Optional.of(materia));

        materiaFinder.findById(id);

        verify(materiaRepository).findById(id);
    }

    @Test
    public void testFindAllOK(){
        List<Materia> materiaList= List.of(materia);

        when(materiaRepository.findAll()).thenReturn(materiaList);

        materiaFinder.findAll();

        verify(materiaRepository).findAll();
    }

    @Test
    public void testFindMateriaWithCorrelativasOk(){
        when(materiaRepository.findWithCorrelativasById(1L)).thenReturn(Optional.of(materia));

        materiaFinder.findMateriaWithCorrelativas(1L);

        verify(materiaRepository).findWithCorrelativasById(1L);

    }

    @Test
    public void testFindMateriaWithCorrelativasNotFound(){
        Long id = 1L;
        when(materiaRepository.findWithCorrelativasById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> materiaFinder.findMateriaWithCorrelativas(id));

        verify(materiaRepository).findWithCorrelativasById(id);
    }

    @Test
    public void testFindMateriasByCarreraIdOk(){
        Long idCarrera = 1L;
        List<Materia> materiaList= List.of(materia);

        when(materiaRepository.findMateriasByCarreraId(idCarrera)).thenReturn(materiaList);

        materiaFinder.findMateriasByCarreraId(idCarrera);

        verify(materiaRepository).findMateriasByCarreraId(idCarrera);
    }

    @Test
    public void testFindByIdNotFound(){
        Long id = 1L;
        when(materiaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> materiaFinder.findById(id));

        verify(materiaRepository).findById(id);
    }
}
