package com.tempus.unit.Service;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.Factory.impls.ComisionFactory;
import com.Tempus.Models.Comision;
import com.Tempus.Models.Materia;
import com.Tempus.Repository.IComisionRepository;
import com.Tempus.Services.IMateriaService;
import com.Tempus.Services.impls.ComisionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComisionServiceTest {
    @Mock
    private IComisionRepository comisionRepository;

    @Mock
    private ComisionFactory comisionFactory;

    @InjectMocks
    private ComisionService comisionService;

    @Mock
    private ComisionCreatedDTO comisionCreatedDTO;

    @Mock
    private Comision comision;

    @Mock
    private Comision savedComision;

    @Mock
    private ComisionCreatedDTO responseDTO;

    @Test
    public void testCreatedComisionOK(){
        when(comisionFactory.toEntity(comisionCreatedDTO)).thenReturn(comision);
        when(comisionFactory.toCreatedDTO(savedComision)).thenReturn(responseDTO);
        when(comisionRepository.save(comision)).thenReturn(savedComision);

        comisionService.createdComision(comisionCreatedDTO);

        verify(comisionRepository).save(comision);
        verify(comisionFactory).toCreatedDTO(savedComision);
        verify(comisionFactory).toEntity(comisionCreatedDTO);
    }

}
