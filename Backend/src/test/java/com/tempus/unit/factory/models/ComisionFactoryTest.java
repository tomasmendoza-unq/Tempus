package com.tempus.unit.factory.models;

import com.tempus.Factory.models.IMateriaFactory;
import com.tempus.Factory.models.impls.ComisionFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.comision.ComisionPostDTO;
import com.tempus.dto.comision.ComisionResponseDTO;
import com.tempus.dto.materia.MateriaSimpleDTO;
import com.tempus.enums.Turno;
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

    @Mock
    MateriaSimpleDTO materiaSimpleDTO;

    @Mock
    IMateriaFactory materiaFactory;

    Long id;

    @Mock
    ComisionResponseDTO comisionResponseDTO;

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

    @Test
    public void testToResponseDTO(){
        when(modelMapper.map(comision, ComisionResponseDTO.class)).thenReturn(comisionResponseDTO);
        when(materiaFactory.toSimpleDTO(materia)).thenReturn(materiaSimpleDTO);
        when(comision.getMateria()).thenReturn(materia);

        comisionFactory.toResponseDTO(comision);

        verify(modelMapper).map(comision, ComisionResponseDTO.class);
        verify(materiaFactory).toSimpleDTO(materia);
        verify(comision).getMateria();
        verify(comisionResponseDTO).setMateria(materiaSimpleDTO);


    }

    @Test
    public void testUpdateEntityOk(){
        Turno turno = Turno.MAÑANA;
        when(finderMateria.findById(id)).thenReturn(materia);
        when(comisionPostDTO.getTurno()).thenReturn(turno);
        when(comisionPostDTO.getIdMateria()).thenReturn(id);

        comisionFactory.updateEntity(comision, comisionPostDTO);

        verify(finderMateria).findById(id);
        verify(comisionPostDTO).getIdMateria();
        verify(comisionPostDTO).getTurno();
        verify(comision).setMateria(materia);
        verify(comision).setTurno(turno);

    }
}
