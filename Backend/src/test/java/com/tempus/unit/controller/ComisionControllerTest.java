package com.tempus.unit.controller;

import com.tempus.controller.ComisionController;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.dto.comision.ComisionResponseDTO;
import com.tempus.enums.Turno;
import com.tempus.service.IComisionService;
import org.apache.coyote.Response;
import org.junit.jupiter.api.BeforeEach;
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
public class ComisionControllerTest {

    @Mock
    ComisionPostDTO comisionPostDTO;

    @Mock
    ComisionPostDTO responsePostDTO;

    @Mock
    IComisionService comisionService;

    @InjectMocks
    ComisionController comisionController;

    @Mock
    ComisionResponseDTO comisionResponseDTO;

    Long id;

    @BeforeEach
    public void setUp(){
        id = 1L;
    }

    @Test
    public void testCreatedComisionOk(){
        when(comisionService.createdComision(comisionPostDTO)).thenReturn(responsePostDTO);


        ResponseEntity<?> response = comisionController.createdComision(comisionPostDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responsePostDTO, response.getBody());

        verify(comisionService).createdComision(comisionPostDTO);

    }

    @Test
    public void testGetComisionesOk(){
        List<ComisionResponseDTO> comisionResponseDTOS = List.of(comisionResponseDTO);

        when(comisionService.getComisiones()).thenReturn(comisionResponseDTOS);

        ResponseEntity<?> response = comisionController.getComisiones();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comisionResponseDTOS, response.getBody());

        verify(comisionService).getComisiones();
    }

    @Test
    public void testGetComisionOk(){
        when(comisionService.getComision(id)).thenReturn(comisionResponseDTO);

        ResponseEntity<?> response = comisionController.getComision(id);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(comisionResponseDTO, response.getBody());

        verify(comisionService).getComision(id);
    }

    @Test
    public void testPutComisionOk(){
        when(comisionService.putComision(comisionPostDTO, id)).thenReturn(comisionPostDTO);

        ResponseEntity<?> response = comisionController.putComision(comisionPostDTO, id);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(comisionPostDTO, response.getBody());

        verify(comisionService).putComision(comisionPostDTO, id);
    }


    @Test
    public void testDeleteComisionOk(){
        ResponseEntity<?> response = comisionController.deleteComision(id);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Se elimino con exito", response.getBody());

        verify(comisionService).deleteComision(id);
    }

    @Test
    public void testGetComisionPorTurnoOk(){
        List<ComisionResponseDTO> comisionResponseDTOS = List.of(comisionResponseDTO);

        when(comisionService.getComisionesPorHorario(Turno.MAÑANA)).thenReturn(comisionResponseDTOS);

        ResponseEntity<?> response = comisionController.getComisionesPorHorario(Turno.MAÑANA);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comisionResponseDTOS, response.getBody());

        verify(comisionService).getComisionesPorHorario(Turno.MAÑANA);
    }
}
