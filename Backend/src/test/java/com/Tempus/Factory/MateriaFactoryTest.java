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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MateriaFactoryTest {

    @Mock
    MateriaDTO materiaDTO;

    @Mock
    MateriaSimpleDTO materiaSimpleDTO;

    @Mock
    MateriaCorrelativaDTO materiaCorrelativaDTO;

    @Mock
    IMateriaRepository materiaRepository;

    @Mock
    ModelMapper modelMapper;

    IMateriaFactory materiaFactory;

    @Mock
    Materia materia;

    @Mock
    MateriaSimple materiaSimple;

    @Mock
    MateriaCorrelativa materiaCorrelativa;

    @Mock
    List<MateriaDTO> materiaDTOList;

    @BeforeEach
    public void setup(){
        materiaFactory = new MateriaFactory(modelMapper,materiaRepository);
        materiaDTOList = List.of(materiaSimpleDTO);
    }

    @Test
    public void factoryRecibeUnaDTOParaFactorizarloTest(){
        when(materiaDTO.toEntity(materiaFactory)).thenReturn(materia);

        assertEquals(materia, materiaFactory.factoryMethod(materiaDTO));

        verify(materiaDTO).toEntity(materiaFactory);

    }

    @Test
    public void factoryCreaUnaInstanciaDeMateriaSimpleTest(){
        when(modelMapper.map(materiaSimpleDTO, MateriaSimple.class)).thenReturn(materiaSimple);

        Materia resultado = materiaFactory.createSimple(materiaSimpleDTO);

        assertEquals(materiaSimple, resultado);

        verify(modelMapper).map(materiaSimpleDTO, MateriaSimple.class);

    }

    @Test
    public void factoryCreaUnaInstanciaDeMateriaCorrelativaTest(){
        when(modelMapper.map(materiaCorrelativaDTO, MateriaCorrelativa.class)).thenReturn(materiaCorrelativa);
        when(materiaRepository.findById(1L)).thenReturn(Optional.ofNullable(materiaSimple));
        when(materiaSimpleDTO.getId()).thenReturn(1L);
        when(materiaCorrelativaDTO.getCorrelativas()).thenReturn(materiaDTOList);

        Materia resultado = materiaFactory.createCorrelativa(materiaCorrelativaDTO);

        assertEquals(materiaCorrelativa, resultado);

        verify(materiaRepository).findById(1L);
        verify(modelMapper).map(materiaCorrelativaDTO, MateriaCorrelativa.class);
        verify(materiaCorrelativaDTO).getCorrelativas();
        verify(materiaSimpleDTO).getId();
    }

}
