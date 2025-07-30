package com.tempus.unit.controller;

import com.tempus.controller.ComisionController;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.service.IComisionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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


    @Test
    public void testCreatedComisionOk(){
        when(comisionService.createdComision(comisionPostDTO)).thenReturn(responsePostDTO);


        ResponseEntity<?> response = comisionController.createdComision(comisionPostDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responsePostDTO, response.getBody());

        verify(comisionService).createdComision(comisionPostDTO);

    }

}
