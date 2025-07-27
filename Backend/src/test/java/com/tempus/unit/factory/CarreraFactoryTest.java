package com.tempus.unit.factory;

import com.tempus.Factory.impls.CarreraFactory;
import com.tempus.dto.carrera.CarreraPostDTO;
import com.tempus.dto.carrera.CarreraResponseDTO;
import com.tempus.models.Carrera;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarreraFactoryTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private CarreraResponseDTO carreraResponseDTO;

    @Mock
    private CarreraPostDTO carreraPostDTO;

    @Mock
    private Carrera carrera;

    @InjectMocks
    private CarreraFactory carreraFactory;

    @Test
    public void testToEntityOK(){
        when(modelMapper.map(carreraPostDTO, Carrera.class)).thenReturn(carrera);

        carreraFactory.toEntity(carreraPostDTO);

        verify(modelMapper).map(carreraPostDTO, Carrera.class);
    }

    @Test
    public void testToResponseDTOOK(){
        when(modelMapper.map(carrera, CarreraResponseDTO.class)).thenReturn(carreraResponseDTO);

        carreraFactory.toResponseDTO(carrera);

        verify(modelMapper).map(carrera, CarreraResponseDTO.class);
    }

}
