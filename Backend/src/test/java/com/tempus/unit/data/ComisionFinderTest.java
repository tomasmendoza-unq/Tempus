package com.tempus.unit.data;

import com.tempus.data.impls.ComisionFinder;
import com.tempus.exceptions.ResourceNotFound;
import com.tempus.models.Comision;
import com.tempus.repository.IComisionRepository;
import org.junit.jupiter.api.BeforeEach;
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
public class ComisionFinderTest {

    @Mock
    IComisionRepository comisionRepository;

    @InjectMocks
    ComisionFinder comisionFinder;

    @Mock
    Comision comision;

    Long id;

    @BeforeEach
    public void setUp(){
        id = 1L;
    }

    @Test
    public void testFindByIdOk(){
        when(comisionRepository.findById(id)).thenReturn(Optional.of(comision));

        comisionFinder.findById(id);

        verify(comisionRepository).findById(id);
    }

    @Test
    public void testFindByIdFailed(){
        when(comisionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> comisionFinder.findById(id));

        verify(comisionRepository).findById(id);
    }

    @Test
    public void testFindAllOk(){
        List<Comision> comisiones = List.of(comision);
        when(comisionRepository.findAllWithMateria()).thenReturn(comisiones);

        comisionFinder.findAll();

        verify(comisionRepository).findAllWithMateria();
    }

    @Test
    public void findComisionWithMateriaOk(){
        when(comisionRepository.findComisionWithMateria(id)).thenReturn(Optional.of(comision));

        comisionFinder.findComisionWithMateria(id);

        verify(comisionRepository).findComisionWithMateria(id);
    }

    @Test
    public void findComisionWithMateriaFailed(){
        when(comisionRepository.findComisionWithMateria(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> comisionFinder.findComisionWithMateria(id));

        verify(comisionRepository).findComisionWithMateria(id);
    }
}
