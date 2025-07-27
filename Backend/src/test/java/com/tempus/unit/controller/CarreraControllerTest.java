package com.tempus.unit.controller;

import com.tempus.controller.CarreraController;
import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.service.ICarreraService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarreraControllerTest {

    @Mock
    private ICarreraService carreraService;

    @InjectMocks
    private CarreraController carreraController;

    @Mock
    private CarreraPostDTO carreraPostDTO;

    @Mock
    private CarreraResponseDTO responsePost;

    @Test
    public void testCreatedCarreraOk(){
        when(carreraService.createdCarrera(carreraPostDTO)).thenReturn(responsePost);

        carreraController.createdCarrera(carreraPostDTO);

        verify(carreraService).createdCarrera(carreraPostDTO);
    }
    @Test
    public void testGetCarreraOk(){
        Long id = 1L;

        when(carreraService.getCarrera(id)).thenReturn(responsePost);

        carreraController.getCarrera(id);

        verify(carreraService).getCarrera(id);
    }

    @Test
    public void testGetCarrerasOk(){
        when(carreraService.getCarreras()).thenReturn(List.of(responsePost));

        carreraController.getCarreras();

        verify(carreraService).getCarreras();
    }

}
