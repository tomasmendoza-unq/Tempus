package com.tempus.unit.service;

import com.tempus.Factory.impls.CarreraFactory;
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


}
