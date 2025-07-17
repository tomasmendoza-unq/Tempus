package com.tempus.unit.Controller;

import com.Tempus.Controller.MateriaController;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Models.Materia;
import com.Tempus.Services.IMateriaService;
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

    @Mock
    MateriaDTO materiaDTO1;

    @Test
    public void testGetCorrelativas(){
        when(materiaService.findCorrelativasById(1L)).thenReturn(materiaDTO);

        ResponseEntity<MateriaDTO> response = materiaController.getCorrelativas(1L);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(materiaDTO, response.getBody());
    }

    @Test
    public void testSetMateria(){
        when(materiaService.createdMateria(materiaDTO)).thenReturn(materiaDTO1);

        ResponseEntity<?> response = materiaController.setMateria(materiaDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(materiaDTO1, response.getBody());

    }
}
