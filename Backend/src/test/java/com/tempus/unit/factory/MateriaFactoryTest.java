package com.tempus.unit.factory;

import com.tempus.Factory.impls.MateriaFactory;
import com.tempus.data.IEntityFinder;
import com.tempus.dto.materia.MateriaPostDTO;
import com.tempus.dto.materia.MateriaResponseDTO;
import com.tempus.models.Carrera;
import com.tempus.models.Materia;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MateriaFactoryTest {

    @Mock
    Materia materia;

    @Mock
    MateriaResponseDTO materiaResponseDTO;

    @Mock
    MateriaPostDTO materiaPostDTO;

    @Mock
    ModelMapper modelMapper;

    @Mock
    IEntityFinder<Carrera> finderCarrera;

    @InjectMocks
    MateriaFactory materiaFactory;

    @Test
    public void testToResponseDTOOk(){
        when(modelMapper.map(materia, MateriaResponseDTO.class)).thenReturn(materiaResponseDTO);

        materiaFactory.toResponseDTO(materia);

        verify(modelMapper).map(materia, MateriaResponseDTO.class);
    }

    @Test
    public void testToEntityOk(){
        when(modelMapper.map(materiaPostDTO, Materia.class)).thenReturn(materia);

        materiaFactory.toEntity(materiaPostDTO);

        verify(modelMapper).map(materiaPostDTO, Materia.class);
    }
}
