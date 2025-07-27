package com.tempus.unit.controller;

import com.tempus.controller.CarreraController;
import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.service.ICarreraService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private CarreraPostDTO responsePost;

    @Test
    public void createdCarreraOk(){
        when(carreraService.createdCarrera(carreraPostDTO)).thenReturn(responsePost);

        carreraController.createdCarrera(carreraPostDTO);

        verify(carreraService).createdCarrera(carreraPostDTO);
    }

}
