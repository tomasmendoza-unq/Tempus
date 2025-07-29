package com.tempus.unit.controller;

import com.tempus.controller.MateriaController;
import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.dto.materia.MateriaSimpleDTO;
import com.tempus.service.IMateriaService;
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
public class MateriaControllerTest {

    @Mock
    MateriaPostDTO materiaPostDTO;

    @Mock
    MateriaResponseDTO materiaResponseDTO;

    @Mock
    MateriaSimpleDTO materiaSimpleDTO;

    @Mock
    IMateriaService materiaService;

    @InjectMocks
    MateriaController materiaController;

    Long id;

    @BeforeEach
    public void setUp(){
        id = 1L;
    }

    @Test
    public void testGetMateriaOk(){
        when(materiaService.getMateria(id)).thenReturn(materiaResponseDTO);

        ResponseEntity<?> response = materiaController.getMateria(id);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(materiaResponseDTO, response.getBody());

        verify(materiaService).getMateria(id);
    }

    @Test
    public void testGetMateriasOk(){
        List<MateriaSimpleDTO> materias = List.of(materiaSimpleDTO);

        when(materiaService.getMaterias()).thenReturn(materias);

        ResponseEntity<?> response = materiaController.getMaterias();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(materias, response.getBody());

        verify(materiaService).getMaterias();
    }

    @Test
    public void testCreatedMateriaOk(){
        when(materiaService.createdMateria(materiaPostDTO)).thenReturn(materiaSimpleDTO);

        ResponseEntity<?> response = materiaController.createdMateria(materiaPostDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(materiaSimpleDTO, response.getBody());

        verify(materiaService).createdMateria(materiaPostDTO);
    }

    @Test
    public void testPutMateria(){
        when(materiaService.putMateria(materiaPostDTO, id)).thenReturn(materiaSimpleDTO);

        ResponseEntity<?> response = materiaController.putMateria(materiaPostDTO, id);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(materiaSimpleDTO, response.getBody());

        verify(materiaService).putMateria(materiaPostDTO, id);
    }

    @Test
    public void testDeleteMateria(){
        ResponseEntity<?> response = materiaController.deleteMateria(id);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals("Se elimino con exito la materia", response.getBody());
    }

}
