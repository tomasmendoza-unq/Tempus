package com.tempus.unit.Controller;

import com.Tempus.Controller.ComisionController;
import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import com.Tempus.Services.IComisionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComisionControllerTest {

    @Mock
    IComisionService comisionService;

    @InjectMocks
    ComisionController comisionController;

    @Mock
    ComisionCreatedDTO comisionCreatedDTO;

    @Mock
    ComisionCreatedDTO responseDTO;

    @Mock
    ComisionDTO comisionDTO;

    @Mock
    ComisionDTO comisionDTO2;

    @Test
    public void  testCreatedComisionOK(){
        when(comisionService.createdComision(comisionCreatedDTO)).thenReturn(responseDTO);

        ResponseEntity<?> response = comisionController.createdComision(comisionCreatedDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());

    }

    @Test
    public void  testGetComisionesOK(){
        List<ComisionDTO> comisionDTOS = List.of(comisionDTO,comisionDTO2);
        when(comisionService.getComisiones()).thenReturn(comisionDTOS);

        ResponseEntity<?> response = comisionController.getComisiones();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(comisionDTOS, response.getBody());

    }

    @Test
    public void testGetComisionOK(){
        when(comisionService.getComision(1L)).thenReturn(comisionDTO);

        ResponseEntity<?> response = comisionController.getComision(1L);

        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(comisionDTO, response.getBody());
    }

    @Test
    public void testPutComisionOk(){
        when(comisionService.putComision(1L, comisionCreatedDTO)).thenReturn(responseDTO);

        ResponseEntity<?> response = comisionController.putComision(1L, comisionCreatedDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());
    }

}
