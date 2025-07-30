package com.tempus.unit.factory;

import com.tempus.Factory.impls.ComisionFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.models.Comision;
import com.tempus.models.Materia;
import org.junit.jupiter.api.BeforeEach;
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
    @Mock
    IEntityFinder<Materia> finderMateria;

    @InjectMocks
    ComisionFactory comisionFactory;

    @Mock
    Materia materia;

    @Mock
    ComisionPostDTO comisionPostDTO;

    @Mock
    Comision comision;

    @Mock
    ModelMapper modelMapper;

    Long id;

    @BeforeEach
    public void setUp(){
        id = 1L;
    }

    @Test
    public void testToEntityOk(){
        when(finderMateria.findById(id)).thenReturn(materia);
        when(comisionPostDTO.getIdMateria()).thenReturn(id);
        when(modelMapper.map(comisionPostDTO, Comision.class)).thenReturn(comision);

        comisionFactory.toEntity(comisionPostDTO);

        verify(finderMateria).findById(id);
        verify(comisionPostDTO).getIdMateria();
        verify(modelMapper).map(comisionPostDTO, Comision.class);
    }

    @Test
    public void testToPostDTOOk(){
        when(modelMapper.map(comision, ComisionPostDTO.class)).thenReturn(comisionPostDTO);

        comisionFactory.toPostDTO(comision);

        verify(modelMapper).map(comision, ComisionPostDTO.class);
    }
}
