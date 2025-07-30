package com.tempus.unit.service;

import com.tempus.Factory.impls.ComisionFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.models.Comision;
import com.tempus.repository.IComisionRepository;
import com.tempus.service.impls.ComisionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComisionServiceTest {

    @Mock
    ComisionFactory comisionFactory;

    @InjectMocks
    ComisionService comisionService;

    @Mock
    IComisionRepository comisionRepository;

    @Mock
    Comision comision;

    @Mock
    Comision saved;

    @Mock
    ComisionPostDTO comisionPostDTO;

    @Mock
    ComisionPostDTO responseSaved;

    @Test
    public void testCreatedComisionOk(){
        when(comisionRepository.save(comision)).thenReturn(saved);
        when(comisionFactory.toEntity(comisionPostDTO)).thenReturn(comision);
        when(comisionFactory.toPostDTO(saved)).thenReturn(responseSaved);

        comisionService.createdComision(comisionPostDTO);

        verify(comisionRepository).save(comision);
        verify(comisionFactory).toEntity(comisionPostDTO);
        verify(comisionFactory).toPostDTO(saved);


    }


}
