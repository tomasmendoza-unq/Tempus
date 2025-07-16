package com.Tempus.Controller;

import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Services.IMateriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MateriaControllerTest {

    @InjectMocks
    MateriaController materiaController;

    @Mock
    IMateriaService materiaService;

    @Mock
    MateriaDTO materiaDTO;

    @Test
    public void testGetCorrelativas(){
        when(materiaService.findCorrelativasById(1L)).thenReturn(materiaDTO);

        ResponseEntity<MateriaDTO> response = materiaController.getCorrelativas(1L);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(materiaDTO, response.getBody());
    }
}
