package com.tempus.unit.service;

import com.tempus.Factory.impls.CarreraFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.models.Carrera;
import com.tempus.repository.ICarreraRepository;
import com.tempus.service.impls.CarreraService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarreraServiceTest {

    @Mock
    CarreraResponseDTO carreraResponseDTO;

    @Mock
    CarreraFactory carreraFactory;

    @Mock
    CarreraPostDTO carreraPostDTO;

    @Mock
    ICarreraRepository carreraRepository;

    @Mock
    Carrera saved;

    @Mock
    Carrera carrera;

    @Mock
    IEntityFinder<Carrera> carreraFinder;

    @InjectMocks
    CarreraService carreraService;

    @Test
    public void testCreatedCarreraOk(){
        when(carreraFactory.toEntity(carreraPostDTO)).thenReturn(carrera);
        when(carreraRepository.save(carrera)).thenReturn(saved);
        when(carreraFactory.toResponseDTO(saved)).thenReturn(carreraResponseDTO);

        carreraService.createdCarrera(carreraPostDTO);

        verify(carreraFactory).toEntity(carreraPostDTO);
        verify(carreraRepository).save(carrera);
        verify(carreraFactory).toResponseDTO(saved);
    }

    @Test
    public void testGetCarreraOk(){
        Long id = 1L;
        when(carreraFactory.toResponseDTO(carrera)).thenReturn(carreraResponseDTO);
        when(carreraFinder.findById(id)).thenReturn(carrera);

        carreraService.getCarrera(id);

        verify(carreraFactory).toResponseDTO(carrera);
        verify(carreraFinder).findById(id);
    }

    @Test
    public void testGetCarrerasOk(){
        when(carreraFactory.toResponseDTO(carrera)).thenReturn(carreraResponseDTO);
        when(carreraRepository.findAll()).thenReturn(List.of(carrera));

        carreraService.getCarreras();

        verify(carreraFactory).toResponseDTO(carrera);
        verify(carreraRepository).findAll();
    }

    @Test
    public void testPutCarreraOk(){
        Long id = 1L;
        when(carreraFactory.toResponseDTO(saved)).thenReturn(carreraResponseDTO);
        when(carreraRepository.save(carrera)).thenReturn(saved);
        when(carreraFinder.findById(id)).thenReturn(carrera);

        carreraService.putCarrera(id, carreraPostDTO);

        verify(carreraFactory).toResponseDTO(saved);
        verify(carreraRepository).save(carrera);
        verify(carreraFinder).findById(id);
    }

    @Test
    public void testDeleteCarreraOk(){
        Long id = 1L;
        when(carreraFinder.findById(id)).thenReturn(carrera);

        carreraService.deleteCarrera(id);

        verify(carreraFinder).findById(id);
    }
}
