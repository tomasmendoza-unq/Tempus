package com.tempus.unit.data;

import com.tempus.data.impls.CarreraFinder;
import com.tempus.exceptions.ResourceNotFound;
import com.tempus.models.Carrera;
import com.tempus.repository.ICarreraRepository;
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
public class CarreraFinderTest {

    @Mock
    ICarreraRepository carreraRepository;

    @InjectMocks
    CarreraFinder carreraFinder;

    @Mock
    Carrera carrera;

    @Test
    public void testFindByIdOK(){
        Long id = 1L;
        when(carreraRepository.findById(id)).thenReturn(Optional.of(carrera));

        carreraFinder.findById(id);

        verify(carreraRepository).findById(id);
    }

    @Test
    public void testFindByIdNotFound(){
        Long id = 1L;
        when(carreraRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFound.class, () -> carreraFinder.findById(id));

        verify(carreraRepository).findById(id);
    }

    @Test
    public void testFindAllOk(){
        List<Carrera> carreraList = List.of(carrera);
        when(carreraRepository.findAll()).thenReturn(carreraList);

        carreraFinder.findAll();

        verify(carreraRepository).findAll();
    }
}
