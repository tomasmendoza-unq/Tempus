package com.Tempus.Factory;

import com.Tempus.DTO.MateriaCorrelativaDTO;
import com.Tempus.DTO.MateriaDTO;
import com.Tempus.DTO.MateriaSimpleDTO;
import com.Tempus.Factory.impls.MateriaFactory;
import com.Tempus.Models.Materia;
import com.Tempus.Models.MateriaCorrelativa;
import com.Tempus.Models.MateriaSimple;
import com.Tempus.Models.MateriaSimpleTest;
import com.Tempus.Repository.IMateriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MateriaFactoryTest {

    MateriaDTO materiaDTO;
    MateriaSimpleDTO materiaSimpleDTO;
    MateriaCorrelativaDTO materiaCorrelativaDTO;
    IMateriaRepository materiaRepository;
    ModelMapper modelMapper;
    IMateriaFactory materiaFactory;
    Materia materia;
    MateriaSimple materiaSimple;
    MateriaCorrelativa materiaCorrelativa;
    List<MateriaDTO> materiaDTOList;

    @BeforeEach
    public void setup(){
        materiaDTO = mock(MateriaDTO.class);
        materiaCorrelativaDTO = mock(MateriaCorrelativaDTO.class);
        materiaSimpleDTO = mock(MateriaSimpleDTO.class);
        materiaRepository = mock(IMateriaRepository.class);
        modelMapper = mock(ModelMapper.class);
        materia = mock(Materia.class);
        materiaSimple = mock(MateriaSimple.class);
        materiaCorrelativa = mock(MateriaCorrelativa.class);
        materiaFactory = new MateriaFactory(modelMapper, materiaRepository);


        when(materiaSimple.getNombre()).thenReturn("Matematica");
        when(materiaCorrelativa.getNombre()).thenReturn("Matematica 2");
        when(materiaSimpleDTO.getNombre()).thenReturn("Matematica");
        when(materiaCorrelativaDTO.getNombre()).thenReturn("Matematica 2");
        when(materiaSimpleDTO.getId()).thenReturn(1L);
        when(materiaCorrelativaDTO.getId()).thenReturn(2L);

        materiaDTOList = List.of(materiaSimpleDTO);
        when(materiaCorrelativaDTO.getCorrelativas()).thenReturn(materiaDTOList);
    }

    @Test
    public void factoryRecibeUnaDTOParaFactorizarlo(){
        when(materiaDTO.toEntity(materiaFactory)).thenReturn(materia);

        assertEquals(materia, materiaFactory.factoryMethod(materiaDTO));

        verify(materiaDTO).toEntity(materiaFactory);

    }

    @Test
    public void factoryCreaUnaInstanciaDeMateriaSimple(){
        when(modelMapper.map(materiaSimpleDTO, MateriaSimple.class)).thenReturn(materiaSimple);

        Materia resultado = materiaFactory.createSimple(materiaSimpleDTO);

        verify(modelMapper).map(materiaSimpleDTO, MateriaSimple.class);

        assertEquals(materiaSimpleDTO.getNombre(), resultado.getNombre());

    }

    @Test
    public void factoryCreaUnaInstanciaDeMateriaCorrelativa(){
        when(modelMapper.map(materiaCorrelativaDTO, MateriaCorrelativa.class)).thenReturn(materiaCorrelativa);
        when(materiaRepository.findById(1L)).thenReturn(Optional.ofNullable(materiaSimple));

        Materia resultado = materiaFactory.createCorrelativa(materiaCorrelativaDTO);

        assertEquals(materiaCorrelativa.getId(), resultado.getId());
        assertEquals(materiaCorrelativa.getNombre(), resultado.getNombre());


        verify(materiaRepository).findById(1L);
        verify(modelMapper).map(materiaCorrelativaDTO, MateriaCorrelativa.class);
        verify(materiaCorrelativaDTO).getCorrelativas();
        verify(materiaSimpleDTO).getId();
    }

}
