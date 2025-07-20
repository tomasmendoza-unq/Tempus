package com.tempus.unit.Controller;

import com.Tempus.Controller.CarreraController;
import com.Tempus.DTO.CarreraDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaResumenDTO;
import com.Tempus.Services.ICarreraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarreraControllerTest {

    @InjectMocks
    private CarreraController carreraController;

    @Mock
    private ICarreraService carreraService;

    @Mock
    private CarreraDTO carrera;

    @Mock
    private CarreraDTO carrera2;

    private List<CarreraDTO> carreraDTOS;

    @Mock
    MateriaResumenDTO materiaDTOS2;

    @Mock
    MateriaResumenDTO materiaDTOS1;

    @Mock
    Set<MateriaResumenDTO> materiaDTOS;

    @BeforeEach
    public void setUp(){
        carreraDTOS = List.of(carrera, carrera2);
        materiaDTOS = Set.of(materiaDTOS1, materiaDTOS2);
    }

    @Test
    public void getCarrerasTest(){
        when(carreraService.getCarreras()).thenReturn(carreraDTOS);

        ResponseEntity<?> response = carreraController.getCarreras();

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(carreraDTOS, response.getBody());

        verify(carreraService).getCarreras();
    }

    @Test
    public void getCarreraTest(){
        when(carreraService.findCarreraById(1L)).thenReturn(carrera);

        ResponseEntity<?> response = carreraController.getCarrera(1L);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(carrera, response.getBody());

        verify(carreraService).findCarreraById(1L);
    }


    @Test
    public void getMateriasCorrelativasOfCarreraTest(){
        when(carreraService.findMateriasOfCarreraById(1L)).thenReturn(materiaDTOS);

        ResponseEntity<?> response = carreraController.getMateriasOfCarrera(1L);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(materiaDTOS, response.getBody());

        verify(carreraService).findMateriasOfCarreraById(1L);
    }

    @Test
    public void createdCarreraTestOK(){
        when(carreraService.createdCarrera(carrera)).thenReturn(carrera);

        ResponseEntity<?> response = carreraController.createdCarrera(carrera);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(carrera, response.getBody());

        verify(carreraService).createdCarrera(carrera);
    }
}
