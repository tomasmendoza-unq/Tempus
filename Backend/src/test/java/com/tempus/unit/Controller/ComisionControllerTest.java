package com.tempus.unit.Controller;

import com.Tempus.Controller.ComisionController;
import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.Services.IComisionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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


    @Test
    public void  createdComisionOK(){
        when(comisionService.createdComision(comisionCreatedDTO)).thenReturn(responseDTO);

        ResponseEntity<?> response = comisionController.createdComision(comisionCreatedDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDTO, response.getBody());

    }
}
