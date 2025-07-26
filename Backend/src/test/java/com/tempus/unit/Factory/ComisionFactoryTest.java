package com.tempus.unit.Factory;

import com.Tempus.DTO.ComisionCreatedDTO;
import com.Tempus.DTO.ComisionDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.Factory.impls.ComisionFactory;
import com.Tempus.Models.Comision;
import com.Tempus.Models.Materia;
import com.Tempus.Services.IMateriaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComisionFactoryTest {

    @InjectMocks
    ComisionFactory comisionFactory;

    @Mock
    IMateriaService materiaService;

    @Mock
    Comision comision;

    @Mock
    ModelMapper modelMapper;

    @Mock
    MateriaDTO materiaDTO;

    @Mock
    ComisionDTO comisionDTO;

    @Mock
    ComisionCreatedDTO comisionCreatedDTO;

    @Mock
    Materia materia;

    @Test
    public void testToDTOOk(){
        when(comision.getMateria()).thenReturn(materia);
        when(materia.getId()).thenReturn(1L);
        when(materiaService.findByIdMateriaDTO(1L)).thenReturn(materiaDTO);
        when(modelMapper.map(comision, ComisionDTO.class)).thenReturn(comisionDTO);

        comisionFactory.toDTO(comision);

        verify(comision).getMateria();
        verify(materia).getId();
        verify(modelMapper).map(comision, ComisionDTO.class);
        verify(materiaService).findByIdMateriaDTO(1L);
        verify(comisionDTO).setMateriaDTO(materiaDTO);
    }

    @Test
    public void toCreatedDTOOk(){
        when(modelMapper.map(comision, ComisionCreatedDTO.class)).thenReturn(comisionCreatedDTO);

        comisionFactory.toCreatedDTO(comision);

        verify(modelMapper).map(comision, ComisionCreatedDTO.class);
    }

    @Test
    public void toEntityOk(){
        when(comisionCreatedDTO.getIdMateria()).thenReturn(1L);
        when(modelMapper.map(comisionCreatedDTO, Comision.class)).thenReturn(comision);
        when(materiaService.findByIdMateria(1L)).thenReturn(materia);

        comisionFactory.toEntity(comisionCreatedDTO);

        verify(comisionCreatedDTO).getIdMateria();
        verify(modelMapper).map(comisionCreatedDTO, Comision.class);
        verify(materiaService).findByIdMateria(1L);
        verify(comision).setMateria(materia);
    }
}
