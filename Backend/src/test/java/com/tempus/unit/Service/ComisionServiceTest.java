package com.tempus.unit.Service;

import com.Tempus.DTO.ComisionCreatedDTO;
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
    private IMateriaService materiaService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ComisionService comisionService;

    @Mock
    private Materia materia;

    @Mock
    private ComisionCreatedDTO comisionCreatedDTO;

    @Mock
    private Comision comision;

    @Mock
    private ComisionCreatedDTO responseDTO;

    @Test
    public void testCreatedComisionOK(){
        when(comisionRepository.save(comision)).thenReturn(comision);
        when(modelMapper.map(comision, ComisionCreatedDTO.class)).thenReturn(responseDTO);
        when(materiaService.findByIdMateria(1L)).thenReturn(materia);
        when(comisionCreatedDTO.getIdMateria()).thenReturn(1L);
        when(modelMapper.map(comisionCreatedDTO, Comision.class)).thenReturn(comision);

        comisionService.createdComision(comisionCreatedDTO);

        verify(comisionRepository).save(comision);
        verify(modelMapper).map(comision, ComisionCreatedDTO.class);
        verify(modelMapper).map(comisionCreatedDTO, Comision.class);
        verify(materiaService).findByIdMateria(1L);
        verify(comisionCreatedDTO).getIdMateria();
    }

}
