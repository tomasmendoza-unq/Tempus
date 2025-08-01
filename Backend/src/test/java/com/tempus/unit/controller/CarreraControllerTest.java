package com.tempus.unit.controller;

import com.tempus.controller.CarreraController;
import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.service.ICarreraService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Mock
    private MateriaResponseDTO materiaResponseDTO;

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

        ResponseEntity<?> response = carreraController.getCarrera(id);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(responsePost, response.getBody());

        verify(carreraService).getCarrera(id);
    }

    @Test
    public void testGetCarrerasOk(){
        List<CarreraResponseDTO> responseDTOS = List.of(responsePost);
        when(carreraService.getCarreras()).thenReturn(responseDTOS);

        ResponseEntity<?> response = carreraController.getCarreras();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTOS, response.getBody());

        verify(carreraService).getCarreras();
    }

    @Test
    public void testGetMateriasByCarreraOk(){
        Long id = 1L;
        List<MateriaResponseDTO> materiaResponseDTOS = List.of(materiaResponseDTO);

        when(carreraService.getMaterias(id)).thenReturn(materiaResponseDTOS);

        ResponseEntity<?> response = carreraController.getMateriasByCarrera(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(materiaResponseDTOS, response.getBody());

        verify(carreraService).getMaterias(id);
    }

    @Test
    public void testPutCarreraOk(){
        Long id = 1L;
        when(carreraService.putCarrera(id, carreraPostDTO)).thenReturn(responsePost);

        ResponseEntity<?> response =  carreraController.putCarrera(id, carreraPostDTO);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(responsePost, response.getBody());

        verify(carreraService).putCarrera(id, carreraPostDTO);
    }

    @Test
    public void testDeleteCarrera(){
        Long id = 1L;

        ResponseEntity<?> response =  carreraController.deleteCarrera(id);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Se elimino con exito", response.getBody());

    }

}
